package com.example.exercise;

import android.app.Application;
import android.content.res.Configuration;

public class MyCustomApplication extends Application {

    private static  MySingletonVolley mySingletonVolley;

    @Override
    public void onCreate() {
        super.onCreate();

         mySingletonVolley = new MySingletonVolley(getApplicationContext());
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
