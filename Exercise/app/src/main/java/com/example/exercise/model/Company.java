package com.example.exercise.model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class Company {
    private JSONObject company;
    private String companyName;
    private double askPrice;
    private double lastPrice;
    private double bidPrice;
    private double highPrice;

    public String getCompanyName() {
        return companyName;
    }

    double getAskPrice() {
        return askPrice;
    }

    double getLastPrice() {
        return lastPrice;
    }

    double getBidPrice() {
        return bidPrice;
    }

    double getHighPrice() {
        return highPrice;
    }

    public Company(JSONObject copmany) throws JSONException {
        this.company = copmany;
        companyName = copmany.get("name").toString();
        askPrice = Double.parseDouble(copmany.get("ask-price").toString());
        lastPrice = Double.parseDouble(copmany.get("last-price").toString());
        bidPrice = Double.parseDouble(copmany.get("bid-price").toString());
        highPrice = Double.parseDouble(copmany.get("high-price").toString());

    }

    @NotNull
    @Override
    public String toString() {
        return "CompanyDetails{" +
                "copmanyDetails=" + company +
                ", companyName='" + companyName + '\'' +
                ", askPrice='" + askPrice + '\'' +
                ", lastPrice='" + lastPrice + '\'' +
                ", bidPrice='" + bidPrice + '\'' +
                ", highPrice='" + highPrice + '\'' +
                '}';
    }
}
