package com.testproject.imgurloader.gallery;

import com.testproject.imgurloader.upload.UploadService;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GalleryPresenter implements GalleryMVP.Presenter {

    private GalleryMVP.Model model;
    private GalleryMVP.View view;

    private Disposable subscription;

    @Inject
    private UploadService mUploadService;

    public GalleryPresenter(GalleryMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(GalleryMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadPicturesFromGallery() {
        subscription = model
                .getPhotoPaths()
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
    public void rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription.isDisposed()) {
                subscription.dispose();
            }
        }
    }
}
