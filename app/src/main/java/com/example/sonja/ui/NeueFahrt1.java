package com.example.sonja.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.Calendar;

public class NeueFahrt1 extends AppCompatActivity implements View.OnClickListener{


    public enum RequestRole{
        DRIVER, PASSENGER, DRIVERORPASSENGER, NOTDECIDED
    }

    RequestRole requestRole = RequestRole.NOTDECIDED;
    Button btn_weiter_screen2;
    Button btn_search;
    Button btn_offer;
    Button btn_both;
    EditText txt_abfahrtszeit;
    EditText txt_ankunftszeit;
    Button btn_switch;
    int to_earliest_hour = 0;
    int to_earliest_minute = 0;
    int to_latest_hour = 0;
    int to_latest_minute = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_fahrt1);


        // Buttons OnClickListener
        btn_weiter_screen2 = findViewById(R.id.btn_weiter_screen2);
        btn_weiter_screen2.setOnClickListener(this);

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

        btn_switch = findViewById(R.id.btn_switch);
        btn_switch.setOnClickListener(this);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner_anzahl_sitze);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);


    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, NeueFahrt2.class);
        intent.putExtra("to_earliest_minute", to_earliest_minute);
        intent.putExtra("to_earliest_hour", to_earliest_hour);
        intent.putExtra("to_latest_minute", to_latest_minute);
        intent.putExtra("to_latest_hour", to_latest_hour);
        intent.putExtra("requestRole", requestRole.toString());
        System.out.println("intent, neuefahrt1" +to_earliest_hour+":"+to_earliest_minute);
        switch (v.getId()) {

            case R.id.btn_weiter_screen2:
                // auf ... weiterleiten

                if (requestRole == RequestRole.PASSENGER) {
                    startActivity(intent);

                    this.finish();
            }
                if (requestRole == RequestRole.DRIVER) {
                    startActivity(intent);
                    this.finish();
                }
                if (requestRole == RequestRole.DRIVERORPASSENGER) {
                    startActivity(intent);
                    this.finish();
                }
                else {
                    System.out.println("Bitte Angabe machen.");
                }

                break;

            case R.id.btn_search:
                // do your code
                btn_offer.setBackgroundResource(R.drawable.button_style);
                btn_both.setBackgroundResource(R.drawable.button_style);
                btn_search.setBackgroundResource(R.drawable.button_style_clicked);
                requestRole = RequestRole.PASSENGER;
                break;

            case R.id.btn_offer:
                // do your code
                btn_search.setBackgroundResource(R.drawable.button_style);
                btn_both.setBackgroundResource(R.drawable.button_style);
                btn_offer.setBackgroundResource(R.drawable.button_style_clicked);
                requestRole = RequestRole.DRIVER;
                break;

            case R.id.btn_both:
                // do your code
                btn_search.setBackgroundResource(R.drawable.button_style);
                btn_offer.setBackgroundResource(R.drawable.button_style);
                btn_both.setBackgroundResource(R.drawable.button_style_clicked);
                requestRole = RequestRole.DRIVERORPASSENGER;
                break;

            case R.id.btn_switch:
                // auf NeueFahrt2 weiterleiten

                startActivity(intent);
                    this.finish();
                    if (requestRole == RequestRole.PASSENGER){
                    }
                    if (requestRole == RequestRole.DRIVER){
                       //TODO
                    }
                     if (requestRole == RequestRole.DRIVERORPASSENGER){

                    }
                    break;

            case R.id.txt_abfahrtszeit:
                // Abfahrtszeit Time Picker dialogue
                Calendar mcurrentTime = Calendar.getInstance();
                //TODO ich muss noch einstellen dass die uhrzeit nicht null ist und Uhrzeit schön darstellen
                to_earliest_hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                to_earliest_minute = mcurrentTime.get(Calendar.MINUTE);
System.out.println("hour plus minute, neuefahrt1" +to_earliest_hour+":"+to_earliest_minute);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_abfahrtszeit.setText( selectedHour + ":" + selectedMinute + "  Uhr");
                    }
                }, to_earliest_hour, to_earliest_minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;

            case R.id.txt_ankunftszeit:
                // Ankunftszeit Time Picker dialogue
                Calendar ncurrentTime = Calendar.getInstance();
                to_latest_hour = ncurrentTime.get(Calendar.HOUR_OF_DAY);
                to_latest_minute = ncurrentTime.get(Calendar.MINUTE);

                TimePickerDialog nTimePicker;
                nTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker ntimePicker, int nselectedHour, int nselectedMinute) {
                        txt_ankunftszeit.setText( nselectedHour + ":" + nselectedMinute + "  Uhr");
                    }
                }, to_latest_hour, to_latest_minute, true);
                nTimePicker.setTitle("Select Time");
                nTimePicker.show();
                break;

            default:
                break;
        }

    }


    // When clicking back you get redirected to starting screen.
    public void onBackPressed(){
        System.out.println("MainActivity onBackPressed() aufgerufen.");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
