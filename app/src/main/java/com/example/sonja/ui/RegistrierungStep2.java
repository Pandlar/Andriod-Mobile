package com.example.sonja.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.EditText;

public class RegistrierungStep2 extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter2;
    EditText signUpStraße;
    EditText signUpPlz;
    EditText signUpStadt;
    EditText signUpTreffpunkt2;
    EditText signUpStraßeAO;
    EditText signUpPlzAO;
    EditText signUpTreffpunkt;
    EditText signUpStadtHome;
    EditText signUpTreffpunktHome;
    EditText signUpStrHome;
    EditText signUpPLZHome;
    EditText signUpHandynr;

    EditText signUpTreffpunktWork;
    EditText signUpStrWork;
    EditText signUpPLZWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step2);

        // Buttons OnClickListener
        btnWeiter2 = findViewById(R.id.btnWeiter2);
        btnWeiter2.setOnClickListener(this);

        signUpPLZHome= findViewById(R.id.etPlz);
        signUpStadtHome = findViewById(R.id.etStadt);
        signUpTreffpunktHome = findViewById(R.id.etTreffpunkt2);
        signUpStrWork = findViewById(R.id.etStraßeAO);
        signUpPLZWork= findViewById(R.id.etPlzAO);
        signUpTreffpunktWork = findViewById(R.id.etTreffpunkt);
        signUpStrHome = findViewById(R.id.etStraße);
        signUpHandynr = findViewById(R.id.etHandynr);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter2:

                if (signUpPLZHome.getText().toString().length() > 0 &
                        signUpStadtHome.getText().toString().length() > 0 &
                        signUpTreffpunktHome.getText().toString().length() > 0 &
                        signUpStrWork.getText().toString().length() > 0 &
                        signUpStrHome.getText().toString().length() > 0 &
                        signUpHandynr.getText().toString().length() > 0 &
                        signUpPLZWork.getText().toString().length() > 0 &
                        signUpTreffpunktWork.getText().toString().length() > 0) {

                    //Eingaben in SharedPreferences speichern
                    SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor saveSignUp = sharedPrefs.edit();

                    saveSignUp.putString(getString(R.string.inputSignUpHandynr),signUpHandynr.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpPLZHome),signUpPLZHome.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpPLZWork),signUpPLZWork.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpStadtHome),signUpStadtHome.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpStrHome),signUpStrHome.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpStrWork),signUpStrWork.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpTreffpunktHome),signUpTreffpunktHome.getText().toString()).apply();
                    saveSignUp.putString(getString(R.string.inputSignUpTreffpunktWork),signUpTreffpunktWork.getText().toString()).apply();

                    // auf Registrierungsscreen Step 3 weiterleiten
                    Intent intent = new Intent(this, RegistrierungStep3.class);
                    startActivity(intent);
                    this.finish();

                } else {
                    Toast.makeText(RegistrierungStep2.this, "Bitte fülle alle Felder aus", Toast.LENGTH_LONG).show();
                }
        }
    }
}



