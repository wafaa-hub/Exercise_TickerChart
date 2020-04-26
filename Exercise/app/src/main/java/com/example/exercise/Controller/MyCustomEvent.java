package com.example.exercise.Controller;


import org.json.JSONArray;

public class MyCustomEvent {

    private  String fragmentsData;
    private  JSONArray MarketWatchData;

    public String getFragmentsData() {
        return fragmentsData;
    }

    public JSONArray getMarketWatchData() {
        return MarketWatchData;
    }
    
    public MyCustomEvent(String fragmentsData) {
        this.fragmentsData = fragmentsData;
    }

    public MyCustomEvent(JSONArray MarketWatchData) {
        this.MarketWatchData = MarketWatchData;
    }
}
