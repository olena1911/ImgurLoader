package com.testproject.imgurloader.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Class-provider to work with imgurloader.db.
 */
public class ImgurProvider extends ContentProvider {

    public static final String LOG_TAG = ImgurProvider.class.getSimpleName();

    private static final int LINKS = 100;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(ImgurContract.CONTENT_AUTHORITY, ImgurContract.PATH_LINKS, LINKS);
    }

    private ImgurDbHelper mImgurDbHelper;

    @Override
    public boolean onCreate() {
        mImgurDbHelper = new ImgurDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mImgurDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case LINKS:
                cursor = database.query(ImgurContract.LinksEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                Log.e(LOG_TAG, "Cannot query unknown URI " + uri);
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LINKS:
                return insertLink(uri, contentValues);
            default:
                Log.e(LOG_TAG, "Insertion is not supported for " + uri);
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertLink(Uri uri, ContentValues values) {
        SQLiteDatabase database = mImgurDbHelper.getWritableDatabase();

        long id = database.insert(ImgurContract.LinksEntry.TABLE_NAME, null, values);

        // If the ID is -1, then the insertion failed. Log an error and return null.
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        // Return the new URI with the ID (of the newly inserted row) appended at the end
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LINKS:
                return updateLink(uri, contentValues, selection, selectionArgs);
            default:
                Log.e(LOG_TAG, "Update is not supported for " + uri);
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateLink(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mImgurDbHelper.getWritableDatabase();
        int rowsUpdated = database.update(ImgurContract.LinksEntry.TABLE_NAME, values, selection, selectionArgs);
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mImgurDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LINKS:
                rowsDeleted = database.delete(ImgurContract.LinksEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                Log.e(LOG_TAG, "Deletion is not supported for " + uri);
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LINKS:
                return ImgurContract.LinksEntry.CONTENT_LIST_TYPE;
            default:
                Log.e(LOG_TAG, "Unknown URI " + uri + " with match " + match);
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}