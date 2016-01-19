package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.byters.bcbarbershop.dataclasses.Enroll;

public class ModelEnroll {
    public static String tablename;
    private Enroll enroll;
    private ArrayList<Date> savedList;
    private int savedMaestroId;
    private Date savedDate;

    public ModelEnroll(Context context) {
    }


    public void setData() {
        savedList = null;
        savedMaestroId = -1;
        savedDate = null;
    }

    @Nullable
    public ArrayList<Date> getDayInfoWithParams(int maestroId, Date selectedDay) {
        if (selectedDay == null) return null;

        if (maestroId == savedMaestroId && selectedDay == savedDate && savedList != null)
            return savedList;

        savedDate = selectedDay;
        savedMaestroId = maestroId;
        savedList = new ArrayList<>();

        //todo debug. need to change code with real customer worklog system
        savedList = getDebugData();

        if (savedList.size() == 0) savedList = null;
        return savedList;
    }

    @NonNull
    private ArrayList<Date> getDebugData() {
        ArrayList<Date> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)
                , 10
                , 0);

        for (int i = 0; i < 10; ++i) {
            Date date = calendar.getTime();
            list.add(date);
            calendar.add(Calendar.HOUR, 1);
        }

        return list;
    }
}
