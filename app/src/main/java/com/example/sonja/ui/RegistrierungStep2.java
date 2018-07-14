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

public class RegistrierungStep2 extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter2;
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

        signUpStadtHome = findViewById(R.id.etStadt);
        signUpTreffpunktHome = findViewById(R.id.etTreffpunkt2);
        signUpStrHome = findViewById(R.id.etStraße);
        signUpPLZHome= findViewById(R.id.etPlz);
        signUpHandynr = findViewById(R.id.etHandynr);
        signUpTreffpunktWork = findViewById(R.id.etTreffpunkt);
        signUpStrWork = findViewById(R.id.etStraßeAO);
        signUpPLZWork= findViewById(R.id.etPlzAO);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter2:

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

                Log.d("Preferences handynr", sharedPrefs.getString(getString(R.string.inputSignUpHandynr),"keine Handynr vorhanden."));
                Log.d("Preferences stadt home", sharedPrefs.getString(getString(R.string.inputSignUpStadtHome),"keine Stadt vorhanden."));
                Log.d("Preferences PLZ HOME", sharedPrefs.getString(getString(R.string.inputSignUpPLZHome),"keine plz vorhanden."));
                Log.d("Preferences PLZ Work", sharedPrefs.getString(getString(R.string.inputSignUpPLZWork),"keine plz vorhanden."));
                Log.d("Preferences Str Work", sharedPrefs.getString(getString(R.string.inputSignUpStrWork),"keine str vorhanden."));
                Log.d("Preferences Str Home", sharedPrefs.getString(getString(R.string.inputSignUpStrHome),"keine str vorhanden."));
                Log.d("Preferences TreffpunktH", sharedPrefs.getString(getString(R.string.inputSignUpTreffpunktHome),"keine treffpunkt vorhanden."));
                Log.d("Preferences TreffpunktW", sharedPrefs.getString(getString(R.string.inputSignUpTreffpunktWork),"keine treffpunkt vorhanden."));

                // auf Registrierungsscreen Step 3 weiterleiten
                Intent intent = new Intent(this, RegistrierungStep3.class);
                startActivity(intent);
                this.finish();
        }
    }
}



