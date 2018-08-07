package com.example.sonja.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class account01 extends AppCompatActivity implements View.OnClickListener{



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        /**
         *initialisiert die Navigation-Bar am oberen Rand und weist den einzelnen Buttons Funktionen bzw. xhtml-Dateien zu, die bei Klick ausgewaehlt werden
         */
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









    @Override
    /**
     * Erstellt das Layout der angegebenen xhtml-Datei
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account01);

// top Navigation initialisieren
        BottomNavigationView menuAccount = (BottomNavigationView) findViewById(R.id.menu_account);
        menuAccount.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        // bottom navigation initialisieren





    }



    @Override
    public void onClick(View v) {

    }



}
