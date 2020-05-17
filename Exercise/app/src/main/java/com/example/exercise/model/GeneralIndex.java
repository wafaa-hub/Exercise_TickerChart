package com.example.exercise.model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;


public class GeneralIndex {

    private JSONObject generalIndex;

    private String name;
    private String trades;
    private String winning;
    private String losing;
    private String fixed;
    private String volume;
    private String amount;

    public String getName() {
        return name;
    }

    public String getTrades() {
        return trades;
    }

    public String getWinning() {
        return winning;
    }

    public String getLosing() {
        return losing;
    }

    public String getFixed() {
        return fixed;
    }

    public String getVolume() {
        return volume;
    }

    public String getAmount() {
        return amount;
    }

    public GeneralIndex(JSONObject generalIndex) throws JSONException {

        this.generalIndex = generalIndex;
        name = generalIndex.get("name").toString();
        trades = checkNumericValue(generalIndex.get("trades").toString());
        String companies = generalIndex.get("companies").toString();
        volume = checkNumericValue(generalIndex.get("volume").toString());
        amount = checkNumericValue(generalIndex.get("amount").toString());
        JSONObject jsonObject = new JSONObject(companies);
        losing = jsonObject.get("losing").toString();
        fixed = jsonObject.get("fixed").toString();
        winning = jsonObject.get("winning").toString();

    }

    @NotNull
    @Override
    public String toString() {
        return "GeneralIndex{" +
                "companyDetails=" + generalIndex +
                ", trades=" + trades +
                ", winning=" + winning +
                ", losing=" + losing +
                ", fixed=" + fixed +
                ", volume=" + volume +
                ", amount=" + amount +
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
