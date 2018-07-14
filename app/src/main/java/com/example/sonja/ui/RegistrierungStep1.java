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
import android.widget.RadioButton;

public class RegistrierungStep1 extends AppCompatActivity implements View.OnClickListener{

    Button btnWeiter1;
    RadioButton rbAGB;
    EditText signUpEmail;
    EditText signUpUserName;
    EditText signUpPassword;
    EditText signUpPassword2;

    private Context mContext;
    private static final String SHARED_PREFERENCE = "SavedValues";
    private static final String PREFERENCE_SIGNUP_USER_EMAIL = "signupUserEmail";
    private static final String PREFERENCE_SIGNUP_USER_USERNAME = "signupUserUsername";
    private static final String PREFERENCE_SIGNUP_USER_PASSWORD = "signupUserPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step1);

        // Buttons OnClickListener
        btnWeiter1 = findViewById(R.id.btnWeiter1);
        btnWeiter1.setOnClickListener(this);


        rbAGB = findViewById(R.id.rbAGB);
        rbAGB.getBackground().setAlpha(1);

        signUpEmail = findViewById(R.id.etEMail);
        signUpUserName = findViewById(R.id.etUsername);
        signUpPassword = findViewById(R.id.etPasswort);
        signUpPassword2 = findViewById(R.id.etPasswort2);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter1:
                Log.d("Weiter","Weiter clicked");
                Log.d("newUserEmail", " " + signUpEmail.getText().toString());
                Log.d("signUpUserName", " " + signUpUserName.getText().toString());
                Log.d("signUpPassword", " " + signUpPassword.getText().toString());
                Log.d("signUpPassword2", " " + signUpPassword2.getText().toString());

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor saveSignUp = sharedPrefs.edit();

                if (signUpPassword.getText().toString().equals(signUpPassword2.getText().toString())){

                saveSignUp.putString(getString(R.string.saveEmail),signUpEmail.getText().toString()).apply();
                saveSignUp.putString(getString(R.string.inputSignUpUsername),signUpUserName.getText().toString()).apply();
                saveSignUp.putString(getString(R.string.inputSignUpPassword),signUpPassword.getText().toString()).apply();
                }

                Log.d("Test Preferences Mail", sharedPrefs.getString(getString(R.string.saveEmail),"keine Email vorhanden"));
                Log.d("Test PrefUsername", sharedPrefs.getString(getString(R.string.inputSignUpUsername),"ein Username vorhanden"));
                Log.d("Test PrefPassword", sharedPrefs.getString(getString(R.string.inputSignUpPassword),"kein Passwort vorhanden"));

                // auf Registrierungsscreen Step 2 weiterleiten
                Intent intent = new Intent(this, RegistrierungStep2.class);
                startActivity(intent);
                this.finish();
        }
    }
}
