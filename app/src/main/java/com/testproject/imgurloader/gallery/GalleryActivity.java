package com.testproject.imgurloader.gallery;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.testproject.imgurloader.R;
import com.testproject.imgurloader.links.LinksActivity;
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

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mPhotosRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        } else{
            mPhotosRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        }

        mGalleryAdapter = new GalleryAdapter(pathsList, presenter);
        mPhotosRecyclerView.setAdapter(mGalleryAdapter);
        showErrorAlert("D:/image.png");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gallery_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_links:
                Intent linksActivityIntent = new Intent(this, LinksActivity.class);
                startActivity(linksActivityIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showErrorAlert(String imageName) {
        AlertDialog errorAlertDialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.alert_not_uploaded_message) + imageName)
                .setTitle(getString(R.string.alert_not_uploaded_title))
                .setPositiveButton(getString(R.string.alert_not_uploaded_button), null)
                .create();
        errorAlertDialog.show();
    }
}
