package com.example.mylab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "film.db";
    public static final String TABLE_NAME = "film_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FILM_TITLE";
    public static final String COL_3 = "FILM_DESCRIPTION";
    public static final String COL_4 = "FILM_YEAR";
    public static final String COL_5 = "FILM_RATE";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "FILM_TITLE TEXT, FILM_DESCRIPTION TEXT, FILM_YEAR TEXT, FILM_RATE TEXT)";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String filmTitle, String filmDescription, String filmYear, String rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, filmTitle);
        contentValues.put(COL_3, filmDescription);
        contentValues.put(COL_4, filmYear);
        contentValues.put(COL_5, rate);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor ShowData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
