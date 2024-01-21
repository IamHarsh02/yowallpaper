package com.example.yowallpaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WallpaperRVAdaptor extends RecyclerView.Adapter<WallpaperRVAdaptor.ViewHolder> {
    private ArrayList<String> wallpaperRVArrayt;
    private Context context;

    public WallpaperRVAdaptor(ArrayList<String> wallpaperRVArrayt, Context context) {
        this.wallpaperRVArrayt = wallpaperRVArrayt;
        this.context = context;
    }

    @NonNull
    @Override
    public WallpaperRVAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(context).inflate(R.layout.wallpaper_rv_item,parent,false);
        return new WallpaperRVAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperRVAdaptor.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context).load(wallpaperRVArrayt.get(position)).into(holder.wallpaperImageView);
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent= new Intent(context,WallpaperActivity.class);
              intent.putExtra("imgUrl",wallpaperRVArrayt.get(position));
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return wallpaperRVArrayt.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView wallpaperImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wallpaperImageView=itemView.findViewById(R.id.idIvWallpaper);
        }
    }
}
