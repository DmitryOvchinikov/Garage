package com.classy.garage;

import android.graphics.Color;
import android.os.Bundle;

import com.classy.common.Activity_GarageParent;

public class Activity_Customer extends Activity_GarageParent {

    private final int GARAGE_COLOR = Color.parseColor("#A4FA39");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Garage App: Customer");
        setGarageColor(GARAGE_COLOR);
    }
}