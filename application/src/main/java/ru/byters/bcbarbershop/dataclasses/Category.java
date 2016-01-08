package ru.byters.bcbarbershop.dataclasses;

import java.io.Serializable;

public class Category implements Serializable{
    private int CategoryID;
    private String Title;
    private String Description;
    private String PhotoUri;
    private int id;

    public int getCategoryID() {
        return CategoryID;
    }

    public String getTitle() {
        return Title;
    }
}
