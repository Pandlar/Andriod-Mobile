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
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrierungStep1 extends AppCompatActivity implements View.OnClickListener{

    Button btnWeiter1;
    RadioButton rbAGB;
    EditText signUpEmail;
    EditText signUpUserName;
    EditText signUpVorname;
    EditText signUpNachname;
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
        signUpVorname = findViewById(R.id.etVorname);
        signUpNachname = findViewById(R.id.etNachname);
        signUpUserName = findViewById(R.id.etUsername);
        signUpPassword = findViewById(R.id.etPasswort);
        signUpPassword2 = findViewById(R.id.etPasswort2);

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter1:

                if (signUpPassword.getText().toString().length() > 0 &
                        signUpEmail.getText().toString().length() > 0 &
                        signUpVorname.getText().toString().length() > 0 &
                        signUpUserName.getText().toString().length() > 0 &
                        signUpNachname.getText().toString().length() > 0 &
                        signUpPassword2.getText().toString().length() > 0) {
                    if (validate(signUpEmail.getText().toString())) {
                        if (signUpPassword.getText().toString().equals(signUpPassword2.getText().toString())){
                            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
                            SharedPreferences.Editor saveSignUp = sharedPrefs.edit();

                            saveSignUp.putString(getString(R.string.saveEmail),signUpEmail.getText().toString()).apply();
                            saveSignUp.putString(getString(R.string.inputSignUpUsername),signUpUserName.getText().toString()).apply();
                            saveSignUp.putString(getString(R.string.inputSignUpPassword),signUpPassword.getText().toString()).apply();
                            saveSignUp.putString(getString(R.string.inputSignUpVorname),signUpVorname.getText().toString()).apply();
                            saveSignUp.putString(getString(R.string.inputSignUpNachname),signUpNachname.getText().toString()).apply();

                            // auf Registrierungsscreen Step 2 weiterleiten
                            Intent intent = new Intent(this, RegistrierungStep2.class);
                            startActivity(intent);
                            this.finish();
                        } else {
                            Toast.makeText(RegistrierungStep1.this, "Passwort und Passwort bestätigen stimmen nicht überein", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegistrierungStep1.this, "Bitte überprüfe deine Emailadresse", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(RegistrierungStep1.this, "Bitte fülle alle Felder aus und akzeptiere die AGB's", Toast.LENGTH_LONG).show();
                }
        }
    }
}
