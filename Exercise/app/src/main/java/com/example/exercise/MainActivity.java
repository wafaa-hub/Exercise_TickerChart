package com.example.exercise;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import butterknife.BindView;
import butterknife.ButterKnife;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.marketwatch) RecyclerView marketWatchRecycler;
    private ArrayList<CompanyDetails> companies = new ArrayList<>();
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

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Iterator<String> keys = response.keys();

                            while(keys.hasNext()) {
                                String key = keys.next();
                                if (response.get(key) instanceof JSONObject) {
                                    CompanyDetails companyDetailsObject = new CompanyDetails(response);
                                    companies.add(companyDetailsObject);
                                }
                            }

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
         MySingletonVolley.getInstance(getApplicationContext()).addToRequestQue(request);
    }
}
