package com.example.sonja.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrierungStep2 extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter2;
    EditText signUpStraße;
    EditText signUpPlz;
    EditText signUpStadt;
    EditText signUpTreffpunkt2;
    EditText signUpStraßeAO;
    EditText signUpPlzAO;
    EditText signUpTreffpunkt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step2);

        // Buttons OnClickListener
        btnWeiter2 = findViewById(R.id.btnWeiter2);
        btnWeiter2.setOnClickListener(this);
        signUpStraße = findViewById(R.id.etStraße);
        signUpPlz = findViewById(R.id.etPlz);
        signUpStadt = findViewById(R.id.etStadt);
        signUpTreffpunkt2 = findViewById(R.id.etTreffpunkt2);
        signUpStraßeAO = findViewById(R.id.etStraßeAO);
        signUpPlzAO = findViewById(R.id.etPlzAO);
        signUpTreffpunkt = findViewById(R.id.etTreffpunkt);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter2:

                if (signUpStraße.getText().toString().length() > 0 &
                        signUpPlz.getText().toString().length() > 0 &
                        signUpStadt.getText().toString().length() > 0 &
                        signUpTreffpunkt2.getText().toString().length() > 0 &
                        signUpStraßeAO.getText().toString().length() > 0 &
                        signUpPlzAO.getText().toString().length() > 0 &
                        signUpTreffpunkt.getText().toString().length() > 0) {

                    // auf Registrierungsscreen Step 3 weiterleiten
                    Intent intent = new Intent(this, RegistrierungStep3.class);
                    startActivity(intent);
                    this.finish();

                } else {
                    Toast.makeText(RegistrierungStep2.this, "Bitte fülle alle Felder aus!", Toast.LENGTH_LONG).show();
                }
        }
    }
}



