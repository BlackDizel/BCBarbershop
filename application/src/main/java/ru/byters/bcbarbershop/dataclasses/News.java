package ru.byters.bcbarbershop.dataclasses;

import java.util.Date;

public class News implements Comparable<News> {
    public int NewsID;
    public String Title;
    public String Description;
    public Date NewsDate;
    public int NewsType;
    public int BarbershopID;
    public String PhotoURI;
    private int id;

    @Override
    public int compareTo(News another) {
        return this.NewsDate.compareTo(another.NewsDate);
    }
}
