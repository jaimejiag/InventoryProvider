package com.jaime.inventoryprovider.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.jaime.inventoryprovider.database.DatabaseContract;
import com.jaime.inventoryprovider.database.DatabaseHelper;

/**
 * Created by usuario on 18/05/17.
 */

public class InventoryProvider extends ContentProvider {
    private static final int PRODUCT = 1;
    private static final int PRODUCT_ID = 2;    //Al final de la Uri se pondrá el ID: content://.../'ID'
    private static final int CATEGORY = 3;
    private static final int CATEGORY_ID = 4;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    static {
        uriMatcher.addURI(InventoryContract.AUTHORITY, InventoryContract.Product.CONTENT_PATH, PRODUCT);
        uriMatcher.addURI(InventoryContract.AUTHORITY, InventoryContract.Product.CONTENT_PATH + "/#", PRODUCT_ID);
        uriMatcher.addURI(InventoryContract.AUTHORITY, InventoryContract.Category.CONTENT_PATH, CATEGORY);
        uriMatcher.addURI(InventoryContract.AUTHORITY, InventoryContract.Category.CONTENT_PATH + "/#", CATEGORY_ID);
    }

    private SQLiteDatabase sqliteDatabase;


    @Override
    public boolean onCreate() {
        sqliteDatabase = DatabaseHelper.getInstance().openDatabase();
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case PRODUCT:
                queryBuilder.setTables(DatabaseContract.ProductEntry.TABLE_NAME);

                //queryBuilder.setProjectionMap(InventoryContract.Product.sProductProjectionMap);

                break;

            case PRODUCT_ID:
                break;

            case CATEGORY:
                break;

            case CATEGORY_ID:
                break;
        }

        Log.d("Query", queryBuilder.buildQuery(projection, selection, null, null, sortOrder, null));
        cursor = queryBuilder.query(sqliteDatabase, projection, selection, selectionArgs, null, null, sortOrder);

        return cursor;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }



}
