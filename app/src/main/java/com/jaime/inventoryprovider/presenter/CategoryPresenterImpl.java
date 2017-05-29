package com.jaime.inventoryprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.jaime.inventoryprovider.Loaders.CategoryLoaderManager;
import com.jaime.inventoryprovider.database.DatabaseContract;
import com.jaime.inventoryprovider.database.DatabaseManager;
import com.jaime.inventoryprovider.interfaces.CategoryPresenter;
import com.jaime.inventoryprovider.pojo.Product;

/**
 * Created by jaime on 10/05/2017.
 */

public class CategoryPresenterImpl implements CategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CATEGORY = 2;

    private CategoryPresenter.View mView;
    private Context mContext;


    public CategoryPresenterImpl(CategoryPresenter.View view) {
        mView = view;
        mContext = view.getContext();
    }


    @Override
    public void requestAllCategory() {
        ((Activity) mContext).getLoaderManager().restartLoader(CATEGORY, null, this);
    }


    @Override
    public String[] requestCategoryColumnName() {
        return new String[] { DatabaseContract.CategoryEntry.COLUMN_NAME };
    }


    @Override
    public void petitionToAddProduct(Product product) {
        DatabaseManager.getInstance().addProduct(product);
    }


    @Override
    public void requestUpdateProduct(Product product) {
        DatabaseManager.getInstance().updateProduct(product);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Loader loader = null;

        switch (i) {
            case CATEGORY:
                loader = new CategoryLoaderManager(mContext);
                break;
        }

        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mView.setCursorCategory(cursor);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.setCursorCategory(null);
    }
}
