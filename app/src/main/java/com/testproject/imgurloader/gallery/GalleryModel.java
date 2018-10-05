package com.testproject.imgurloader.gallery;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GalleryModel implements GalleryMVP.Model {

    private Context mContext;

    public GalleryModel(Context context) {
        mContext = context;
    }

    @Override
    public Observable<String> getPhotoPaths() {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = mContext.getContentResolver().query(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                MediaStore.Images.Media.DEFAULT_SORT_ORDER);

        List<String> pathsList = new ArrayList<>();
        cursor.moveToFirst();
        do {
            pathsList.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            cursor.moveToNext();
        }
        while (!cursor.isLast());

        cursor.close();
        return Observable.fromIterable(pathsList);
    }

}
