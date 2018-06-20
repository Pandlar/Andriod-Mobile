package com.example.sonja.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AccountSettings03 extends AppCompatActivity implements View.OnClickListener {

    Button btn_bezahlung_java;
    Button btn_go_hilfe;
    Button btn_agbs;
    Button btn_faqs;
    Button btn_passwort;


    private BottomNavigationView.OnNavigationItemSelectedListener YYOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_avatar:
                    Intent intentAvatar = new Intent(AccountSettings03.this, account01.class);
                    startActivityForResult(intentAvatar, 0);
                    return true;
                case R.id.menu_car:
                    Intent intentCar = new Intent(AccountSettings03.this, AccountCar02.class);
                    startActivityForResult(intentCar, 0);
                    return true;
                case R.id.menu_settings:
                    Intent intentSettings = new Intent(AccountSettings03.this, AccountSettings03.class);
                    startActivityForResult(intentSettings, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings03);

        // Bottom Navigation initialisieren
        BottomNavigationView menuAccount3 = (BottomNavigationView) findViewById(R.id.menu_account);
        menuAccount3.setOnNavigationItemSelectedListener(YYOnNavigationItemSelectedListener);


        // Button: Bezahlung
        btn_bezahlung_java = findViewById(R.id.btn_bezahlung2);
        btn_bezahlung_java.setOnClickListener(this);

        btn_go_hilfe = findViewById(R.id.btn_hilfe);
        btn_go_hilfe.setOnClickListener(this);

        btn_agbs = findViewById(R.id.agbs);
        btn_agbs.setOnClickListener(this);

        btn_faqs = findViewById(R.id.faq);
        btn_faqs.setOnClickListener(this);

        btn_passwort = findViewById(R.id.passwortbutton);
        btn_passwort.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_bezahlung2:
                Intent intent = new Intent(this, Bezahlung.class);
                startActivity(intent);
                this.finish();
                break;

            case R.id.btn_hilfe:
                Intent intent2 = new Intent(this, Hilfe.class);
                startActivity(intent2);
                this.finish();
                break;

            case R.id.agbs:
                Intent intent3 = new Intent(this, Agb.class);
                startActivity(intent3);
                this.finish();
                break;

            case R.id.faq:
                Intent intent4 = new Intent(this, Faq.class);
                startActivity(intent4);
                this.finish();
                break;

            case R.id.passwortbutton:
                Intent intent5 = new Intent(this, Passwort.class);
                startActivity(intent5);
                this.finish();
                break;


        }

    }



}