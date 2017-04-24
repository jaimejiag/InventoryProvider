package com.jaime.inventory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Esta clase es la clase DAO que gestiona la conexión a la base de datos y contiene los métodos
 * que se llamarán desde la aplicación para realizar las consultas.
 */

public class DatabaseManager {
    private Context mContext;
    private static DatabaseManager mInstance;


    public DatabaseManager() {
        DatabaseHelper.getInstance();
    }


    public static DatabaseManager getInstance(Context context) {
        if (mInstance == null)
            mInstance = new DatabaseManager();

        return mInstance;
    }


    public void insertProduct() {
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.ProductEntry.COLUMN_SERIAL, "1246124");
        values.put(DatabaseContract.ProductEntry.COLUMN_SORTNAME, "carcacha");
        values.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, "La carcachita que más quiero");
        values.put(DatabaseContract.ProductEntry.COLUMN_CATEGORY, "Coche");
        values.put(DatabaseContract.ProductEntry.COLUMN_SUBCATEGORY, "Cochecito");
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCTCLASS, "Compuesto");

        db.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, values);
    }


    public Cursor getAllProduct() {
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        Cursor cursor;

        cursor = db.rawQuery(DatabaseContract.ProductEntry.SQL_SELECT_ENTRIES, null);
        return cursor;
    }
}
