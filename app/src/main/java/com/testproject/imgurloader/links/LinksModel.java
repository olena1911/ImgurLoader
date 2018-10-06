package com.testproject.imgurloader.links;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.testproject.imgurloader.db.ImgurContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class LinksModel implements LinksMVP.Model {

    private Context mContext;

    public LinksModel(Context context) {
        mContext = context;
    }

    @Override
    public Observable<String> getLinks() {
        String[] projection = {
                ImgurContract.LinksEntry._ID,
                ImgurContract.LinksEntry.COLUMN_LINK};

        Cursor linksCursor =
                mContext.getContentResolver().query(
                        ImgurContract.LinksEntry.CONTENT_URI,
                        projection,
                        null,
                        null,
                        null);

        linksCursor.moveToFirst();

        List<String> linksList = new ArrayList<>();

        do {
            linksList.add(linksCursor.getString(linksCursor.getColumnIndex(ImgurContract.LinksEntry.COLUMN_LINK)));
            linksCursor.moveToNext();
        }
        while (!linksCursor.isLast());

        linksCursor.close();
        return Observable.fromIterable(linksList);
    }

    /**
     * Saves link in database.
     * @param url   link
     * @param deletehash    hash by which the image can be deleted
     */
    @Override
    public void addLink(String url, String deletehash) {
        ContentValues values = new ContentValues();
        values.put(ImgurContract.LinksEntry.COLUMN_LINK, url);
        values.put(ImgurContract.LinksEntry.COLUMN_DELETEHASH, deletehash);
        mContext.getContentResolver().insert(ImgurContract.LinksEntry.CONTENT_URI, values);
    }
}
