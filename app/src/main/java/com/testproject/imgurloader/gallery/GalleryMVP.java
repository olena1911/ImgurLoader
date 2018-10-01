package com.testproject.imgurloader.gallery;

import io.reactivex.Observable;

public interface GalleryMVP {

    interface Model {
        Observable<String> getPhotoPaths();
    }

    interface View {
        void updateData(String path);
    }

    interface Presenter {
        void setView(GalleryMVP.View view);
        void loadPicturesFromGallery();
        void rxUnsubscribe();
    }
}
