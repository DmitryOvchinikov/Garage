package com.classy.common;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity_GarageParent extends AppCompatActivity {

    private TextView garage_LBL_title;
    private TextView garage_LBL_info;
    private RelativeLayout garage_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abstract);

        findViews();
    }

    private void findViews() {
        garage_LBL_title = findViewById(R.id.garage_LBL_title);
        garage_LBL_info = findViewById(R.id.garage_LBL_info);
        garage_layout = findViewById(R.id.garage_layout);
    }

    protected void setTitle(String title) {
        garage_LBL_title.setText(title);
    }



}
