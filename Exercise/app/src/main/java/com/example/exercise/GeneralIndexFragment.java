package com.example.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import androidx.fragment.app.Fragment;

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
import com.android.volley.toolbox.Volley;
import com.example.exercise.Controller.MySingletonVolley;
import com.example.exercise.Model.Company;
import com.example.exercise.Model.GeneralIndex;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GeneralIndexFragment extends Fragment {

    @BindView(R.id.amount_general)
    TextView amountGeneral;
    @BindView(R.id.volume)
    TextView volumeGeneral;
    @BindView(R.id.tradesCount)
    TextView tradesGeneral;
    @BindView(R.id.numOfWinning)
    TextView winCompanies;
    @BindView(R.id.numOfFixing)
    TextView fixCompanies;
    @BindView(R.id.numOflosing)
    TextView loseCompanies;
    private Unbinder unbinder;

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_index_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        generalIndexJsonParse();
        return view;
    }

    public void generalIndexJsonParse() {

        String url = "http://tickerchart.com/interview/general-index.json";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            GeneralIndex generalIndex = new GeneralIndex(jsonObject);
                            amountGeneral.setText(generalIndex.getAmount());
                            tradesGeneral.setText(generalIndex.getTrades());
                            volumeGeneral.setText(generalIndex.getVolume());
                            winCompanies.setText(generalIndex.getWinning());
                            fixCompanies.setText(generalIndex.getFixed());
                            loseCompanies.setText(generalIndex.getLosing());

                        } catch (JSONException e) {
                            throw  new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        MySingletonVolley.getInstance(getContext()).addToRequestQue(request);

    }
}
