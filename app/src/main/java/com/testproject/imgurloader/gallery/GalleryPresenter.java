package com.testproject.imgurloader.gallery;

import android.content.Context;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GalleryPresenter implements GalleryMVP.Presenter {

    private GalleryMVP.Model model;
    private GalleryMVP.View view;

    private Disposable subscription;

    public GalleryPresenter(GalleryMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(GalleryMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadPicturesFromGallery(Context context) {
        subscription = model
                .getPhotoPaths(context)
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
                        /*if (view != null) {
                            view.showSnackbar("Error getting movies");
                        }*/
                    }

                });
    }

    @Override
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }
}
