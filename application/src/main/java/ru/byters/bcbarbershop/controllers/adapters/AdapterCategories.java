package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Category;
import ru.byters.bcbarbershop.ui.activities.ActivityShop;


public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {
    int[] colors;
    private Controller controller;

    public AdapterCategories(Controller controller) {
        this.controller = controller;
        colors = controller.getResources().getIntArray(R.array.main_menu_colors);
    }

    @Override
    public int getItemCount() {
        return controller.categories.getSize();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu_main_item, parent, false));
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private TextView tvItem;
        private int categoryID;

        public ViewHolder(View v) {
            super(v);
            tvItem = (TextView) v.findViewById(R.id.tvData);
            v.setOnClickListener(this);
        }

        public void setData(int position) {
            Category item = controller.categories.getItem(position);
            tvItem.setText(item.getTitle().toUpperCase());
            tvItem.setBackgroundColor(colors[position % colors.length]);
            categoryID = item.getCategoryID();
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ActivityShop.class);
            i.putExtra(ActivityShop.INTENT_EXTRA_ID, categoryID);
            v.getContext().startActivity(i);
        }
    }
}
