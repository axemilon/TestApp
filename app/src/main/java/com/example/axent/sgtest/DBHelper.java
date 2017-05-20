package com.example.axent.sgtest;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;

/**
 * Created by aXenT on 17.05.2017.
 */

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UsersDB";
    public static final String TABLE_USERS = "Users";

    public static final String KEY_ID = "_id";
    public static final String KEY_MAIL = "mail";
    public static final String KEY_PASSWORD = "password";


    public DBHelper(Context context, String name, int version) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USERS + "("
                + KEY_ID + " integer primary key,"
                + KEY_MAIL + " text,"
                + KEY_PASSWORD + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int checkMail(String mail){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT mail FROM Users WHERE mail = '"+mail+"'", null);
        return cursor.getCount();
    }
    public String getPassword(String mail) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT password FROM Users WHERE mail = '"+mail+"'", null);
        cursor.moveToFirst();
        int passwordColIndex = cursor.getColumnIndex(this.KEY_PASSWORD);
        return cursor.getString(passwordColIndex);
    }
}
