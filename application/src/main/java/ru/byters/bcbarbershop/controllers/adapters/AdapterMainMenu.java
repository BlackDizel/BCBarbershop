package ru.byters.bcbarbershop.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.dataclasses.ItemMenuColored;
import ru.byters.bcbarbershop.models.ModelMainMenu;


public class AdapterMainMenu extends RecyclerView.Adapter<AdapterMainMenu.ViewHolder> {
    public static String TAG = "filter";
    private ModelMainMenu model;
    //private Context context;

    public AdapterMainMenu(Context context) {
        String[] names = context.getResources().getStringArray(R.array.main_menu);
        int[] colors = context.getResources().getIntArray(R.array.main_menu_colors);
        ItemMenuColored[] d = new ItemMenuColored[names.length];

        for (int i = 0; i < d.length; ++i)
            d[i] = new ItemMenuColored(names[i], colors[i]);

        model = new ModelMainMenu(d);
//        this.context = context;
    }

    @Override
    public int getItemCount() {
        return model.getSize();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(model.getItem(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.view_menu_main_item, parent, false));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private TextView tvItem;

        public ViewHolder(View v) {
            super(v);
            tvItem = (TextView) v.findViewById(R.id.tvData);
            tvItem.setOnClickListener(this);
        }

        public void setData(ItemMenuColored item) {
            tvItem.setText(item.text);
            tvItem.setBackgroundColor(item.color);
        }

        @Override
        public void onClick(View v) {
            //todo implement
            //Intent i = new Intent(context, ActivityShop.class);
            //i.putExtra(TAG, ((TextView)v).getText());
            //context.startActivity(i);
        }
    }
}
