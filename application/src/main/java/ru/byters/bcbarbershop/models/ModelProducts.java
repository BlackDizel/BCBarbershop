package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ModelProducts {
    public static String tablename;
    private ArrayList<Product> data;
    private ArrayList<Product> savedData;
    private int savedID;

    public ModelProducts(Context context, ArrayList<Product> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Product>) ControllerStorage.readObjectFromFile(context, ControllerStorage.PRODUCTS);
    }

    public ArrayList<Product> getData() {
        return data;
    }

    public ArrayList<Product> getDataWithID(int id) {
        if (savedID == id && savedData != null)
            return savedData;

        if (data == null) return null;

        savedID = id;
        savedData = new ArrayList<>();
        for (Product p : data)
            if (p.getCategoryID() == savedID)
                savedData.add(p);

        return savedData;
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Product> result) {
        data = result;
        savedID = -1;
        savedData = null;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.PRODUCTS);
    }
}

