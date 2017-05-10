package com.jaime.inventory.Loaders;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.jaime.inventory.database.DatabaseManager;

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
