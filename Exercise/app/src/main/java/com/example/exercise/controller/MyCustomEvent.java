package com.example.exercise.controller;


import com.example.exercise.model.CompanyDetails;
import com.example.exercise.model.GeneralIndex;

import org.json.JSONObject;


class MyCustomEvent {

    private CompanyDetails companyDetailsData;
    private GeneralIndex generalIndexData;
    private JSONObject MarketWatchData;

    GeneralIndex getGeneralIndexData() {
        return generalIndexData;
    }

    CompanyDetails getCompanyDetailsData() {
        return companyDetailsData;
    }

    JSONObject getMarketWatchData() {
        return MarketWatchData;
    }

    MyCustomEvent(CompanyDetails companyDetailsData) {
        this.companyDetailsData = companyDetailsData;
    }

    MyCustomEvent(GeneralIndex generalIndexData) {
        this.generalIndexData = generalIndexData;
    }

    MyCustomEvent(JSONObject MarketWatchData) {
        this.MarketWatchData = MarketWatchData;
    }
}
