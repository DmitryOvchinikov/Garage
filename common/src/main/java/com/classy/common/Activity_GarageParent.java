package com.classy.common;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Activity_GarageParent extends AppCompatActivity {

    private TextView garage_LBL_title;
    private TextView garage_LBL_time;
    private ListView garage_LST_info;
    private RelativeLayout garage_layout;
    private Garage myGarage;

    private long startTime = 0;
    private long endTime = 0;
    private long currentDuration = 0;
    private GarageDatabase garageDatabase;

    //Retrofit callback
    private Callback<Garage> callBack_string = new Callback<Garage>() {

        @Override
        public void onResponse(Call<Garage> call, Response<Garage> response) {
            if (response.isSuccessful()) {
                myGarage = response.body();
                Log.d("AAAT", Arrays.toString(myGarage.getCars()));
                Log.d("AAAT", "" + myGarage.isOpen());
                Log.d("AAAT", myGarage.getAddress());
                Log.d("AAAT", myGarage.getName());
                setAdapter(myGarage.getCars());
            }
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
        startTimeLog();
        fetchGarageInfo();
    }

    private void findViews() {
        garage_LBL_title = findViewById(R.id.garage_LBL_title);
        garage_LST_info = findViewById(R.id.garage_LST_info);
        garage_layout = findViewById(R.id.garage_layout);
        garage_LBL_time = findViewById(R.id.garage_LBL_time);
    }

    protected void setTitle(String title) {
        garage_LBL_title.setText(title);
    }

    private void setAdapter(String[] cars) {
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cars);
        garage_LST_info.setAdapter(itemsAdapter);
    }

    private void startTimeLog() {
        garageDatabase = Room.databaseBuilder(getApplicationContext(), GarageDatabase.class, "TlogsDB.db").build();
        getAllLogs();
        startTime = System.currentTimeMillis();
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

    protected void logTime() {
        endTime = System.currentTimeMillis();
        currentDuration = endTime - startTime;

        final TLog tLog = new TLog("Session", startTime, endTime, currentDuration);
        new Thread(new Runnable() {
            @Override
            public void run() {
                garageDatabase.tLogDAO().insertAll(tLog);
            }
        }).start();
    }

    protected void getAllLogs() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int seconds = 0;
                List<TLog> tLogs = garageDatabase.tLogDAO().getAll();
                for (TLog log : tLogs) {
                    seconds += (log.duration / 1000);
                }
                final String str = "Time used: " + seconds + " seconds";

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        garage_LBL_time.setText(str);
                    }
                });
            }
        }).start();
    }

    //Logging the time onPause
    @Override
    protected void onPause() {
        super.onPause();
        logTime();
    }

    public interface GarageService {
        @GET("WypPzJCt")
        Call<Garage> getResponse();
    }



}
