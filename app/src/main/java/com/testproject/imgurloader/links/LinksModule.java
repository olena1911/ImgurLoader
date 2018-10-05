package com.testproject.imgurloader.links;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class LinksModule {

    @Provides
    public LinksMVP.Presenter provideLinksPresenter(LinksMVP.Model linksModel) {
        return new LinksPresenter(linksModel);
    }

    @Provides
    public LinksMVP.Model provideLinksModel(Context context) {
        return new LinksModel(context);
    }

}
