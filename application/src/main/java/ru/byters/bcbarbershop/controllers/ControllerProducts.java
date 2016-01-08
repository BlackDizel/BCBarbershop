package ru.byters.bcbarbershop.controllers;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import ru.byters.azure.AzureConnect;
import ru.byters.bcbarbershop.dataclasses.Product;
import ru.byters.bcbarbershop.models.ModelProducts;

public class ControllerProducts {

    private boolean isUpdating;
    @NonNull
    private ModelProducts products;

    public ControllerProducts(@NonNull Context context, @NonNull AzureConnect azure) {
        products = new ModelProducts(context, null);
        isUpdating = false;
        if (products.getData() == null) //no cached data
            updateProducts(azure);
    }

    @NonNull
    public ModelProducts getProducts() {
        return products;
    }

    public void updateProducts(@NonNull AzureConnect azure) {
        if (!isUpdating) {
            isUpdating = true;
            azure.getTableTop(ModelProducts.tablename, Product.class, 500);
        }
    }

    public void setData(@NonNull Context context, ArrayList<Product> result) {
        isUpdating = false;
        if (result != null)
            products.setData(context, result);
    }
}
