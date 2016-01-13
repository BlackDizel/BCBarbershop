package ru.byters.bcbarbershop.ui.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;

public class FragmentMenu extends FragmentBase {

    RecyclerView rvMenu;

    public FragmentMenu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        rvMenu = (RecyclerView) rootView.findViewById(R.id.rvMainMenu);
        rvMenu.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(
                rootView.getContext()
                , rootView.getContext().getResources().getInteger(R.integer.menu_columns_num));
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == ((Controller) getActivity().getApplicationContext()).adapterCategories.getItemCount() - 1
                        && position % 2 == 0
                        && getActivity().getResources().getInteger(R.integer.menu_columns_num) > 1)
                    return 2;
                return 1;
            }
        });
        rvMenu.setLayoutManager(layoutManager);
        rvMenu.setAdapter(((Controller) rootView.getContext().getApplicationContext()).adapterCategories);
        rvMenu.addItemDecoration(new ItemsDecorator((Controller) getActivity().getApplicationContext(), rootView.getContext().getResources().getInteger(R.integer.menu_columns_num)));
        return rootView;
    }

    private class ItemsDecorator extends RecyclerView.ItemDecoration {
        private final int columns;
        private Controller controller;

        public ItemsDecorator(Controller controller, int columns) {
            this.columns = columns;
            this.controller = controller;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int space = (int) getResources().getDimension(R.dimen.item_menu_margin);
            int pos = parent.getChildAdapterPosition(view);
            if (columns == 1) {
                outRect.set(4 * space, 4 * space, 4 * space, 0);
            } else if (columns == 2) {
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
            if (controller.adapterCategories.getItemCount() - 1 == pos)
                outRect.set(4 * space, 4 * space, 4 * space, 0);
        }
    }

}