package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Category;

public class ModelCategories {
    public static String tablename;
    private ArrayList<Category> data;

    public ModelCategories(Context context, ArrayList<Category> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Category>) ControllerStorage.readObjectFromFile(context, ControllerStorage.CATEGORIES);
    }

    public ArrayList<Category> getData() {
        return data;
    }

    public int getSize() {
        if (data != null)
            return data.size();
        return 0;
    }

    public Category getItem(int pos) {
        if ((pos < data.size()) && (pos >= 0))
            return data.get(pos);
        return null;
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Category> result) {
        data = result;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.CATEGORIES);
    }

}
