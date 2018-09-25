package com.testproject.imgurloader.gallery;

public class GalleryModel implements GalleryMVP.Model {

    private Repository repository;

    public GalleryModel(Repository repository) {
        this.repository = repository;
    }
}
