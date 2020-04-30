package com.example.exercise.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Company {
    JSONObject copmany;
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

    public Company(JSONObject copmany) throws JSONException {
        this.copmany = copmany;
        companyName = copmany.get("name").toString();
        askPrice = Double.parseDouble(copmany.get("ask-price").toString());
        lastPrice = Double.parseDouble(copmany.get("last-price").toString());
        bidPrice = Double.parseDouble(copmany.get("bid-price").toString());
        highPrice = Double.parseDouble(copmany.get("high-price").toString());

    }

    @Override
    public String toString() {
        return "CompanyDetails{" +
                "copmanyDetails=" + copmany +
                ", companyName='" + companyName + '\'' +
                ", askPrice='" + askPrice + '\'' +
                ", lastPrice='" + lastPrice + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                ", highPrice='" + highPrice + '\'' +
                '}';
    }
}
