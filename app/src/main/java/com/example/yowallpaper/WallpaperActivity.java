package com.example.yowallpaper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.IOException;

import kotlin.ReplaceWith;

public class WallpaperActivity extends AppCompatActivity {
    private ImageView wallpaperIv;
    private  ImageView iosBackBtn;
    private Button setWallpaperBtn;
    private String url;
    WallpaperManager wallpaperManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        wallpaperIv=findViewById(R.id.idIVWallpaper);
        setWallpaperBtn =findViewById(R.id.idBtnSetWallpaper);
        url=getIntent().getStringExtra("imgUrl");
        Glide.with(this).load(url).into(wallpaperIv);
        wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        setWallpaperBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Glide.with(WallpaperActivity.this).asBitmap().load(url).listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Bitmap> target, boolean isFirstResource) {
                        Toast.makeText(WallpaperActivity.this, "Failed To Load Image!!!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Bitmap resource, @NonNull Object model, Target<Bitmap> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                    try {
                        wallpaperManager.setBitmap(resource);
                    }catch (IOException e){
                        e.printStackTrace();
                        Toast.makeText(WallpaperActivity.this, "Failed To Load Image!!!", Toast.LENGTH_SHORT).show();
                    }

                        return false;
                    }
                }).submit();
                FancyToast.makeText( WallpaperActivity.this,"Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
            }
        });
    }

    public void onBackButtonClick(View view) {
        finish(); // Close the current activity to go back
    }

}