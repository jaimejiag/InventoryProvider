package com.jaime.inventoryprovider.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import com.jaime.inventoryprovider.database.DatabaseContract;

import java.util.HashMap;

/**
 * Created by usuario on 18/05/17.
 */

public final class InventoryContract {
    public static final String AUTHORITY = "com.jaime.inventoryprovider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);


    private InventoryContract() {

    }


    public static class Product implements BaseColumns {
        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);

        public static final String SERIAL = "serial";
        public static final String SORTNAME = "sortname";
        public static final String DESCRIPTION = "description";
        public static final String CATEGORY = "category";
        public static final String SUBCATEGORY = "subcategory";
        public static final String PRODUCTCLASS = "productclass";

        public static final String[] PROJECTION = new String[] { BaseColumns._ID,
                SERIAL, SORTNAME, DESCRIPTION, CATEGORY, SUBCATEGORY, PRODUCTCLASS };

      /*  public static final HashMap<String, String> sProductProjectionMap = new HashMap<>();
        static {
            sProductProjectionMap.put(CONTENT_PATH + "." + _ID, DatabaseContract.ProductEntry._ID);
            sProductProjectionMap.put(CONTENT_PATH + "." + SERIAL, DatabaseContract.ProductEntry.COLUMN_SERIAL);
            sProductProjectionMap.put(CONTENT_PATH + "." + SORTNAME, DatabaseContract.ProductEntry.COLUMN_SORTNAME);
            sProductProjectionMap.put(CONTENT_PATH + "." + DESCRIPTION, DatabaseContract.ProductEntry.COLUMN_DESCRIPTION);
            sProductProjectionMap.put(CONTENT_PATH + "." + CATEGORY, DatabaseContract.ProductEntry.COLUMN_CATEGORY);
            sProductProjectionMap.put(CONTENT_PATH + "." + SUBCATEGORY, DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY);
            sProductProjectionMap.put(CONTENT_PATH + "." + PRODUCTCLASS, DatabaseContract.ProductEntry.COLUMN_PRODUCTCLASS);
        }*/
    }


    public static class Category implements BaseColumns {
        public static final String CONTENT_PATH = "category";
    }
}
