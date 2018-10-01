package com.testproject.imgurloader.links;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;

import com.testproject.imgurloader.db.ImgurContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LinksModel implements LinksMVP.Model {

    @Inject
    private Context mContext;

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
}
