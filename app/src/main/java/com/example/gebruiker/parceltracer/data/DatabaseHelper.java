package com.example.gebruiker.parceltracer.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gebruiker.parceltracer.data.DatabaseContract.TrackingEntry;
import com.example.gebruiker.parceltracer.data.DatabaseContract.CheckpointEntry;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "parceltracer.db";

    public DatabaseHelper(Context context) {
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TRACKING_TABLE =
                "CREATE TABLE " + TrackingEntry.TABLE_NAME + " (" +
                TrackingEntry._ID + "INTEGER PRIMARY KEY, " +
                TrackingEntry.COLUMN_TRACKING_ID + "TEXT UNIQUE NOT NULL, " +
                TrackingEntry.COLUMN_TRACKING_NUMBER + "TEXT UNIQUE NOT NULL, " +
                TrackingEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
                TrackingEntry.COLUMN_SLUG + "TEXT NOT NULL, " +
                TrackingEntry.COLUMN_CUSTOMER + "TEXT NOT NULL, " +
                TrackingEntry.COLUMN_CATEGORY + "TEXT NOT NULL, " +
                TrackingEntry.COLUMN_TAG + "TEXT NOT NULL, " +
                TrackingEntry.COLUMN_NOTE + "TEXT, " +
                TrackingEntry.COLUMN_ORIGIN_ISO3 + "TEXT, " +
                TrackingEntry.COLUMN_DESTINATION_ISO3 + "TEXT, " +
                TrackingEntry.COLUMN_EXPECTED_DELIVERY + "TEXT, " +
                TrackingEntry.COLUMN_DELIVERY_DATE + "TEXT, " +
                TrackingEntry.COLUMN_SIGNED_BY + "TEXT" +
                " );";

        final String SQL_CREATE_CHECKPOINT_TABLE =
                "CREATE TABLE " + CheckpointEntry.TABLE_NAME + " (" +
                CheckpointEntry._ID + "INTEGER PRIMARY KEY, " +
                CheckpointEntry.COLUMN_DATE + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_SLUG + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_MESSAGE + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_TAG + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_CITY + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_ZIP + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_STATE + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_COUNTRY_ISO3 + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_COUNTRY + "TEXT NOT NULL, " +
                CheckpointEntry.COLUMN_TRACKING_KEY + "TEXT NOT NULL, " +
                "FOREIGN KEY (" + CheckpointEntry.COLUMN_TRACKING_KEY + ") " +
                "REFERENCES " + TrackingEntry.TABLE_NAME + " (" + TrackingEntry._ID + ");";

        sqLiteDatabase.execSQL(SQL_CREATE_TRACKING_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CHECKPOINT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TrackingEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CheckpointEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}