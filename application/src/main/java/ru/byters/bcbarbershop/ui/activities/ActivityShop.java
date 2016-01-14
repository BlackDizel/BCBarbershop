package ru.byters.bcbarbershop.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.adapters.AdapterProducts;

public class ActivityShop extends ActivityBase {

    public static final String INTENT_EXTRA_CATEGORY_ID = "category_id";
    public static final String INTENT_EXTRA_PRODUCT_ID = "product_id";
    private AdapterProducts adapter;
    private int categoryID;
    private int productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = ((Controller) getApplicationContext()).adapterProducts;
        categoryID = getIntent().getIntExtra(INTENT_EXTRA_CATEGORY_ID, AdapterProducts.NO_VALUE);
        productID = getIntent().getIntExtra(INTENT_EXTRA_PRODUCT_ID, AdapterProducts.NO_VALUE);

        RecyclerView rvMenu = (RecyclerView) findViewById(R.id.rvShop);
        rvMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvMenu.setLayoutManager(mLayoutManager);
        rvMenu.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setCategoryID(categoryID);
        adapter.setProductID(productID);
        adapter.setViews((SwipeRefreshLayout) findViewById(R.id.srlProducts));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
