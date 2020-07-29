package com.darkabhi.covidproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.darkabhi.covidproject.NewsModel;
import com.darkabhi.covidproject.R;
import com.google.android.material.textview.MaterialTextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private Context context;
    private ArrayList<NewsModel> mList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public NewsAdapter(Context context, ArrayList<NewsModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.news_card, parent, false);
        return new NewsHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsModel currentItem = mList.get(position);
        String imageUrl = currentItem.getImageUrl();
        String newsTitle = currentItem.getTitle();
        String newsDesc = currentItem.getContent();
        holder.newsTitle.setText(newsTitle);
        holder.newsContent.setText(newsDesc);
        Picasso.with(context).load(imageUrl).fit().centerCrop().into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {

        MaterialTextView newsTitle, newsContent;
        ImageView newsImage;

        public NewsHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsContent = itemView.findViewById(R.id.newsContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getLayoutPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
