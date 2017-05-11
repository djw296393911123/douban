package com.djw.douban.ui.message.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.djw.douban.R;
import com.djw.douban.base.BaseFragment;
import com.djw.douban.data.calendar.CalendarBaseData;
import com.djw.douban.ui.message.adapter.CalendarAdapter;
import com.djw.douban.ui.message.contract.CalenderContract;
import com.djw.douban.ui.message.presenter.CalendarPresenter;
import com.djw.douban.util.CalendarUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends BaseFragment<CalendarPresenter> implements CalenderContract.View {


    @BindView(R.id.rv_calendar)
    RecyclerView rvCalendar;
    private CalendarAdapter adapter;
    private int curMonth;
    private int month;
    private int year;
    private int curYear;


    public static CalendarFragment newInstance(int year, int month) {

        Bundle args = new Bundle();
        args.putInt("year", year);
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

    }

    @Override
    protected void doBusiness() {

    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
        mPresenter.attachView(this);
        Bundle bundle = getArguments();
        month = bundle.getInt("month");
        year = Integer.parseInt(CalendarUtil.getYear());
        curMonth = month;
        curYear = bundle.getInt("year");
        mPresenter.getCalendar(year, month, true);
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

    }
}
