package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Barbershop;
import ru.yandex.yandexmapkit.utils.GeoPoint;

public class ModelBarbershop {
    public static String tablename;
    private ArrayList<Barbershop> data;

    public ModelBarbershop(Context context, ArrayList<Barbershop> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Barbershop>) ControllerStorage.readObjectFromFile(context, ControllerStorage.BARBERSHOP);
    }

    @Nullable
    public Barbershop getData() {
        if (data == null || data.size() == 0) return null;
        //todo return actual barbershop info
        return data.get(0);
    }

    public void setData(ArrayList<Barbershop> data) {
        this.data = data;
    }

    @Nullable
    public GeoPoint getCoords() {
        if (getData() != null)
            return new GeoPoint(getData().PointX, getData().PointY);
        return null;
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Barbershop> result) {
        data = result;
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.BARBERSHOP);
    }

}
