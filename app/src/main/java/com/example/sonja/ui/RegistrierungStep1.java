package com.example.sonja.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

                if (signUpPassword.getText().toString() == signUpPassword2.getText().toString()) {
                    SharedPreferences.Editor editor = mContext.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE).edit();
                    editor.putString(PREFERENCE_SIGNUP_USER_EMAIL, signUpEmail.getText().toString());
                    editor.putString(PREFERENCE_SIGNUP_USER_USERNAME, signUpUserName.getText().toString());
                    editor.putString(PREFERENCE_SIGNUP_USER_PASSWORD, signUpPassword.getText().toString());
                    editor.apply();
                    Log.d("success","asd");
                }

                SharedPreferences sharedPreferences = this.getSharedPreferences(SHARED_PREFERENCE, this.MODE_PRIVATE);
                String test = sharedPreferences.getString(PREFERENCE_SIGNUP_USER_EMAIL, "");

                Log.d("register email", "email" + test);


                // auf Registrierungsscreen Step 2 weiterleiten
                Intent intent = new Intent(this, RegistrierungStep2.class);
                startActivity(intent);
                this.finish();
        }
    }
}
