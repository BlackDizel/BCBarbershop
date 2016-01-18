package ru.byters.bcbarbershop.controllers;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import ru.byters.azure.AzureConnect;
import ru.byters.azure.AzureThrowListener;
import ru.byters.bcbarbershop.R;
import ru.byters.bcbarbershop.controllers.adapters.AdapterCategories;
import ru.byters.bcbarbershop.controllers.adapters.AdapterMaestro;
import ru.byters.bcbarbershop.controllers.adapters.AdapterNews;
import ru.byters.bcbarbershop.controllers.adapters.AdapterProducts;
import ru.byters.bcbarbershop.controllers.utils.MyImageLoadingListener;
import ru.byters.bcbarbershop.dataclasses.Barbershop;
import ru.byters.bcbarbershop.dataclasses.Category;
import ru.byters.bcbarbershop.dataclasses.Maestro;
import ru.byters.bcbarbershop.dataclasses.News;
import ru.byters.bcbarbershop.dataclasses.Product;
import ru.byters.bcbarbershop.dataclasses.ProductMaestro;
import ru.byters.bcbarbershop.models.ModelBarbershop;
import ru.byters.bcbarbershop.models.ModelCategories;
import ru.byters.bcbarbershop.models.ModelMaestro;
import ru.byters.bcbarbershop.models.ModelNews;
import ru.byters.bcbarbershop.models.ModelProducts;
import ru.byters.bcbarbershop.models.ModelProductsMaestro;
import ru.byters.view.LabeledImageView;

public class Controller extends Application implements AzureThrowListener, Application.ActivityLifecycleCallbacks {
    //public ModelEnroll enroll;

    public ControllerProducts controllerProducts;
    public ControllerNews controllerNews;
    public ControllerBarbershop controllerBarbershopInfo;
    public ControllerCategories controllerCategories;
    public ControllerMaestro controllerMaestro;
    public ControllerProductMaestro controllerProductMaestro;

    public AdapterMaestro adapterMaestro;
    public AdapterNews adapterNews;
    public AdapterProducts adapterProducts;
    public AdapterCategories adapterCategories;
    public Activity currentActivity;

    AzureConnect azure;
    DisplayImageOptions options;

    public void Init() {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        azure = new AzureConnect(this
                , getString(R.string.azure_address)
                , getString(R.string.azure_key));
        azure.addListener(this);

        //enroll = new ModelEnroll();

        ModelProductsMaestro.tablename = "ProductMaestro";
        ModelMaestro.tablename = "Maestro";
        ModelProducts.tablename = "Products";
        ModelNews.tablename = "News";
        ModelCategories.tablename = "Categories";
        ModelBarbershop.tablename = "Barbershops";

        controllerBarbershopInfo = new ControllerBarbershop(this, azure);
        controllerNews = new ControllerNews(this, azure);
        controllerProducts = new ControllerProducts(this, azure);
        controllerCategories = new ControllerCategories(this, azure);
        controllerMaestro = new ControllerMaestro(this, azure);
        controllerProductMaestro = new ControllerProductMaestro(this, azure);

        adapterMaestro = new AdapterMaestro(this);
        adapterNews = new AdapterNews(this);
        adapterProducts = new AdapterProducts(this);
        adapterCategories = new AdapterCategories(this);

        registerActivityLifecycleCallbacks(this);
    }

    public void setImage(LabeledImageView v, News data) {
        ImageLoader.getInstance().loadImage(data.PhotoURI, options, new MyImageLoadingListener(v, data));
    }

    @Override
    public <T> void OnDownloadCompleted(String tablename, List<T> result, Boolean error) {
        if (error)
            result = null;

        if (tablename.equals(ModelProductsMaestro.tablename)) {
            controllerProductMaestro.setData(this, (ArrayList<ProductMaestro>) result);
            controllerMaestro.getModel().updateProductsData();
            adapterMaestro.setDataUpdated();
        } else if (tablename.equals(ModelMaestro.tablename)) {
            controllerMaestro.setData(this, (ArrayList<Maestro>) result);
            adapterMaestro.setDataUpdated();
        } else if (tablename.equals(ModelNews.tablename)) {
            controllerNews.setData(this, (ArrayList<News>) result);
            adapterNews.setDataUpdated();
        } else if (tablename.equals(ModelProducts.tablename)) {
            controllerProducts.setData(this, (ArrayList<Product>) result);
            adapterProducts.setDataUpdated();
        } else if (tablename.equals(ModelCategories.tablename)) {
            adapterCategories.notifyDataSetChanged();
            controllerCategories.setData(this, (ArrayList<Category>) result);
        } else if (tablename.equals(ModelBarbershop.tablename) && result != null) {
            controllerBarbershopInfo.setData(this, (ArrayList<Barbershop>) result);
            controllerBarbershopInfo.updateUI();
            if (controllerBarbershopInfo.model.getData() != null)
                adapterCategories.updateFooter(controllerBarbershopInfo.model.getData().Phone);
        }

    }

    @Override
    public void OnUploadCompleted(String tablename, Boolean error) {

    }

    public void updateNews() {
        controllerNews.updateData(azure);
    }

    public void updateProducts() {
        controllerProducts.updateData(azure);
    }

    //region activity lifecycle subscription
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        currentActivity = null;
    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
    //endregion

/*    public void sendEnroll() {
        azure.postTable("Enroll", enroll.getEnroll());
    }

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
    }

    public void updateEnrollView(LabeledImageView v) {
        Maestro m = null;
        if (enroll != null)
            if (maestro != null)
                m = maestro.getItemById(enroll.getEnrollMaestro());
        if (m != null) {
            v.setData(null, m.FIO, "", null);
        }

    }*/


}
