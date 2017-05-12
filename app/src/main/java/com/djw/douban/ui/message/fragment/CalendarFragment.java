package com.djw.douban.ui.message.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.calendar.CalendarBaseData;
import com.djw.douban.ui.message.adapter.CalendarAdapter;
import com.djw.douban.ui.message.contract.CalenderContract;
import com.djw.douban.ui.message.presenter.CalendarPresenter;
import com.djw.douban.util.CalendarUtil;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends BaseFragment<CalendarPresenter> implements CalenderContract.View {


    @BindView(R.id.rv_calendar)
    RecyclerView rvCalendar;
    private CalendarAdapter adapter;


    public static CalendarFragment newInstance(int month) {

        Bundle args = new Bundle();
        args.putInt("month", month);
        CalendarFragment fragment = new CalendarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initView(View view) {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
        rvCalendar.setLayoutManager(layoutManager);
        adapter = new CalendarAdapter() {
            @Override
            public void onLeftClick() {

            }

            @Override
            public void onRightClick() {

            }
        };
        rvCalendar.setAdapter(adapter);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isSpan(position);
            }
        });
    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        Bundle bundle = getArguments();
        int month = bundle.getInt("month");
        int year = Integer.parseInt(CalendarUtil.getYear());
        mPresenter.getCalendar(year, month, month == CalendarUtil.getMonth());
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_canledar;
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
    public void showCalendar(List<CalendarBaseData> list) {
        adapter.notifyDataChange(list);
    }
}
