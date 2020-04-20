package com.example.exercise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.exercise.Controller.MySingletonVolley;
import com.example.exercise.Model.Company;
import com.example.exercise.Model.CompanyDetailsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.marketwatch) RecyclerView marketWatchRecycler;
    private ArrayList<Company> companies = new ArrayList<>();
    private CompanyDetailsAdapter companyDetailsAdapter;
    private RequestQueue requestQueue;

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
        marketWatchRecycler.setAdapter(companyDetailsAdapter);
        marketWatchJsonParse();
        companyDetailsAdapter.notifyDataSetChanged();
    }

    public void marketWatchJsonParse() {

        String url = "http://tickerchart.com/interview/marketwatch.json";
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache, network);
        requestQueue.start();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                System.out.print(response);

                for(int i =0 ; i<  response.length() ; i++)
                {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);
                        Company company = new Company(jsonObject);
                        companies.add(company);
                        companyDetailsAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        throw  new RuntimeException(e);
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                throw  new RuntimeException(error);

            }
        });

        MySingletonVolley.getInstance(getApplicationContext()).addToRequestQue(request);

    }
}
