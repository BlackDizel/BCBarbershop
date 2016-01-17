package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.ProductMaestro;

public class ModelProductsMaestro {
    public static String tablename;
    private ArrayList<ProductMaestro> data;

    public ModelProductsMaestro(Context context, ArrayList<ProductMaestro> l) {
        data = l;
        if (data == null)
            data = (ArrayList<ProductMaestro>) ControllerStorage.readObjectFromFile(context, ControllerStorage.PRODUCTMAESTRO);
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<ProductMaestro> result) {
        data = result;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.PRODUCTMAESTRO);
    }

    @Nullable
    public ArrayList<ProductMaestro> getData() {
        return data;
    }
}
