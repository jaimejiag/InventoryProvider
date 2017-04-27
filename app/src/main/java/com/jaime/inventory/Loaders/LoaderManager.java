package com.jaime.inventory.Loaders;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.jaime.inventory.database.DatabaseManager;

/**
 * Created by usuario on 24/04/17.
 */

public class LoaderManager extends CursorLoader {

    public LoaderManager(Context context) {
        super(context);
    }


    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllProduct();
    }
}
