package com.jaime.inventory.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

/**
 * Created by usuario on 24/04/17.
 */

public class ProductAdapter extends CursorLoader {

    public ProductAdapter(Context context) {
        super(context);
    }


    @Override
    public Cursor loadInBackground() {
        return super.loadInBackground();
    }
}
