package com.example.yowallpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CategoryRVAdaptor.CategoryOnClick {

    private EditText editText;
    private ImageView searchImage;
    private RecyclerView categoryRv, wallpaperRv;
    private ProgressBar progressBar;
    private ArrayList<String> wallpaperArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalsArrayList;
    private CategoryRVAdaptor categoryRVAdaptor;
    private WallpaperRVAdaptor wallpaperRVAdaptor;
    private Context context;
    private  ImageView myImage;

    //api key W8pQcvfDKphnJy34OEhCKF10QKyrBIoBRG0C0ozXq32hblnHanpd8AeW
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.idEditSearch);
        searchImage = findViewById(R.id.idAlSearch);
        categoryRv = findViewById(R.id.idRVCategory);
        wallpaperRv = findViewById(R.id.idRVWallpaper);
        progressBar = findViewById(R.id.idProgress);
        myImage=findViewById(R.id.myImage);
        wallpaperArrayList = new ArrayList<>();
        categoryRVModalsArrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        categoryRv.setLayoutManager(linearLayoutManager);
        categoryRVAdaptor = new CategoryRVAdaptor(categoryRVModalsArrayList, this, this::onClickCategorey);
        categoryRv.setAdapter(categoryRVAdaptor);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        wallpaperRv.setLayoutManager(gridLayoutManager);
        wallpaperRVAdaptor = new WallpaperRVAdaptor(wallpaperArrayList, this);
        wallpaperRv.setAdapter(wallpaperRVAdaptor);
        TranslateAnimation jumpAnimation = new TranslateAnimation(0, 0, 0, -5);
        jumpAnimation.setDuration(500); // Set the duration of each jump
        jumpAnimation.setRepeatMode(Animation.REVERSE); // Reverse the animation to jump back down
        jumpAnimation.setRepeatCount(Animation.INFINITE); // Repeat the animation infinitely
        // Start the animation
        myImage.startAnimation(jumpAnimation);

        getWallpapers();
        getCategory();

        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = editText.getText().toString();
                if (searchText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter YO Text!!!", Toast.LENGTH_SHORT).show();
                } else {
                    getWallpaperSearchCategory(searchText);
                }
            }
        });
    }

    private void getWallpaperSearchCategory(String category) {

        wallpaperArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);
        String Url = "https://api.pexels.com/v1/search?query=" + category + "&per_page=30&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray photoArray = response.getJSONArray("photos");
                    for (int i = 0; i < photoArray.length(); i++) {
                        JSONObject photoObj = photoArray.getJSONObject(i);
                        String imgUrl = photoObj.getJSONObject("src").getString("portrait");
                        wallpaperArrayList.add(imgUrl);
                    }
                    wallpaperRVAdaptor.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed To Load Data for"+category, Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "W8pQcvfDKphnJy34OEhCKF10QKyrBIoBRG0C0ozXq32hblnHanpd8AeW");
                return headers;
            }

        };
        requestQueue.add(jsonObjectRequest);
    }
    public void onClickLogo(View view) {
        Intent intent= new Intent(MainActivity.this,DeveloperActivity.class);
        startActivity(intent);
    }

    private void getCategory() {
        categoryRVModalsArrayList.add(new CategoryRVModal("Technology","https://www.pexels.com/photo/plasma-ball-illustration-414860/" ));
        categoryRVModalsArrayList.add(new CategoryRVModal("Cars", "https://unsplash.com/photos/white-car-m3m-lnR90uM"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Flower", "https://unsplash.com/photos/orange-petaled-flowers-koy6FlCCy5s"));
        categoryRVModalsArrayList.add(new CategoryRVModal("Anime", "https://unsplash.com/photos/people-crossing-on-pedestrian-lane-during-night-time-3OdajQGd9sk"));
        categoryRVAdaptor.notifyDataSetChanged();
    }


    private void getWallpapers() {
        wallpaperArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);
        String url = "https://api.pexels.com/v1/curated?per_page=30&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);
                try {
                    JSONArray photoArray =response.getJSONArray("photos");
                    for(int i =0;i<photoArray.length();i++){
                   JSONObject photoObj=photoArray.getJSONObject(i);
                    String imgUrl =photoObj.getJSONObject("src").getString("portrait");
                    wallpaperArrayList.add(imgUrl);
                    }
                    wallpaperRVAdaptor.notifyDataSetChanged();
                }catch (JSONException e){
                e.printStackTrace();
                }

            }},new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error ) {
                    Toast.makeText(MainActivity.this,"Failed To Load Data wallpaer",Toast.LENGTH_SHORT).show();

                }
        }){
            @Override
            public Map<String,String> getHeaders()throws AuthFailureError {
                HashMap<String,String>headers=new HashMap<>();
                headers.put("Authorization","W8pQcvfDKphnJy34OEhCKF10QKyrBIoBRG0C0ozXq32hblnHanpd8AeW");
                return  headers;
            }

            };
        requestQueue.add(jsonObjectRequest);
        }



    @Override
    public void onClickCategorey(int position) {
        String category = categoryRVModalsArrayList.get(position).getCategory();
        getWallpaperSearchCategory(category);
    }

}