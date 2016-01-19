package ru.byters.bcbarbershop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.Date;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.adapters.AdapterDateTime;

public class ActivityDateTime extends ActivityBase {

    public static final String EXTRA_INTENT_DATE = "intent_date";
    public static final String EXTRA_INTENT_MAESTRO_ID = "maestro_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView rvCalendar = (RecyclerView) findViewById(R.id.rvTime);
        rvCalendar.setHasFixedSize(true);
        rvCalendar.setLayoutManager(new LinearLayoutManager(this));
        AdapterDateTime adapter = ((Controller) getApplicationContext()).adapterDateTime;
        adapter.setMaestroId(getIntent().getIntExtra(EXTRA_INTENT_MAESTRO_ID, AdapterDateTime.NO_VALUE));
        rvCalendar.setAdapter(adapter);
    }

    public void confirm(Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_INTENT_DATE, date);
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
