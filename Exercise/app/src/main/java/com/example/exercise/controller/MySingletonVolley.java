package com.example.exercise.controller;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class MySingletonVolley {

    private volatile static MySingletonVolley mySingletonVolley;
    private static RequestQueue requestQueue;
    private static Context mcontext;


    private MySingletonVolley(Context context) {
        mcontext = context;
        requestQueue = getRequestQue();
    }

    private RequestQueue getRequestQue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }

    static synchronized MySingletonVolley getInstance(Context context) {
        if (mySingletonVolley == null) {
            synchronized (MySingletonVolley.class) {
                if (mySingletonVolley == null)
                    mySingletonVolley = new MySingletonVolley(context);
            }
        }
        return mySingletonVolley;
    }

    <T> void addToRequestQue(Request<T> request) {
        requestQueue.add(request);

    }


}
