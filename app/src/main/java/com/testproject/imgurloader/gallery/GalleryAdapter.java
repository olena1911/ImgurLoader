package com.testproject.imgurloader.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testproject.imgurloader.R;

import java.util.List;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder> {

    private Context mContext;
    private List<String> pathList;

    public GalleryAdapter(Context context, List<String> pathList) {
        mContext = context;
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
        /*if (!mCursor.moveToPosition(position)) {
            return;
        }
        //int pathColumnIndex = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);*/
        //holder.pathTextView.setText(mCursor.getString(pathColumnIndex));
        holder.pathTextView.setText(pathList.get(position));
    }

    @Override
    public int getItemCount() {
        /*if (mCursor == null)
            return 0;
        return mCursor.getCount();*/
        return pathList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {

        //ImageView photoImageView;
        TextView pathTextView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            //photoImageView = itemView.findViewById(R.id.img_photo);
            pathTextView = itemView.findViewById(R.id.img_text);
        }
    }
}
