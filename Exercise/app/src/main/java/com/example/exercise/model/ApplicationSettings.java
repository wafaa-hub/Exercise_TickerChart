package com.example.exercise.model;

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

import com.example.exercise.controller.MainActivity;
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
    private static SharedPreferences sharedPreferencesThemes;

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
        loadLocale();

    }

    private void init() {
        resources = getResources();
        sharedPreferencesLanguage = getSharedPreferences(Locale_Preference, MODE_PRIVATE);
        editorLocale = sharedPreferencesLanguage.edit();

        sharedPreferencesThemes = getSharedPreferences(Theme_Preference, MODE_PRIVATE);
        editorTheme = sharedPreferencesThemes.edit();
    }

    private void setListeners() {
        switchTheme.setOnClickListener(this);
        switchLanguage.setOnClickListener(this);
        close.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String lang = "en";
        String theme = "light";
        switch (v.getId()) {
            case R.id.switchLanguage:
                if (Objects.equals(sharedPreferencesLanguage.getString(Locale_KeyValue, "en"), "ar")) {
                    lang = "en";
                } else {
                    lang = "ar";
                }
                changeLocale(lang);
                recreate();
                break;
            case R.id.switchTheme:
                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                    theme = "night";
                } else {
                    theme = "light";
                }
               setSwitchTheme(theme);
                break;
            case R.id.closeImage:
                startActivity(new Intent(this,MainActivity.class));
                finish();
                break;
        }
    }

    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        Locale myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = resources.getConfiguration();
        config.setLocale(myLocale);
        config.setLayoutDirection(myLocale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        updateTextLanguage(lang);
    }

    public void saveLocale(String lang) {
        editorLocale.putString(Locale_KeyValue, lang);
        editorLocale.apply();
    }

    public void saveTheme(String theme) {
        editorTheme.putString(Theme_KeyValue, theme);
        editorTheme.apply();
    }

    public void loadLocale() {
        String language = sharedPreferencesLanguage.getString(Locale_KeyValue, "");
        assert language != null;
        changeLocale(language);

    }

    private void updateTextLanguage(String lang) {
        if (lang.equals("en"))
            switchLanguage.setText(R.string.ar_language);
        else if (lang.equals("ar"))
            switchLanguage.setText(R.string.en_language);
    }

    private void updateTextsTheme() {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
            switchTheme.setText(R.string.light_theme);
        else
            switchTheme.setText(R.string.dark_theme);
    }

    private void setSwitchTheme(String theme) {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        updateTextsTheme();
        saveTheme(theme);

    }

}