package ru.byters.bcbarbershop.models;

import java.util.ArrayList;
import java.util.Collections;

import ru.byters.bcbarbershop.dataclasses.News;

public class ModelNews {
    public static final int MODE_ALL = -1;
    public static final int MODE_PROMO = 0;
    public static final int MODE_INFO = 1;
    public static final int MODE_COMMUNITY = 2;
    public static String tablename;
    public ArrayList<News> Data;
    public int mode;
    private ArrayList<News> data;

    public ModelNews(ArrayList<News> l) {
        data = l;
        Data = data;
        mode = MODE_ALL;
    }

    public void setFilteredList() {
        if (mode == MODE_ALL)
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
        for (News m : Data)
            if (m.NewsID == id)
                return m;
        return null;

    }

    public void Sort() {
        Collections.sort(Data);
    }

    public void Reverse() {
        Collections.reverse(Data);
    }

    public void resetData() {
        Data = data;
    }


}
