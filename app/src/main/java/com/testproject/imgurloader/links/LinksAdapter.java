package com.testproject.imgurloader.links;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testproject.imgurloader.R;

import java.util.List;

import butterknife.BindView;

public class LinksAdapter extends RecyclerView.Adapter<LinksAdapter.LinkViewHolder>{

    private LinksMVP.View mLinksView;
    private List<String> linksList;

    public LinksAdapter(LinksMVP.View linksView, List<String> linksList) {
        mLinksView = linksView;
        this.linksList = linksList;
    }

    @Override
    public LinksAdapter.LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinksAdapter.LinkViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_links, parent, false));
    }

    @Override
    public void onBindViewHolder(LinksAdapter.LinkViewHolder holder, final int position) {
        holder.linkTextView.setText(linksList.get(position));
        Linkify.addLinks(holder.linkTextView, Linkify.WEB_URLS);
        holder.linkTextView.setLinkTextColor(Color.BLUE);
        holder.linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLinksView.openLinkInBrowser(linksList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return linksList.size();
    }

    public class LinkViewHolder extends RecyclerView.ViewHolder {

        TextView linkTextView;

        public LinkViewHolder(View itemView) {
            super(itemView);
            linkTextView = itemView.findViewById(R.id.txt_link);
        }
    }
}
