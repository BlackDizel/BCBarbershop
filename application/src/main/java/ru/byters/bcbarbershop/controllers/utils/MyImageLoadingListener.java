package ru.byters.bcbarbershop.controllers.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.Calendar;

import ru.byters.bcbarbershop.dataclasses.News;
import ru.byters.view.LabeledImageView;

public class MyImageLoadingListener implements ImageLoadingListener {

    LabeledImageView v;
    News data;

    public MyImageLoadingListener(LabeledImageView v, News data) {
        this.v = v;
        this.data = data;
    }

    @Override
    public void onLoadingCancelled(String arg0, View arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLoadingComplete(String uri, View arg1, Bitmap bmp) {
        Drawable d = new BitmapDrawable(bmp);
        Calendar c = Calendar.getInstance();
        c.setTime(data.NewsDate);

        v.setData(d, data.Title, data.Description, c);


    }

    @Override
    public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onLoadingStarted(String arg0, View arg1) {
        // TODO Auto-generated method stub

    }


}
