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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sonja.ui.asyncTasks.GetRequestAsync;
import com.example.sonja.ui.asyncTasks.GetRequestParams;

import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Home4 extends AppCompatActivity implements View.OnClickListener{

    Button bewerten;
    TextView bewertet;
    TextView Fahrt_abgesagt;
    Button bewerten2;
    TextView bewertet2;
    TextView Fahrt_abgesagt2;
    Button kasten_aktuell;

    //erstes Feld
    public static int status_fahrer = 0; //0: bewerten 1: bewertet 2: Fahrt abgesagt 3: leer
    //zweites Feld
    public static int status_mitfahrer = 0; //0: bewerten 1: bewertet 2: Fahrt abgesagt 3: leer

    //Textviews für ersten Fahrteneintrag
    TextView textView_Ankunft_Ort;
    TextView textView_Abfahrt_Ort;
    TextView textView_Freie_Sitzplaetze;
    TextView textView_Anzahl_Freie_Sitzplaetze;
    TextView textView_Keine_Sitzplaetze;
    TextView textView_Fahrer;
    TextView textView_Mitfahrer;
    TextView textView_Uhrzeit;

    //Textviews für zweiten Fahrteneintrag
    TextView textView_Ankunft_Ort2;
    TextView textView_Abfahrt_Ort2;
    TextView textView_Freie_Sitzplaetze2;
    TextView textView_Anzahl_Freie_Sitzplaetze2;
    TextView textView_Keine_Sitzplaetze2;
    TextView textView_Fahrer2;
    TextView textView_Mitfahrer2;
    TextView textView_Uhrzeit2;


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
                    Intent intentProfile = new Intent(Home4.this, account01.class);
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

        System.out.println("Home4.java aufgerufen");


        BottomNavigationView navigation = findViewById(R.id.navigation);
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

        //erster Eintrag auf Screen
        textView_Fahrer = findViewById(R.id.textView_Fahrer);
        textView_Mitfahrer = findViewById(R.id.textView_Mitfahrer);
        textView_Uhrzeit = findViewById(R.id.textView_Uhrzeit);

        textView_Abfahrt_Ort = findViewById(R.id.textView_Ankunft_Ort);
        textView_Ankunft_Ort = findViewById(R.id.textView_Abfahrt_Ort);
        textView_Freie_Sitzplaetze = findViewById(R.id.textView_Freie_Sitzplaetze);
        textView_Anzahl_Freie_Sitzplaetze = findViewById(R.id.textView_Anzahl_Freie_Sitzplaetze);
        textView_Keine_Sitzplaetze = findViewById(R.id.textView_Keine_Sitzplaetze);

        //zweiter Eintrag auf Screen
        textView_Fahrer2 = findViewById(R.id.textView_Fahrer2);
        textView_Mitfahrer2 = findViewById(R.id.textView_Mitfahrer2);
        textView_Uhrzeit2 = findViewById(R.id.textView_Uhrzeit2);

        textView_Abfahrt_Ort2 = findViewById(R.id.textView_Ankunft_Ort2);
        textView_Ankunft_Ort2 = findViewById(R.id.textView_Abfahrt_Ort2);
        textView_Freie_Sitzplaetze2 = findViewById(R.id.textView_Freie_Sitzplaetze2);
        textView_Anzahl_Freie_Sitzplaetze2 = findViewById(R.id.textView_Anzahl_Freie_Sitzplaetze2);
        textView_Keine_Sitzplaetze2 = findViewById(R.id.textView_Keine_Sitzplaetze2);


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String uuid = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");

        System.out.println("We are in Home4.java now and the uuid is " + uuid);

        try {

            HttpTest httpUUIDTest = new HttpTest();
            //String json = httpUUIDTest.sendGet("ridesPast", "userId", uuid, "eq", "&order=date.desc,latestArrivalTime.desc");

            GetRequestParams paramsOfRequest = new GetRequestParams("ridesPast", uuid, "&order=date.desc,latestArrivalTime.desc");
            GetRequestAsync asyncGetRequestFuture = new GetRequestAsync();
            JSONArray arr = asyncGetRequestFuture.execute(paramsOfRequest).get();

            String role1 = arr.getJSONObject(0).getString("role");
            String home1 = arr.getJSONObject(0).getString("homeAddress");
            String work1 = arr.getJSONObject(0).getString("officeAddress");
            String date1 = arr.getJSONObject(0).getString("date");
            String time1 = arr.getJSONObject(0).getString("latestArrivalTime");
            String direction1 = arr.getJSONObject(0).getString("direction");
            String seats1 = arr.getJSONObject(0).getString("seats");
            String status1 = arr.getJSONObject(0).getString("status");
            System.out.println("Erster Datensatz: \nrole: " + role1 + ", home: " + home1 + ", work: " + work1 + ", date: " + date1 + ", Time: " + time1 + ", Direction: " +  direction1);

            System.out.println("Länge der Abfrage: " + arr.length());

            //Date formatieren
            String dateTime1 = date1 + " " + time1;
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssX", Locale.GERMAN);
            Date dateFormatted1 = format.parse(dateTime1);
            System.out.println("Formatiertes Datum: " + dateFormatted1);

            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy, HH:mm");
            String dateView1 = DATE_FORMAT.format(dateFormatted1);
            System.out.println("New Date Format: " + dateView1);

            if (role1.equals("driver")){
                textView_Fahrer.setText("Fahrer");
                textView_Freie_Sitzplaetze.setText("Freie Sitzplätze: ");
                textView_Anzahl_Freie_Sitzplaetze.setText(seats1);

            } else if (role1.equals("passenger")){
                textView_Fahrer.setText("Mitfahrer");
                textView_Freie_Sitzplaetze.setText("");
                textView_Anzahl_Freie_Sitzplaetze.setText("");

                // es gibt momentan keine anderen Werte
                switch (status1) {
                    case "not answered": status_fahrer=0;
                        break;
                }
                nachStatusAnzeigen_Fahrer();
            } else {
                textView_Fahrer.setText("");
                textView_Freie_Sitzplaetze.setText("");}


            if (direction1.equals("towards Home")){
                textView_Abfahrt_Ort.setText(work1);
                textView_Ankunft_Ort.setText(home1);
            } else if (direction1.equals("towards Office")){
                textView_Abfahrt_Ort.setText(home1);
                textView_Ankunft_Ort.setText(work1);
            }
            textView_Uhrzeit.setText(dateView1 + " Uhr");

            // es gibt momentan keine anderen Werte
            switch (status1) {
                case "not answered": status_fahrer=0;
                    break;
            }

            if(arr.length()==0){
                status_fahrer=3;
                status_mitfahrer=3;
                }

            if(arr.length()==1){
                status_mitfahrer=3;
                }
            nachStatusAnzeigen_Mitfahrer();
            nachStatusAnzeigen_Fahrer();

            System.out.println("Anfang Status Mitfahrer: " + status_mitfahrer);

            if(arr.length()>=2){
                String role2 = arr.getJSONObject(1).getString("role");
                String home2 = arr.getJSONObject(1).getString("homeAddress");
                String work2 = arr.getJSONObject(1).getString("officeAddress");
                String date2 = arr.getJSONObject(1).getString("date");
                String time2 = arr.getJSONObject(1).getString("latestArrivalTime");
                String direction2 = arr.getJSONObject(1).getString("direction");
                String seats2 = arr.getJSONObject(1).getString("seats");
                String status2 = arr.getJSONObject(1).getString("status");

                // Date formatieren
                String dateTime2 = date2 + " " + time2;
                Date dateFormatted2 = format.parse(dateTime2);
                System.out.println("Formatiertes Datum: " + dateFormatted2);

                String dateView2 = DATE_FORMAT.format(dateFormatted2);
                System.out.println("New Date Format: " + dateView2);

                switch (status2) {
                    case "not answered": status_mitfahrer=0;
                        break;
                }
                nachStatusAnzeigen_Mitfahrer();

                //TODO Wiebke: textView_Fahrer2, textView_Freie_Sitzplaetze2 und textView_Anzahl_Freie_Sitzplaetze2 anlegen
                if (role2.equals("driver")){
                    textView_Fahrer2.setText("Fahrer");
                    textView_Freie_Sitzplaetze2.setText("Freie Sitzplätze: ");
                    textView_Anzahl_Freie_Sitzplaetze2.setText(seats2);

                } else if (role2.equals("passenger")){
                    textView_Fahrer2.setText("Mitfahrer");
                    textView_Freie_Sitzplaetze2.setText("");
                    textView_Anzahl_Freie_Sitzplaetze2.setText("");
                } else {
                    textView_Fahrer2.setText("");
                    textView_Freie_Sitzplaetze2.setText("");
                }
                if (direction2.equals("towards Home")){
                    textView_Abfahrt_Ort2.setText(work2);
                    textView_Ankunft_Ort2.setText(home2);
                } else if (direction2.equals("towards Office")){
                    textView_Abfahrt_Ort2.setText(home2);
                    textView_Ankunft_Ort2.setText(work2);
                }
                textView_Uhrzeit2.setText(dateView2 + " Uhr");
                System.out.println("Zweiter Datensatz: \nrole: " + role2 + ", home: " + home2 + ", work: " + work2 + ", date: " + date2+ ", Time: " + time2+ ", Direction: " +  direction2);
                System.out.println("Ende Status Mitfahrer: " + status_mitfahrer);
            }

        } catch (Exception e ){
            e.printStackTrace();
            status_fahrer=3;
            status_mitfahrer=3;
            nachStatusAnzeigen_Fahrer();
            nachStatusAnzeigen_Mitfahrer();
        }

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
                // Fahrersicht
                bewerten.setVisibility(View.VISIBLE);
                bewertet.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                break;
            case 1:
                // Mitfahrersicht
                bewerten.setVisibility(View.INVISIBLE);
                bewertet.setVisibility(View.VISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                break;
            case 2:
                // Fahrt abgesagt
                bewerten.setVisibility(View.INVISIBLE);
                bewertet.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.VISIBLE);
                break;
            case 3:
                //keine Einträge vorhanden
                bewerten.setVisibility(View.INVISIBLE);
                bewertet.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void nachStatusAnzeigen_Mitfahrer(){
        switch(status_mitfahrer){
            case 0:
                // Fahrersicht
                bewerten2.setVisibility(View.VISIBLE);
                bewertet2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                break;
            case 1:
                // Mitfahrersicht
                bewerten2.setVisibility(View.INVISIBLE);
                bewertet2.setVisibility(View.VISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                break;
            case 2:
                // Fahrt abgesagt
                bewerten2.setVisibility(View.INVISIBLE);
                bewertet2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.VISIBLE);
                break;
            case 3:
                //keine Einträge vorhanden
                bewerten2.setVisibility(View.INVISIBLE);
                bewertet2.setVisibility(View.INVISIBLE);
                Fahrt_abgesagt2.setVisibility(View.INVISIBLE);
                break;
        }

        }
    }

