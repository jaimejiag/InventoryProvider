package com.jaime.inventory.interfaces;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 24/04/17.
 */

public interface ProductPresenter {
    interface View {
        Context getContext();
        void setCursor(Cursor cursor);
    }

    void requestAllProduct();
    void requestDeleteProduct(int id);
}
