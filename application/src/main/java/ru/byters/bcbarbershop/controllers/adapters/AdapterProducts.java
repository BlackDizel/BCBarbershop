package ru.byters.bcbarbershop.controllers.adapters;

import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Product;
import ru.byters.view.LabeledImageView;


public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolderBase>
        implements SwipeRefreshLayout.OnRefreshListener {

    private final static int HEADER = 0;
    private final static int ITEM = 1;
    Controller controller;
    SwipeRefreshLayout refreshLayout;
    int categoryID;

    public AdapterProducts(Controller controller) {
        this.controller = controller;
    }

    public void setViews(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
        refreshLayout.setOnRefreshListener(this);
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        ArrayList<Product> list = controller.controllerProducts.getProducts().getTopLevelDataWithCategoryID(categoryID);
        return list == null
                ? 0
                : (list.size() + (controller.categories.getDataSubcategories(categoryID) == null ? 0 : 1));
    }

    @Override
    public void onBindViewHolder(ViewHolderBase holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && controller.categories.getDataSubcategories(categoryID) != null)
            return HEADER;
        return ITEM;
    }

    @Override
    public ViewHolderBase onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER) {
            return new ViewHolderHeader(LayoutInflater.from(controller).inflate(R.layout.view_products_list_header, parent, false));
        }
        return new ViewHolderItem(new LabeledImageView(parent.getContext()));
    }

    public void setDataUpdated() {
        if (refreshLayout != null) refreshLayout.setRefreshing(false);
        notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        controller.updateProducts();
    }

    public abstract class ViewHolderBase extends RecyclerView.ViewHolder {

        public ViewHolderBase(View itemView) {
            super(itemView);
        }

        public abstract void setData(int position);
    }

    public class ViewHolderHeader extends ViewHolderBase {
        private RecyclerView rv;

        public ViewHolderHeader(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView;
            LinearLayoutManager layoutManager = new LinearLayoutManager(controller, LinearLayoutManager.HORIZONTAL, false);
            rv.setLayoutManager(layoutManager);
            rv.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    int pos = parent.getChildLayoutPosition(view);
                    boolean isLast = (controller.categories.getDataSubcategories(categoryID) != null
                            && pos == controller.categories.getDataSubcategories(categoryID).size() - 1);
                    int margin = (int) controller.getResources().getDimension(R.dimen.item_subcategory_margin);
                    outRect.set(margin, margin, isLast ? margin : 0, margin);
                }
            });
        }

        @Override
        public void setData(int position) {
            rv.setAdapter(new SubcategoriesAdapter(controller, categoryID));
        }
    }

    public class ViewHolderItem extends ViewHolderBase
            implements View.OnClickListener {

        LabeledImageView view;
        int id;

        public ViewHolderItem(View v) {
            super(v);
            v.setOnClickListener(this);
            view = (LabeledImageView) v;
        }

        public void setData(int position) {
            int pos = controller.categories.getDataSubcategories(categoryID) == null ? position : position - 1;
            Product item = null;
            ArrayList<Product> list = controller.controllerProducts.getProducts().getTopLevelDataWithCategoryID(categoryID);
            if (list == null) return;
            if (pos < list.size()) item = list.get(pos);
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
