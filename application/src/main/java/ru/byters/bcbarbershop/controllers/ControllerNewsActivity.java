package ru.byters.bcbarbershop.controllers;

import android.text.TextUtils;
import android.view.View;

import ru.byters.bcbarbershop.dataclasses.News;

public class ControllerNewsActivity {

    public void setUI(Controller controller, View view, int id) {
        News news = controller.controllerNews.getNews().getItemById(id);
        if (news == null) return;

        if (!TextUtils.isEmpty(news.PhotoURI)) {
            //todo set photo
        } else {
            //todo set text
        }

    }
}
