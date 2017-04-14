package com.djw.douban.ui.home.book.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.book.BookRoot;
import com.djw.douban.data.book.BookTypeData;
import com.djw.douban.ui.home.book.adapter.BookRecyclerAdapter;
import com.djw.douban.ui.home.book.adapter.BookTypeAdapter;
import com.djw.douban.ui.home.book.contract.BookContract;
import com.djw.douban.ui.home.book.presenter.ComprehensivePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends BaseFragment<ComprehensivePresenter> implements BookContract.View, XRecyclerView.LoadingListener {

    private BookTypeAdapter adapter;

    private String[] types = {"综合", "文学", "流行", "文化", "生活", "天文", "地理", "小说", "短篇", "中篇", "长篇", "郭敬明", "韩寒", "莫言", "明晓溪", "饶雪漫"};
    private XRecyclerView xRecyclerView;
    private int position;
    private BookRecyclerAdapter bookRecyclerAdapter;


    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_book_type);
        xRecyclerView = ((XRecyclerView) view.findViewById(R.id.xrv_book));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        xRecyclerView.setLayoutManager(layoutManager);
        xRecyclerView.setLoadingListener(this);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                else
                    return 1;
            }
        });
        bookRecyclerAdapter = new BookRecyclerAdapter(getActivity());
        xRecyclerView.setAdapter(bookRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BookTypeAdapter() {
            @Override
            public void OnItemClick(int position) {
                BookFragment.this.position = position;
                mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[position], false, true);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {
        List<BookTypeData> list = new ArrayList<>();
        list.add(new BookTypeData(types[0], true));
        for (int i = 1; i < types.length; i++) {
            list.add(new BookTypeData(types[i], false));
        }
        adapter.notifyDataChange(list);
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[0], false, false);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_book;
    }

    @Override
    public void onRefresh() {
        mPresenter.getBookList(ParamsData.START, ParamsData.COUNT, types[position], false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getBookList(bookRecyclerAdapter.getItemCount() + 1, ParamsData.COUNT, types[position], true, false);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        ((MainActivity) getActivity()).showProgress();
    }

    @Override
    public void dismissProgress() {
        ((MainActivity) getActivity()).dismissProgress();
        xRecyclerView.loadMoreComplete();
        xRecyclerView.refreshComplete();
    }

    @Override
    public void showBookList(BookRoot bookRoot, boolean isLoadMore) {
        bookRecyclerAdapter.notifyDataChange(bookRoot.getBooks(), isLoadMore);
    }
}
