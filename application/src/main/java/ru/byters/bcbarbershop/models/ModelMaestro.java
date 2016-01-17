package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Maestro;

public class ModelMaestro {
    public static String tablename;
    private ArrayList<Maestro> data;

    public ModelMaestro(Context context, ArrayList<Maestro> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Maestro>) ControllerStorage.readObjectFromFile(context, ControllerStorage.MAESTRO);
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Maestro> result) {
        data = result;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.MAESTRO);
    }

    @Nullable
    public ArrayList<Maestro> getData() {
        return data;
    }
}
