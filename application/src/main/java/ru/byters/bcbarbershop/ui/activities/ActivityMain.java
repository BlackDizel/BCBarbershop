package ru.byters.bcbarbershop.ui.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.controllers.adapters.AdapterMainActivity;
import ru.byters.bcbarbershop.models.ModelNews;

public class ActivityMain extends ActivityBase implements OnPageChangeListener {

    private ViewPager viewPager;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new AdapterMainActivity(this, getSupportFragmentManager()));
        viewPager.setOnPageChangeListener(this);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        Controller controller = (Controller) getApplicationContext();
        controller.Init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    //region menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        menu.clear();
        switch (viewPager.getCurrentItem()) {
            case 0:
                getMenuInflater().inflate(R.menu.news, menu);
                break;
            case 1:
                // getMenuInflater().inflate(R.menu.activity_main, menu);
                break;
            case 2:
                // getMenuInflater().inflate(R.menu.activity_main, menu);
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (viewPager.getCurrentItem()) {
            case 0:
                switch (id) {
                    case R.id.action_filter_all:
                        ((Controller) getApplicationContext())
                                .adapterNews.getFilter().filter(String.valueOf(ModelNews.MODE_ALL));
                        break;
                    case R.id.action_filter_promo:
                        ((Controller) getApplicationContext())
                                .adapterNews.getFilter().filter(String.valueOf(ModelNews.MODE_PROMO));
                        break;
                    case R.id.action_filter_info:
                        ((Controller) getApplicationContext())
                                .adapterNews.getFilter().filter(String.valueOf(ModelNews.MODE_INFO));
                        break;
                    case R.id.action_filter_community:
                        ((Controller) getApplicationContext())
                                .adapterNews.getFilter().filter(String.valueOf(ModelNews.MODE_COMMUNITY));
                        break;

                    default:
                        break;
                }

                break;
            case 1:
                break;
            case 2:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menu != null)
            onCreateOptionsMenu(menu);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //endregion
}
