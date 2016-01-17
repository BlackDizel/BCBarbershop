package ru.byters.bcbarbershop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.adapters.AdapterMaestro;

public class ActivityMaestro extends ActivityBase {

    public static final String EXTRA_INTENT_MAESTRO_ID = "maestro_id";
    private static final String EXTRA_INTENT_PRODUCT_ID = "product_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maestro);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView rvMaestro = (RecyclerView) findViewById(R.id.rvMaestro);
        rvMaestro.setHasFixedSize(true);
        rvMaestro.setLayoutManager(new LinearLayoutManager(this));
        AdapterMaestro adapter = ((Controller) getApplicationContext()).adapterMaestro;
        rvMaestro.setAdapter(adapter);
        adapter.setProductId(getIntent().getIntExtra(EXTRA_INTENT_PRODUCT_ID, AdapterMaestro.NO_VALUE));
    }

    public void confirm(int id) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_INTENT_MAESTRO_ID, id);
        setResult(RESULT_OK, intent);
        finish();
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
