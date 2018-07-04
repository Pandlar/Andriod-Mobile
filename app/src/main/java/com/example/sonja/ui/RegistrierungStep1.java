package com.example.sonja.ui;

import android.content.Intent;
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


                // auf Registrierungsscreen Step 2 weiterleiten
                Intent intent = new Intent(this, RegistrierungStep2.class);
                startActivity(intent);
                this.finish();
        }
    }
}
