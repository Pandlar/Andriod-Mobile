package com.example.sonja.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

public class Bewertung1 extends AppCompatActivity implements View.OnClickListener{

    Button btn_Veroeffentlichen;
    EditText bewertung;
    RatingBar stars;
    private Context mContext;

//* Navigation Bar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Bewertung1.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Bewertung1.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Bewertung1.this, Confirm.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung1);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btn_Veroeffentlichen = findViewById(R.id.btn_Veroeffentlichen);
        btn_Veroeffentlichen.setOnClickListener(this);


    }

    //* Die Bewertung wird in die Datenbank gespeichert zu der zugehörigen Fahrt.
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Veroeffentlichen:

                bewertung = findViewById(R.id.bewertung_text);
                Log.d("Bewertung", " " + bewertung.getText().toString());

                stars = findViewById(R.id.ratingBar);
                System.out.println("Stars: " + stars.getNumStars());

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                sharedPrefs.getString(getString(R.string.saveEmail),"no mail");

                // auf Bewertung2 weiterleiten
                Intent intent = new Intent(this, Bewertung2.class);
                startActivity(intent);
                this.finish();
        }
    }
}