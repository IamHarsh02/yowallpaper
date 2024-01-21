package com.example.yowallpaper;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.shashank.sony.fancytoastlib.FancyToast;

public class DeveloperActivity extends AppCompatActivity {
    private ImageView logoImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        logoImage=findViewById(R.id.idMyLogo);

    }
    public void onFeebackButtonClick(View view) {
        Intent emailIntent=new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"patareharsh@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"YO Wallpaper User Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Good Job !");
            startActivity(emailIntent);

    }


    public void onBackButtonClick(View view) {
        finish();
    }
}
