package com.example.yowallpaper;

import android.annotation.SuppressLint;
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

public class CategoryRVAdaptor extends RecyclerView.Adapter<CategoryRVAdaptor.ViewHolder> {
    private final ArrayList<CategoryRVModal> categoryRVAdaptors;
    private Context context;
    private  CategoryOnClick categoryOnClick;

    public CategoryRVAdaptor(ArrayList<CategoryRVModal> categoryRVAdaptors, Context context, CategoryOnClick categoryOnClick) {
        this.categoryRVAdaptors = categoryRVAdaptors;
        this.context = context;
        this.categoryOnClick = categoryOnClick;
    }

    @NonNull
    @Override
    public CategoryRVAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_rv_item,parent,false);
        return new CategoryRVAdaptor.ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CategoryRVAdaptor.ViewHolder holder, int position) {
        CategoryRVModal categoryRVAdaptor = categoryRVAdaptors.get(position);
        holder.categoryTV.setText(categoryRVAdaptor.getCategory());
//        holder.categoryIV.setImageBitmap(categoryRVAdaptors.);
        Glide.with(context).load(categoryRVAdaptor.getCategoryIVUrl()).into(holder.categoryIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               categoryOnClick.onClickCategorey(position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return categoryRVAdaptors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTV;
        ImageView categoryIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTV = itemView.findViewById(R.id.idTVCategory);
            categoryIV = itemView.findViewById(R.id.idIvCategory);
        }
    }
    public  interface CategoryOnClick{
        void  onClickCategorey(int position);
    }
}
