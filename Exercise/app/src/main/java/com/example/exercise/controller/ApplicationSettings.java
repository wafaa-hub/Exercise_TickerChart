package com.example.exercise.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.exercise.R;


import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationSettings extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.switchLanguage)
    TextView switchLanguage;
    @BindView(R.id.switchTheme)
    TextView switchTheme;
    @BindView(R.id.closeImage)
    ImageView close;

    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static final String Theme_Preference = "Theme Preference";
    private static final String Theme_KeyValue = "Saved Theme";
    private static SharedPreferences sharedPreferencesLanguage;
    private static  SharedPreferences sharedPreferencesThemes;

    private static SharedPreferences.Editor editorLocale;
    private static SharedPreferences.Editor editorTheme;

    private static Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_settings);
        ButterKnife.bind(this);
        init();
        setListeners();
    }

    @SuppressLint("CommitPrefEdits")
    private void init() {
        resources = getResources();
        sharedPreferencesLanguage = getSharedPreferences(Locale_Preference, Context.MODE_PRIVATE);
        editorLocale = sharedPreferencesLanguage.edit();

        sharedPreferencesThemes = getSharedPreferences(Theme_Preference, Context.MODE_PRIVATE);
        editorTheme = sharedPreferencesThemes.edit();
    }

    private void setListeners() {
        switchTheme.setOnClickListener(this);
        switchLanguage.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String lang;
        String theme;

        switch (v.getId()) {
            case R.id.switchLanguage:
                if (!Objects.equals(sharedPreferencesLanguage.getString(Locale_KeyValue, "en"), "ar")) {
                    lang = "ar";
                } else {
                    lang = "en";
                }
                changeLocale(lang);
                break;
            case R.id.switchTheme:
                if (!Objects.equals(sharedPreferencesThemes.getString(Theme_KeyValue, "light"), "night")) {
                    theme = "night";
                } else {
                    theme = "light";
                }
                setSwitchTheme();
                saveTheme(theme);
                updateTextsTheme();
                break;
            case R.id.closeImage:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    private void changeLocale(String language) {
        Locale myLocale = new Locale(language);
        saveLocale(language);
        Locale.setDefault(myLocale);
        Configuration config = resources.getConfiguration();
        config.setLocale(myLocale);
        config.setLayoutDirection(myLocale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        recreate();
        updateTextLanguage(language);
    }

    private void saveLocale(String lang) {
        editorLocale.putString(Locale_KeyValue, lang);
        editorLocale.apply();
    }

    private void saveTheme(String theme) {
        editorTheme.putString(Theme_KeyValue, theme);
        editorTheme.apply();
    }

    private void updateTextLanguage(String lang) {
        if (lang.equals("en"))
            switchLanguage.setText(R.string.ar_language);
        else if (lang.equals("ar"))
            switchLanguage.setText(R.string.en_language);

    }

    private void updateTextsTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switchTheme.setText(R.string.light_theme);
        } else {
            switchTheme.setText(R.string.dark_theme);
        }
    }

    private void setSwitchTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}