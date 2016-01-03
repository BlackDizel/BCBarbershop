package ru.byters.bcbarbershop.dataclasses;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

public class News implements Comparable<News>, Serializable {
    public int NewsID;
    public String Title;
    public String Description;
    public Date NewsDate;
    public int NewsType;
    public String PhotoURI;

    @Override
    public int compareTo(@NonNull News another) {
        return this.NewsDate.compareTo(another.NewsDate);
    }
}
