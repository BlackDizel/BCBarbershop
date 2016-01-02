package ru.byters.bcbarbershop.controllers.adapters;

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
import ru.byters.view.LabeledImageView;


public class AdapterNews
        extends RecyclerView.Adapter<AdapterNews.ViewHolder>
        implements Filterable, SwipeRefreshLayout.OnRefreshListener {

    public static final String INTENT_EXTRA_KEY = "id";

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
        return controller.news == null ? 0 : controller.news.getSize();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new LabeledImageView(parent.getContext()));
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
                    controller.news.resetData();
                } else {
                    controller.news.mode = Integer.valueOf((String) constraint);
                    controller.news.setFilteredList();
                }

                results.count = controller.news.Data.size();
                results.values = controller.news.Data;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                setStateData(controller.news.Data != null);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public void onRefresh() {
        //todo implement
    }

    //region holder
    public class ViewHolder
            extends RecyclerView.ViewHolder
            implements OnClickListener {

        private LabeledImageView view;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            view = (LabeledImageView) v;
        }

        public void setData(int position) {
            News item = controller.news.getItem(position);

            if (!TextUtils.isEmpty(item.PhotoURI))
                ((Controller) view.getContext().getApplicationContext()).setImage(view, item);
            else {
                Calendar c = Calendar.getInstance();
                c.setTime(item.NewsDate);
                view.setData(null, item.Title, item.Description, c);
            }

            view.setTag(item.NewsID);
        }

        @Override
        public void onClick(View v) {
            //todo implement
            /*int id = (Integer) v.getTag();
            Intent i = new Intent(v.getContext(), ActivityNews.class);
            i.putExtra(INTENT_EXTRA_KEY, id);
            v.getContext().startActivity(i);*/
        }
    }
    //endregion
}
