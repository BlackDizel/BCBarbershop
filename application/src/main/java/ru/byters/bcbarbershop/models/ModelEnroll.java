package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Enroll;

public class ModelEnroll {
    public static String tablename;
    private ArrayList<Date> savedList;
    private int savedMaestroId;
    private String savedUserPhone;
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
        savedList = getDebugData(selectedDay);

        if (savedList.size() == 0) savedList = null;
        return savedList;
    }

    @NonNull
    private ArrayList<Date> getDebugData(Date selectedDay) {
        ArrayList<Date> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(selectedDay);

        Calendar today = Calendar.getInstance();
        if (calendar.get(Calendar.YEAR) == today.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH)
                && calendar.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))
            calendar = today;

        int minHour = Math.max(10, calendar.get(Calendar.HOUR_OF_DAY));

        calendar.set(calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)
                , minHour
                , 0);

        for (int i = 0; i < Math.max(20 - minHour, 0); ++i) {
            Date date = calendar.getTime();
            list.add(date);
            calendar.add(Calendar.HOUR, 1);
        }

        return list;
    }

    public void setPhone(Context context, String phone) {
        savedUserPhone = phone;
        ControllerStorage.writeObjectToFile(context, savedUserPhone, ControllerStorage.USERPHONE);
    }

    @Nullable
    public String getPhone(Context context) {
        if (savedUserPhone == null)
            savedUserPhone = (String) ControllerStorage.readObjectFromFile(context, ControllerStorage.USERPHONE);
        return savedUserPhone;
    }

    @Nullable
    public Enroll getEnroll(Context context, String comment, int product_id, int maestro_id, Date date) {
        if (TextUtils.isEmpty(getPhone(context)))
            return null;

        Enroll enroll = new Enroll();
        enroll.MaestroID = maestro_id;
        enroll.EnrollDate = date;
        enroll.Comment = String.format("%s, %s", getPhone(context), TextUtils.isEmpty(comment) ? "" : comment);
        enroll.ProductID = product_id;
        return enroll;
    }

}
