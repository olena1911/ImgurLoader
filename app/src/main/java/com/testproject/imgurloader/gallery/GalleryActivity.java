package com.testproject.imgurloader.gallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.testproject.imgurloader.R;

public class GalleryActivity extends AppCompatActivity implements GalleryMVP.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
    }
}
