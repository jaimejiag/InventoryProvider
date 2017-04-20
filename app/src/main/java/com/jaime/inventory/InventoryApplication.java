package com.jaime.inventory;

import android.app.Application;
import android.content.Context;

import com.jaime.inventory.database.DatabaseHelper;

/**
 * Created by usuario on 20/04/17.
 */

public class InventoryApplication extends Application {
    private static InventoryApplication mInstance;


    @Override
    public void onCreate() {
        super.onCreate();

        // Cuando se abra la aplicación, creará la base de datos.
        DatabaseHelper.getInstance().openDatabase();
    }


    public InventoryApplication() {
        mInstance = this;
    }


    public static Context getContext() {
        return mInstance;
    }
}
