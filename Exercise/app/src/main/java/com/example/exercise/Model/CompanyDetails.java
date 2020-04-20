package com.example.exercise.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class CompanyDetails {

    JSONObject companyDetails;
    String company_name;
    String symbol;
    String trades_count;
    String high;
    String low;
    String volume;
    String amount;

    public String getCompany_name() {
        return company_name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTrades_count() {
        return trades_count;
    }

    public String getHigh() { return high; }

    public String getLow() {
        return low;
    }

    public String getVolume() {
        return volume;
    }

    public String getAmount() {
        return amount;
    }

    public CompanyDetails(JSONObject companyDetails) throws JSONException {

        this.companyDetails = companyDetails;
        company_name = companyDetails.get("company-name").toString();
        symbol = companyDetails.get("symbol").toString();
        trades_count = companyDetails.get("trades-count").toString();
        high = companyDetails.get("high").toString();
        low = companyDetails.get("low").toString();
        volume = companyDetails.get("volume").toString();
        amount = companyDetails.get("amount").toString();
    }

    @Override
    public String toString() {
        return "CompanyDetails{" +
                "companyDetails=" + companyDetails +
                ", company-name='" + company_name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", trades-count='" + trades_count + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", volume='" + volume + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
