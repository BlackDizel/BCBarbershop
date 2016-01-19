package ru.byters.bcbarbershop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Maestro;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ActivityEnroll extends ActivityBase implements OnClickListener {

    public static final String INTENT_EXTRA_PRODUCT_ID = "product_id";
    private static final String INTENT_EXTRA_MAESTRO_ID = "maestro_id";
    private static final String INTENT_EXTRA_DATE = "date";

    private static final int NO_VALUE = -1;
    final int REQUEST_CODE_DATETIME = 1;
    final int REQUEST_CODE_MAESTRO = 2;

    private int product_id;
    private int maestro_id;
    private Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState != null) {
            maestro_id = savedInstanceState.getInt(INTENT_EXTRA_MAESTRO_ID, NO_VALUE);
            product_id = savedInstanceState.getInt(INTENT_EXTRA_PRODUCT_ID, NO_VALUE);
            date = (Date) savedInstanceState.getSerializable(INTENT_EXTRA_DATE);
        } else {
            product_id = getIntent().getIntExtra(INTENT_EXTRA_PRODUCT_ID, NO_VALUE);
            maestro_id = NO_VALUE;
        }

        findViewById(R.id.livMaestro).setOnClickListener(this);
        findViewById(R.id.livProduct).setOnClickListener(this);
        findViewById(R.id.tvDate).setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(INTENT_EXTRA_MAESTRO_ID, maestro_id);
        outState.putInt(INTENT_EXTRA_PRODUCT_ID, product_id);
        outState.putSerializable(INTENT_EXTRA_DATE, date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_enroll, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_DATETIME:
                if (resultCode == RESULT_OK)
                    date = (Date) data.getSerializableExtra(ActivityDateTime.EXTRA_INTENT_DATE);
                break;
            case REQUEST_CODE_MAESTRO:
                if (resultCode == RESULT_OK)
                    maestro_id = data.getIntExtra(ActivityMaestro.EXTRA_INTENT_MAESTRO_ID, NO_VALUE);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (product_id != NO_VALUE) {
            Product product = ((Controller) getApplicationContext()).controllerProducts.getModel().getProductWithId(product_id);
            if (product != null) {
                if (!TextUtils.isEmpty(product.getPhotoURI())) {
                    ImageView imgView = ((ImageView) findViewById(R.id.livProduct).findViewById(ru.byters.view.R.id.imgView));
                    ImageLoader.getInstance().displayImage(product.getPhotoURI(), imgView);
                }
                ((TextView) findViewById(R.id.livProduct).findViewById(ru.byters.view.R.id.tvPrimary)).setText(product.getTitle());
            }
        }
        if (maestro_id != NO_VALUE) {
            Maestro maestro = ((Controller) getApplicationContext()).controllerMaestro.getModel().getMaestroWithId(maestro_id);
            if (maestro != null) {
                if (!TextUtils.isEmpty(maestro.PhotoURI)) {
                    ImageView imgView = ((ImageView) findViewById(R.id.livMaestro).findViewById(ru.byters.view.R.id.imgView));
                    ImageLoader.getInstance().displayImage(maestro.PhotoURI, imgView);
                }
                ((TextView) findViewById(R.id.livMaestro).findViewById(ru.byters.view.R.id.tvPrimary)).setText(maestro.FIO);
            }
        } else {
            //todo set first available maestro with sooner data
        }
        if (date != null) {
            ((TextView) findViewById(R.id.tvDate)).setText(new SimpleDateFormat("HH:mm DD MMMM").format(date));
        } else {
            //todo set first available date
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_confirm) {
            //   ((Controller) getApplicationContext()).sendEnroll();
            return true;
        } else if (id == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.livProduct:
                //enable if product_id is not set
                if (product_id == NO_VALUE)
                    //todo navigate to select product activity;
                    ;
                break;
            case R.id.tvDate:
                if (maestro_id != NO_VALUE) {
                    intent = new Intent(this, ActivityDateTime.class);
                    intent.putExtra(ActivityDateTime.EXTRA_INTENT_MAESTRO_ID, maestro_id);
                    startActivityForResult(intent, REQUEST_CODE_DATETIME);
                }
                break;
            case R.id.livMaestro:
                intent = new Intent(this, ActivityMaestro.class);
                if (product_id != NO_VALUE)
                    intent.putExtra(ActivityMaestro.EXTRA_INTENT_PRODUCT_ID, product_id);
                startActivityForResult(intent, REQUEST_CODE_MAESTRO);
                break;
        }
    }
}
