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
import com.example.sonja.ui.asyncTasks.PostRequestAsync;
import com.example.sonja.ui.asyncTasks.PostRequestParams;

import org.json.JSONArray;

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


        BottomNavigationView navigation = findViewById(R.id.navigation);
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


        //TODO  Text aus DB in Textviews einfügen für Fahrteneinträge
        //erster Eintrag auf Screen
        textView_Fahrer = findViewById(R.id.textView_Fahrer);
        textView_Uhrzeit = findViewById(R.id.textView_Uhrzeit);

        // TODO Überprüfen, ob das hier auch wie in Klasse Home4.java ist
        textView_Abfahrt_Ort = findViewById(R.id.textView_Ankunft_Ort);
        textView_Ankunft_Ort = findViewById(R.id.textView_Abfahrt_Ort);
        textView_Freie_Sitzplaetze = findViewById(R.id.textView_Freie_Sitzplaetze);
        textView_Anzahl_Freie_Sitzplaetze = findViewById(R.id.textView_Anzahl_Freie_Sitzplaetze);

        //zweiter Eintrag auf Screen
        textView_Uhrzeit2 = findViewById(R.id.textView_Uhrzeit2);
        textView_Ankunft_Ort2 = findViewById(R.id.textView_Ankunft_Ort2);
        textView_Abfahrt_Ort2 = findViewById(R.id.textView_Abfahrt_Ort2);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String uuid = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");

        try {

            HttpTest httpUUIDTest = new HttpTest();
            //String json = httpUUIDTest.sendGet("ridesFuture", "userId", uuid, "eq", "&order=date.asc,latestArrivalTime.asc");


            GetRequestParams paramsOfRequest = new GetRequestParams("ridesFuture", "1b4a0156-7a2f-11e8-a8c9-0a181e304e34", "&order=date.asc,latestArrivalTime.asc");
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

                // TODO es gibt momentan keine anderen Werte (dafür müsste der View um den Status des Ratings ergänzt werden
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