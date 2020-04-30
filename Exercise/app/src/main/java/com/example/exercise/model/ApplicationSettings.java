package com.example.exercise.model;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.exercise.R;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApplicationSettings extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.switchLanguage) TextView switchLanguage;
    @BindView(R.id.switchTheme) TextView switchTheme;
    @BindView(R.id.closeImage) ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_settings);
        ButterKnife.bind(this);


        switchTheme.setOnClickListener(this);
        switchLanguage.setOnClickListener(this);
        close.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.switchLanguage:
                if (switchLanguage.getText().equals(R.string.ar_language)) {
                    setLocal("ar");
                    switchLanguage.setText(R.string.en_language);
                } else if (switchLanguage.getText().equals(R.string.en_language)) {
                    setLocal("en");
                    switchLanguage.setText(R.string.ar_language);
                }
                recreate();
                break;
            case R.id.switchTheme:

                if (switchTheme.getText().equals(R.string.light_theme)) {
                    setSwitchTheme(true);
                    switchTheme.setText(R.string.dark_theme);
                } else if (switchTheme.getText().equals(R.string.dark_theme)) {
                    setSwitchTheme(false);
                    switchTheme.setText(R.string.light_theme);
                }
                recreate();
                break;
            case R.id.closeImage:
                finish();
                break;
        }
    }

    private  void setSwitchTheme(boolean check)
    {
        if(check)
        {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        SharedPreferences.Editor editor = getSharedPreferences("SwitchTheme", MODE_PRIVATE).edit();
        editor.putBoolean("theme", check);
        editor.apply();
    }

    private void setLocal(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("Language", language);
        editor.apply();

    }
}
