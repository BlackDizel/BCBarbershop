package ru.byters.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.Calendar;


public class LabeledImageView extends RelativeLayout {

    Controller c;

    public LabeledImageView(Context context) {
        this(context, null);
    }

    public LabeledImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabeledImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        ((Activity) getContext())
                .getLayoutInflater()
                .inflate(R.layout.layout, this, true);

        c = new Controller(this);

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.LabeledImageView, 0, 0);


            Drawable d = a.getDrawable(R.styleable.LabeledImageView_android_drawable);
            String s = a.getString(R.styleable.LabeledImageView_android_text);
            c.setData(d, s, "", null);
            a.recycle();
        }

        c.updateData();
    }

    public void setData(Drawable image, String Title, String Description, Calendar Date) {
        c.setData(image, Title, Description, Date);
    }

}
