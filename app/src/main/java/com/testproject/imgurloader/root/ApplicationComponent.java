package com.testproject.imgurloader.root;

import com.testproject.imgurloader.gallery.GalleryActivity;
import com.testproject.imgurloader.gallery.GalleryModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, GalleryModule.class})
public interface ApplicationComponent {

    void inject(GalleryActivity target);

}