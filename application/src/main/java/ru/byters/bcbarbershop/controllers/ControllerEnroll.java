package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.dataclasses.Enroll;
import ru.byters.bcbarbershop.models.ModelEnroll;

public class ControllerEnroll {

    //private boolean isUpdating;
    @NonNull
    private ModelEnroll model;

    public ControllerEnroll(@NonNull Context context, @NonNull AzureConnect azure) {
        model = new ModelEnroll(context);
        // isUpdating = false;
        //if (model.getData() == null) //no cached model
        //    updateData(azure);
    }

    @NonNull
    public ModelEnroll getModel() {
        return model;
    }

    public void sendEnroll(@NonNull Context context, @NonNull AzureConnect azure, @Nullable String comment, int product_id, int maestro_id, @NonNull Date date) {
        Enroll enroll = model.getEnroll(context, comment, product_id, maestro_id, date);
        if (enroll == null) return;
        azure.postTable(ModelEnroll.tablename, enroll);
    }

    /*
    public void updateData(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelEnroll.tablename, Enroll.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<Enroll> result) {
        isUpdating = false;
        if (result != null)
            model.setData(context, result);
    }*/
}
