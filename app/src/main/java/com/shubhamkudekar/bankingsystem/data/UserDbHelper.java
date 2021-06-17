package com.shubhamkudekar.bankingsystem.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.shubhamkudekar.bankingsystem.data.UserContract.UserEntry;

import androidx.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME="Users.db";

    public UserDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                UserEntry._ID + " INTEGER PRIMARY KEY," +
                UserEntry.COLUMN_NAME_USERNAME + " TEXT," +
                UserEntry.COLUMN_NAME_PHONE + " TEXT, "+
                UserEntry.COLUMN_NAME_BALANCE + " INTEGER,"+
                UserEntry.COLUMN_NAME_EMAIL + " TEXT UNIQUE)";
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Since the Database version is never changed nothing is done here
    }
}
