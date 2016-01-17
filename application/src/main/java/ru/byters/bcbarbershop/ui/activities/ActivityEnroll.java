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

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Product;

public class ActivityEnroll extends ActivityBase implements OnClickListener {

    public static final String INTENT_EXTRA_PRODUCT_ID = "product_id";
    private static final int NO_VALUE = -1;
    final int REQUEST_CODE_DATETIME = 1;
    final int REQUEST_CODE_MAESTRO = 2;

    private int product_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        product_id = getIntent().getIntExtra(INTENT_EXTRA_PRODUCT_ID, NO_VALUE);

        findViewById(R.id.livMaestro).setOnClickListener(this);
        findViewById(R.id.livProduct).setOnClickListener(this);
        findViewById(R.id.tvDate).setOnClickListener(this);
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
                //todo update date
                break;
            case REQUEST_CODE_MAESTRO:
                //todo update maestro
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (product_id != NO_VALUE) {
            Product product = ((Controller) getApplicationContext()).controllerProducts.getProducts().getProductWithId(product_id);
            if (product != null) {
                if (!TextUtils.isEmpty(product.getPhotoURI())) {
                    ImageView imgView = ((ImageView) findViewById(R.id.livProduct).findViewById(ru.byters.view.R.id.imgView));
                    ImageLoader.getInstance().displayImage(product.getPhotoURI(), imgView);
                }
                ((TextView) findViewById(R.id.livProduct).findViewById(ru.byters.view.R.id.tvTitle)).setText(product.getTitle());
            }
        }
        //todo update data
        // ((Controller) getApplicationContext()).updateEnrollView(livMaestro);
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
        switch (v.getId()) {
            case R.id.livProduct:
                //enable if product_id is not set
                if (product_id == NO_VALUE)
                    //todo navigate to select product activity;
                    ;
                break;
            case R.id.tvDate:
                //todo navigate to select date activity
                //      startActivityForResult(new Intent(this, ActivityDateTime.class), REQUEST_CODE_DATETIME);
                break;
            case R.id.livMaestro:
                //todo navigate to select maestro activity
                //    startActivityForResult(new Intent(this, ActivityMaestro.class), REQUEST_CODE_MAESTRO);
                break;
        }
    }
}