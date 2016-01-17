package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.Calendar;

import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.News;
import ru.byters.bcbarbershop.ui.activities.ActivityNewsDetails;
import ru.byters.view.LabeledImageView;


public class AdapterNews
        extends RecyclerView.Adapter<AdapterNews.ViewHolder>
        implements Filterable, SwipeRefreshLayout.OnRefreshListener {

    Controller controller;
    View vData, vError;
    SwipeRefreshLayout refreshLayout;

    public AdapterNews(Controller controller) {
        this.controller = controller;
    }

    public void setViews(View vData, View vError, SwipeRefreshLayout refreshLayout) {
        this.vData = vData;
        this.vError = vError;
        this.refreshLayout = refreshLayout;
        refreshLayout.setOnRefreshListener(this);
    }

    @Override
    public int getItemCount() {
        return controller.controllerNews.getModel().getSize();
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

    public void setStateData(boolean isDataExist) {
        if (vData == null || vError == null) return;
        if (isDataExist) {
            vData.setVisibility(View.VISIBLE);
            vError.setVisibility(View.INVISIBLE);
        } else {
            vData.setVisibility(View.INVISIBLE);
            vError.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (TextUtils.isEmpty(constraint)) {
                    controller.controllerNews.getModel().resetData();
                } else {
                    controller.controllerNews.getModel().mode = Integer.valueOf((String) constraint);
                    controller.controllerNews.getModel().setFilteredList();
                }

                results.count = controller.controllerNews.getModel().getSize();
                results.values = controller.controllerNews.getModel().getData();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                setStateData(controller.controllerNews.getModel().getData() != null);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onRefresh() {
        controller.updateNews();
    }

    //region holder
    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements OnClickListener {

        private LabeledImageView view;
        private int id;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            view = (LabeledImageView) v;
        }

        public void setData(int position) {
            News item = controller.controllerNews.getModel().getItem(position);

            if (!TextUtils.isEmpty(item.PhotoURI))
                ((Controller) view.getContext().getApplicationContext()).setImage(view, item);
            else {
                Calendar c = Calendar.getInstance();
                c.setTime(item.NewsDate);
                view.setData(null, item.Title, item.Description, c);
            }

            id = item.NewsID;
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), ActivityNewsDetails.class);
            i.putExtra(ActivityNewsDetails.INTENT_EXTRA_KEY, id);
            v.getContext().startActivity(i);
        }
    }
    //endregion
}
