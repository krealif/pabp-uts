package com.krealif.beritaku;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private final ArrayList<News> newsList;
    private final LayoutInflater inflater;

    public NewsAdapter(Context context, ArrayList<News> newsList) {
        this.newsList = newsList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        final News news = newsList.get(position);
        int imgId = holder.itemView.getResources().getIdentifier(news.getId(), "drawable", inflater.getContext().getPackageName());
        holder.txtTitle.setText(news.getTitle());
        holder.txtDate.setText(new SimpleDateFormat("dd MMMM yyyy").format(news.getPublished()));
        holder.imgNews.setImageResource(imgId);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitle, txtDate;
        ImageView imgNews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNews = itemView.findViewById(R.id.image_news);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDate = itemView.findViewById(R.id.txt_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            News news = newsList.get(position);
            Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
            intent.putExtra("imgId", news.getId());
            intent.putExtra("title", news.getTitle());
            intent.putExtra("published", new SimpleDateFormat("dd MMMM yyyy").format(news.getPublished()));
            intent.putExtra("ageRating", String.valueOf(news.getAgeRating()));
            intent.putExtra("body", news.getBody());
            view.getContext().startActivity(intent);
        }
    }
}
