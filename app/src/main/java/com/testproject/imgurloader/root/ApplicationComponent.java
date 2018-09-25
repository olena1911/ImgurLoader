package com.testproject.imgurloader.root;

import com.testproject.imgurloader.GalleryActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(GalleryActivity target);

}