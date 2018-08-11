package com.example.sonja.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.sonja.ui.asyncTasks.GetUserAsync;
import com.example.sonja.ui.asyncTasks.GetUserParams;

import org.json.JSONArray;

/**
 * Übersicht über das Auto
 */
public class AccountCar02 extends AppCompatActivity implements View.OnClickListener {

    public EditText markeET;
    public EditText farbeET;
    public EditText kennzeichenET;
    public EditText sitzplätzeET;

    /**
     * Top Navigation Bar.
     * Weiterleitung an Hauptprofilscreen und die Account Settings.
     */
        private BottomNavigationView.OnNavigationItemSelectedListener XXOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_avatar:
                        Intent intentAvatar = new Intent(AccountCar02.this, account01.class);
                        startActivityForResult(intentAvatar, 0);
                        return true;
                    case R.id.menu_car:
                        Intent intentCar = new Intent(AccountCar02.this, AccountCar02.class);
                        startActivityForResult(intentCar, 0);
                        return true;
                    case R.id.menu_settings:
                        Intent intentSettings = new Intent(AccountCar02.this, AccountSettings03.class);
                        startActivityForResult(intentSettings, 0);
                        return true;
                }
                return false;
            }
        };

    /**
     * Bottom Navigation View mit Weiterleitung an Home3.class, NeueFahrt1.class und account01.class
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Switch-Case für Home aufgerufen");
                    Intent intentHome = new Intent(AccountCar02.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(AccountCar02.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(AccountCar02.this, account01.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    /**
     * Zieht Autoinformationen aus Datenbank, indem auf die User-Tabelle zugegriffen wird.
     * @param savedInstanceState
     */
            @Override
            /**
             * Erstellt das Layout der angegebenen xhtml-Datei
             */
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_account_car02);

                // Bottom Navigation initialisieren
                BottomNavigationView menuAccount2 = findViewById(R.id.menu_account);
                menuAccount2.setOnNavigationItemSelectedListener(XXOnNavigationItemSelectedListener);

                BottomNavigationView menuBottom = findViewById(R.id.navigation);
                menuBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

                markeET = findViewById(R.id.marke_input);
                farbeET = findViewById(R.id.farbe_input);
                kennzeichenET = findViewById(R.id.kennzeichen_input);
                sitzplätzeET = findViewById(R.id.anzahlSitzplaetze_input);

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                String uuid = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");

                try{
                    GetUserParams paramsOfRequest = new GetUserParams(uuid);
                    GetUserAsync asyncGetRequestFuture = new GetUserAsync();

                    JSONArray arr = asyncGetRequestFuture.execute(paramsOfRequest).get();

                    String marke = arr.getJSONObject(0).getString("vehicleBrand");
                    String farbe = arr.getJSONObject(0).getString("vehicleColour");
                    String kennzeichen = arr.getJSONObject(0).getString("vehicleLicensePlate");

                    System.out.println("Daten zurückgegeben: Marke: "+ marke +  ", Farbe: " + farbe + ", Kennzeichen: " + kennzeichen);

                    markeET.setText(marke);
                    farbeET.setText(farbe);
                    kennzeichenET.setText(kennzeichen);
                    sitzplätzeET.setText("1");

                } catch (Exception e){
                    e.printStackTrace();
                }
            }

    @Override
    public void onClick(View v) {

    }
}

