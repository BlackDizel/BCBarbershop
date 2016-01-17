package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.dataclasses.Maestro;
import ru.byters.bcbarbershop.models.ModelMaestro;

public class ControllerMaestro {

    private boolean isUpdating;
    @NonNull
    private ModelMaestro model;

    public ControllerMaestro(@NonNull Context context, @NonNull AzureConnect azure) {
        model = new ModelMaestro(context, null);
        isUpdating = false;
        if (model.getData() == null) //no cached model
            updateModel(azure);
    }

    @NonNull
    public ModelMaestro getData() {
        return model;
    }

    public void updateModel(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelMaestro.tablename, Maestro.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<Maestro> result) {
        isUpdating = false;
        if (result != null)
            model.setData(context, result);
    }
}
