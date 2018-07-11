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

public class Home4 extends AppCompatActivity implements View.OnClickListener{

    Button bewerten;
    TextView bewertet;
    TextView Fahrt_abgesagt;
    Button bewerten2;
    TextView bewertet2;
    TextView Fahrt_abgesagt2;
    Button kasten_aktuell;
    public static int status_fahrer = 0; //0: bewerten 1: bewertet 2: Fahrt abgesagt
    public static int status_mitfahrer = 0; //0: bewerten 1: bewertet 2: Fahrt abgesagt

    TextView textView_Ankunft_Ort;
    TextView textView_Abfahrt_Ort;
    TextView textView_Freie_Sitzplaetze;
    TextView textView_Anzahl_Freie_Sitzplaetze;
    TextView textView_Fahrer;
    TextView textView_Uhrzeit;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Home4.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Home4.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Home4.this, Confirm.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home4);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        bewerten = findViewById(R.id.bewerten);
        bewerten.setOnClickListener(this);

        bewertet = findViewById(R.id.bewertet);
        bewertet.setOnClickListener(this);

        Fahrt_abgesagt = findViewById(R.id.Fahrt_abgesagt);
        Fahrt_abgesagt.setOnClickListener(this);

        bewerten2 = findViewById(R.id.bewerten2);
        bewerten2.setOnClickListener(this);

        bewertet2 = findViewById(R.id.bewertet2);
        bewertet2.setOnClickListener(this);

        Fahrt_abgesagt2 = findViewById(R.id.Fahrt_abgesagt2);
        Fahrt_abgesagt2.setOnClickListener(this);

        kasten_aktuell = findViewById(R.id.kasten_aktuell);
        kasten_aktuell.setOnClickListener(this);

        nachStatusAnzeigen_Fahrer();
        nachStatusAnzeigen_Mitfahrer();

        //TODO  Text aus DB in Textviews einfügen
        textView_Fahrer = (TextView) findViewById(R.id.textView_Fahrer);
        textView_Fahrer.setText("Du bist Fahrer!");

        textView_Uhrzeit = (TextView) findViewById(R.id.textView_Uhrzeit);
        textView_Uhrzeit.setText("08. Juni 08:00 Uhr");

        textView_Ankunft_Ort = (TextView) findViewById(R.id.textView_Ankunft_Ort);
        textView_Ankunft_Ort.setText("Badensche Str. 50-51, 10715 Berlin");

        textView_Abfahrt_Ort = (TextView) findViewById(R.id.textView_Abfahrt_Ort);
        textView_Abfahrt_Ort.setText("Berliner Str. 30, 10715 Berlin");

        textView_Freie_Sitzplaetze = (TextView) findViewById(R.id.textView_Freie_Sitzplaetze);
        textView_Freie_Sitzplaetze.setText("Freie Sitzplätze: ");

        textView_Anzahl_Freie_Sitzplaetze = (TextView) findViewById(R.id.textView_Anzahl_Freie_Sitzplaetze);
        textView_Anzahl_Freie_Sitzplaetze.setText("1");


    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bewerten:
                // auf Bewertung2 weiterleiten
                Intent intent = new Intent(this, Bewertung2.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.bewerten2:
                // auf Bewertung1 weiterleiten
                Intent intent2 = new Intent(this, Bewertung1.class);
                startActivity(intent2);
                this.finish();
                break;
            case R.id.kasten_aktuell:
                // auf Home3 weiterleiten
                Intent intent3 = new Intent(this, Home3.class);
                startActivity(intent3);
                this.finish();
        }}

    public void nachStatusAnzeigen_Fahrer(){
        switch(status_fahrer){
            case 0:
                bewerten.setVisibility(View.VISIBLE);
                bewertet.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                break;
            case 1:
                bewerten.setVisibility(View.INVISIBLE);
                bewertet.setVisibility(View.VISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                break;
            case 2:
                bewerten.setVisibility(View.INVISIBLE);
                bewertet.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.VISIBLE);
                break;

        }
    }

    public void nachStatusAnzeigen_Mitfahrer(){
        switch(status_mitfahrer){
            case 0:
                bewerten2.setVisibility(View.VISIBLE);
                bewertet2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                bewerten2.setVisibility(View.INVISIBLE);
                bewertet2.setVisibility(View.VISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                bewerten2.setVisibility(View.INVISIBLE);
                bewertet2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.VISIBLE);
                break;

        }
    }

}