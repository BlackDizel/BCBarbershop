package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.dataclasses.Barbershop;
import ru.byters.bcbarbershop.models.ModelBarbershop;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;

public class ControllerBarbershop {

    @NonNull
    ModelBarbershop model;

    private View v;
    private boolean isUpdating;

    public ControllerBarbershop(@NonNull Context context, @NonNull AzureConnect azure) {
        model = new ModelBarbershop(context, null);
        isUpdating = false;
        if (model.getData() == null) //no cached data
            updateData(azure);
    }

    public void setUI(View v) {
        this.v = v;
        if (model.getData() != null) {
            ((TextView) v.findViewById(R.id.tvTitle)).setText(model.getData().Title);
            ((TextView) v.findViewById(R.id.tvDescription)).setText(model.getData().Description);
            ((TextView) v.findViewById(R.id.tvPhone)).setText(model.getData().Phone);
            ((TextView) v.findViewById(R.id.tvAddress)).setText(model.getData().Address);
        }
    }

    public void updateUI() {
        if (v != null)
            setUI(v);
    }

    @Nullable
    public OverlayItem getMapPin(Context context) {
        if (model.getCoords() == null || model.getData() == null) return null;
        OverlayItem oItem = new OverlayItem(model.getCoords(), context.getResources().getDrawable(R.drawable.ic_launcher));

        BalloonItem bItem = new BalloonItem(context.getApplicationContext(), oItem.getGeoPoint());
        bItem.setText(model.getData().Title);
        oItem.setBalloonItem(bItem);

        return oItem;
    }

    public void updateData(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelBarbershop.tablename, Barbershop.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<Barbershop> result) {
        isUpdating = false;
        if (result != null)
            model.setData(context, result);
    }

}
