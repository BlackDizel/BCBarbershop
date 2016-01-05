package ru.byters.bcbarbershop.ui.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
        rvMenu.setLayoutManager(new GridLayoutManager(
                rootView.getContext()
                , rootView.getContext().getResources().getInteger(R.integer.menu_columns_num)));
        rvMenu.setAdapter(new AdapterMainMenu(rootView.getContext()));
        rvMenu.addItemDecoration(new ItemsDecorator(rootView.getContext().getResources().getInteger(R.integer.menu_columns_num)));
        return rootView;
    }

    private class ItemsDecorator extends RecyclerView.ItemDecoration {
        private final int columns;

        public ItemsDecorator(int columns) {
            this.columns = columns;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int space = (int) getResources().getDimension(R.dimen.item_menu_margin);
            int pos = parent.getChildAdapterPosition(view);

            if (columns == 2) {
                //[4]x[2][2]x[4]
                if (pos % 2 == 0)
                    outRect.set(4 * space, 4 * space, 2 * space, 0);
                else
                    outRect.set(2 * space, 4 * space, 4 * space, 0);
            } else if (columns == 3) {
                //[6]x[0][3]x[3][0]x[6]
                if (pos % 3 == 0)
                    outRect.set(6 * space, 4 * space, 0, 0);
                else if (pos % 3 == 1)
                    outRect.set(3 * space, 4 * space, 3 * space, 0);
                else if (pos % 3 == 2)
                    outRect.set(0, 4 * space, 6 * space, 0);
            }
        }
    }

}