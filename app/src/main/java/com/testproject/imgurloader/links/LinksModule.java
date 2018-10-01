package com.testproject.imgurloader.links;

import dagger.Module;
import dagger.Provides;

@Module
public class LinksModule {

    @Provides
    public LinksMVP.Presenter provideLinksPresenter(LinksMVP.Model linksModel) {
        return new LinksPresenter(linksModel);
    }

    @Provides
    public LinksMVP.Model provideLinksModel() {
        return new LinksModel();
    }

}
