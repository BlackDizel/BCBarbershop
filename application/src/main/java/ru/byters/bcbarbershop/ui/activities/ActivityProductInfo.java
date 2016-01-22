package ru.byters.bcbarbershop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ActivityProductInfo extends ActivityBase implements View.OnClickListener {

    public static final String INTENT_EXTRA_PRODUCT_ID = "product_id";
    private int product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        product_id = getIntent().getIntExtra(INTENT_EXTRA_PRODUCT_ID, -1);
        Product product = ((Controller) getApplicationContext()).controllerProducts.getModel().getProductWithId(product_id);

        if (product != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(product.getTitle());
            ((TextView) findViewById(R.id.tvDescription)).setText(product.getDescription());

            ImageView ivProduct = (ImageView) findViewById(R.id.ivProduct);
            if (!TextUtils.isEmpty(product.getPhotoURI()))
                ImageLoader.getInstance().displayImage(product.getPhotoURI(), ivProduct);
            else
                //todo set placeholder
                ImageLoader.getInstance().displayImage("http://lorempixel.com/g/400/400/", ivProduct);
        }

        findViewById(R.id.fab).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ActivityEnroll.class);
        intent.putExtra(ActivityEnroll.INTENT_EXTRA_PRODUCT_ID, product_id);
        startActivity(intent);
    }
}
