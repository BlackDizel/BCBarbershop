package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Category;
import ru.byters.bcbarbershop.ui.activities.ActivityShop;

public class AdapterSubcategories extends RecyclerView.Adapter<AdapterSubcategories.ViewHolder> {
    private int categoryID;
    private Controller controller;

    public AdapterSubcategories(@NonNull Controller controller, int categoryID) {
        this.categoryID = categoryID;
        this.controller = controller;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(controller).inflate(R.layout.view_subcategories_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        ArrayList<Category> categories = controller.controllerCategories.getCategorties().getDataSubcategories(categoryID);
        return categories == null ? 0 : categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        private int intent_category_id;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
            itemView.setOnClickListener(this);
        }

        public void setData(int position) {
            Category category = controller.controllerCategories.getCategorties().getDataSubcategories(categoryID).get(position);
            if (category == null) return;
            intent_category_id = category.getCategoryID();
            textView.setText(category.getTitle().toUpperCase());
        }

        @Override
        public void onClick(View v) {
            if (controller.currentActivity == null)
                return;
            Intent intent = new Intent(controller, ActivityShop.class);
            intent.putExtra(ActivityShop.INTENT_EXTRA_CATEGORY_ID, intent_category_id);
            controller.currentActivity.startActivity(intent);
        }
    }
}
