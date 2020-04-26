package com.example.exercise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;


import com.android.volley.toolbox.JsonArrayRequest;

import com.example.exercise.Controller.MyCustomEvent;
import com.example.exercise.Controller.MySingletonVolley;
import com.example.exercise.Model.Company;
import com.example.exercise.Model.CompanyDetailsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.marketwatch) RecyclerView marketWatchRecycler;
    private ArrayList<Company> companies = new ArrayList<>();
    private CompanyDetailsAdapter companyDetailsAdapter;
    private MyCustomEvent myCustomEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        marketWatchRecycler.setLayoutManager(layoutManager);
        marketWatchRecycler.setItemAnimator(new DefaultItemAnimator());
        marketWatchRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        companyDetailsAdapter = new CompanyDetailsAdapter(MainActivity.this,companies);
        marketWatchJsonParse();

    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void marketWatchJsonParse() {

        String url = "http://tickerchart.com/interview/marketwatch.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    myCustomEvent = new MyCustomEvent(response);
                    EventBus.getDefault().post(myCustomEvent);
                }, error -> {
                    throw  new RuntimeException(error);

                });

        MySingletonVolley.getInstance(getApplicationContext()).addToRequestQue(request);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(MyCustomEvent event){
       JSONArray marketWatchData = event.getMarketWatchData();

        for(int i = 0 ; i<  marketWatchData.length() ; i++)
        {
            JSONObject jsonObject = null;
            try {
                jsonObject = marketWatchData.getJSONObject(i);
                Company company = new Company(jsonObject);
                companies.add(company);
                companyDetailsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw  new RuntimeException(e);
            }}
        marketWatchRecycler.setAdapter(companyDetailsAdapter);
        companyDetailsAdapter.notifyDataSetChanged();

    }
}
