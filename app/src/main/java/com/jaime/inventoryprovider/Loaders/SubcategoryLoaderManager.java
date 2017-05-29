package com.jaime.inventoryprovider.Loaders;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.jaime.inventoryprovider.database.DatabaseManager;

/**
 * Created by jaime on 10/05/2017.
 */

public class SubcategoryLoaderManager extends CursorLoader {
    private int mIdCategory;


    public SubcategoryLoaderManager(Context context, int idCategory) {
        super(context);
        mIdCategory = idCategory;
    }


    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllSubcategoryFromId(mIdCategory);
    }
}
