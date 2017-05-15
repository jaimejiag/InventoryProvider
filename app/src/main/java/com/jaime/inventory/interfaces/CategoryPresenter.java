package com.jaime.inventory.interfaces;

import android.content.Context;
import android.database.Cursor;

import com.jaime.inventory.pojo.Product;

/**
 * Created by jaime on 10/05/2017.
 */

public interface CategoryPresenter {
    interface View {
        Context getContext();
        void setCursorCategory(Cursor cursor);
    }

    void requestAllCategory();
    String[] requestCategoryColumnName();
    void petitionToAddProduct(Product product);
    void requestUpdateProduct(Product product);
}
