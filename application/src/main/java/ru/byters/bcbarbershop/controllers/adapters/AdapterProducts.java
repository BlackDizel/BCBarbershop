package ru.byters.bcbarbershop.controllers.adapters;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Product;
import ru.byters.view.LabeledImageView;


public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolder>
        implements SwipeRefreshLayout.OnRefreshListener {

    Controller controller;
    SwipeRefreshLayout refreshLayout;
    int CategoryID;

    public AdapterProducts(Controller controller) {
        this.controller = controller;
    }

    public void setViews(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
        refreshLayout.setOnRefreshListener(this);
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        ArrayList<Product> list = controller.controllerProducts.getProducts().getTopLevelDataWithCategoryID(CategoryID);
        return list == null ? 0 : list.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new LabeledImageView(parent.getContext()));
    }

    public void setDataUpdated() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
        notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        controller.updateProducts();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        LabeledImageView view;
        int id;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            view = (LabeledImageView) v;
        }

        public void setData(int position) {
            Product item = null;
            ArrayList<Product> list = controller.controllerProducts.getProducts().getTopLevelDataWithCategoryID(CategoryID);
            if (list == null) return;
            if (position < list.size()) item = list.get(position);
            if (item == null) return;

            //todo setData
            /*
            if (!TextUtils.isEmpty(item.PhotoURI))
                ((Controller) view.getContext().getApplicationContext()).setImage(view, item);
            else {
                Calendar c = Calendar.getInstance();
                c.setTime(item.NewsDate);
                view.setData(null, item.Title, item.Description, c);
            }*/

            id = item.getProductID();
        }

        @Override
        public void onClick(View v) {
            //todo implement
        }
    }


}
