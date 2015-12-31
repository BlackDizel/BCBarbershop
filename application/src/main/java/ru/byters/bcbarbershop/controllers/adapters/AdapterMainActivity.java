package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.Locale;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.ui.fragments.FragmentAbout;
import ru.byters.bcbarbershop.ui.fragments.FragmentMenu;
import ru.byters.bcbarbershop.ui.fragments.FragmentNews;

public class AdapterMainActivity extends FragmentStatePagerAdapter {

    Context context;

    public AdapterMainActivity(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentNews();
            case 1:
                return new FragmentMenu();
            case 2:
                return new FragmentAbout();
            default:
                break;
        }
        return null;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return context.getString(R.string.title_section1).toUpperCase(l);
            case 1:
                return context.getString(R.string.title_section2).toUpperCase(l);
            case 2:
                return context.getString(R.string.title_section3).toUpperCase(l);
        }
        return null;
    }
}
