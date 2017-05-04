package com.jaime.inventory.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.jaime.inventory.Loaders.ProductLoaderManager;
import com.jaime.inventory.interfaces.ProductPresenter;

/**
 * Created by usuario on 24/04/17.
 */

public class ProductPresenterImpl implements ProductPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private final static int PRODUCT = 1;
    private ProductPresenter.View mView;
    private Context mContext;


    public ProductPresenterImpl(ProductPresenter.View  view) {
        mView = view;
        mContext = view.getContext();
    }


    @Override
    public void requestAllProduct() {
        ((Activity) mView.getContext()).getLoaderManager().initLoader(PRODUCT, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case PRODUCT:
                loader = new ProductLoaderManager(mContext);
                break;
        }

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mView.setCursor(data);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.setCursor(null);

    }
}
