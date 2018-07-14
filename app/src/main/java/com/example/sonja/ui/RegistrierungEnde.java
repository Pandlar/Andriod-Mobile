package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sonja.ui.aws.AWSLoginHandler;
import com.example.sonja.ui.aws.AWSLoginModel;

public class RegistrierungEnde extends AppCompatActivity implements View.OnClickListener, AWSLoginHandler {

    AWSLoginModel awsLoginModel;

    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_ende);

        awsLoginModel = new AWSLoginModel(this, this);

        // Buttons OnClickListener
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

    }

    @Override
    public void onRegisterSuccess(boolean mustConfirmToComplete) {
        if (mustConfirmToComplete) {
            Toast.makeText(RegistrierungEnde.this, "Almost done! Confirm code to complete registration", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegistrierungEnde.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRegisterConfirmed() {
        Toast.makeText(RegistrierungEnde.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSuccess() {
        RegistrierungEnde.this.startActivity(new Intent(RegistrierungEnde.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        Toast.makeText(RegistrierungEnde.this, whatProcess + exception.getMessage(), Toast.LENGTH_LONG).show();
    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnOk:

                // auf Mainscreen zurückkehren weiterleiten
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
        }
    }
}
