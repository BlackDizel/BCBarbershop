package ru.byters.bcbarbershop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.Date;

import ru.byters.bcbarbershop.R;

public class ActivityDateTime extends ActivityBase {

    public static final String EXTRA_INTENT_DATE = "intent_date";

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
        //rvCalendar.setAdapter();

    }

    public void confirm(Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_INTENT_DATE, date);
        setResult(RESULT_OK, intent);
        finish();
    }

}
