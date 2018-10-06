package com.testproject.imgurloader.gallery;

import com.testproject.imgurloader.api.model.ImageToUpload;
import com.testproject.imgurloader.upload.UploadService;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GalleryPresenter implements GalleryMVP.Presenter {

    private GalleryMVP.Model model;
    private GalleryMVP.View view;

    private Disposable subscription;

    UploadService mUploadService;

    public GalleryPresenter(GalleryMVP.Model model, UploadService uploadService) {
        this.model = model;
        mUploadService = uploadService;
        mUploadService.setGalleryPresenter(this);
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

    @Override
    public void onItemClicked(int position, String path) {
        view.showLoadingSpinner(position);
        File imageFile = new File(path);
        ImageToUpload imageToUpload = new ImageToUpload();
        imageToUpload.setPosition(position);
        imageToUpload.setImageFile(imageFile);
        imageToUpload.setTitle(imageFile.getName());
        imageToUpload.setUri(path);
        mUploadService.uploadFile(imageToUpload);
    }

    @Override
    public void onImageUploaded(int position) {
        view.hideLoadingSpinner(position);
    }

    @Override
    public void onErrorWhileUploading(int position, String imageName) {
        view.hideLoadingSpinner(position);
        view.showErrorAlert(imageName);
    }
}
