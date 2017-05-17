package com.jaime.inventory.database;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.jaime.inventory.pojo.Product;

/**
 * Esta clase es la clase DAO que gestiona la conexión a la base de datos y contiene los métodos
 * que se llamarán desde la aplicación para realizar las consultas.
 */

public class DatabaseManager {
    private static DatabaseManager mInstance;


    public DatabaseManager() {
        DatabaseHelper.getInstance();
    }


    public static synchronized DatabaseManager getInstance() {
        if (mInstance == null)
            throw new IllegalStateException("DatabaseManager is not initialized, call initialize");

        return mInstance;
    }


    public static synchronized void initialize(DatabaseManager databaseManager) {
        if (mInstance == null)
            mInstance = databaseManager;
    }


    /**
     * Método que devuelve todos los productos de la base de datos.
     * @return
     */
    public Cursor getAllProduct() {
        Cursor cursor = null;
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();

        try {
            cursor = db.query(DatabaseContract.ProductEntry.TABLE_NAME,
                    DatabaseContract.ProductEntry.ALL_COLUMNS, null, null, null, null, null);
        } catch (SQLiteException e) {

        }

        return cursor;
    }


    public Cursor getAllProductInnerJoin() {
        Cursor cursor = null;
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(DatabaseContract.ProductEntry.PRODUCT_JOIN_CATEGORY_SUBCATEGORY);
        cursor = queryBuilder.query(db, DatabaseContract.ProductEntry.PROJECTION_INNER,
                null, null, null, null, null);

        return cursor;
    }


    public Cursor getAllCategory() {
        Cursor cursor = null;
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();

        try {
            cursor = db.query(DatabaseContract.CategoryEntry.TABLE_NAME,
                    DatabaseContract.CategoryEntry.ALL_COLUMNS, null, null, null, null, null);
        } catch (SQLiteException e) {
            Log.e("SQLiteException", e.getMessage());
        }

        return cursor;
    }


    public Cursor getAllSubcategoryFromId(int idCategory) {
        Cursor cursor = null;
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        String selection = DatabaseContract.SubcategoryEntry.COLUMN_IDCATEGORY + " = ?";
        String[] selectionArgs = new String[] { String.valueOf(idCategory) };

        try {
            cursor = db.query(DatabaseContract.SubcategoryEntry.TABLE_NAME,
                    DatabaseContract.SubcategoryEntry.ALL_COLUMNS, selection, selectionArgs, null,
                    null, null);
        } catch (SQLiteException e) {
            Log.e("getAllSubcategoryFromId", e.getMessage());
        }

        return cursor;
    }


    public void addProduct(Product product) {
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        try {
            values.put(DatabaseContract.ProductEntry.COLUMN_SERIAL, product.getSerial());
            values.put(DatabaseContract.ProductEntry.COLUMN_SORTNAME, product.getSortname());
            values.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
            values.put(DatabaseContract.ProductEntry.COLUMN_CATEGORY, product.getCategory());
            values.put(DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY, product.getSubcategory());
            values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCTCLASS, product.getProductclass());

            db.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, values);
        } catch (SQLiteException e) {
            Log.e("Insert exception", e.getMessage());
        }
    }


    public void deleteProduct(int id) {
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        String whereClause = DatabaseContract.ProductEntry._ID + " = ?";
        String[] whereValue = new String[] { String.valueOf(id) };

        try {
            db.delete(DatabaseContract.ProductEntry.TABLE_NAME, whereClause, whereValue);
        } catch (SQLiteException e) {
            Log.e("Delete exception", e.getMessage());
        }
    }


    public void updateProduct(Product product) {
        SQLiteDatabase db = DatabaseHelper.getInstance().openDatabase();
        String whereClause = DatabaseContract.ProductEntry._ID + " = ?";
        String[] whereValues = new String[] { String.valueOf(product.getId()) };
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.ProductEntry.COLUMN_SERIAL, product.getSerial());
        values.put(DatabaseContract.ProductEntry.COLUMN_SORTNAME, product.getSortname());
        values.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, product.getDescription());
        values.put(DatabaseContract.ProductEntry.COLUMN_CATEGORY, product.getCategory());
        values.put(DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY, product.getSubcategory());

        try {
            db.update(DatabaseContract.ProductEntry.TABLE_NAME, values, whereClause, whereValues);
        } catch (SQLiteException e) {
            Log.e("Update exception", e.getMessage());
        }
    }
}
