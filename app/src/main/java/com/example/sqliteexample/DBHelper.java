package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "userdata.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key,contact TEXT,dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Userdetails");
    }

    public Boolean insertUserDetails(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        long result=DB.insert("Userdetails",null,contentValues);
        return result != -1;
    }

    public Boolean updateUserDetails(String name,String contact,String dob){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);

        Cursor cursor=DB.rawQuery("SELECT * FROM Userdetails where name=?",new String[] {name});
        if(cursor.getCount()>0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[] {name});
            return result != -1;
        }
        else
            return false;
    }

    public Boolean deleteUserDetails(String name){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("SELECT * FROM Userdetails where name=?",new String[] {name});
        if(cursor.getCount()>0) {
            long result = DB.delete("Userdetails", "name=?", new String[] {name});
            return result != -1;
        }
        else
            return false;
    }

    public Cursor getData(){
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("SELECT * FROM Userdetails ",null);
        return cursor;
    }
}
