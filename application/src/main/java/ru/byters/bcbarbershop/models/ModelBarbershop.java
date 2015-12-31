package ru.byters.bcbarbershop.models;

import ru.byters.bcbarbershop.dataclasses.Barbershop;
import ru.yandex.yandexmapkit.utils.GeoPoint;

public class ModelBarbershop {
    public Barbershop data;
    public String tablename;

    public void setData(Barbershop data) {
        this.data = data;
    }

    public GeoPoint getCoords() {
        if (data != null)
            return new GeoPoint(data.PointX, data.PointY);
        return null;
    }


}
