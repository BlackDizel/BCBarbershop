package ru.byters.bcbarbershop.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.ControllerStorage;
import ru.byters.bcbarbershop.dataclasses.Maestro;
import ru.byters.bcbarbershop.dataclasses.ProductMaestro;

public class ModelMaestro {
    public static String tablename;
    private ArrayList<Maestro> data;
    private int savedProductId;
    private ArrayList<Maestro> savedDataWithProductId;

    public ModelMaestro(Context context, ArrayList<Maestro> l) {
        data = l;
        if (data == null)
            data = (ArrayList<Maestro>) ControllerStorage.readObjectFromFile(context, ControllerStorage.MAESTRO);
    }

    public void setData(@NonNull Context context, @NonNull ArrayList<Maestro> result) {
        data = result;
        updateProductsData();
        ControllerStorage.writeObjectToFile(context, data, ControllerStorage.MAESTRO);
    }

    public void updateProductsData() {
        savedProductId = -1;
        savedDataWithProductId = null;
    }

    @Nullable
    public ArrayList<Maestro> getData() {
        return data;
    }

    @Nullable
    public ArrayList<Maestro> getDataWithProductId(Controller controller, int productId) {
        if (savedProductId == productId && savedDataWithProductId != null)
            return savedDataWithProductId;

        if (data == null) return null;
        if (controller.controllerProductMaestro.getModel().getData() == null) return null;

        savedProductId = productId;
        savedDataWithProductId = new ArrayList<>();
        for (Maestro maestro : data)
            for (ProductMaestro product : controller.controllerProductMaestro.getModel().getData())
                if (product.ProductID == savedProductId && product.MaestroID == maestro.MaestroID) {
                    savedDataWithProductId.add(maestro);
                    break;
                }

        if (savedDataWithProductId.size() == 0) savedDataWithProductId = null;
        return savedDataWithProductId;
    }
}
