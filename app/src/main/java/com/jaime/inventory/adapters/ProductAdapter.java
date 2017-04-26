package com.jaime.inventory.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.jaime.inventory.database.DatabaseManager;

/**
 * Created by usuario on 24/04/17.
 */

public class ProductAdapter extends CursorLoader {
    private Context mContext;


    public ProductAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance(mContext).getAllProduct();
    }



}
