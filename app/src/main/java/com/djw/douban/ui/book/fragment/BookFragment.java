package com.djw.douban.ui.book.fragment;


import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.book.BookTypeData;
import com.djw.douban.data.newbook.BookBaseData;
import com.djw.douban.ui.book.activity.BookFromTagActivity;
import com.djw.douban.ui.book.adapter.BookRecyclerAdapter;
import com.djw.douban.ui.book.adapter.BookTypeAdapter;
import com.djw.douban.ui.book.contract.BookContract;
import com.djw.douban.ui.book.presenter.ComprehensivePresenter;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment<ComprehensivePresenter> implements BookContract.View, OnRefreshListener, OnLoadMoreListener {

    private BookTypeAdapter adapter;

    public int position;
    private BookRecyclerAdapter bookRecyclerAdapter;
    private SwipeToLoadLayout swipeToLoadLayout;
    private RecyclerView xRecyclerView;
    private List<BookTypeData> list = new ArrayList<>();
    private String[] types;
    private BoomMenuButton bmb;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        bmb = ((BoomMenuButton) view.findViewById(R.id.bmb));
        swipeToLoadLayout = ((SwipeToLoadLayout) view.findViewById(R.id.stll_movies));
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        xRecyclerView = ((RecyclerView) view.findViewById(R.id.swipe_target));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        xRecyclerView.setLayoutManager(layoutManager);
        bookRecyclerAdapter = new BookRecyclerAdapter(getActivity()) {
            @Override
            public void reLoadData() {
                mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, list.get(position).getTitle(), false, true);
            }
        };
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(bookRecyclerAdapter);
        scaleAdapter.setFirstOnly(false);
        xRecyclerView.setAdapter(scaleAdapter);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                return 1;
            }
        });
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_book_type);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookTypeAdapter() {
            @Override
            public void OnItemClick(int position) {
                BookFragment.this.position = position;
                mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, list.get(position).getTitle(), false, true);
            }
        };
        recyclerView.setAdapter(adapter);
        //为RecycleView绑定触摸事件
        ItemTouchHelper helper = new ItemTouchHelper(new RecyclerViewTouchCallback(list, adapter));
        helper.attachToRecyclerView(recyclerView);
    }

    private static class RecyclerViewTouchCallback extends ItemTouchHelper.Callback {

        private List<BookTypeData> list;

        private BookTypeAdapter adapter;

        RecyclerViewTouchCallback(List<BookTypeData> list, BookTypeAdapter adapter) {
            this.list = list;
            this.adapter = adapter;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            //首先回调的方法 返回int表示是否监听该方向
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
            int swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//侧滑删除
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            //滑动事件
            Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
            adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            adapter.notifyDataChange(list);
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //侧滑事件
//                list.remove(viewHolder.getAdapterPosition());
//                adapter.notifyDataChange(list);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            //是否可拖拽
            return true;
        }
    }

    @Override
    protected void doBusiness() {

        list.add(new BookTypeData(types[0], true));
        for (int i = 1; i < types.length; i++) {
            list.add(new BookTypeData(types[i], false));
        }
        adapter.notifyDataChange(list);
        Resources resources = getResources();
        final String[] bmb_title = resources.getStringArray(R.array.book_bmb_title);
        String[] bmb_subTitle = resources.getStringArray(R.array.book_bmb_title);
        int[] bmb_icon = {R.mipmap.xiaoshuo, R.mipmap.xuanhuan, R.mipmap.zhanzehng, R.mipmap.wangyou, R.mipmap.mingzhu};
        for (int i = 0; i < bmb.getButtonPlaceEnum().buttonNumber(); i++) {
            bmb.addBuilder(new HamButton.Builder()
                    .normalText(bmb_title[i])
                    .subNormalText(bmb_subTitle[i])
                    .normalImageRes(bmb_icon[i])
                    .imagePadding(new Rect(50, 50, 50, 50))
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Bundle bundle = new Bundle();
                            bundle.putString("tag", bmb_title[index]);
                            startActivity(BookFromTagActivity.class, bundle);
                        }
                    }));
        }
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        types = getResources().getStringArray(R.array.book_type_title);
        mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[0], false, true);
    }

    public void scrollToTop() {
        xRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_book;
    }

    @Override
    public void onRefresh() {
        mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, list.get(position).getTitle(), false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBookList(bookRecyclerAdapter.getItemCount() + 1, ParamsData.COUNT, list.get(position).getTitle(), true, false);
    }

    @Override
    public void showError(String msg) {
        refreshOrLoadMoreStop();
        bookRecyclerAdapter.notifyError(msg);
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
    }

    private void refreshOrLoadMoreStop() {
        if (swipeToLoadLayout.isRefreshing()) {
            swipeToLoadLayout.setRefreshing(false);
        }
        if (swipeToLoadLayout.isLoadingMore()) {
            swipeToLoadLayout.setLoadingMore(false);
        }
    }

    @Override
    public void showBookList(List<BookBaseData> list, boolean isLoadMore) {
        refreshOrLoadMoreStop();
        bookRecyclerAdapter.notifyDataChange(list, isLoadMore);
    }

}
