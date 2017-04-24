package com.jaime.inventory.presenter;

import android.content.Context;
import android.database.Cursor;

import com.jaime.inventory.database.DatabaseManager;
import com.jaime.inventory.interfaces.ProductPresenter;

/**
 * Created by usuario on 24/04/17.
 */

public class ProductPresenterImpl implements ProductPresenter{

    @Override
    public Cursor requestAllProduct(Context context) {
        return DatabaseManager.getInstance(context).getAllProduct();
    }
}
