package ru.byters.bcbarbershop.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ControllerStorage {

    public static final String NEWS = "cache_news";
    public static final String CATEGORIES = "cache_categories";
    public static final String PRODUCTS = "cache_products";
    public static final String BARBERSHOP = "cache_barbershop";
    public static final String MAESTRO = "cache_maestro";
    public static final String PRODUCTMAESTRO = "cache_productmaestro";
    private static final String TAG = "controllerStorage";

    public synchronized static void writeObjectToFile(Context context, Object object, String filename) {

        ObjectOutputStream objectOut = null;
        if (object == null)
            context.deleteFile(filename);
        else {
            try {
                FileOutputStream fileOut = context.openFileOutput(filename, Activity.MODE_PRIVATE);
                objectOut = new ObjectOutputStream(fileOut);
                objectOut.writeObject(object);
                fileOut.getFD().sync();

            } catch (IOException e) {
                Log.e(TAG, Log.getStackTraceString(e));
            } finally {
                if (objectOut != null) {
                    try {
                        objectOut.close();
                    } catch (IOException e) {
                        Log.e(TAG, Log.getStackTraceString(e));
                    }
                }
            }
        }
    }

    public synchronized static Object readObjectFromFile(Context context, String filename) {

        ObjectInputStream objectIn = null;
        Object object = null;
        boolean needRemove = false;
        try {

            FileInputStream fileIn = context.getApplicationContext().openFileInput(filename);
            objectIn = new ObjectInputStream(fileIn);
            object = objectIn.readObject();

        } catch (FileNotFoundException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        } catch (IOException | ClassNotFoundException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            needRemove = true;
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {
                    Log.e(TAG, Log.getStackTraceString(e));
                }
            }
        }

        if (needRemove) RemoveFile(context, filename);

        return object;
    }

    public static synchronized void RemoveFile(Context ctx, String filename) {
        try {
            ctx.getApplicationContext().deleteFile(filename);
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }


    /*
    public static synchronized void RemoveFilesByMask(Context ctx, String mask) {
        try {
            String[] files = ctx.getApplicationContext().fileList();
            for (int i = 0; i < files.length; ++i) {
                if (files[i].contains(mask))
                    RemoveFile(ctx, files[i]);
            }
        } catch (Exception e) {
        }
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
    }
    */
}
