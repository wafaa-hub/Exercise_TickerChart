package com.example.exercise;


import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class CompanyDetails  {
    JSONObject copmanyDetails;
    String     companyName;
    String     askPrice;
    String     lastPrice;
    String     bidPrice;
    String     highPrice;

     public CompanyDetails(){

     }

    public String getCompanyName() {
        return companyName;
    }

    public String getAskPrice() {
        return askPrice;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public String getBidPrice() {
        return bidPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public CompanyDetails(JSONObject copmanyDetails) throws JSONException {
        this.copmanyDetails = copmanyDetails;
        companyName =         copmanyDetails.get("name").toString();
        askPrice =            copmanyDetails.get("ask-price").toString();
        lastPrice =           copmanyDetails.get("last-price").toString();
        bidPrice =            copmanyDetails.get("bid-price").toString();
        highPrice =           copmanyDetails.get("high-price").toString();

    }

    public JSONObject updateCompany(JSONObject copmanyDetails) throws Exception {
        Iterator keys = copmanyDetails.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            switch (key) {
                case "name":
                    this.companyName = copmanyDetails.getString("date");
                    break;
                case "ask-price":
                    this.askPrice = copmanyDetails.getString("askprice");
                    break;
                case "last-price":
                    this.lastPrice = copmanyDetails.getString("gclose");
                    break;
                case "bid-price":
                    this.bidPrice = copmanyDetails.getString("gclose");
                    break;
                case "high-price":
                    this.highPrice = copmanyDetails.getString("gclose");
                    break;
            }
        }
        return  copmanyDetails;
    }

    @Override
    public String toString() {
        return "CompanyDetails{" +
                "copmanyDetails=" + copmanyDetails +
                ", companyName='" + companyName + '\'' +
                ", askPrice='" + askPrice + '\'' +
                ", lastPrice='" + lastPrice + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                ", highPrice='" + highPrice + '\'' +
                '}';
    }
}
