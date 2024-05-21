package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseAdapter {

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

    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    private final Context context;

    public DatabaseAdapter(Context ctx) {
        this.context = ctx;
    }
    public DatabaseAdapter open() {
        dbHelper = new DatabaseHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    public void addSV(int mssv, String name, String phone, String lop){
        ContentValues cv = new ContentValues();
        cv.put(KEY_MSSV,mssv);
        cv.put(KEY_NAME,name);
        cv.put(KEY_PH_NO,phone);
        cv.put(KEY_CLASS,lop);
        sqLiteDatabase.insert(TABLE_SINHVIEN,null, cv);
    }

    public SinhVien selectSV(int mssv){
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_SINHVIEN
                + " WHERE " + KEY_MSSV + "=" + mssv, null);
        if (c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String phone = c.getString(2);
            String lop = c.getString(3);
            return new SinhVien(id,name,phone,lop);
        }
        else
            return null;
    }

    public ArrayList<SinhVien> selectAllSV(){
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_SINHVIEN, null);
        ArrayList<SinhVien> DSLop = new ArrayList<SinhVien>();
        while (c.moveToNext()){
            int id = c.getInt(0);
            String name = c.getString(1);
            String phone = c.getString(2);
            String lop = c.getString(3);
            SinhVien newSV = new SinhVien(id,name,phone,lop);
            DSLop.add(newSV);
        }
        return DSLop;
    }

    public int updateSV(SinhVien sv){

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,sv.getName());
        cv.put(KEY_PH_NO,sv.getPhone());
        cv.put(KEY_CLASS,sv.getLop());

        int kq = sqLiteDatabase.update(TABLE_SINHVIEN, cv, "mssv = ?", new String[]{Integer.toString(sv.getMssv())});
        return kq;
    }

    public int deleteSV(SinhVien sv){
        int id = sv.getMssv();
        return sqLiteDatabase.delete(TABLE_SINHVIEN, "mssv =" + id, null);
    }

    public boolean deleteAllUsers() {
        return sqLiteDatabase.delete(TABLE_SINHVIEN, null, null) > 0;

    }
}
