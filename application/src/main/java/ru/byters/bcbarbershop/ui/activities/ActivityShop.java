package ru.byters.bcbarbershop.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import ru.byters.bcbarbershop.R;

public class ActivityShop extends ActivityBase {

    public static final String INTENT_EXTRA_ID = "category_id";
    RecyclerView rvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        rvMenu = (RecyclerView) findViewById(R.id.rvShop);
        rvMenu.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        rvMenu.setLayoutManager(mLayoutManager);

        //  rvMenu.setAdapter(((Controller) getApplicationContext()).adapterProducts);
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
