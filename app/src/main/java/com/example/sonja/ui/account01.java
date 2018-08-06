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
import android.widget.TextView;

import com.example.sonja.ui.asyncTasks.GetRequestAsync;
import com.example.sonja.ui.asyncTasks.GetRequestParams;
import com.example.sonja.ui.asyncTasks.GetUserAsync;
import com.example.sonja.ui.asyncTasks.GetUserParams;

import org.json.JSONArray;

public class account01 extends AppCompatActivity implements View.OnClickListener{


    public EditText handynummerET;
    public EditText nameET;
    public EditText vornameET;
    public EditText adresseET;

    /**
     * Top Navigation Liste.
     * Weiterleitung an Darstellung der Auto-Informationen und die Account-Einstellungen.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_avatar:
                    Intent intentAvatar = new Intent(account01.this, account01.class);
                    startActivityForResult(intentAvatar, 0);
                    return true;
                case R.id.menu_car:
                    Intent intentCar = new Intent(account01.this, AccountCar02.class);
                    startActivityForResult(intentCar, 0);
                    return true;
                case R.id.menu_settings:
                    Intent intentSettings = new Intent(account01.this, AccountSettings03.class);
                    startActivityForResult(intentSettings, 0);
                    return true;
            }
            return false;
        }
    };

    /**
     * Bottom Navigation Leiste.
     * Weiterleitung an Home3.java (bevorstehende Fahrten), NeueFahrt1.java (Neue Fahrt erstellen) oder den Account-Screen.
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mListenerBottomBar
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    System.out.println("Switch-Case für Home aufgerufen");
                    Intent intentHome = new Intent(account01.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(account01.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(account01.this, account01.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    /**
     * Zieht sich die Nutzerdaten aus der Datenbank mit dern GetUserAsync Abfrage.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account01);

// top Navigation initialisieren
        BottomNavigationView menuAccount = findViewById(R.id.menu_account);
        menuAccount.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationView menuBottom = findViewById(R.id.navigation);
        menuBottom.setOnNavigationItemSelectedListener(mListenerBottomBar);

        handynummerET = findViewById(R.id.handynummer_input);
        nameET = findViewById(R.id.name_input);
        vornameET = findViewById(R.id.vorname_input);
        adresseET = findViewById(R.id.strassehausnummer_input);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String uuid = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");

        try{
            GetUserParams paramsOfRequest = new GetUserParams(uuid);
            GetUserAsync asyncGetRequestFuture = new GetUserAsync();

            JSONArray arr = asyncGetRequestFuture.execute(paramsOfRequest).get();

            String handynummer = arr.getJSONObject(0).getString("phoneNumber");
            String name = arr.getJSONObject(0).getString("surname");
            String vorname = arr.getJSONObject(0).getString("name");
            String adresse = arr.getJSONObject(0).getString("homeAddress");

            System.out.println("Daten zurückgegeben: Handynr: "+ handynummer + ", Name: " + name + ", Vorname: " + vorname + ", Adresse: " + adresse);

            handynummerET.setText(handynummer);
            nameET.setText(name);
            vornameET.setText(vorname);
            adresseET.setText(adresse);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

    }

}
