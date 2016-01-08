package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ModelProducts {
    public static String tablename;
    private ArrayList<Product> data;

    public ModelProducts(Context context, ArrayList<Product> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Product>) ControllerStorage.readObjectFromFile(context, ControllerStorage.PRODUCTS);
    }

    public ArrayList<Product> getData() {
        return data;
    }

    public int getSize() {
        if (data != null)
            return data.size();
        return 0;
    }

    public Product getItem(int pos) {
        if ((pos < data.size()) && (pos >= 0))
            return data.get(pos);
        return null;
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Product> result) {
        data = result;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.PRODUCTS);
    }
}

