package com.jaime.inventory.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jaime.inventory.InventoryApplication;


/**
 * Created by usuario on 20/04/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NAME = "inventory.db";
    private static DatabaseHelper mInstance;
    private SQLiteDatabase mDatabase;


    public DatabaseHelper() {
        super(InventoryApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static DatabaseHelper getInstance() {
        if (mInstance == null)
            mInstance = new DatabaseHelper();

        return mInstance;
    }


    public SQLiteDatabase openDatabase() {
        mDatabase = getWritableDatabase();
        return mDatabase;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.SubcategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.ProductClassEntry.SQL_CREATE_ENTRIES);

            db.execSQL(DatabaseContract.ProductEntry.SQL_INSERT_ENTRIES);
            CategoryInsertMock(db);
            SubcategoryInsertMock(db);

            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("ERROR", "Mal creación de tablas");
        } finally {
            db.endTransaction();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.beginTransaction();

            db.execSQL(DatabaseContract.ProductEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.CategoryEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.SubcategoryEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.ProductClassEntry.SQL_DELETE_ENTRIES);

            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("DATABASE ERROR", e.getLocalizedMessage());
        } finally {
            db.endTransaction();
        }
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, newVersion, oldVersion);
    }


    public void closeDatabase() {
        mInstance.closeDatabase();
    }


    private void CategoryInsertMock(SQLiteDatabase db) {
        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s) VALUES ('Televiones', 'TV', " +
                        "'Pantallacas para tu cuerpesito')",
                DatabaseContract.CategoryEntry.TABLE_NAME,
                DatabaseContract.CategoryEntry.COLUMN_NAME,
                DatabaseContract.CategoryEntry.COLUMN_SORTNAME,
                DatabaseContract.CategoryEntry.COLUMN_DESCRIPTION));

        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s) VALUES ('Consolas', 'CSL', " +
                        "'Videoconsolas para tu cabesa')",
                DatabaseContract.CategoryEntry.TABLE_NAME,
                DatabaseContract.CategoryEntry.COLUMN_NAME,
                DatabaseContract.CategoryEntry.COLUMN_SORTNAME,
                DatabaseContract.CategoryEntry.COLUMN_DESCRIPTION));
    }


    private void SubcategoryInsertMock(SQLiteDatabase db) {
        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('Samsung', 'SMNG', " +
                        "'Pantalla Samsung', 1)",
                DatabaseContract.SubcategoryEntry.TABLE_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_SORTNAME,
                DatabaseContract.SubcategoryEntry.COLUMN_DESCRIPTION,
                DatabaseContract.SubcategoryEntry.COLUMN_IDCATEGORY));

        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('Philips', 'PHLS', " +
                        "'Pantalla Philips', 1)",
                DatabaseContract.SubcategoryEntry.TABLE_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_SORTNAME,
                DatabaseContract.SubcategoryEntry.COLUMN_DESCRIPTION,
                DatabaseContract.SubcategoryEntry.COLUMN_IDCATEGORY));

        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('Xbox', 'XB', " +
                        "'Videoconsola Xbox', 2)",
                DatabaseContract.SubcategoryEntry.TABLE_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_SORTNAME,
                DatabaseContract.SubcategoryEntry.COLUMN_DESCRIPTION,
                DatabaseContract.SubcategoryEntry.COLUMN_IDCATEGORY));

        db.execSQL(String.format("INSERT INTO %s (%s, %s, %s, %s) VALUES ('PlayStation', 'PS', " +
                        "'Videoconsola PlayStation', 2)",
                DatabaseContract.SubcategoryEntry.TABLE_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_NAME,
                DatabaseContract.SubcategoryEntry.COLUMN_SORTNAME,
                DatabaseContract.SubcategoryEntry.COLUMN_DESCRIPTION,
                DatabaseContract.SubcategoryEntry.COLUMN_IDCATEGORY));
    }
}
