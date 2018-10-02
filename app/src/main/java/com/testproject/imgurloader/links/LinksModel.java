package com.testproject.imgurloader.links;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.testproject.imgurloader.db.ImgurContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LinksModel implements LinksMVP.Model {

    @Inject
    Context mContext;

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
        int count = 0;
        do {
            linksList.add(linksCursor.getString(linksCursor.getColumnIndex(ImgurContract.LinksEntry.COLUMN_LINK)));
            linksCursor.moveToNext();
            count++;
        }
        while (!linksCursor.isLast());

        linksCursor.close();
        return Observable.fromIterable(linksList);
    }

    @Override
    public void addLink(String url) {
        ContentValues values = new ContentValues();
        values.put(ImgurContract.LinksEntry.COLUMN_LINK, url);
        mContext.getContentResolver().insert(ImgurContract.LinksEntry.CONTENT_URI, values);
    }
}
