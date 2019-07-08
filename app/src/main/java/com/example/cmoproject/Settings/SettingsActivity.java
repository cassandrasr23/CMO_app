package com.example.cmoproject.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cmoproject.EditProfile.EditProfileActivity;
import com.example.cmoproject.Home.HomeActivity;
import com.example.cmoproject.R;

public class SettingsActivity extends AppCompatActivity{

   private Spinner currency;
   private Spinner language;
   private Button editprofile;
   private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currency = findViewById(R.id.CurrencySpinner);
        language = findViewById(R.id.LanguageSpinner);
        editprofile = findViewById(R.id.Editprofile);
        back = findViewById(R.id.back);


        String[] currency_values = new String[]{"Euro €", "Dollar $"};
        String[] language_values = new String[]{"English", "Portuguese"};

        ArrayAdapter<String> adapterC = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, currency_values);
        currency.setAdapter(adapterC);

        ArrayAdapter<String> adapterL = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, language_values);
        language.setAdapter(adapterL);


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, EditProfileActivity.class);
                startActivity(intent);

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });
    }

}
