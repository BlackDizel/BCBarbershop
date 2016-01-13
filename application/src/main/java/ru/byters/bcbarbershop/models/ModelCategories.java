package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Category;

public class ModelCategories {
    public static String tablename;
    private ArrayList<Category> data;
    private ArrayList<Category> dataTopLevel;

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

    public int getSizeTopLevel() {
        if (data != null) {
            if (dataTopLevel == null || dataTopLevel.size() == 0) {
                dataTopLevel = new ArrayList<>();
                for (Category category : data)
                    if (category.getParentCategory() == 0)
                        dataTopLevel.add(category);
            }
            return dataTopLevel.size();
        }
        return 0;
    }

    public Category getItem(int pos) {
        if ((pos < data.size()) && (pos >= 0))
            return data.get(pos);
        return null;
    }

    @Nullable
    public Category getItemTopLevel(int pos) {
        if (dataTopLevel != null && (pos < dataTopLevel.size()) && (pos >= 0))
            return dataTopLevel.get(pos);
        return null;
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Category> result) {
        data = result;
        dataTopLevel = null;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.CATEGORIES);
    }

}
