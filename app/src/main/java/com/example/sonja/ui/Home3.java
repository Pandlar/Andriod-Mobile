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
import android.widget.TextView;

public class Home3 extends AppCompatActivity implements View.OnClickListener{

    Button btn_jetzt_bestätigen;
    TextView bestätigt;
    TextView noch_offen;
    TextView Fahrt_abgesagt;
    Button btn_jetzt_bestätigen2;
    TextView Fahrt_abgesagt2;
    Button kasten_chronik;
    public static int status = 3; //0: noch offen 1: bestätigen 2: bestätigt 3: Fahrt abgesagt


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

        bestätigt = findViewById(R.id.bestätigt);
        bestätigt.setOnClickListener(this);

        noch_offen = findViewById(R.id.noch_offen);
        noch_offen.setOnClickListener(this);

        Fahrt_abgesagt = findViewById(R.id.Fahrt_abgesagt);
        Fahrt_abgesagt.setOnClickListener(this);

        btn_jetzt_bestätigen2 = findViewById(R.id.btn_jetzt_bestätigen2);
        btn_jetzt_bestätigen2.setOnClickListener(this);

        Fahrt_abgesagt2 = findViewById(R.id.Fahrt_abgesagt2);
        Fahrt_abgesagt2.setOnClickListener(this);

        kasten_chronik = findViewById(R.id.kasten_chronik);
        kasten_chronik.setOnClickListener(this);

        nachStatusAnzeigen();
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_jetzt_bestätigen:
                // auf Home5 weiterleiten
                Intent intent = new Intent(this, Home3.class);
                startActivity(intent);
                this.finish();
                status = 2;
                nachStatusAnzeigen();
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

    public void nachStatusAnzeigen(){
        switch(status){
            case 0:
                btn_jetzt_bestätigen.setVisibility(View.INVISIBLE);
                bestätigt.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                noch_offen.setVisibility(View.VISIBLE);
                break;
            case 1:
                btn_jetzt_bestätigen.setVisibility(View.VISIBLE);
                bestätigt.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                noch_offen.setVisibility(View.INVISIBLE);
                break;
            case 2:
                btn_jetzt_bestätigen.setVisibility(View.INVISIBLE);
                bestätigt.setVisibility(View.VISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                noch_offen.setVisibility(View.INVISIBLE);
                break;
            case 3:
                btn_jetzt_bestätigen.setVisibility(View.INVISIBLE);
                bestätigt.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.VISIBLE);
                noch_offen.setVisibility(View.INVISIBLE);
                break;

        }
    }
}