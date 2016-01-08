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
}
