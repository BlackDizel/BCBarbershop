package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
    private String phone;

    public AdapterCategories(Controller controller) {
        this.controller = controller;
        phone = controller.controllerBarbershopInfo.model.getData() != null ?
                controller.controllerBarbershopInfo.model.getData().Phone : null;
        colors = controller.getResources().getIntArray(R.array.main_menu_colors);
    }

    @Override
    public int getItemCount() {
        return controller.controllerCategories.getCategorties().getSizeTopLevel() + (TextUtils.isEmpty(phone) ? 0 : 1);
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

    public void updateFooter(@Nullable String phone) {
        boolean isPhoneExist = false;
        if (!TextUtils.isEmpty(this.phone)) isPhoneExist = true;
        if (!TextUtils.isEmpty(phone)) {
            this.phone = phone;
            if (isPhoneExist) notifyItemChanged(getItemCount() - 1);
            else notifyItemInserted(getItemCount() - 1);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private TextView tvItem;
        private TextView tvDesc;
        private View view;
        private int categoryID;

        public ViewHolder(View v) {
            super(v);
            tvItem = (TextView) v.findViewById(R.id.tvData);
            tvDesc = (TextView) v.findViewById(R.id.tvDescription);
            this.view = v;
            v.setOnClickListener(this);
        }

        public void setData(int position) {
            if (!TextUtils.isEmpty(phone) && position == getItemCount() - 1) {
                tvItem.setText(controller.getResources().getString(R.string.call_to_barbershop));
                tvDesc.setText("");
            } else {
                Category item = controller.controllerCategories.getCategorties().getItemTopLevel(position);
                if (item == null) return;
                tvItem.setText(item.getTitle().toUpperCase());
                tvDesc.setText(item.getDescription());
                categoryID = item.getCategoryID();
            }
            view.setBackgroundColor(colors[position % colors.length]);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;

            if (!TextUtils.isEmpty(phone) && position == getItemCount() - 1) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                if (intent.resolveActivity(controller.getPackageManager()) != null)
                    v.getContext().startActivity(intent);
            } else {
                Intent i = new Intent(v.getContext(), ActivityShop.class);
                i.putExtra(ActivityShop.INTENT_EXTRA_CATEGORY_ID, categoryID);
                v.getContext().startActivity(i);
            }
        }
    }
}
