package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.News;

public class ModelNews {
    public static final int MODE_ALL = -1;
    public static final int MODE_PROMO = 0;
    public static final int MODE_INFO = 1;
    public static final int MODE_COMMUNITY = 2;
    public static String tablename;
    public int mode;
    private ArrayList<News> Data;
    private ArrayList<News> data;

    public ModelNews(Context context, ArrayList<News> l) {
        data = l;
        if (data == null)
            data = (ArrayList<News>) ControllerStorage.readObjectFromFile(context, ControllerStorage.NEWS);
        Data = data;
        mode = MODE_ALL;
    }

    public ArrayList<News> getData() {
        return Data;
    }

    public void setFilteredList() {
        if (mode == MODE_ALL || data == null)
            Data = data;
        else {
            Data = new ArrayList<>();
            for (News n : data)
                if (n.NewsType == mode)
                    Data.add(n);
        }
    }

    public int getSize() {
        if (Data != null)
            return Data.size();
        return 0;
    }

    public News getItem(int pos) {
        if ((pos < Data.size()) && (pos >= 0))
            return Data.get(pos);
        return null;
    }

    public News getItemById(int id) {
        if (data == null) return null;

        for (News m : data)
            if (m.NewsID == id)
                return m;
        return null;

    }

    public void setData(@NonNull Context context, @NonNull ArrayList<News> result) {
        data = result;
        Collections.sort(data);
        Collections.reverse(data);
        setFilteredList();
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.NEWS);
    }

    public void resetData() {
        Data = data;
    }
}
