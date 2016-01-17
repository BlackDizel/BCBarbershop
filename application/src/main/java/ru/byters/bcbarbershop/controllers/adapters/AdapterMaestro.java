package ru.byters.bcbarbershop.controllers.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.Controller;
import ru.byters.bcbarbershop.dataclasses.Maestro;
import ru.byters.bcbarbershop.ui.activities.ActivityMaestro;


public class AdapterMaestro extends RecyclerView.Adapter<AdapterMaestro.ViewHolder> {
    public static final int NO_VALUE = -1;
    private int productId;
    private Controller controller;

    public AdapterMaestro(Controller controller) {
        this.controller = controller;
        productId = NO_VALUE;
    }

    @Override
    public int getItemCount() {
        if (productId == NO_VALUE)
            return controller.controllerMaestro.getModel().getData() == null
                    ? 0
                    : controller.controllerMaestro.getModel().getData().size();
        return controller.controllerMaestro.getModel().getDataWithProductId(controller, productId) == null
                ? 0
                : controller.controllerMaestro.getModel().getDataWithProductId(controller, productId).size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_maestro_list_item, parent, false);
        return new ViewHolder(v);
    }

    public void setDataUpdated() {
        notifyDataSetChanged();
    }

    public void setProductId(int productId) {
        this.productId = productId;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private int id;
        private ImageView ivPhoto;
        private TextView tvFIO, tvDescription;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ivPhoto = (ImageView) v.findViewById(R.id.ivPhoto);
            tvFIO = (TextView) v.findViewById(R.id.tvFIO);
            tvDescription = (TextView) v.findViewById(R.id.tvDescription);
        }

        public void setData(int position) {
            Maestro item;
            if (productId == NO_VALUE)
                item = controller.controllerMaestro.getModel().getData() == null
                        ? null
                        : controller.controllerMaestro.getModel().getData().get(position);
            else
                item = controller.controllerMaestro.getModel().getDataWithProductId(controller, productId) == null
                        ? null
                        : controller.controllerMaestro.getModel().getDataWithProductId(controller, productId).get(position);

            if (item == null) return;
            ImageLoader.getInstance().displayImage(item.PhotoURI, ivPhoto);
            tvFIO.setText(item.FIO);
            tvDescription.setText(item.Description);
            id = item.MaestroID;
        }

        @Override
        public void onClick(View v) {
            ActivityMaestro a = (ActivityMaestro) v.getContext();
            if (a != null)
                a.confirm(id);
        }
    }


}
