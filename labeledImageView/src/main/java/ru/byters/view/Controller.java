package ru.byters.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * view attributes and data
 **/
public class Controller {
    Model data;
    ModelView view;

    TextView tPrimary, tDate, tDescription, tHuge;
    ImageView imgView;

    public Controller(ViewGroup viewGroup) {
        Context ctx = viewGroup.getContext();
        data = new Model();
        view = new ModelView(ctx);

        tDate = (TextView) viewGroup.findViewById(R.id.tvEnd);
        tDescription = (TextView) viewGroup.findViewById(R.id.tvSecondary);
        tHuge = (TextView) viewGroup.findViewById(R.id.tvHugeTitle);

        imgView = (ImageView) viewGroup.findViewById(R.id.imgView);

    }

    void updateData() {
        if (data.Image != null) {
            view.tvDrawableTitleVisibility = View.GONE;
            view.imgViewVisibility = View.VISIBLE;
            imgView.setImageDrawable(data.Image);
            tPrimary.setText(data.Title);
        } else {
            view.tvDrawableTitleVisibility = View.VISIBLE;
            view.imgViewVisibility = View.GONE;
            tHuge.setText(data.Title);
            tPrimary.setText(data.Description);
        }
        tHuge.setVisibility(view.tvDrawableTitleVisibility);
        imgView.setVisibility(view.imgViewVisibility);

        String dt = "";
        if (data.Date != null)
            dt = new SimpleDateFormat("DD MMM").format(data.Date.getTime());
        tDate.setText(dt);

    }

    public void setData(Drawable image, String title, String description, Calendar date) {
        data.Image = image;
        data.Title = title;
        data.Description = description;
        data.Date = date;
        updateData();
    }

}
