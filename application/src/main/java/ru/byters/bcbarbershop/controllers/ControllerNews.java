package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.dataclasses.News;
import ru.byters.bcbarbershop.models.ModelNews;

public class ControllerNews {

    private boolean isUpdating;
    @NonNull
    private ModelNews model;

    public ControllerNews(@NonNull Context context, @NonNull AzureConnect azure) {
        model = new ModelNews(context, null);
        isUpdating = false;
        if (model.getData() == null) //no cached data
            updateData(azure);
    }

    @NonNull
    public ModelNews getModel() {
        return model;
    }

    public void updateData(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelNews.tablename, News.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<News> result) {
        isUpdating = false;
        if (result != null)
            model.setData(context, result);
    }
}
