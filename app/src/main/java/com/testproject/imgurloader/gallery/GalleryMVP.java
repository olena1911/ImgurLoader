package com.testproject.imgurloader.gallery;

import android.content.Context;

import io.reactivex.Observable;

public interface GalleryMVP {

    interface Model {
        Observable<String> getPhotoPaths(Context context);
    }

    interface View {
        void updateData(String path);
    }

    interface Presenter {
        void setView(GalleryMVP.View view);
        void loadPicturesFromGallery(Context context);
        void rxUnsubscribe();
    }
}
