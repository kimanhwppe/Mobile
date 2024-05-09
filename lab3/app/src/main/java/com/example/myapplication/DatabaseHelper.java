package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "QLSV";
    // Contacts table name
    private static final String TABLE_SINHVIEN = "SINHVIEN";
    // Contacts Table Columns names
    private static final String KEY_MSSV = "mssv";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone";
    private static final String KEY_CLASS = "lop";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_SINHVIEN_TABLE = "CREATE TABLE " + TABLE_SINHVIEN + "("
                + KEY_MSSV + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_CLASS + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_SINHVIEN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SINHVIEN);
        onCreate(sqLiteDatabase);
    }
}