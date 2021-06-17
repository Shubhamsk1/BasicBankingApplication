package com.shubhamkudekar.bankingsystem.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.shubhamkudekar.bankingsystem.data.TransactionsContract.*;
import androidx.annotation.Nullable;



public class TransactionDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME="Transaction.db";

    public TransactionDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TransactionsEntry.TRANSACTION_TABLE_NAME + " (" +
                TransactionsEntry._ID + " INTEGER PRIMARY KEY, " +
                TransactionsEntry.TRANSACTION_COLUMN_FROM+ " TEXT," +
                TransactionsEntry.TRANSACTION_COLUMN_TO + " TEXT, "+
                TransactionsEntry.TRANSACTION_COLUMN_AMOUNT + " INTEGER )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //DATABASE VERSION IS NEVER CHANGED SO NO NEED TO UPDATE THIS
    }
}
