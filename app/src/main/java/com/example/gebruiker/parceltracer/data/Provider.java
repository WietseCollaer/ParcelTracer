package com.example.gebruiker.parceltracer.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import io.reactivex.annotations.Nullable;

import com.example.gebruiker.parceltracer.data.DatabaseContract.TrackingEntry;
import com.example.gebruiker.parceltracer.data.DatabaseContract.CheckpointEntry;

public class Provider extends ContentProvider {
    private static final int TRACKING = 100;
    private static final int CHECKPOINT = 200;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DatabaseHelper mDbHelper;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DatabaseContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, DatabaseContract.PATH_TRACKING, TRACKING);
        matcher.addURI(authority, DatabaseContract.PATH_CHECKPOINT, CHECKPOINT);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TRACKING:
                return TrackingEntry.CONTENT_TYPE;
            case CHECKPOINT:
                return CheckpointEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;

        switch (sUriMatcher.match(uri)) {
            case TRACKING:
                retCursor = mDbHelper.getReadableDatabase().query(
                        TrackingEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            case CHECKPOINT:
                retCursor = mDbHelper.getReadableDatabase().query(
                        CheckpointEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case TRACKING: {
                long _id = db.insert(TrackingEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = TrackingEntry.buildTrackingUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case CHECKPOINT: {
                long _id = db.insert(CheckpointEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = CheckpointEntry.buildCheckpointUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Nullable
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowsDeleted;

        switch (sUriMatcher.match(uri)) {
            case TRACKING:
                rowsDeleted = db.delete(TrackingEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case CHECKPOINT:
                rowsDeleted = db.delete(CheckpointEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (selection == null || rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Nullable
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowsUpdated;

        switch (sUriMatcher.match(uri)) {
            case TRACKING:
                rowsUpdated = db.update(TrackingEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CHECKPOINT:
                rowsUpdated = db.update(TrackingEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0)
            getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}