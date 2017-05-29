package com.jaime.inventoryprovider.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.jaime.inventoryprovider.database.DatabaseManager;
import com.jaime.inventoryprovider.interfaces.ProductPresenter;
import com.jaime.inventoryprovider.provider.InventoryContract;

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
    public void requestDeleteProduct(int id) {
        DatabaseManager.getInstance().deleteProduct(id);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case PRODUCT:
                loader = new CursorLoader(mContext, InventoryContract.Product.CONTENT_URI, InventoryContract.Product.PROJECTION,
                        null, null, null);
                break;
        }

        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.setNotificationUri(mContext.getContentResolver(), InventoryContract.Product.CONTENT_URI);
        mView.setCursor(cursor);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.setCursor(null);
    }
}
