package com.example.exercise.model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class CompanyDetails {

    private JSONObject companyDetails;
    private String company_name;
    private String symbol;
    private String trades_count;
    private String high;
    private String low;
    private String volume;
    private String amount;

    public String getCompany_name() {
        return company_name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getTrades_count() {
        return trades_count;
    }

    public String getHigh() {
        return high;
    }

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
        volume = checkNumericValue(companyDetails.get("volume").toString());
        amount = checkNumericValue(companyDetails.get("amount").toString());
    }

    @NotNull
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

    private String checkNumericValue(String value) {
        String numValue;
        if (!value.equals("#")) {
            numValue = formatLastValue(value);
        } else {
            numValue = value;
        }
        return numValue;
    }

    private String formatLastValue(String formattedValue) {
        String pattern = "###,###.###";
        DecimalFormat formatter = new DecimalFormat(pattern);
        double parseValue = Double.parseDouble(formattedValue);
        formattedValue = formatter.format(parseValue);
        return formattedValue;

    }
}
