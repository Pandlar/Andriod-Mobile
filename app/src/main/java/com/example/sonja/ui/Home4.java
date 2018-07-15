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

import java.util.ArrayList;
import java.util.List;

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

    //Textviews für ersten Fahrteneintrag
    TextView textView_Ankunft_Ort;
    TextView textView_Abfahrt_Ort;
    TextView textView_Freie_Sitzplaetze;
    TextView textView_Anzahl_Freie_Sitzplaetze;
    TextView textView_Fahrer;
    TextView textView_Uhrzeit;
    //Textviews für zweiten Fahrteneintrag
    TextView textView_Ankunft_Ort2;
    TextView textView_Abfahrt_Ort2;
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

        //TODO  Text aus DB in Textviews einfügen
        textView_Fahrer = findViewById(R.id.textView_Fahrer);
        textView_Uhrzeit = findViewById(R.id.textView_Uhrzeit);

        // geändert und andersrum (nicht verwirrt sein, dass in xml anders)
        textView_Abfahrt_Ort = findViewById(R.id.textView_Ankunft_Ort);
        textView_Ankunft_Ort = findViewById(R.id.textView_Abfahrt_Ort);
        textView_Freie_Sitzplaetze = findViewById(R.id.textView_Freie_Sitzplaetze);
        textView_Anzahl_Freie_Sitzplaetze = findViewById(R.id.textView_Anzahl_Freie_Sitzplaetze);

        //zweiter Eintrag auf Screen
        textView_Uhrzeit2 = findViewById(R.id.textView_Uhrzeit2);
        textView_Abfahrt_Ort2 = findViewById(R.id.textView_Ankunft_Ort2);
        textView_Ankunft_Ort2 = findViewById(R.id.textView_Abfahrt_Ort2);


        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String uuid = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");

        System.out.println("We are in Home4.java now and the uuid is " + uuid);

        try {

            HttpTest httpUUIDTest = new HttpTest();
            //String json = httpUUIDTest.sendGet("ridesPast", "userId", uuid, "eq", "&order=date.desc,latestArrivalTime.desc");

            GetRequestParams paramsOfRequest = new GetRequestParams("ridesPast", uuid, "&order=date.desc,latestArrivalTime.desc");
            GetRequestAsync asyncGetRequestFuture = new GetRequestAsync();
            JSONArray arr = new JSONArray(asyncGetRequestFuture.execute(paramsOfRequest));

            String role1 = arr.getJSONObject(0).getString("role");
            String home1 = arr.getJSONObject(0).getString("homeAddress");
            String work1 = arr.getJSONObject(0).getString("officeAddress");
            String date1 = arr.getJSONObject(0).getString("date");
            String time1 = arr.getJSONObject(0).getString("latestArrivalTime");
            String direction1 = arr.getJSONObject(0).getString("direction");
            String seats1 = arr.getJSONObject(0).getString("seats");
            String status1 = arr.getJSONObject(0).getString("status");
            System.out.println("Erster Datensatz: \nrole: " + role1 + ", home: " + home1 + ", work: " + work1 + ", date: " + date1 + ", Time: " + time1 + ", Direction: " +  direction1);

            String role2 = arr.getJSONObject(1).getString("role");
            String home2 = arr.getJSONObject(1).getString("homeAddress");
            String work2 = arr.getJSONObject(1).getString("officeAddress");
            String date2 = arr.getJSONObject(1).getString("date");
            String time2 = arr.getJSONObject(1).getString("latestArrivalTime");
            String direction2 = arr.getJSONObject(1).getString("direction");
            String seats2 = arr.getJSONObject(1).getString("seats");
            String status2 = arr.getJSONObject(1).getString("status");

            System.out.println("Zweiter Datensatz: \nrole: " + role2 + ", home: " + home2 + ", work: " + work2 + ", date: " + date2+ ", Time: " + time2+ ", Direction: " +  direction2);

            if (role1.equals("driver")){
                textView_Fahrer.setText("Du bist Fahrer!");
                textView_Freie_Sitzplaetze.setText("Freie Sitzplätze: ");
                textView_Anzahl_Freie_Sitzplaetze.setText(seats1);

                // es gibt momentan keine anderen Werte
                switch (status1) {
                    case "not answered": status_fahrer=0;
                    break;
                }
                nachStatusAnzeigen_Fahrer();
            } else if (role1.equals("passenger")){
                textView_Fahrer.setText("Du bist Mitfahrer!");
                textView_Freie_Sitzplaetze.setText("");
                textView_Anzahl_Freie_Sitzplaetze.setText("");

                // es gibt momentan keine anderen Werte
                switch (status1) {
                    case "not answered": status_mitfahrer=0;
                        break;
                }
                nachStatusAnzeigen_Mitfahrer();
            } else {
                textView_Fahrer.setText("");
                textView_Freie_Sitzplaetze.setText("");}

            // es gibt momentan keine anderen Werte
            switch (status2) {
                case "not answered": status_mitfahrer=0;
                    break;
            }
            nachStatusAnzeigen_Mitfahrer();

            if (direction1.equals("towards Home")){
                textView_Abfahrt_Ort.setText(work1);
                textView_Ankunft_Ort.setText(home1);
            } else if (direction1.equals("towards Office")){
                textView_Abfahrt_Ort.setText(home1);
                textView_Ankunft_Ort.setText(work1);
            }
            textView_Uhrzeit.setText(date1 + ", " + time1);

            //TODO Wiebke: textView_Fahrer2, textView_Freie_Sitzplaetze2 und textView_Anzahl_Freie_Sitzplaetze2 anlegen
            if (role2.equals("driver")){
                //textView_Fahrer2.setText("Du bist Fahrer!");
                //textView_Freie_Sitzplaetze2.setText("Freie Sitzplätze: ");
                //textView_Anzahl_Freie_Sitzplaetze2.setText(seats2);

                // es gibt momentan keine anderen Werte
                switch (status2) {
                    case "not answered": status_fahrer=0;
                        break;
                }
                nachStatusAnzeigen_Fahrer();
            } else if (role2.equals("passenger")){
                //textView_Fahrer2.setText("Du bist Mitfahrer!");
                //textView_Freie_Sitzplaetze2.setText("");
                //textView_Anzahl_Freie_Sitzplaetze2.setText("");
            } else {
                //textView_Fahrer2.setText("");
                //textView_Freie_Sitzplaetze2.setText("");
                }
            if (direction2.equals("towards Home")){
                textView_Abfahrt_Ort2.setText(work2);
                textView_Ankunft_Ort2.setText(home2);
            } else if (direction2.equals("towards Office")){
                textView_Abfahrt_Ort2.setText(home2);
                textView_Ankunft_Ort2.setText(work2);
            }
            textView_Uhrzeit2.setText(date2 + ", " + time2);

        } catch (Exception e ){
            e.printStackTrace();
        }

        //old Version of Getting Data
        /*try {
            HttpTest httpRatingPost = new HttpTest();

            String json = null;

            json = httpRatingPost.sendGet("user", "email", mail, "eq", "");
            JSONArray arr = new JSONArray(json);
            String id = arr.getJSONObject(0).getString("id");
            System.out.println(id);

            // RequestIds of the User
            String jsonRequests = httpRatingPost.sendGet("request", "userId", id, "eq", "");
            System.out.println(jsonRequests);

            JSONArray arr2 = new JSONArray(jsonRequests);

            List<String> matches = new ArrayList<>();
            int jsnlength = arr2.length();
            for (int i = 0; i < jsnlength; i++) {
                System.out.println("Request Id :" + arr2.getJSONObject(i).getString("id"));
                System.out.println("Role: " + arr2.getJSONObject(i).getString("role"));

                String requestID = arr2.getJSONObject(i).getString("id");
                String role = arr2.getJSONObject(i).getString("role");

                if (role.equals("driver")) {

                    try {
                        String jsonDriverMatches = httpRatingPost.sendGet("match", "driverRequestId", requestID, "eq", "");
                        JSONArray arrDriverMatches = new JSONArray(jsonDriverMatches);

                        String matchID = arrDriverMatches.getJSONObject(0).getString("id");
                        System.out.println("matchID: " + matchID);
                        matches.add(matchID);
                    } catch (Exception E) {
                        System.out.println("Es gibt noch keinen Match für diesen Request");
                    }

                }
                if (role.equals("passenger")) {
                    System.out.println("passenger aufgerufen");
                    try {
                        String jsonPassengerMatches = httpRatingPost.sendGet("match", "passenger0RequestId", requestID, "eq", "");
                        JSONArray arrPassengerMatches = new JSONArray(jsonPassengerMatches);

                        String matchID = arrPassengerMatches.getJSONObject(0).getString("id");
                        System.out.println("matchID: " + matchID);
                        matches.add(matchID);

                        //TODO: muss eigentlich auch noch für die anderen Passengers eingestellt werden
                    } catch (Exception E) {
                        System.out.println("Es gibt noch keinen Match für diesen Request");
                    }


                    System.out.println("Das sind alle gefundenen Matches: " + matches);
                    System.out.println("Mit diesen Match-Ergebnissen könnte jetzt gearbeitet werden");
                }
            }
        } catch (Exception E ){
            System.out.println("Something is not working, sorry");
        }*/


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

        }
    }

}