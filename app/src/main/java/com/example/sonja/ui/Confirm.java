package com.example.sonja.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sonja.ui.asyncTasks.PostRequestAsync;
import com.example.sonja.ui.asyncTasks.PostRequestParams;

public class Confirm extends AppCompatActivity implements View.OnClickListener {

    TextView abfahrt1, ankunft1, sitze1;
    TextView abfahrt2, ankunft2, sitze2;
    Button btn_confirm;
    TextView textView;
    View grey_background2;
    TextView seats1;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Confirm.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Confirm.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Confirm.this, Confirm.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    // Zwischenspeicher Variablen
    int to_earliest_minute;
    int to_earliest_hour;
    int to_latest_minute ;
    int to_latest_hour ;
    NeueFahrt1.RequestRole requestRole ;

    int from_earliest_minute;
    int from_earliest_hour;
    int from_latest_minute;
    int from_latest_hour;

    //int radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            to_earliest_minute = extras.getInt("to_earliest_minute");
            to_earliest_hour = extras.getInt("to_earliest_hour");
            to_latest_minute = extras.getInt("to_latest_minute");
            to_latest_hour = extras.getInt("to_latest_hour");
            requestRole = NeueFahrt1.RequestRole.valueOf(extras.getString("requestRole"));

            from_earliest_minute = extras.getInt("from_earliest_minute");
            from_earliest_hour = extras.getInt("from_earliest_hour");
            from_latest_minute = extras.getInt("from_latest_minute");
            from_latest_hour = extras.getInt("from_latest_hour");

         //   radio = extras.getInt("radio");

            }



        setContentView(R.layout.activity_confirm);

        //Anzeigetext befüllen (Du bist Fahrer/Mitfahrer)
        textView = findViewById(R.id.textView);
        //textView.setText("Du bist ..?");
           if (requestRole == NeueFahrt1.RequestRole.DRIVER){
                textView.setText("Du bist Fahrer!");
            } else if (requestRole == NeueFahrt1.RequestRole.PASSENGER){
                textView.setText("Du bist Mitfahrer!");
            } else if (requestRole == NeueFahrt1.RequestRole.DRIVERORPASSENGER){
                textView.setText("Du bist Mitfahrer und/oder Fahrer!");
            } else {
                requestRole = NeueFahrt1.RequestRole.NOTDECIDED;
            }

      /*  grey_background2 = findViewById(R.id.grey_background2);
        //textView.setText
        if (radio == 1){
            grey_background2.setVisibility(View.INVISIBLE);
        } else{
            System.out.println("nicht nur Mitfahrer");
        }*/

        // Bottom Navigation initialisieren
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        abfahrt1 = findViewById(R.id.txt_abfahrt1);
        ankunft1 = findViewById(R.id.txt_ankunft1);
        sitze1 = findViewById(R.id.txt_sitze1);

        abfahrt1.setText(addLeadingZeros(to_earliest_hour) + ":" + addLeadingZeros(to_earliest_minute)+" Uhr");
        ankunft1.setText(addLeadingZeros(to_latest_hour) + ":" + addLeadingZeros(to_latest_minute)+" Uhr");
        sitze1.setText("1");
        String sitze1String = sitze1.getText().toString();
        int seats1 = Integer.parseInt(sitze1String);

        abfahrt2 = findViewById(R.id.txt_abfahrt2);
        ankunft2 = findViewById(R.id.txt_ankunft2);
        sitze2 = findViewById(R.id.txt_sitze2);

        abfahrt2.setText(addLeadingZeros(from_earliest_hour) + ":" + addLeadingZeros(from_earliest_minute)+" Uhr");
        ankunft2.setText(addLeadingZeros(from_latest_hour) + ":" + addLeadingZeros(from_latest_minute)+" Uhr");
        sitze2.setText("1");
        sitze2 = findViewById(R.id.txt_sitze2);

        // Buttons OnClickListener
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);




        // Daten übernehmen aus NeueFahrt1

    }


    /**
     * Leitet weiter zum nächsten Screen und pusht die Request Einträge in die Datenbank.
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_confirm:
                // auf Screen3 weiterleiten

                try{
                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                    String id = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");

                    PostRequestParams paramsToOffice = new PostRequestParams(id,to_earliest_minute,to_earliest_hour, to_latest_minute, to_latest_hour, requestRole, "towards Office", 1);
                    PostRequestAsync asyncRunnerToOffice = new PostRequestAsync();
                    asyncRunnerToOffice.execute(paramsToOffice);

                    PostRequestParams paramsToHome = new PostRequestParams(id,from_earliest_minute,from_earliest_hour, from_latest_minute, from_latest_hour, requestRole, "towards Home", 1);
                    PostRequestAsync asyncRunnerToHome = new PostRequestAsync();
                    asyncRunnerToHome.execute(paramsToHome);

                }catch(Exception e){
                    e.printStackTrace();
                }

                Intent intent = new Intent(this, Confirm2.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

        // When clicking back you get redirected to starting screen.
        public void onBackPressed() {
            Intent intent = new Intent(this, NeueFahrt2.class);
            startActivity(intent);
            this.finish();
        }

    /**
     * Fügt eine führende 0 zu ints hinzu, die kleiner sind als 10.
     * @param x
     * @return
     */
    public String addLeadingZeros(int x){
        if(x<10){
            return "0"+x;
        }
        return ""+x;
    }
    }

