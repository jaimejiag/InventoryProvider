package com.jaime.inventory.database;

import android.provider.BaseColumns;

/**
 * Clase que contiene todas las constantes con las sentencias SQL que crean la base de datos
 * y realizan las operaciones de insercción de los datos.
 *
 * Por cada tabla de la BD se crea una clase interna que corresponde con cada tabla.
 *
 * Todas las clases internas heredan de BaseColumns porque tiene automáticamente
 * el id necesario para trabajar con SQLite.
 */

public class DatabaseContract {

    //Esta clase no puede ser instanciada
    private DatabaseContract() {

    }


    public static class  ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_SERIAL = "serial";
        public static final String COLUMN_SORTNAME = "sortname";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_SUBCATEGORY = "subcategory";
        public static final String COLUMN_PRODUCTCLASS = "productclass";

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INT NOT NULL, " +
                        "%s INT NOT NULL, " +
                        "%s INT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_SERIAL,
                COLUMN_SORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_CATEGORY,
                COLUMN_SUBCATEGORY,
                COLUMN_PRODUCTCLASS);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE %s", TABLE_NAME);

        public static final String SQL_INSERT_ENTRIES = String.format("INSERT INTO %s (%s, %s, %s, " +
                "%s, %s, %s) VALUES ('213351', 'carcacha', 'La carcachita que más quiero', 1, 1, 1)",
                TABLE_NAME,
                COLUMN_SERIAL,
                COLUMN_SORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_CATEGORY,
                COLUMN_SUBCATEGORY,
                COLUMN_PRODUCTCLASS);


        public static final String[] ALL_COLUMNS = new String[] {BaseColumns._ID, COLUMN_SERIAL, COLUMN_SORTNAME,
                COLUMN_DESCRIPTION, COLUMN_CATEGORY, COLUMN_SUBCATEGORY, COLUMN_PRODUCTCLASS};
    }


    public static class  CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SORTNAME = "sortname";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_SORTNAME,
                COLUMN_DESCRIPTION);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE %s", TABLE_NAME);

        public static final String[] ALL_COLUMNS = new String[] { BaseColumns._ID, COLUMN_NAME,
                COLUMN_SORTNAME, COLUMN_DESCRIPTION };
    }


    public static class  SubcategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "subcategory";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SORTNAME = "sortname";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IDCATEGORY = "idcategory";

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT UNIQUE NOT NULL, " +
                        "%s TEXT NOT NULL, " +
                        "%s INTEGER NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_SORTNAME,
                COLUMN_DESCRIPTION,
                COLUMN_IDCATEGORY);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE %s", TABLE_NAME);

        public static final String[] ALL_COLUMNS = new String[] { BaseColumns._ID, COLUMN_NAME,
                COLUMN_SORTNAME, COLUMN_DESCRIPTION, COLUMN_IDCATEGORY };
    }


    public static class  ProductClassEntry implements BaseColumns {
        public static final String TABLE_NAME = "productclass";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_DESCRIPTION);

        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE %s", TABLE_NAME);
    }
}
