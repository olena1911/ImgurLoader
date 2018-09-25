package com.testproject.imgurloader.gallery;

public class GalleryPresenter implements GalleryMVP.Presenter {

    private GalleryMVP.Model model;
    private GalleryMVP.View view;

    public GalleryPresenter(GalleryMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(GalleryMVP.View view) {
        this.view = view;
    }
}
