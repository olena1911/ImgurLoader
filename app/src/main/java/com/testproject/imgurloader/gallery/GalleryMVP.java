package com.testproject.imgurloader.gallery;

public interface GalleryMVP {

    interface Model {

    }

    interface View {

    }

    interface Presenter {
        void setView(GalleryMVP.View view);
    }
}
