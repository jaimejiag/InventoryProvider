package com.jaime.inventoryprovider.interfaces;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by usuario on 11/05/17.
 */

public interface SubcategoryPresenter {
    interface View {
        Context getContext();
        void setCursorSubcategory(Cursor cursor);
    }

    void requestSubcategorySelection(int idCategory);
    String[] requestSubcategoryColumnName();
}
