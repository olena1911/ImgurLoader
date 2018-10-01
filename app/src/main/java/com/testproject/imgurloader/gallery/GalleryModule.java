package com.testproject.imgurloader.gallery;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GalleryModule {

    @Provides
    public GalleryMVP.Presenter provideGalleryPresenter(GalleryMVP.Model galleryModel) {
        return new GalleryPresenter(galleryModel);
    }

    @Provides
    public GalleryMVP.Model provideGalleryModel() {
        return new GalleryModel();
    }

}
