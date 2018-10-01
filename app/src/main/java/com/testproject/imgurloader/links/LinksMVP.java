package com.testproject.imgurloader.links;

import android.content.Context;

import io.reactivex.Observable;

public interface LinksMVP {

    interface Model {
        Observable<String> getLinks();
        void addLink(String url);
    }

    interface View {
        void updateData(String link);
        void openLinkInBrowser(String url);
    }

    interface Presenter {
        void setView(LinksMVP.View view);
        void loadLinks();
        void saveLink(String url);
    }
}
