package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Product;
import ru.byters.bcbarbershop.ui.activities.ActivityProductInfo;
import ru.byters.bcbarbershop.ui.activities.ActivityShop;
import ru.byters.view.LabeledImageView;


public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.ViewHolderBase>
        implements SwipeRefreshLayout.OnRefreshListener {

    public static final int NO_VALUE = -1;
    private final static int HEADER = 0;
    private final static int ITEM = 1;
    Controller controller;
    SwipeRefreshLayout refreshLayout;
    int categoryID;
    private int productID;

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

    public void setProductID(int productID) {
        this.productID = productID;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (categoryID != NO_VALUE) {
            ArrayList<Product> list = controller.controllerProducts.getModel().getTopLevelDataWithCategoryID(categoryID);
            return list == null
                    ? 0
                    : (list.size() + (!isHeaderMode() ? 0 : 1));
        }
        return controller.controllerProducts.getModel().getSubproducts(productID) == null
                ? 0
                : controller.controllerProducts.getModel().getSubproducts(productID).size();
    }

    @Override
    public void onBindViewHolder(ViewHolderBase holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && isHeaderMode())
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

    public boolean isHeaderMode() {
        return categoryID != NO_VALUE && controller.controllerCategories.getModel().getDataSubcategories(categoryID) != null;
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
                    boolean isLast = (controller.controllerCategories.getModel().getDataSubcategories(categoryID) != null
                            && pos == controller.controllerCategories.getModel().getDataSubcategories(categoryID).size() - 1);
                    int margin = (int) controller.getResources().getDimension(R.dimen.item_subcategory_margin);
                    outRect.set(margin, margin, isLast ? margin : 0, margin);
                }
            });
        }

        @Override
        public void setData(int position) {
            rv.setAdapter(new AdapterSubcategories(controller, categoryID));
        }
    }

    public class ViewHolderItem extends ViewHolderBase
            implements View.OnClickListener {

        ImageView imgView;
        TextView tvTitle;
        int id;

        public ViewHolderItem(View v) {
            super(v);
            v.setOnClickListener(this);
            imgView = (ImageView) v.findViewById(ru.byters.view.R.id.imgView);
            tvTitle = (TextView) v.findViewById(ru.byters.view.R.id.tvPrimary);
            v.findViewById(ru.byters.view.R.id.tvEnd).setVisibility(View.GONE);
        }

        public void setData(int position) {
            Product item = null;
            ArrayList<Product> list;
            int pos;
            if (categoryID != NO_VALUE) {
                pos = controller.controllerCategories.getModel().getDataSubcategories(categoryID) == null ? position : position - 1;
                list = controller.controllerProducts.getModel().getTopLevelDataWithCategoryID(categoryID);
            } else {
                pos = position;
                list = controller.controllerProducts.getModel().getSubproducts(productID);
            }
            if (list == null) return;
            if (pos >= 0 && pos < list.size()) item = list.get(pos);
            if (item == null) return;

            if (!TextUtils.isEmpty(item.getPhotoURI()))
                ImageLoader.getInstance().displayImage(item.getPhotoURI(), imgView);
            else {
                //todo setData
            }
            tvTitle.setText(item.getTitle());

            id = item.getProductID();
        }

        @Override
        public void onClick(View v) {
            if (controller.controllerProducts.getModel().getSubproducts(id) != null) {
                Intent intent = new Intent(controller, ActivityShop.class);
                intent.putExtra(ActivityShop.INTENT_EXTRA_PRODUCT_ID, id);
                controller.currentActivity.startActivity(intent);
            } else {
                Intent intent = new Intent(controller, ActivityProductInfo.class);
                intent.putExtra(ActivityProductInfo.INTENT_EXTRA_PRODUCT_ID, id);
                controller.currentActivity.startActivity(intent);
            }
        }
    }


}
