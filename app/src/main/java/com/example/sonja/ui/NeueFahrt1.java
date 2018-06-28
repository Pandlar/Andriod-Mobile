package com.example.sonja.ui;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sonja.ui.aws.AWSLoginHandler;
import com.example.sonja.ui.aws.AWSLoginModel;

import java.util.Calendar;

public class NeueFahrt1 extends AppCompatActivity implements View.OnClickListener, AWSLoginHandler {


    public enum RequestRole{
        DRIVER, PASSENGER, DRIVERORPASSENGER, NOTDECIDED
    }

    RequestRole requestRole = RequestRole.NOTDECIDED;
    Button btn_weiter_screen2;
    Button btn_search;
    Button btn_offer;
    Button btn_both;
    Button btn_log_out;
    EditText txt_abfahrtszeit;
    EditText txt_ankunftszeit;
    TextView txt_anzahl_sitze;
    Button btn_switch;
    Spinner dropdown;
    int to_earliest_hour = 0;
    int to_earliest_minute = 0;
    int to_latest_hour = 0;
    int to_latest_minute = 0;

    AWSLoginModel awsLoginModel;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(NeueFahrt1.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(NeueFahrt1.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(NeueFahrt1.this, account01.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neue_fahrt1);

        // instantiating AWSLoginModel(context, callback)
        awsLoginModel = new AWSLoginModel(this, this);

        btn_log_out = (Button) findViewById(R.id.btn_log_out);

        btn_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("myTag", "Log Out function called!");

                // do sign out and handles on interface
                awsLoginModel.signOutUser();

                NeueFahrt1.this.startActivity(new Intent(NeueFahrt1.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

// Bottom Navigation initialisieren
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



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

        txt_anzahl_sitze = findViewById(R.id.txt_anzahl_sitze);

        //TODO Spinner Logik implementieren!
        //get the spinner from the xml.
        dropdown = findViewById(R.id.spinner_anzahl_sitze);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        //set the spinners adapter to the previously created one.
         dropdown.setAdapter(adapter);


    }

    @Override
    public void onRegisterSuccess(boolean mustConfirmToComplete) {
        if (mustConfirmToComplete) {
            Toast.makeText(NeueFahrt1.this, "Almost done! Confirm code to complete registration", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(NeueFahrt1.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRegisterConfirmed() {
        Toast.makeText(NeueFahrt1.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSuccess() {
        NeueFahrt1.this.startActivity(new Intent(NeueFahrt1.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void onFailure(int process, Exception exception) {
        exception.printStackTrace();
        String whatProcess = "";
        switch (process) {
            case AWSLoginModel.PROCESS_SIGN_IN:
                whatProcess = "Sign In:";
                break;
            case AWSLoginModel.PROCESS_REGISTER:
                whatProcess = "Registration:";
                break;
            case AWSLoginModel.PROCESS_CONFIRM_REGISTRATION:
                whatProcess = "Registration Confirmation:";
                break;
        }
        Toast.makeText(NeueFahrt1.this, whatProcess + exception.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this, NeueFahrt2.class);
        intent.putExtra("to_earliest_minute", to_earliest_minute);
        intent.putExtra("to_earliest_hour", to_earliest_hour);
        intent.putExtra("to_latest_minute", to_latest_minute);
        intent.putExtra("to_latest_hour", to_latest_hour);
        intent.putExtra("requestRole", requestRole.toString());
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
                txt_anzahl_sitze.setVisibility(View.INVISIBLE);
                dropdown.setVisibility(View.INVISIBLE);
                btn_offer.setBackgroundResource(R.drawable.button_style);
                btn_both.setBackgroundResource(R.drawable.button_style);
                btn_search.setBackgroundResource(R.drawable.button_style_clicked);
                requestRole = RequestRole.PASSENGER;
                break;

            case R.id.btn_offer:
                // do your code
                txt_anzahl_sitze.setVisibility(View.VISIBLE);
                dropdown.setVisibility(View.VISIBLE);
                btn_search.setBackgroundResource(R.drawable.button_style);
                btn_both.setBackgroundResource(R.drawable.button_style);
                btn_offer.setBackgroundResource(R.drawable.button_style_clicked);
                requestRole = RequestRole.DRIVER;
                break;

            case R.id.btn_both:
                // do your code
                txt_anzahl_sitze.setVisibility(View.VISIBLE);
                dropdown.setVisibility(View.VISIBLE);
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

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        txt_abfahrtszeit.setText( addLeadingZeros(selectedHour) + ":" + addLeadingZeros(selectedMinute) + "  Uhr");
                        to_earliest_hour = selectedHour;
                        to_earliest_minute = selectedMinute;
                    }
                },mcurrentTime.get(Calendar.HOUR_OF_DAY),mcurrentTime.get(Calendar.MINUTE), true);
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
                        to_latest_hour = nselectedHour;
                        to_latest_minute = nselectedMinute;
                    }
                },ncurrentTime.get(Calendar.HOUR_OF_DAY), ncurrentTime.get(Calendar.MINUTE), true);
                nTimePicker.setTitle("Select Time");
                nTimePicker.show();

                break;

            default:
                break;
        }

    }


    // When clicking back you get redirected to starting screen.
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
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
