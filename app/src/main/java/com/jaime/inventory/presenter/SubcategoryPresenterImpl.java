package com.jaime.inventory.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.jaime.inventory.Loaders.SubcategoryLoaderManager;
import com.jaime.inventory.database.DatabaseContract;
import com.jaime.inventory.interfaces.SubcategoryPresenter;

/**
 * Created by usuario on 11/05/17.
 */

public class SubcategoryPresenterImpl implements SubcategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int SUBCATEGORY = 3;

    private SubcategoryPresenter.View mView;
    private Context mContext;
    private int mIdCategory;


    public SubcategoryPresenterImpl(SubcategoryPresenter.View view) {
        mView = view;
        mContext = view.getContext();
    }


    @Override
    public void requestSubcategorySelection(int idCategory) {
        mIdCategory = idCategory;
        ((Activity) mContext).getLoaderManager().restartLoader(SUBCATEGORY, null, this);
    }


    @Override
    public String[] requestSubcategoryColumnName() {
        return new String[] { DatabaseContract.SubcategoryEntry.COLUMN_NAME };
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader loader = null;

        switch (id) {
            case SUBCATEGORY:
                loader = new SubcategoryLoaderManager(mContext, mIdCategory);
                break;
        }

        return loader;
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mView.setCursorSubcategory(data);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mView.setCursorSubcategory(null);
    }
}
