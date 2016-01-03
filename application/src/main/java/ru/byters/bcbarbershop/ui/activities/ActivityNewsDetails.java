package ru.byters.bcbarbershop.ui.activities;

import android.os.Bundle;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.ControllerNewsActivity;

public class ActivityNewsDetails extends ActivityBase {

    public static final String INTENT_EXTRA_KEY = "id";
    //todo add share action
    //todo add like action (если новость - фотография другого пользователя, дать возможность лайкнуть)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        new ControllerNewsActivity().setUI(
                (Controller) getApplicationContext()
                , findViewById(R.id.flRoot)
                , getIntent().getIntExtra(INTENT_EXTRA_KEY, 0));
    }
}
