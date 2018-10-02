package com.testproject.imgurloader.gallery;

import android.content.Context;

import com.testproject.imgurloader.api.ImgurApi;
import com.testproject.imgurloader.upload.UploadService;

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
    public GalleryMVP.Model provideGalleryModel(Context context) {
        return new GalleryModel(context);
    }

    @Provides
    public UploadService provideUploadService(ImgurApi imgurApi) {
        return new UploadService(imgurApi);
    }
}
