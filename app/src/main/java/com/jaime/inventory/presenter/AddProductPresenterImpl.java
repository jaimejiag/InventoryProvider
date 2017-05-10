package com.jaime.inventory.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.jaime.inventory.Loaders.CategoryLoaderManager;
import com.jaime.inventory.Loaders.SubcategoryLoaderManager;
import com.jaime.inventory.database.DatabaseContract;
import com.jaime.inventory.interfaces.AddProductPresenter;

/**
 * Created by jaime on 10/05/2017.
 */

public class AddProductPresenterImpl implements AddProductPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CATEGORY = 2;
    private static final int SUBCATEGORY = 3;

    private AddProductPresenter.View mView;
    private Context mContext;
    private int mIdCategory;


    public AddProductPresenterImpl(AddProductPresenter.View view) {
        mView = view;
        mContext = view.getContext();
    }


    @Override
    public void requestAllCategory() {
        ((Activity) mContext).getLoaderManager().initLoader(CATEGORY, null, this);
    }


    @Override
    public void requestSubcategorySelection(int idCategory) {
        ((Activity) mContext).getLoaderManager().initLoader(SUBCATEGORY, null, this);
        mIdCategory = idCategory;
    }


    @Override
    public String[] requestCategoryColumnName() {
        return new String[] { DatabaseContract.CategoryEntry.COLUMN_NAME };
    }


    @Override
    public String[] requestSubcategoryColumnName() {
        return new String[] { DatabaseContract.SubcategoryEntry.COLUMN_NAME };
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Loader loader = null;

        switch (i) {
            case CATEGORY:
                loader = new CategoryLoaderManager(mContext);
                break;

            case SUBCATEGORY:
                loader = new SubcategoryLoaderManager(mContext, mIdCategory);
                break;
        }

        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mView.setCursor(cursor);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.setCursor(null);
    }
}
