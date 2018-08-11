package com.example.sonja.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Erfolgsmeldung, dass eine Anfrage oder ein Angebot erstellt wurde.
 */
public class Confirm2 extends AppCompatActivity {


    /**
     * Bottom Navigation Bar
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Confirm2.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Confirm2.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Confirm2.this, account01.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    /**
     * startet die Activity Confirm2
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm2);

        // Bottom Navigation initialisieren
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Initialisiert den "weiter"-Button
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_weiter_ok:
                // auf Screen3 weiterleiten
                Intent intent = new Intent(this, Home3.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

   public void onClickConfirm (View v) {
       System.out.println("onClickConfirm aufgerufen");
       Intent intent = new Intent(this, Home3.class);
       startActivity(intent);
       this.finish();
   }

    // Bei Klick auf "zurück" wird man zum starting screen weitergeleitet
    public void onBackPressed(){
        System.out.println("Confirm onBackPressed() aufgerufen.");
        Intent intent = new Intent(this, Confirm.class);
        startActivity(intent);
        this.finish();
    }


}
