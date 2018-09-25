package com.testproject.imgurloader.root;

import android.app.Application;

import com.testproject.imgurloader.gallery.GalleryModel;
import com.testproject.imgurloader.gallery.GalleryModule;

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .galleryModule(new GalleryModule())
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}