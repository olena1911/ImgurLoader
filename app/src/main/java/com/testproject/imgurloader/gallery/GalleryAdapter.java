package com.testproject.imgurloader.gallery;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;
import com.testproject.imgurloader.R;

import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder> {

    private List<String> pathList;
    private GalleryMVP.Presenter mGalleryPresenter;

    public GalleryAdapter(List<String> pathList, GalleryMVP.Presenter galleryPresenter) {
        this.pathList = pathList;
        mGalleryPresenter = galleryPresenter;
    }

    @Override
    public GalleryAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryAdapter.PhotoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_gallery_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(final GalleryAdapter.PhotoViewHolder holder, final int position) {
        Picasso.get()
                .load("file://" + pathList.get(position))
                .resize(300, 300)
                .centerInside()
                .into(holder.photoImageView);

        holder.photoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGalleryPresenter.onItemClicked(position, pathList.get(position));
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position, @NonNull List<Object> payloads) {
        onBindViewHolder(holder, position);
        if (payloads != null && payloads.size() != 0) {
            Boolean showLoadingSpinner = (Boolean) payloads.get(payloads.size()-1);
            if (showLoadingSpinner) {
                holder.progressBar.setVisibility(View.VISIBLE);
            } else {
                holder.progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (pathList == null) {
            return 0;
        } else {
            return pathList.size();
        }
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView photoImageView;
        ProgressBar progressBar;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.img_photo);
            progressBar = itemView.findViewById(R.id.progressbar_loading);
        }
    }
}
