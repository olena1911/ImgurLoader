package com.testproject.imgurloader.links;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LinksPresenter implements LinksMVP.Presenter {

    private LinksMVP.Model model;
    private LinksMVP.View view;

    private Disposable subscription;

    public LinksPresenter(LinksMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LinksMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadLinks() {
            subscription = model
                    .getLinks()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<String>() {
                        @Override
                        public void onComplete() {
                        }

                        @Override
                        public void onNext(String s) {
                            if (view != null) {
                                view.updateData(s);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }
                    });
    }

    @Override
    public void saveLink(String url, String deletehash) {
        model.addLink(url, deletehash);
    }
}
