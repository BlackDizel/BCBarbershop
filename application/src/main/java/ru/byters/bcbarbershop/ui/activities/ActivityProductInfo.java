package ru.byters.bcbarbershop.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ActivityProductInfo extends ActivityBase {

    public static final String INTENT_EXTRA_PRODUCT_ID = "product_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");

        Product product = ((Controller) getApplicationContext()).controllerProducts.getProducts().getProductWithId(getIntent().getIntExtra(INTENT_EXTRA_PRODUCT_ID, -1));

        if (product != null) {
            ((TextView) findViewById(R.id.tvTitle)).setText(product.getTitle());
            ((TextView) findViewById(R.id.tvDescription)).setText(product.getDescription());

            ImageView ivProduct = (ImageView) findViewById(R.id.ivProduct);
            if (!TextUtils.isEmpty(product.getPhotoURI()))
                ImageLoader.getInstance().displayImage(product.getPhotoURI(), ivProduct);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
