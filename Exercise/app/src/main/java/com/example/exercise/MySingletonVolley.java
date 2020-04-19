package com.example.exercise;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingletonVolley {

    private static MySingletonVolley mySingletonVolley;
    private RequestQueue requestQueue;
    private static Context mcontext;

    public MySingletonVolley (Context context)
    {
        mcontext = context;
        requestQueue = getRequestQue();
    }

    public RequestQueue getRequestQue()
    {
        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(mcontext.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingletonVolley getInstance(Context context)
    {
        if (mySingletonVolley == null)
        {
            mySingletonVolley = new MySingletonVolley(context);
        }
        return mySingletonVolley;
    }

    public <T> void addToRequestQue (Request<T> request)
    {
       requestQueue.add(request);

    }


}
