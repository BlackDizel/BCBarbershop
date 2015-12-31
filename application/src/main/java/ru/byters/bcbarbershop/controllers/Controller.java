package ru.byters.bcbarbershop.controllers;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import ru.byters.azure.AzureConnect;
import ru.byters.azure.AzureThrowListener;
import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.adapters.AdapterNews;
import ru.byters.bcbarbershop.controllers.utils.MyImageLoadingListener;
import ru.byters.bcbarbershop.dataclasses.Barbershop;
import ru.byters.bcbarbershop.dataclasses.News;
import ru.byters.bcbarbershop.models.ModelMaestro;
import ru.byters.bcbarbershop.models.ModelNews;
import ru.byters.bcbarbershop.models.ModelProducts;
import ru.byters.bcbarbershop.models.ModelProductsMaestro;
import ru.byters.bcbarbershop.ui.fragments.FragmentDates;
import ru.byters.view.LabeledImageView;

public class Controller extends Application implements AzureThrowListener {
    //public ModelProducts products;
    //public ModelMaestro maestro;
    // public ModelEnroll enroll;
    public ModelNews news;
    // public AdapterProducts adapterProducts;
    // public AdapterMaestro adapterMaestro;
    public AdapterBarbershopInfo barbershop;
    public AdapterNews adapterNews;
    //  ModelProductsMaestro productmaestro;
    AzureConnect azure;
    DisplayImageOptions options;

    public void Init() {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        barbershop = new AdapterBarbershopInfo();

        azure = new AzureConnect(this
                , getString(R.string.azure_address)
                , getString(R.string.azure_key));
        azure.addListener(this);

        ModelProducts.tablename = "Products";
        ModelProductsMaestro.tablename = "ProductsMaestro";
        ModelMaestro.tablename = "Maestro";
        ModelNews.tablename = "News";
        barbershop.model.tablename = "Barbershops";

        // adapterProducts = new AdapterProducts(this);
        //  adapterMaestro = new AdapterMaestro(this);
        adapterNews = new AdapterNews(this);

        // azure.getTableTop(ModelProducts.tablename, Product.class, 500);
        // azure.getTableTop(ModelMaestro.tablename, Maestro.class, 500);
        //  azure.getTableTop(ModelProductsMaestro.tablename, ProductMaestro.class, 500);
        azure.getTableTop(ModelNews.tablename, News.class, 500);
          azure.getTableTop(barbershop.model.tablename, Barbershop.class, 500);

        //enroll = new ModelEnroll();
    }

    public void setImage(LabeledImageView v, News data) {

        ImageLoader.getInstance().loadImage(data.PhotoURI, options, new MyImageLoadingListener(v, data));

    }

/*    public void sendEnroll() {
        azure.postTable("Enroll", enroll.getEnroll());
    }*/
/*
    public Fragment InitFragmentDates() {
        int monthnum = 3;
        Bundle[] arrb = new Bundle[monthnum];
        for (int i = 0; i < arrb.length; ++i) {
            Bundle b = new Bundle();
            b.putInt(FragmentDates.KEY, i);
            arrb[i] = b;
        }
        return //new ModelSimpleString(Calendar.getInstance().getTime(),monthnum,ModelSimpleString.Type.Months).getData(),
                //arrb,
                new FragmentDates();
    }*/

    @Override
    public <T> void OnDownloadCompleted(String tablename, List<T> result, Boolean error) {
        if (!error) {
            /*if (tablename.equals(ModelProducts.tablename)) {
                products = new ModelProducts((ArrayList<Product>) result);
                adapterProducts.updateModel(this);
                adapterProducts.notifyDataSetChanged();
            } else if (tablename.equals(ModelProductsMaestro.tablename))
                productmaestro = new ModelProductsMaestro((ArrayList<ProductMaestro>) result);
            else if (tablename.equals(ModelMaestro.tablename)) {
                maestro = new ModelMaestro((ArrayList<Maestro>) result);
                adapterMaestro.updateModel(this);
                adapterMaestro.notifyDataSetChanged();
            } else*/
            if (tablename.equals(ModelNews.tablename)) {
                news = new ModelNews((ArrayList<News>) result);
                news.Sort();
                news.Reverse();
                news.setFilteredList();
                adapterNews.notifyDataSetChanged();
            } else if (tablename.equals(barbershop.model.tablename)) {
                ArrayList<Barbershop> l = (ArrayList<Barbershop>) result;
                barbershop.model.setData(l.get(0));
                barbershop.updateData();
            }
        }
    }

    @Override
    public void OnUploadCompleted(String tablename, Boolean error) {

    }


    /*public void updateEnrollView(LabeledImageView v) {
        Maestro m = null;
        if (enroll != null)
            if (maestro != null)
                m = maestro.getItemById(enroll.getEnrollMaestro());
        if (m != null) {
            v.setData(null, m.FIO, "", null);
        }

    }*/


}
