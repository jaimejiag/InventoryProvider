package com.jaime.inventory.database;

import android.content.Context;

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
}
