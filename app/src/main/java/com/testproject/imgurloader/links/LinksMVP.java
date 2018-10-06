package com.testproject.imgurloader.links;

import io.reactivex.Observable;

/**
 * Declares Links MVP interfaces.
 */
public interface LinksMVP {

    interface Model {
        Observable<String> getLinks();
        void addLink(String url, String deletehash);
    }

    interface View {
        void updateData(String link);
        void openLinkInBrowser(String url);
    }

    interface Presenter {
        void setView(LinksMVP.View view);
        void loadLinks();
        void saveLink(String url, String deletehash);
    }
}
