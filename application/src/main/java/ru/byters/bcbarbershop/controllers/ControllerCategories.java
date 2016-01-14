package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.dataclasses.Category;
import ru.byters.bcbarbershop.models.ModelCategories;

public class ControllerCategories {

    private boolean isUpdating;
    @NonNull
    private ModelCategories categorties;

    public ControllerCategories(@NonNull Context context, @NonNull AzureConnect azure) {
        categorties = new ModelCategories(context, null);
        isUpdating = false;
        if (categorties.getData() == null) //no cached data
            updateData(azure);
    }

    @NonNull
    public ModelCategories getCategorties() {
        return categorties;
    }

    public void updateData(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelCategories.tablename, Category.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<Category> result) {
        isUpdating = false;
        if (result != null)
            categorties.setData(context, result);
    }
}
