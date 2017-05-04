package com.jaime.inventory.database;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jaime.inventory.InventoryApplication;

/**
 * Created by usuario on 20/04/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
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
            Log.d("Creation", DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.SubcategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.ProductClassEntry.SQL_CREATE_ENTRIES);

            db.execSQL(DatabaseContract.ProductEntry.SQL_INSERT_ENTRIES);
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
}
