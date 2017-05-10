package com.jaime.inventory.interfaces;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by jaime on 10/05/2017.
 */

public interface AddProductPresenter {
    interface View {
        Context getContext();
        void setCursor(Cursor cursor);
    }

    void requestAllCategory();
    void requestSubcategorySelection(int idCategory);
    String[] requestCategoryColumnName();
    String[] requestSubcategoryColumnName();
}
