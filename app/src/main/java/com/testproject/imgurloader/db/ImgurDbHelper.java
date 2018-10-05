package com.testproject.imgurloader.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.testproject.imgurloader.db.ImgurContract.LinksEntry;

public class ImgurDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ImgurDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "imgurloader.db";

    private static final int DATABASE_VERSION = 1;

    public ImgurDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_LINKS_TABLE =  "CREATE TABLE " + LinksEntry.TABLE_NAME + " ("
                + LinksEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LinksEntry.COLUMN_LINK + " TEXT NOT NULL, "
                + LinksEntry.COLUMN_DELETEHASH + " TEXT NOT NULL)";

        db.execSQL(SQL_CREATE_LINKS_TABLE);
        Log.i(LOG_TAG, "Links table is created");;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}