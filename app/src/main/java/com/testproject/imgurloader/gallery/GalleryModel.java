package com.testproject.imgurloader.gallery;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class GalleryModel implements GalleryMVP.Model {

    private Repository repository;

    public GalleryModel(Repository repository) {
        this.repository = repository;
    }

    // TODO remove context
    @Override
    public Observable<String> getPhotoPaths(Context context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, // Which columns to return
                null,       // Return all rows
                null,
                MediaStore.Images.Media.DEFAULT_SORT_ORDER);
        cursor.moveToFirst();
        // TODO change to cursor
        List<String> pathsList = new ArrayList<>();
        do {
            pathsList.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            cursor.moveToNext();
        }
            while (!cursor.isLast());
        return Observable.fromIterable(pathsList);
    }

}
