package com.testproject.imgurloader.gallery;

import io.reactivex.Observable;

/**
 * Declares Gallery MVP interfaces.
 */
public interface GalleryMVP {

    interface Model {
        Observable<String> getPhotoPaths();
    }

    interface View {
        void updateData(String path);
        void showLoadingSpinner(int position);
        void hideLoadingSpinner(int position);
    }

    interface Presenter {
        void setView(GalleryMVP.View view);
        void loadPicturesFromGallery();
        void onItemClicked(int position, String path);
        void onImageUploaded(int position);
        void rxUnsubscribe();
    }
}
