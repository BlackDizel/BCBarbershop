package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import ru.byters.azure.AzureConnect;
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

    public void sendEnroll(String comment) {
        //todo implement
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
