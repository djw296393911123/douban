package com.djw.douban.ui.cloud.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.djw.douban.MainActivity;
import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.ParamsData;
import com.djw.douban.data.cloud.CityData;
import com.djw.douban.data.cloud.CloudItemData;
import com.djw.douban.ui.cloud.adapter.CloudAdapter;
import com.djw.douban.ui.cloud.contract.CloudContract;
import com.djw.douban.ui.cloud.presenter.CloudPresenter;
import com.djw.douban.util.CityPopWindows;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CloudFragment extends BaseFragment<CloudPresenter> implements CloudContract.View, XRecyclerView.LoadingListener, View.OnClickListener {

    private boolean isFirst = true;
    private XRecyclerView recyclerView;
    private CloudAdapter adapter;
    private Toolbar toolbar;
    private CityPopWindows cityPopWindows;
    private TextView title;
    private String id = ParamsData.CITY;
    private TextView type_all;
    private TextView type_day;
    private CityPopWindows typePop;
    private CityPopWindows dayPop;
    private String id_day = ParamsData.DAY;
    private String id_type = ParamsData.TYPE;
    private View type_view;


    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        toolbar = ((Toolbar) view.findViewById(R.id.tl_base));
        view.findViewById(R.id.ll_cloud).setOnClickListener(this);
        type_view = view.findViewById(R.id.ll_type);
        type_view.setOnClickListener(this);
        view.findViewById(R.id.ll_type_day).setOnClickListener(this);
        type_all = ((TextView) view.findViewById(R.id.tv_type));
        type_day = ((TextView) view.findViewById(R.id.tv_type_day));
        recyclerView = ((XRecyclerView) view.findViewById(R.id.xrv_cloud));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLoadingListener(this);
        recyclerView.setLoadingMoreProgressStyle(25);
        adapter = new CloudAdapter(getActivity()) {
            @Override
            public void onImageClick(String url) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(ImageFragment.newInstance(url), "img");
                transaction.commitAllowingStateLoss();
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void doBusiness() {
        toolbar.setTitle("");
        title = ((TextView) toolbar.findViewById(R.id.tv_toolbar_title));

    }

    public void scrollToTop() {
        recyclerView.scrollToPosition(0);
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        if (isFirst) {
            mPresenter.getType();
            mPresenter.getTypeData();
            mPresenter.getCity();
        }
        isFirst = false;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_cloud;
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
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
    }

    @Override
    public void showCity(List<CityData> list) {
        cityPopWindows = new CityPopWindows(getActivity(), list) {

            @Override
            public void onItemClicks(String id, String titles) {
                cityPopWindows.dismiss();
                CloudFragment.this.id = id;
                title.setText(titles);
                mPresenter.getActivitys(id, id_type, id_day, ParamsData.START, ParamsData.COUNT, false, true);
            }
        };
        title.setText(list.get(0).getTitle());
        mPresenter.getActivitys(list.get(0).getId(), id_type, id_day, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void showActivitys(List<CloudItemData.EventsBean> list, boolean isLoadMore) {

        adapter.notifyDataChange(list, isLoadMore);

    }

    @Override
    public void showType(List<CityData> list) {
        typePop = new CityPopWindows(getActivity(), list) {

            @Override
            public void onItemClicks(String id, String title) {
                typePop.dismiss();
                type_all.setText(title);
                id_type = id;
                mPresenter.getActivitys(CloudFragment.this.id, id, id_day, ParamsData.START, ParamsData.COUNT, false, true);
            }
        };
    }

    @Override
    public void showTypeDay(List<CityData> list) {
        dayPop = new CityPopWindows(getActivity(), list) {

            @Override
            public void onItemClicks(String id, String title) {
                dayPop.dismiss();
                type_day.setText(title);
                id_day = id;
                mPresenter.getActivitys(CloudFragment.this.id, id_type, id, ParamsData.START, ParamsData.COUNT, false, true);
            }
        };
    }

    @Override
    public void onRefresh() {
        mPresenter.getActivitys(id, id_type, id_day, ParamsData.START, ParamsData.COUNT, false, false);
    }

    @Override
    public void onLoadMore() {
        mPresenter.getActivitys(id, id_type, id_day, adapter.getItemCount() + 1, ParamsData.COUNT, true, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_type:
                typePop.showAsDropDown(type_view, 5, 5);
                break;
            case R.id.ll_type_day:
                dayPop.showAsDropDown(type_view, 5, 5);
                break;
            case R.id.ll_cloud:
                cityPopWindows.showAsDropDown(toolbar, 5, 5);
                break;
        }
    }
}
