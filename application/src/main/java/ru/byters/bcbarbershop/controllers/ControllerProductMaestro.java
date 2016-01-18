package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.dataclasses.ProductMaestro;
import ru.byters.bcbarbershop.models.ModelProductsMaestro;

public class ControllerProductMaestro {

    private boolean isUpdating;
    @NonNull
    private ModelProductsMaestro model;

    public ControllerProductMaestro(@NonNull Context context, @NonNull AzureConnect azure) {
        model = new ModelProductsMaestro(context, null);
        isUpdating = false;
        if (model.getData() == null) //no cached model
            updateData(azure);
    }

    @NonNull
    public ModelProductsMaestro getModel() {
        return model;
    }

    public void updateData(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelProductsMaestro.tablename, ProductMaestro.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<ProductMaestro> result) {
        isUpdating = false;
        if (result != null)
            model.setData(context, result);
    }
}
