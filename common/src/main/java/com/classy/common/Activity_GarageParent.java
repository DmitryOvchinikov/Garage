package com.classy.common;

import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Activity_GarageParent extends AppCompatActivity {

    private TextView garage_LBL_title;
    private TextView garage_LBL_info;
    private RelativeLayout garage_layout;
    private Garage myGarage;

    //Retrofit callback
    private Callback<Garage> callBack_string = new Callback<Garage>() {

        @Override
        public void onResponse(Call<Garage> call, Response<Garage> response) {
            myGarage = response.body();
            Log.d("AAAT", Arrays.toString(myGarage.getCars()));
            Log.d("AAAT", "" + myGarage.isOpen());
            Log.d("AAAT", myGarage.getAddress());
            Log.d("AAAT", myGarage.getName());
            garage_LBL_info.setText(Arrays.toString(myGarage.getCars()).replace('[', ' ').replace(']', ' '));
        }

        @Override
        public void onFailure(Call<Garage> call, Throwable t) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);

        findViews();
        fetchGarageInfo();
    }

    private void findViews() {
        garage_LBL_title = findViewById(R.id.garage_LBL_title);
        garage_LBL_info = findViewById(R.id.garage_LBL_info);
        garage_layout = findViewById(R.id.garage_layout);
    }

    protected void setTitle(String title) {
        garage_LBL_title.setText(title);
    }

    //Fetching the garage information from the "API"
    protected void fetchGarageInfo() {
        runOnUiThread(() -> {
            Gson gson = new GsonBuilder().setLenient().create();

            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://pastebin.com/raw/").addConverterFactory(GsonConverterFactory.create(gson)).build();
            GarageService service = retrofit.create(GarageService.class);
            Call<Garage> garage = service.getResponse();
            garage.enqueue(callBack_string);
        });
    }

    public interface GarageService {
        @GET("WypPzJCt")
        Call<Garage> getResponse();
    }



}
