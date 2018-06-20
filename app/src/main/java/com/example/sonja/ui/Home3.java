package com.example.sonja.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Home3 extends AppCompatActivity implements View.OnClickListener{

    Button btn_jetzt_bestätigen;
    Button btn_jetzt_bestätigen2;
    Button kasten_chronik;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Home3.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Home3.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Home3.this, Confirm.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btn_jetzt_bestätigen = findViewById(R.id.btn_jetzt_bestätigen);
        btn_jetzt_bestätigen.setOnClickListener(this);

        btn_jetzt_bestätigen2 = findViewById(R.id.btn_jetzt_bestätigen2);
        btn_jetzt_bestätigen2.setOnClickListener(this);

        kasten_chronik = findViewById(R.id.kasten_chronik);
        kasten_chronik.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_jetzt_bestätigen:
                // auf Home5 weiterleiten
                Intent intent = new Intent(this, Home3.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.btn_jetzt_bestätigen2:
                // auf Home5 weiterleiten
                Intent intent2 = new Intent(this, Home3.class);
                startActivity(intent2);
                this.finish();
                break;
            case R.id.kasten_chronik:
                // auf Home5 weiterleiten
                Intent intent3 = new Intent(this, Home4.class);
                startActivity(intent3);
                this.finish();
        }
    }
}