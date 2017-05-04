package com.jaime.inventory.database;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

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
            cursor = db.query(DatabaseContract.ProductEntry.TABLE_NAME, DatabaseContract.ProductEntry.ALL_COLUMNS, null, null, null, null, null);
        } catch (SQLiteException e) {

        } finally {
            //DatabaseHelper.getInstance().closeDatabase();
        }

        return cursor;
    }
}
