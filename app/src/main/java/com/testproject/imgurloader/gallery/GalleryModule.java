package com.testproject.imgurloader.gallery;

import android.content.Context;

import com.testproject.imgurloader.api.ImgurApi;
import com.testproject.imgurloader.links.LinksMVP;
import com.testproject.imgurloader.upload.UploadService;

import dagger.Module;
import dagger.Provides;

@Module
public class GalleryModule {

    @Provides
    public GalleryMVP.Presenter provideGalleryPresenter(GalleryMVP.Model galleryModel, UploadService uploadService) {
        return new GalleryPresenter(galleryModel, uploadService);
    }

    @Provides
    public GalleryMVP.Model provideGalleryModel(Context context) {
        return new GalleryModel(context);
    }

    @Provides
    public UploadService provideUploadService(LinksMVP.Presenter linksPresenter, ImgurApi imgurApi) {
        return new UploadService(linksPresenter, imgurApi);
    }
}
