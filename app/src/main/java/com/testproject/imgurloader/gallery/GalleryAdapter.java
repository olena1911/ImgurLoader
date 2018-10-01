package com.testproject.imgurloader.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.testproject.imgurloader.R;

import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder> {

    private List<String> pathList;

    public GalleryAdapter(List<String> pathList) {
        this.pathList = pathList;
    }

    @Override
    public GalleryAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GalleryAdapter.PhotoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_gallery_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.PhotoViewHolder holder, int position) {
        Picasso.get()
                .load(pathList.get(position))
                .resize(300, 300)
                .centerInside()
                .into(holder.photoImageView);
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView photoImageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            photoImageView = itemView.findViewById(R.id.img_photo);
        }
    }
}
