package com.djw.douban.ui.mine.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.mine.LikeOrHideData;
import com.djw.douban.data.mine.MineItemData;
import com.djw.douban.ui.mine.adapter.MineAdapter;
import com.djw.douban.ui.mine.contract.OnlineContract;
import com.djw.douban.ui.mine.presenter.OnlinePresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mcxtzhang.layoutmanager.swipecard.CardConfig;
import com.mcxtzhang.layoutmanager.swipecard.OverLayCardLayoutManager;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends BaseFragment<OnlinePresenter> implements OnlineContract.View, XRecyclerView.LoadingListener{

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tl_base)
    Toolbar tlBase;
    RecyclerView recyclerView;
    private MineAdapter adapter;
    ItemTouchHelper helper ;

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        recyclerView = ((RecyclerView) view.findViewById(R.id.xrv_mine));
        recyclerView.setLayoutManager(new OverLayCardLayoutManager());
        CardConfig.initConfig(getActivity());
        adapter = new MineAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        tlBase.setTitle("");
        tvToolbarTitle.setText(getString(R.string.mine));
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        mPresenter.getUrl(ParamsData.START, ParamsData.COUNT);
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showOnline(MineItemData mineItemData) {
    }

    @Override
    public void showImg(final List<LikeOrHideData> list) {
        Log.i("list", list.toString());
        adapter.notifyDataChange(list);
        //为RecycleView绑定触摸事件
        helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                //首先回调的方法 返回int表示是否监听该方向
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//拖拽
                int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;//侧滑删除
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件
                Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑事件
                list.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataChange(list);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                //是否可拖拽
                return true;
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {

    }
}
