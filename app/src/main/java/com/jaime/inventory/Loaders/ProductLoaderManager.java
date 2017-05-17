package com.jaime.inventory.Loaders;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.jaime.inventory.database.DatabaseManager;

/**
 * Created by usuario on 24/04/17.
 */

public class ProductLoaderManager extends CursorLoader {

    public ProductLoaderManager(Context context) {
        super(context);
    }


    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllProductInnerJoin();
    }


}
