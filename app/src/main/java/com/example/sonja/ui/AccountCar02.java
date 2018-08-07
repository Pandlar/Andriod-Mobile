package com.example.sonja.ui;

import android.accounts.Account;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class AccountCar02 extends AppCompatActivity implements View.OnClickListener {

    /**
     *initialisiert die Navigation-Bar am oberen Rand und weist den einzelnen Buttons Funktionen bzw. xhtml-Dateien zu, die bei Klick ausgewaehlt werden
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

            @Override
            /**
             * Erstellt das Layout der angegebenen xhtml-Datei
             */
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_account_car02);

                // Bottom Navigation initialisieren
                BottomNavigationView menuAccount2 = (BottomNavigationView) findViewById(R.id.menu_account);
                menuAccount2.setOnNavigationItemSelectedListener(XXOnNavigationItemSelectedListener);
            }

    @Override
    public void onClick(View v) {

    }
}

