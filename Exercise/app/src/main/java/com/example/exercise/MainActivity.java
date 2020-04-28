package com.example.exercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;


import com.android.volley.toolbox.JsonArrayRequest;

import com.example.exercise.Controller.MyCustomEvent;
import com.example.exercise.Controller.MySingletonVolley;
import com.example.exercise.Model.ApplicationSettings;
import com.example.exercise.Model.Company;
import com.example.exercise.Model.CompanyDetailsAdapter;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.marketwatch) RecyclerView marketWatchRecycler;
    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private ArrayList<Company> companies = new ArrayList<>();
    private CompanyDetailsAdapter companyDetailsAdapter;
    private JSONArray responseArray;

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

        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return  true;
        }

        return super.onOptionsItemSelected(item);
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
                    responseArray = response;
                    JSONObject jsonObject = null;

                    for(int i = 0 ; i<  response.length() ; i++)
                    {
                        try {
                            jsonObject = response.getJSONObject(i);
                            EventBus.getDefault().post(new MyCustomEvent(jsonObject));

                        } catch (JSONException e) {
                            throw  new RuntimeException(e);
                        }}
                }, error -> {
                    throw  new RuntimeException(error);

                });

        MySingletonVolley.getInstance(getApplicationContext()).addToRequestQue(request);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(MyCustomEvent event){
        Company marketWatchData = null;
        JSONObject jsonObject = event.getMarketWatchData();

            try {
                marketWatchData = new Company(jsonObject);
                companies.add(marketWatchData);
                companyDetailsAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
             }
        marketWatchRecycler.setAdapter(companyDetailsAdapter);
        companyDetailsAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.settings){
            menuItem.setChecked(true);
           Intent intent = new Intent(MainActivity.this, ApplicationSettings.class);
            startActivity(intent);
            drawerLayout.closeDrawers();
        }
        return false;
    }


}
