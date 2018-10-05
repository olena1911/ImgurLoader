package com.testproject.imgurloader.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testproject.imgurloader.R;
import com.testproject.imgurloader.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class GalleryActivity extends AppCompatActivity implements GalleryMVP.View {

    private List<String> pathsList = new ArrayList<>();
    private GalleryAdapter mGalleryAdapter;

    @Inject
    GalleryMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ((App) getApplication()).getComponent().inject(this);

        RecyclerView mPhotosRecyclerView = findViewById(R.id.list_gallery);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mPhotosRecyclerView.setLayoutManager(gridLayoutManager);

        mGalleryAdapter = new GalleryAdapter(pathsList, presenter);
        mPhotosRecyclerView.setAdapter(mGalleryAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadPicturesFromGallery();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
        pathsList.clear();
        mGalleryAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(String path) {
        pathsList.add(path);
        mGalleryAdapter.notifyItemInserted(pathsList.size() - 1);
    }

    @Override
    public void showLoadingSpinner(int position) {
        mGalleryAdapter.notifyItemChanged(position, true);
    }

    @Override
    public void hideLoadingSpinner(int position) {
        mGalleryAdapter.notifyItemChanged(position, false);
    }
}
