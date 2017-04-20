package com.jaime.inventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jaime.inventory.InventoryApplication;

/**
 * Created by usuario on 20/04/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
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

        db.execSQL(DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DatabaseContract.SubcategoryEntry.SQL_CREATE_ENTRIES);
        db.execSQL(DatabaseContract.ProductClassEntry.SQL_CREATE_ENTRIES);

        db.endTransaction();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();

        db.execSQL(DatabaseContract.ProductEntry.SQL_DELETE_ENTRIES);
        db.execSQL(DatabaseContract.CategoryEntry.SQL_DELETE_ENTRIES);
        db.execSQL(DatabaseContract.SubcategoryEntry.SQL_DELETE_ENTRIES);
        db.execSQL(DatabaseContract.ProductClassEntry.SQL_DELETE_ENTRIES);

        db.endTransaction();
        onCreate(db);
    }


    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, newVersion, oldVersion);
    }
}
