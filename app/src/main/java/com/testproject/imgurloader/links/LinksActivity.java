package com.testproject.imgurloader.links;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testproject.imgurloader.R;
import com.testproject.imgurloader.root.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LinksActivity extends AppCompatActivity implements LinksMVP.View {

    private List<String> linksList = new ArrayList<>();
    private LinksAdapter mLinksAdapter;

    @Inject
    LinksMVP.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        ((App) getApplication()).getComponent().inject(this);

        RecyclerView mLinksRecyclerView = findViewById(R.id.list_links);
        mLinksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLinksAdapter = new LinksAdapter(this, linksList);
        mLinksRecyclerView.setAdapter(mLinksAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.setView(this);
        presenter.loadLinks();
    }

    @Override
    public void openLinkInBrowser(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void updateData(String link) {
        linksList.add(link);
        mLinksAdapter.notifyItemInserted(linksList.size() - 1);
    }
}
