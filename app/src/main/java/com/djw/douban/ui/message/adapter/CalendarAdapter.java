package com.djw.douban.ui.message.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djw.douban.R;
import com.djw.douban.data.calendar.CalendarBaseData;
import com.djw.douban.data.calendar.CalendarDayData;
import com.djw.douban.data.calendar.CalenderMonthData;
import com.djw.douban.data.things.ThingsBaseData;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JasonDong
 * <p>
 * on 2017/5/10.
 */

public abstract class CalendarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CalendarBaseData> list;

    protected CalendarAdapter() {
        this.list = new ArrayList<>();
    }

    public void notifyDataChange(List<CalendarBaseData> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CalendarBaseData.MONTH:
                return new MonthHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_month, parent, false));
            case CalendarBaseData.DAY:
                return new DayHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_day, parent, false));
            case CalendarBaseData.WEEK:
                return new WeekHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_week, parent, false));

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (list.get(position).getType()) {
            case CalendarBaseData.MONTH:
                MonthHolder monthHolder = (MonthHolder) holder;
                monthHolder.month.setText(((CalenderMonthData) list.get(position)).getMonth());
                monthHolder.left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLeftClick();
                    }
                });
                monthHolder.right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onRightClick();
                    }
                });
                break;
            case CalendarBaseData.DAY:
                DayHolder dayHolder = (DayHolder) holder;
                final CalendarDayData dayData = (CalendarDayData) list.get(position);
                TextView day = dayHolder.day;
                TextView festival = dayHolder.festival;
                TextView tvLunar = dayHolder.lunar;
                View layout = dayHolder.view;

                if (dayData.isCur()) {
                    day.setBackgroundResource(R.drawable.bg_calendar_curday);
                }

                if (!dayData.isCurMonth()) {
                    tvLunar.setTextColor(Color.GRAY);
                    day.setTextColor(Color.GRAY);
                    festival.setTextColor(Color.GRAY);
                } else if (!dayData.isCur()) {
                    tvLunar.setTextColor(Color.BLACK);
                    day.setTextColor(Color.BLACK);
                    festival.setTextColor(Color.RED);
                }
                String[] lunar = dayData.getLunar().split(",");
                tvLunar.setText(lunar[0]);
                festival.setText(lunar.length > 1 ? lunar[1] : "");
                day.setText(dayData.getDay());
                day.setSelected(dayData.isSelect());
                layout.setTag(position);
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 2; i < list.size(); i++) {
                            ((CalendarDayData) list.get(i)).setSelect(false);
                        }
                        dayData.setSelect(true);
                        notifyDataSetChanged();
                        onItemClick(dayData.getList());
                    }
                });
                break;
        }
    }

    public int isSpan(int position) {
        return list.get(position).getType() == CalendarBaseData.DAY ? 1 : 7;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private static class MonthHolder extends RecyclerView.ViewHolder {

        private final TextView month;
        private final View left;
        private final View right;

        MonthHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            month = ((TextView) itemView.findViewById(R.id.tv_month));
            left = itemView.findViewById(R.id.iv_left);
            right = itemView.findViewById(R.id.iv_right);
        }
    }

    private static class DayHolder extends RecyclerView.ViewHolder {

        private final TextView day;
        private final TextView lunar;
        private final TextView festival;
        private final View view;

        DayHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            day = ((TextView) itemView.findViewById(R.id.tv_day));
            lunar = ((TextView) itemView.findViewById(R.id.tv_lunar));
            festival = ((TextView) itemView.findViewById(R.id.tv_festival));
            view = itemView.findViewById(R.id.ll_day);
        }
    }

    private static class WeekHolder extends RecyclerView.ViewHolder {

        WeekHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
        }
    }

    public abstract void onLeftClick();

    public abstract void onRightClick();

    public abstract void onItemClick(List<ThingsBaseData> list);

}
