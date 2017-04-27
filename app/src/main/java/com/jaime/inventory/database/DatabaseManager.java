package com.jaime.inventory.database;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Esta clase es la clase DAO que gestiona la conexión a la base de datos y contiene los métodos
 * que se llamarán desde la aplicación para realizar las consultas.
 */

public class DatabaseManager {
    private static DatabaseManager mInstance;


    public DatabaseManager() {
        DatabaseHelper.getInstance();
    }


    public static DatabaseManager getInstance() {
        if (mInstance == null)
            mInstance = new DatabaseManager();

        return mInstance;
    }


    public Cursor getAllProduct() {
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        Cursor cursor;

        cursor = db.rawQuery(DatabaseContract.ProductEntry.SQL_SELECT_ENTRIES, null);
        return cursor;
    }
}
