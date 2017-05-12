package com.djw.douban.ui.message;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.djw.douban.R;
import com.djw.douban.base.RxToolbarActivity;
import com.djw.douban.data.calendar.CalendarBaseData;
import com.djw.douban.ui.message.adapter.CalendarAdapter;
import com.djw.douban.ui.message.contract.CalenderContract;
import com.djw.douban.ui.message.presenter.CalendarPresenter;
import com.djw.douban.util.CalendarUtil;
import com.djw.douban.util.DividerGridItemDecoration;

import java.util.List;

import butterknife.BindView;

public class CalendarActivity extends RxToolbarActivity<CalendarPresenter> implements CalenderContract.View {

    @BindView(R.id.rv_calendar)
    RecyclerView rvCalendar;
    private CalendarAdapter adapter;
    private int curMonth;
    private int month;
    private int year;
    private int curYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    @Override
    public void initView() {

        GridLayoutManager layoutManager = new GridLayoutManager(this, 7);
        rvCalendar.setLayoutManager(layoutManager);
        rvCalendar.addItemDecoration(new DividerGridItemDecoration(this));
        adapter = new CalendarAdapter() {
            @Override
            public void onLeftClick() {

                if (curMonth == 1) {
                    curMonth = 13;
                    curYear = --curYear;
                    setToolBarTitle(curYear + "年");
                }
                mPresenter.getCalendar(curYear, curMonth = --curMonth, curMonth == month && curYear == year);
            }

            @Override
            public void onRightClick() {

                if (curMonth == 12) {
                    curMonth = 0;
                    curYear = ++curYear;
                    setToolBarTitle(curYear + "年");
                }
                mPresenter.getCalendar(curYear, curMonth = ++curMonth, curMonth == month && curYear == year);
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
    protected void scrollToTop() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
        mPresenter.attachView(this);
        month = CalendarUtil.getMonth();
        year = Integer.parseInt(CalendarUtil.getYear());
        curMonth = month;
        curYear = year;
        setToolBarTitle(year + "年");
        mPresenter.getCalendar(year, month, true);
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCalendar(List<CalendarBaseData> list) {
        adapter.notifyDataChange(list);
    }
}