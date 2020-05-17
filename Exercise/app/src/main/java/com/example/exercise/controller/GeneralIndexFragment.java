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

import com.android.volley.toolbox.StringRequest;
import com.example.exercise.R;

import com.example.exercise.model.GeneralIndex;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
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
    @BindView(R.id.name)
    TextView name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.general_index_fragment, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
        generalIndexJsonParse();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void customEventReceived(MyCustomEvent event) {
        GeneralIndex generalIndex = event.getGeneralIndexData();
        name.setText(generalIndex.getName());
        amountGeneral.setText(generalIndex.getAmount());
        tradesGeneral.setText(generalIndex.getTrades());
        volumeGeneral.setText(generalIndex.getVolume());
        winCompanies.setText(generalIndex.getWinning());
        fixCompanies.setText(generalIndex.getFixed());
        loseCompanies.setText(generalIndex.getLosing());
    }

    private void generalIndexJsonParse() {

        String url = "http://tickerchart.com/interview/general-index.json";
        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        GeneralIndex generalIndex = new GeneralIndex(jsonObject);
                        EventBus.getDefault().post(new MyCustomEvent(generalIndex));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }, error -> {

        });

        MySingletonVolley.getInstance(getContext()).addToRequestQue(request);

    }
}
