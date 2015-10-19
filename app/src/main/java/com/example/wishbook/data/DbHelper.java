package com.example.wishbook.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wishbook.data.Contract.WishEntry;
import com.example.wishbook.model.Wish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manaday on 18/10/15.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "wishbook.db";

    private String wishEntryAllColumns[] = new String[]{
            WishEntry.COLUMN_WISH_ID,
            WishEntry.COLUMN_WISH_TITLE,
            WishEntry.COLUMN_WISH_DESC
    };

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WISH_TABLE = "CREATE TABLE " + WishEntry.TABLE_NAME + " (" +
                WishEntry._ID + " INTEGER PRIMARY KEY, " +
                WishEntry.COLUMN_WISH_TITLE + " TEXT NOT NULL, " +
                WishEntry.COLUMN_WISH_DESC + " TEXT);";

        db.execSQL(SQL_CREATE_WISH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addWish(Wish wish) {

        SQLiteDatabase db = this.getWritableDatabase();
        long result;

        ContentValues values = new ContentValues();
        values.put(WishEntry.COLUMN_WISH_TITLE, wish.getTitle());
        values.put(WishEntry.COLUMN_WISH_DESC, wish.getDescription());

        // Stores formula entry and returns row ID or -1 if error occurred
        result = db.insert(WishEntry.TABLE_NAME, null, values);

        values.clear();
        db.close();

        return (result != -1);
    }

    public Wish getWish(int id) {

        Wish wish = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(WishEntry.TABLE_NAME, wishEntryAllColumns, WishEntry.COLUMN_WISH_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor.moveToFirst()) {

            wish = new Wish(
                    cursor.getInt(cursor.getColumnIndex(WishEntry.COLUMN_WISH_ID)),
                    cursor.getString(cursor.getColumnIndex(WishEntry.COLUMN_WISH_TITLE)),
                    cursor.getString(cursor.getColumnIndex(WishEntry.COLUMN_WISH_DESC)));

        }

        cursor.close();
        db.close();

        return wish;
    }

    public Cursor getAllWish() {

        String selectQuery = "SELECT * FROM " + WishEntry.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

}
