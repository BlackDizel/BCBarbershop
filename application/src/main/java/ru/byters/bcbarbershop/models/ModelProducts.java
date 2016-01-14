package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ModelProducts {
    public static String tablename;
    private ArrayList<Product> data;
    private ArrayList<Product> savedTopLevelData;
    private int savedCategoryID;
    private int savedProductId;
    private ArrayList<Product> dataSubproduct;

    public ModelProducts(Context context, ArrayList<Product> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Product>) ControllerStorage.readObjectFromFile(context, ControllerStorage.PRODUCTS);
    }

    public ArrayList<Product> getData() {
        return data;
    }

    public ArrayList<Product> getTopLevelDataWithCategoryID(int id) {
        if (savedCategoryID == id && savedTopLevelData != null)
            return savedTopLevelData;

        if (data == null) return null;

        savedCategoryID = id;
        savedTopLevelData = new ArrayList<>();
        for (Product p : data)
            if (p.getCategoryID() == savedCategoryID
                    && p.getParentProduct() == 0)
                savedTopLevelData.add(p);

        return savedTopLevelData;
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Product> result) {
        data = result;
        savedCategoryID = -1;
        savedTopLevelData = null;
        savedProductId = -1;
        dataSubproduct = null;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.PRODUCTS);
    }

    public ArrayList<Product> getSubproducts(int id) {
        if (id == savedProductId && dataSubproduct != null)
            return dataSubproduct;

        if (data == null) return null;

        savedProductId = id;

        dataSubproduct = new ArrayList<>();
        for (Product product : data)
            if (product.getParentProduct() == savedProductId)
                dataSubproduct.add(product);

        if (dataSubproduct.size() == 0) dataSubproduct = null;
        return dataSubproduct;
    }
}

