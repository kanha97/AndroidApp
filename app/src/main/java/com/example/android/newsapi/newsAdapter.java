package com.example.android.newsapi;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class newsAdapter extends RecyclerView.Adapter<newsAdapter.ViewHolder> {

    private Context context;
    ArrayList<news> newses = new ArrayList<>();

    public newsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public newsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull newsAdapter.ViewHolder holder, final int position) {
        holder.titles.setText(newses.get(position).getTitle());
        holder.description.setText(newses.get(position).getDescription());
        Glide
                .with(context)
                .asBitmap()
                .load(newses.get(position).getImageurl())
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titles;
        private ImageView imageView;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titles = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
        }
    }
    public void setNewses (ArrayList < news > newses) {
        this.newses = newses;
        notifyDataSetChanged();
    }
}


