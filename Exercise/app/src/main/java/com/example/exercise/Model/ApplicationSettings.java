package com.example.exercise.Model;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercise.R;

import java.util.Locale;

import butterknife.BindView;

public class ApplicationSettings extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.switchTheme)
    TextView switchTheme;
    @BindView(R.id.switchLanguage)
    TextView switchLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_settings);

        switchTheme.setOnClickListener(this);
        switchLanguage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.switchLanguage) {
            if (switchLanguage.getText().equals(R.string.ar_language)) {
                setLocal("ar");
                switchLanguage.setText(R.string.en_language);
            } else if (switchLanguage.getText().equals(R.string.en_language)) {
                setLocal("en");
                switchLanguage.setText(R.string.ar_language);
            }
            recreate();
        } else if (v.getId() == R.id.switchTheme) {

        }
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
