package com.testproject.imgurloader.root;

import com.testproject.imgurloader.api.ApiModule;
import com.testproject.imgurloader.gallery.GalleryActivity;
import com.testproject.imgurloader.gallery.GalleryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, GalleryModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(GalleryActivity target);

}