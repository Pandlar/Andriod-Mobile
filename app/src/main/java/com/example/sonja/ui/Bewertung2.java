package com.example.sonja.ui;

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

import org.json.JSONArray;

public class Bewertung2 extends AppCompatActivity implements View.OnClickListener{

    Button btn_Veroeffentlichen2;
    EditText bewertung;
    RatingBar stars;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Bewertung2.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Bewertung2.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Bewertung2.this, Confirm.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung2);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btn_Veroeffentlichen2 = findViewById(R.id.btn_Veroeffentlichen2);
        btn_Veroeffentlichen2.setOnClickListener(this);

    }
    public void onClick(View v)  {
        switch (v.getId()) {

            case R.id.btn_Veroeffentlichen2:

                try {
                HttpTest httpRatingPost = new HttpTest();

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                String mail = sharedPrefs.getString(getString(R.string.saveEmail),"no mail");
                System.out.println(mail);

                // getting user id with email
                String json = httpRatingPost.sendGet("user", "email", mail, "eq", "");
                JSONArray arr = new JSONArray(json);
                String id = arr.getJSONObject(0).getString("id");

                bewertung = findViewById(R.id.editText);
                String bewertungString = bewertung.getText().toString();
                stars = findViewById(R.id.ratingBar);
                int starsInt = stars.getNumStars();

                //TODO home4 etc. mit DB synchronisieren, dass die Daten daraus gezogen und auf den Screen übertragen werden
                String createdBy = id;
                String ratedUserId = "6a737ef5-4095-4ce3-9e02-0c3d4b9c0539";
                String matchID = "cbbb7972-97a6-4a12-b6a1-864f2dd7f2e3";

                Log.d("Bewertung", " " + bewertungString);
                System.out.println("Stars: " + starsInt);

                httpRatingPost.sendPostRating("rating", bewertungString, createdBy,
                        ratedUserId, matchID, starsInt);


                } catch (Exception e) {
                    e.printStackTrace();
                }
                // auf Home1 weiterleiten
                Intent intent = new Intent(this, Home1.class);
                startActivity(intent);
                this.finish();
        }
    }
}