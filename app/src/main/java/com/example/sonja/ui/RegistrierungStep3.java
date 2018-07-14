package com.example.sonja.ui;

import android.content.Context;
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

import com.example.sonja.ui.aws.AWSLoginHandler;
import com.example.sonja.ui.aws.AWSLoginModel;

public class RegistrierungStep3 extends AppCompatActivity implements View.OnClickListener,AWSLoginHandler {

    AWSLoginModel awsLoginModel;

    Button btnWeiter3;
    EditText signUpCarMarke;
    EditText signUpCarModell;
    EditText signUpCarFarbe;
    EditText signUpCarNummernschild;
    EditText signUpCarSitzplätze;

    private static final String SHARED_PREFERENCE = "SavedValues";
    private static final String PREFERENCE_SIGNUP_USER_EMAIL = "signupUserEmail";
    private static final String PREFERENCE_SIGNUP_USER_USERNAME = "signupUserUsername";
    private static final String PREFERENCE_SIGNUP_USER_PASSWORD = "signupUserPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step3);

        // instantiating AWSLoginModel(context, callback)
        awsLoginModel = new AWSLoginModel(this, this);

        // Buttons OnClickListener
        btnWeiter3 = findViewById(R.id.btnWeiter3);
        btnWeiter3.setOnClickListener(this);

        //EditText-Felder
        signUpCarMarke = findViewById(R.id.etMarke);
        signUpCarModell = findViewById(R.id.etModell);
        signUpCarFarbe = findViewById(R.id.etFarbe);
        signUpCarNummernschild = findViewById(R.id.etNummernschild);
        signUpCarSitzplätze = findViewById(R.id.etAnzahlSP);

    }

    @Override
    public void onRegisterSuccess(boolean mustConfirmToComplete) {
        if (mustConfirmToComplete) {

            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor saveSignUp = sharedPrefs.edit();

            saveSignUp.putString(getString(R.string.inputSignUpCarMarke),signUpCarMarke.getText().toString()).apply();
            saveSignUp.putString(getString(R.string.inputSignUpCarModell),signUpCarModell.getText().toString()).apply();
            saveSignUp.putString(getString(R.string.inputSignUpCarFarbe),signUpCarFarbe.getText().toString()).apply();
            saveSignUp.putString(getString(R.string.inputSignUpCarNummernschild),signUpCarNummernschild.getText().toString()).apply();
            saveSignUp.putString(getString(R.string.inputSignUpCarSitzplaetze),signUpCarSitzplätze.getText().toString()).apply();

            Log.d("Preferences Marke", sharedPrefs.getString(getString(R.string.inputSignUpCarMarke),"keine Handynr vorhanden."));
            Log.d("Preferences Modell", sharedPrefs.getString(getString(R.string.inputSignUpCarModell),"keine Stadt vorhanden."));
            Log.d("Preferences Farbe", sharedPrefs.getString(getString(R.string.inputSignUpCarFarbe),"keine plz vorhanden."));
            Log.d("Preferences Nummer", sharedPrefs.getString(getString(R.string.inputSignUpCarNummernschild),"keine plz vorhanden."));


            Toast.makeText(RegistrierungStep3.this, "Almost done! Confirm code to complete registration", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegistrierungStep3.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRegisterConfirmed() {
        Toast.makeText(RegistrierungStep3.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSuccess() {
        RegistrierungStep3.this.startActivity(new Intent(RegistrierungStep3.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        Toast.makeText(RegistrierungStep3.this, whatProcess + exception.getMessage(), Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter3:

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

                String passwordInput = sharedPrefs.getString(getString(R.string.inputSignUpPassword), "keine Email vorhanden");
                String usernameInput = sharedPrefs.getString(getString(R.string.inputSignUpUsername), "keine Email vorhanden");
                String emailInput = sharedPrefs.getString(getString(R.string.saveEmail), "keine Email vorhanden");
                Log.d("reg2ister email", "emailInput: " + emailInput);
                Log.d("regi2st2er email", "passwordInput: " + passwordInput);
                Log.d("regis1ter email", "usernameInput: " + usernameInput);

                awsLoginModel.registerUser(usernameInput, emailInput, passwordInput);


                // auf Registrierungsscreen Ende weiterleiten
                Intent intent = new Intent(this, RegistrierungEnde.class);
                startActivity(intent);
                this.finish();
        }
    }
}


