package com.example.exercise.controller;


import com.example.exercise.model.CompanyDetails;
import com.example.exercise.model.GeneralIndex;

import org.json.JSONObject;


public class MyCustomEvent {


    private CompanyDetails companyDetailsData;
    private GeneralIndex generalIndexData;
    private JSONObject MarketWatchData;

    public GeneralIndex getGeneralIndexData() {
        return generalIndexData;
    }

    public CompanyDetails getCompanyDetailsData() {
        return companyDetailsData;
    }

    public JSONObject getMarketWatchData() {
        return MarketWatchData;
    }

    public MyCustomEvent(CompanyDetails companyDetailsData) {
        this.companyDetailsData = companyDetailsData;
    }

    public MyCustomEvent(GeneralIndex generalIndexData) {
        this.generalIndexData = generalIndexData;
    }

    public MyCustomEvent(JSONObject MarketWatchData) {
        this.MarketWatchData = MarketWatchData;
    }
}