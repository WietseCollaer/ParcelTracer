package com.example.gebruiker.parceltracer.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "parceltracer.db";

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TRACKING_TABLE =
                "CREATE TABLE " + DatabaseContract.TrackingEntry.TABLE_NAME + " (" +
                DatabaseContract.TrackingEntry.COLUMN_TRACKING_ID + "TEXT PRIMARY KEY, " +
                DatabaseContract.TrackingEntry.COLUMN_TRACKING_NUMBER + "TEXT UNIQUE NOT NULL, " +
                DatabaseContract.TrackingEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
                DatabaseContract.TrackingEntry.COLUMN_SLUG + "TEXT NOT NULL, " +
                DatabaseContract.TrackingEntry.COLUMN_CUSTOMER + "TEXT NOT NULL, " +
                DatabaseContract.TrackingEntry.COLUMN_CATEGORY + "TEXT NOT NULL, " +
                DatabaseContract.TrackingEntry.COLUMN_TAG + "TEXT NOT NULL, " +
                DatabaseContract.TrackingEntry.COLUMN_NOTE + "TEXT, " +
                DatabaseContract.TrackingEntry.COLUMN_ORIGIN_ISO3 + "TEXT, " +
                DatabaseContract.TrackingEntry.COLUMN_DESTINATION_ISO3 + "TEXT, " +
                DatabaseContract.TrackingEntry.COLUMN_EXPECTED_DELIVERY + "TEXT, " +
                DatabaseContract.TrackingEntry.COLUMN_DELIVERY_DATE + "TEXT, " +
                DatabaseContract.TrackingEntry.COLUMN_SIGNED_BY + "TEXT" +
                " );";

        final String SQL_CREATE_CHECKPOINT_TABLE =
                "CREATE TABLE " + DatabaseContract.CheckpointEntry.TABLE_NAME + " (" +
                DatabaseContract.CheckpointEntry._ID + "INTEGER PRIMARY KEY, " +
                DatabaseContract.CheckpointEntry.COLUMN_DATE + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_SLUG + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_MESSAGE + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_TAG + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_CITY + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_ZIP + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_STATE + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_COUNTRY_ISO3 + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_COUNTRY + "TEXT NOT NULL, " +
                DatabaseContract.CheckpointEntry.COLUMN_TRACKING_KEY + "TEXT NOT NULL, " +
                "FOREIGN KEY (" + DatabaseContract.CheckpointEntry.COLUMN_TRACKING_KEY + ") " +
                "REFERENCES " + DatabaseContract.TrackingEntry.TABLE_NAME + " (" + DatabaseContract.TrackingEntry._ID + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_TRACKING_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CHECKPOINT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TrackingEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.CheckpointEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}