package ru.byters.bcbarbershop.controllers.adapters;

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

public class SubcategoriesAdapter extends RecyclerView.Adapter<SubcategoriesAdapter.ViewHolder> {
    private int categoryID;
    private Controller controller;

    public SubcategoriesAdapter(@NonNull Controller controller, int categoryID) {
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
        ArrayList<Category> categories = controller.categories.getDataSubcategories(categoryID);
        return categories == null ? 0 : categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }

        public void setData(int position) {
            textView.setText(controller.categories.getDataSubcategories(categoryID).get(position).getTitle().toUpperCase());
        }
    }
}
