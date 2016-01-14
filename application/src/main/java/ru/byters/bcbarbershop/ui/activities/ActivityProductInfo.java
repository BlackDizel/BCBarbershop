package ru.byters.bcbarbershop.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ru.byters.bcbarbershop.R;

public class ActivityProductInfo extends ActivityBase {

    public static final String INTENT_EXTRA_PRODUCT_ID = "product_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
