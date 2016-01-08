package ru.byters.bcbarbershop.dataclasses;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private int ProductID;
    private int BarbershopID;
    private String Title;
    private String Description;
    private String PhotoURI;
    private String Price;
    private int CategoryID;
    private int ParentProduct;

    public int getProductID() {
        return ProductID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getPhotoURI() {
        return PhotoURI;
    }

    public String getPrice() {
        return Price;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public int getParentProduct() {
        return ParentProduct;
    }
}
