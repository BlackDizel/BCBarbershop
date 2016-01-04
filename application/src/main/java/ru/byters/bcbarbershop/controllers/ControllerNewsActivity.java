package ru.byters.bcbarbershop.controllers;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.dataclasses.News;

public class ControllerNewsActivity {

    public void setUI(Controller controller, ViewGroup view, int id) {
        News news = controller.controllerNews.getNews().getItemById(id);
        if (news == null) return;

        View v;
        if (!TextUtils.isEmpty(news.PhotoURI)) {
            v = LayoutInflater.from(controller).inflate(R.layout.view_news_details_photo, view);
            ImageLoader.getInstance().displayImage(news.PhotoURI, (ImageView) v.findViewById(R.id.ivPhoto));
        } else {
            v = LayoutInflater.from(controller).inflate(R.layout.view_news_details_text, view);
            ((TextView) v.findViewById(R.id.tvDescription)).setText(news.Description);
        }

        ((TextView) v.findViewById(R.id.tvTitle)).setText(news.Title);
        ((TextView) v.findViewById(R.id.tvDate)).setText(new SimpleDateFormat("DD MMMM").format(news.NewsDate));
    }
}
