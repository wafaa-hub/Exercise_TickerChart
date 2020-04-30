package com.example.exercise.controller;

import android.app.Application;
import android.content.res.Configuration;

public class MyCustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

         MySingletonVolley.getInstance(getApplicationContext());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
