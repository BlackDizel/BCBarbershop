package ru.byters.view;

import android.graphics.drawable.Drawable;

import java.util.Calendar;

public class Model {
    String Title;
    String Description;
    Drawable Image;
    Calendar Date;

    public Model() {
        Title = "no title";
        Description = "no description";
        Image = null;
        Date = Calendar.getInstance();
    }
}
