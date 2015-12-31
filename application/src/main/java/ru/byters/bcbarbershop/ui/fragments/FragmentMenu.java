package ru.byters.bcbarbershop.ui.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.adapters.AdapterMainMenu;

public class FragmentMenu extends FragmentBase {

    RecyclerView rvMenu;

    public FragmentMenu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        rvMenu = (RecyclerView) rootView.findViewById(R.id.rvMainMenu);
        rvMenu.setHasFixedSize(true);
        rvMenu.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        rvMenu.setAdapter(new AdapterMainMenu(rootView.getContext()));
        rvMenu.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int margin = (int) getResources().getDimension(R.dimen.item_menu_margin);
                outRect.set(margin, margin, margin, 0);
            }
        });
        return rootView;
    }
}