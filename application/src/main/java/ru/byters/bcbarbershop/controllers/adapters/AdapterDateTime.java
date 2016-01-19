package ru.byters.bcbarbershop.controllers.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.ui.activities.ActivityDateTime;

public class AdapterDateTime extends RecyclerView.Adapter<AdapterDateTime.ViewHolderBase> {
    public static final int NO_VALUE = -1;
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private Controller controller;
    private int maestroId;
    private Date selectedDay;

    public AdapterDateTime(Controller controller) {
        this.controller = controller;
    }

    @Override
    public ViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER)
            return new ViewHolderHeader(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_calendar_list_header, parent, false));
        return new ViewHolderItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_calendar_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderBase holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return HEADER;
        return ITEM;
    }

    @Override
    public int getItemCount() {
        ArrayList<Date> list = controller.controllerEnroll.getModel().getDayInfoWithParams(maestroId, selectedDay);
        return 1 + (list == null ? 0 : list.size());
    }

    public void setMaestroId(int maestroId) {
        this.maestroId = maestroId;
        notifyDataSetChanged();
    }

    private class ViewHolderHeader extends ViewHolderBase implements OnDateSelectedListener {
        MaterialCalendarView calendarView;

        public ViewHolderHeader(View itemView) {
            super(itemView);
            calendarView = (MaterialCalendarView) itemView;
            calendarView.setMinimumDate(Calendar.getInstance());
            calendarView.setOnDateChangedListener(this);
        }

        @Override
        public void setData(int position) {
            if (selectedDay == null) selectedDay = Calendar.getInstance().getTime();
            calendarView.setSelectedDate(selectedDay);
        }

        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
            if (selectedDay != date.getDate()) {
                selectedDay = date.getDate();
                notifyDataSetChanged();
            }
        }
    }

    private class ViewHolderItem extends ViewHolderBase implements View.OnClickListener {
        private TextView tvTitle;

        public ViewHolderItem(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView;
            itemView.setOnClickListener(this);
        }

        @Override
        public void setData(int position) {
            Date date = controller.controllerEnroll.getModel().getDayInfoWithParams(maestroId, selectedDay).get(position - 1);
            tvTitle.setText(new SimpleDateFormat("HH:mm").format(date));
        }

        @Override
        public void onClick(View v) {
            if (v.getContext() instanceof ActivityDateTime)
                ((ActivityDateTime) v.getContext()).confirm(selectedDay);
        }
    }

    public abstract class ViewHolderBase extends RecyclerView.ViewHolder {
        public ViewHolderBase(View itemView) {
            super(itemView);
        }

        public abstract void setData(int position);
    }
}
