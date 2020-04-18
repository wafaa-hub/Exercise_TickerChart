package com.example.exercise;

import org.json.JSONException;
import org.json.JSONObject;

public class CompanyDetails {
    JSONObject copmanyDetails;
    String companyName;
    double askPrice;
    double lastPrice;
    double bidPrice;
    double highPrice;

    public String getCompanyName() {
        return companyName;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public CompanyDetails(JSONObject copmanyDetails) throws JSONException {
        this.copmanyDetails = copmanyDetails;
        companyName = copmanyDetails.get("name").toString();
        askPrice  = Double.parseDouble( copmanyDetails.get("ask-price").toString());
        lastPrice = Double.parseDouble( copmanyDetails.get("last-price").toString());
        bidPrice  = Double.parseDouble(copmanyDetails.get("bid-price").toString());
        highPrice = Double.parseDouble(copmanyDetails.get("high-price").toString());

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
