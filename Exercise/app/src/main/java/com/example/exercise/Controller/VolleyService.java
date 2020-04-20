package com.example.exercise.Controller;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class VolleyService extends Service {

    private final IBinder binder = new MyBinder();
    private final Random mGenerator = new Random();

    public class Mybinder extends Binder{
        VolleyService getService(){
            return VolleyService.this;
        }
    }
    public VolleyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
