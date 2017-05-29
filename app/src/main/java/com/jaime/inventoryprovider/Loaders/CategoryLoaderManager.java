package com.jaime.inventoryprovider.Loaders;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.jaime.inventoryprovider.database.DatabaseManager;

/**
 * Created by jaime on 10/05/2017.
 */

public class CategoryLoaderManager extends CursorLoader {

    public CategoryLoaderManager(Context context) {
        super(context);
    }


    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllCategory();
    }
}
