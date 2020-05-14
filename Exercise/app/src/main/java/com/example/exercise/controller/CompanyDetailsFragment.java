package com.example.exercise.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.exercise.R;
import com.example.exercise.controller.MyCustomEvent;
import com.example.exercise.controller.MySingletonVolley;
import com.example.exercise.model.CompanyDetails;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyDetailsFragment extends Fragment {

    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.symbol)
    TextView sybmol;
    @BindView(R.id.tradesCount)
    TextView tradesDetails;
    @BindView(R.id.high)
    TextView highPriceCompany;
    @BindView(R.id.low)
    TextView lowPriceCompany;
    @BindView(R.id.amount)
    TextView amountCompanyDetails;
    @BindView(R.id.volume)
    TextView volumeCompanyDetails;
    private Unbinder unbinder;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.company_details_fragment, container, false);
         unbinder = ButterKnife.bind(this, view);
        companyDetailsJsonParse();
        return view;
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    public void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void customEventReceived(MyCustomEvent event){
           CompanyDetails companyDetails = event.getCompanyDetailsData();

            companyName.setText(companyDetails.getCompany_name());
            sybmol.setText(companyDetails.getSymbol());
            amountCompanyDetails.setText(companyDetails.getAmount());
            volumeCompanyDetails.setText(companyDetails.getVolume());
            highPriceCompany.setText(companyDetails.getHigh());
            lowPriceCompany.setText(companyDetails.getLow());
            tradesDetails.setText(companyDetails.getTrades_count());
    }

    public void companyDetailsJsonParse() {

        String url = "http://tickerchart.com/interview/company-details.json";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            CompanyDetails companyDetails = new CompanyDetails(jsonObject);
                            EventBus.getDefault().post(new MyCustomEvent(companyDetails));

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
