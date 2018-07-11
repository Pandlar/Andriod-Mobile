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
    TextView bestätigt2;
    TextView noch_offen2;
    TextView Fahrt_abgesagt2;
    Button kasten_chronik;

    TextView textView_Ankunft_Ort;
    TextView textView_Abfahrt_Ort;
    TextView textView_Freie_Sitzplaetze;
    TextView textView_Anzahl_Freie_Sitzplaetze;
    TextView textView_Fahrer;
    TextView textView_Uhrzeit;

    public static int status_fahrer = 2; //0: noch offen 1: bestätigen 2: bestätigt 3: Fahrt abgesagt
    public static int status_mitfahrer = 2; //0: noch offen 1: bestätigen 2: bestätigt 3: Fahrt abgesagt

    /**
     * initialisiert die Button Navigation Bar
     */
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

        bestätigt2 = findViewById(R.id.bestätigt2);
        bestätigt2.setOnClickListener(this);

        noch_offen2 = findViewById(R.id.noch_offen2);
        noch_offen2.setOnClickListener(this);

        Fahrt_abgesagt2 = findViewById(R.id.Fahrt_abgesagt2);
        Fahrt_abgesagt2.setOnClickListener(this);

        kasten_chronik = findViewById(R.id.kasten_chronik);
        kasten_chronik.setOnClickListener(this);

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

            case R.id.btn_jetzt_bestätigen:
                status_fahrer = 2;
                nachStatusAnzeigen_Fahrer();
                break;
            case R.id.btn_jetzt_bestätigen2:
                status_mitfahrer = 2;
                nachStatusAnzeigen_Mitfahrer();
                break;
            case R.id.kasten_chronik:
                // auf Home4 weiterleiten
                Intent intent3 = new Intent(this, Home4.class);
                startActivity(intent3);
                this.finish();
        }
    }

    /**
     * zeigt den Status der Fahrt an für Fahrer(noch offen, bestätigt, noch bestätigen, Fahrt abgesagt)
     */
    public void nachStatusAnzeigen_Fahrer(){
        switch(status_fahrer){
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

        }}

    /**
     * zeigt den Status der Fahrt an für Mitfahrer(noch offen, bestätigt, jetzt bestätigen, Fahrt abgesagt)
     */
    public void nachStatusAnzeigen_Mitfahrer(){
        switch(status_mitfahrer){
            case 0:
                btn_jetzt_bestätigen2.setVisibility(View.INVISIBLE);
                bestätigt2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                noch_offen2.setVisibility(View.VISIBLE);
                break;
            case 1:
                btn_jetzt_bestätigen2.setVisibility(View.VISIBLE);
                bestätigt2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                noch_offen2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                btn_jetzt_bestätigen2.setVisibility(View.INVISIBLE);
                bestätigt2.setVisibility(View.VISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                noch_offen2.setVisibility(View.INVISIBLE);
                break;
            case 3:
                btn_jetzt_bestätigen2.setVisibility(View.INVISIBLE);
                bestätigt2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.VISIBLE);
                noch_offen2.setVisibility(View.INVISIBLE);
                break;


        }
    }
}