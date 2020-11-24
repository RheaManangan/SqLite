package com.example.studentinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(studentid TEXT primary key,fname TEXT , address TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {

        DB.execSQL("drop Table if exists Userdetails");

    }


    public boolean insertuserdata(String studentid, String fname, String address) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentid", studentid);
        contentValues.put("fname", fname);
        contentValues.put("address", address);
        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }

    public boolean updateuserdata(String studentid, String fname, String address) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("address", address);
        Cursor cursor = DB.rawQuery("Select* from Userdetails where studentid= ?", new String[]{studentid});
        if (cursor.getCount() > 0) {


            long result = DB.update("Userdetails", contentValues, "studentid=?", new
                    String[]{studentid});

            if (result == -1) {
                return false;

            } else {
                return true;
            }

        } else {
            return false;
        }
    }




        public boolean deletedata(String studentid) {

            SQLiteDatabase DB = this.getWritableDatabase();
            Cursor cursor = DB.rawQuery("Select* from Userdetails where studentid= ?", new String[]{studentid});
            if (cursor.getCount()>0){


                long result = DB.delete("Userdetails","studentid=?",new
                        String[]{studentid});

                if (result == -1) {
                    return false;

                } else {
                    return true;
                }

            }else {
                return false;
            }

    }




    public Cursor getdata() {

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;

    }
























}