package ru.byters.azure;

import android.content.Context;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.QueryOrder;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;
import com.microsoft.windowsazure.mobileservices.TableQueryCallback;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public final class AzureConnect {
    private static MobileServiceClient client;
    private List<AzureThrowListener> listeners = new ArrayList<>();

    public AzureConnect(Context context, String address, String key) {
        try {
            if (client == null)
                client = new MobileServiceClient(address, key, context);
        } catch (MalformedURLException e) {
            Log.v("Azure", "init error");
        }
    }

    public void addListener(AzureThrowListener tl) {
        if (!listeners.contains(tl))
            listeners.add(tl);
    }

    public void removeListener(AzureThrowListener tl) {
        if (listeners.contains(tl))
            listeners.remove(tl);
    }

    private <T> void throwOnDownload(String tablename, List<T> result, boolean error) {
        Log.v("Azure", "throw azure table download " + tablename + ". Error is " + String.valueOf(error));
        for (AzureThrowListener listener : listeners)
            listener.OnDownloadCompleted(tablename, result, error);
    }

    private void throwOnUpload(String tablename, boolean error) {
        Log.v("Azure", "throw azure table upload " + tablename + ". Error is " + String.valueOf(error));
        for (AzureThrowListener listener : listeners)
            listener.OnUploadCompleted(tablename, error);
    }

    public <T> void getTableTop(String tablename, Class<T> className, int top) {
        getTable(tablename, className, 0, top);
    }

    public <T> void getTableTopOrderBy(String tablename, Class<T> className, int top, String field, QueryOrder order) {
        getTableOrderBy(tablename, className, 0, top, field, order);
    }

    public <T> void getTable(String tablename, Class<T> className, int skip, int top) {
        client.getTable(tablename, className)
                .skip(skip)
                .top(top)
                .execute(new MyTableQueryCallback<T>(tablename));
        Log.v("Azure", "get table " + tablename + " request sent");
    }

    public <T> void getTableOrderBy(String tablename, Class<T> className, int skip, int top, String field, QueryOrder order) {
        client.getTable(tablename, className)
                .skip(skip)
                .top(top)
                .orderBy(field, order)
                .execute(new MyTableQueryCallback<T>(tablename));
        Log.v("Azure", "get table " + tablename + " ordered request sent");
    }

    @SuppressWarnings("unchecked")
    public <E> void postTable(String tablename, E el) {
        client.getTable(tablename, (Class<E>) (el.getClass()))
                .insert(el, new MyTableOperationCallback<E>(tablename));
    }

    private class MyTableQueryCallback<T> implements TableQueryCallback<T> {
        String tablename;

        public MyTableQueryCallback(String s) {
            this.tablename = s;
        }

        @Override
        public void onCompleted(List<T> result, int count, Exception exception, ServiceFilterResponse response) {
            List<T> l = null;
            Boolean error = false;
            if (exception == null) l = result;
            else error = true;
            throwOnDownload(tablename, l, error);
        }

    }

    private class MyTableOperationCallback<T> implements TableOperationCallback<T> {

        String tablename;

        public MyTableOperationCallback(String tb) {
            tablename = tb;
        }

        @Override
        public void onCompleted(T arg0, Exception exception,
                                ServiceFilterResponse response) {
            boolean error = false;
            if (exception != null) error = true;
            throwOnUpload(tablename, error);

        }
    }


}
