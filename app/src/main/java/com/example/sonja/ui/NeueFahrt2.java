package com.example.sonja.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.TextView;

import java.util.Calendar;

public class NeueFahrt2 extends AppCompatActivity implements View.OnClickListener {


    Button btn_weiter_screen3;
    Button btn_search;
    Button btn_offer;
    Button btn_both;
    EditText txt_abfahrtszeit;
    EditText txt_ankunftszeit;
    Button btn_switch_back;
    TextView txt_anzahl_sitze;
    HttpTest httpCon;

    // Bottom Navigation aktivieren
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(NeueFahrt2.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(NeueFahrt2.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(NeueFahrt2.this, account01.class);
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
    // Zwischenspeicher Variablen
    int from_earliest_minute;
    int from_earliest_hour;
    int from_latest_minute ;
    int from_latest_hour ;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_fahrt2);
/* Daten aus NeueFahrt1 auslesen - nicht ready
        Intent intent = new Intent();
        NeueFahrt1 f = new NeueFahrt1();
        if (NeueFahrt1.mitfahrer == 1){
            mitfahrer = 1;
            btn_search.setBackgroundResource(R.drawable.button_style_clicked);
        }
        if (NeueFahrt1.fahrer == 1){
            fahrer = 1;
            btn_offer.setBackgroundResource(R.drawable.button_style_clicked);
        }
        if (NeueFahrt1.both == 1){
            both = 1;
            btn_both.setBackgroundResource(R.drawable.button_style_clicked);
        }
*/
        // Buttons OnClickListener
        btn_weiter_screen3 = findViewById(R.id.btn_weiter_screen3);
        btn_weiter_screen3.setOnClickListener(this);

        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);

        btn_offer = findViewById(R.id.btn_offer);
        btn_offer.setOnClickListener(this);

        btn_both = findViewById(R.id.btn_both);
        btn_both.setOnClickListener(this);

        txt_abfahrtszeit = findViewById(R.id.txt_abfahrtszeit);
        txt_abfahrtszeit.setOnClickListener(this);

        txt_ankunftszeit = findViewById(R.id.txt_ankunftszeit);
        txt_ankunftszeit.setOnClickListener(this);

        btn_switch_back = findViewById(R.id.btn_switch_back);
        btn_switch_back.setOnClickListener(this);

        txt_anzahl_sitze = findViewById(R.id.txt_anzahl_sitze);

        // Daten übernehmen aus NeueFahrt1
        Bundle extras = getIntent().getExtras();
        if(extras!= null) {
            to_earliest_minute = extras.getInt("to_earliest_minute");
            to_earliest_hour = extras.getInt("to_earliest_hour");
            to_latest_minute = extras.getInt("to_latest_minute");
            to_latest_hour = extras.getInt("to_latest_hour");
            requestRole = NeueFahrt1.RequestRole.valueOf(extras.getString("requestRole"));
        }
        System.out.println("###### empfange, neuefahrt2, to earliest" +to_earliest_hour+":"+to_earliest_minute);
        System.out.println("###### empfange, neuefahrt2, to latest" +to_latest_hour+":"+to_latest_minute);

        if (requestRole == NeueFahrt1.RequestRole.DRIVER){
            txt_anzahl_sitze.setVisibility(View.VISIBLE);
            btn_offer.setBackgroundResource(R.drawable.button_style_clicked);
            btn_both.setBackgroundResource(R.drawable.button_style);
            btn_search.setBackgroundResource(R.drawable.button_style);
        } else if (requestRole == NeueFahrt1.RequestRole.PASSENGER){
            txt_anzahl_sitze.setVisibility(View.INVISIBLE);
            btn_offer.setBackgroundResource(R.drawable.button_style);
            btn_both.setBackgroundResource(R.drawable.button_style);
            btn_search.setBackgroundResource(R.drawable.button_style_clicked);
        } else if (requestRole == NeueFahrt1.RequestRole.DRIVERORPASSENGER){
            txt_anzahl_sitze.setVisibility(View.VISIBLE);
            btn_offer.setBackgroundResource(R.drawable.button_style);
            btn_both.setBackgroundResource(R.drawable.button_style_clicked);
            btn_search.setBackgroundResource(R.drawable.button_style);
        } else {
            requestRole = NeueFahrt1.RequestRole.NOTDECIDED;
        }


        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner_anzahl_sitze);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        httpCon = new HttpTest();
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, Confirm.class);
        intent.putExtra("from_earliest_minute", from_earliest_minute);
        intent.putExtra("from_earliest_hour", from_earliest_hour);
        intent.putExtra("from_latest_minute", from_latest_minute);
        intent.putExtra("from_latest_hour", from_latest_hour);
        intent.putExtra("to_earliest_minute", to_earliest_minute);
        intent.putExtra("to_earliest_hour", to_earliest_hour);
        intent.putExtra("to_latest_minute", to_latest_minute);
        intent.putExtra("to_latest_hour", to_latest_hour);
        intent.putExtra("requestRole", requestRole.toString());

        switch (v.getId()) {

            case R.id.btn_weiter_screen3:
                // auf Screen Confirm weiterleiten
                try{
                    String email ="";
                    httpCon.postRequest(email,from_earliest_minute,from_earliest_hour,
                            from_latest_minute, from_latest_hour, requestRole, "towards Home");

                    httpCon.postRequest(email,to_earliest_minute, to_earliest_hour,
                            to_latest_minute, to_latest_hour, requestRole, "towards Office");
                }catch(Exception e){
                    System.out.println("###### NeueFahrt2 - postRequest aufruf");
                }

                if (requestRole == NeueFahrt1.RequestRole.PASSENGER) {

                    startActivity(intent);
                    this.finish();
                }
                else if (requestRole == NeueFahrt1.RequestRole.DRIVER) {
                    startActivity(intent);
                    this.finish();
                }
                else if (requestRole == NeueFahrt1.RequestRole.DRIVERORPASSENGER) {
                    startActivity(intent);
                    this.finish();
                }
                //TODO logik überlegen
                else {
                    System.out.println("Bitte Angabe machen.");
                }

                break;

            case R.id.btn_search:
                break;

            case R.id.btn_offer:
                break;

            case R.id.btn_both:
                break;

            case R.id.btn_switch_back:
                // auf Screeen NeueFahrt1 weiterleiten
                Intent intentswitch = new Intent(this, NeueFahrt1.class);
                startActivity(intentswitch);
                this.finish();
                break;

            case R.id.txt_abfahrtszeit:
                // Abfahrtszeit Time Picker dialogue
                Calendar mcurrentTime = Calendar.getInstance();

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_abfahrtszeit.setText( addLeadingZeros(selectedHour) + ":" + addLeadingZeros(selectedMinute) + "  Uhr");
                        from_earliest_hour =selectedHour;
                        from_earliest_minute = selectedMinute;
                        System.out.println(from_latest_hour);
                        System.out.println(from_latest_hour);

                    }
                }, mcurrentTime.get(Calendar.HOUR_OF_DAY), mcurrentTime.get(Calendar.MINUTE), true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

                break;

            case R.id.txt_ankunftszeit:
                // Ankunftszeit Time Picker dialogue
                Calendar ncurrentTime = Calendar.getInstance();


                TimePickerDialog nTimePicker;
                nTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker ntimePicker, int nselectedHour, int nselectedMinute) {
                        txt_ankunftszeit.setText( addLeadingZeros(nselectedHour) + ":" + addLeadingZeros(nselectedMinute) + "  Uhr");
                        from_latest_hour =nselectedHour;
                        from_latest_minute = nselectedMinute;
                    }
                }, ncurrentTime.get(Calendar.HOUR_OF_DAY), ncurrentTime.get(Calendar.MINUTE), true);
                nTimePicker.setTitle("Select Time");
                nTimePicker.show();
                break;

            default:
                break;
        }

    }


    // When clicking back you get redirected to starting screen.
    public void onBackPressed(){
        Intent intent = new Intent(this, NeueFahrt1.class);
        startActivity(intent);
        this.finish();
    }

    /**
     * adds a leading zero to integers lower than 10
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