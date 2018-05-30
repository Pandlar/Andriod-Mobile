package com.example.sonja.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Confirm extends AppCompatActivity implements View.OnClickListener {

    TextView abfahrt1, ankunft1, sitze1;
    TextView abfahrt2, ankunft2, sitze2;
    Button btn_confirm;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Confirm.this, MainActivity.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Confirm.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Confirm.this, Confirm.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        // Bottom Navigation initialisieren
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        abfahrt1 = findViewById(R.id.txt_abfahrt1);
        ankunft1 = findViewById(R.id.txt_ankunft1);
        sitze1 = findViewById(R.id.txt_sitze1);

        abfahrt1.setText("08:00 Uhr");
        ankunft1.setText("09:00 Uhr");
        sitze1.setText("1");

        abfahrt2 = findViewById(R.id.txt_abfahrt2);
        ankunft2 = findViewById(R.id.txt_ankunft2);
        sitze2 = findViewById(R.id.txt_sitze2);

        abfahrt2.setText("16:00 Uhr");
        ankunft2.setText("17:00 Uhr");
        sitze2.setText("1");

        // Buttons OnClickListener
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
    }



    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_confirm:
                // auf Screen3 weiterleiten
                Intent intent = new Intent(this, Confirm2.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

        // When clicking back you get redirected to starting screen.
        public void onBackPressed() {
            System.out.println("Neue Fahrt2 onBackPressed() aufgerufen.");
            Intent intent = new Intent(this, NeueFahrt2.class);
            startActivity(intent);
            this.finish();
        }
    }

