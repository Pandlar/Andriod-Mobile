package com.example.sonja.ui;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sonja.ui.asyncTasks.GetUUIDAsync;
import com.example.sonja.ui.asyncTasks.PostCacheLocationAsync;
import com.example.sonja.ui.asyncTasks.PostCacheLocationsParams;
import com.example.sonja.ui.asyncTasks.PostUserAsync;
import com.example.sonja.ui.asyncTasks.PostUserParams;
import com.example.sonja.ui.asyncTasks.UUIDParams;
import com.example.sonja.ui.aws.AWSLoginHandler;
import com.example.sonja.ui.aws.AWSLoginModel;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements AWSLoginHandler {

    AWSLoginModel awsLoginModel;

    private static final String PREFERENCE_USER_EMAIL = "awsUserEmail";

    Button btn_logIn;
    Button btn_forgotPassword;
    Button btn_createAccount;
    Button btnONB;
    SharedPreferences sharedPrefs;
    Context ctx;
    String firstRun;
    // Fahrten updaten

    @Override
    /**
     *
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //addPreferencesFromResource(R.xml.preferences);

        // instantiating AWSLoginModel(context, callback)
        awsLoginModel = new AWSLoginModel(this, this);

        Log.d("MainActivity JWT: ", "before actual JWT");
        Log.d("okay max its here","this is the email:" + AWSLoginModel.getSavedEmail(MainActivity.this));
        Log.d("MainActivity JWT: ", AWSLoginModel.getSavedUserJWT(MainActivity.this));

        btn_logIn = findViewById(R.id.btn_login);
        btn_logIn.getBackground().setAlpha(1);
        btn_logIn.setBackgroundColor(Color.WHITE);
        btn_logIn.setTextColor(Color.BLACK);

        btn_forgotPassword = findViewById(R.id.btn_forgot_password);
        btn_forgotPassword.getBackground().setAlpha(1);

        btn_createAccount = findViewById(R.id.btn_create_account);
        btn_createAccount.getBackground().setAlpha(1);

        btnONB = findViewById(R.id.btnONB);
        btnONB.getBackground().setAlpha(1);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String currentUser = sharedPrefs.getString("uuid","");
        System.out.println("############### MainActivity - uuid : "+currentUser);

        firstRun = sharedPrefs.getString(getString(R.string.sharedPrefsFirstRunString), "true");

        // Um erstes Mal App-Öffnen zu simulieren, den Code aus Line 52-54 und 77-78 aktivieren,
        // einmal laufen lassen, anschließen wieder auskommentieren und nun richtig laufen lassen.

        /*SharedPreferences.Editor clear = sharedPrefs.edit();
          clear.clear().apply();
          System.out.println("reset firstrun: " + getString(R.string.sharedPrefsFirstRunString));*/

    }

    @Override
    public void onRegisterSuccess(boolean mustConfirmToComplete) {
        if (mustConfirmToComplete) {
            Toast.makeText(MainActivity.this, "Almost done! Confirm code to complete registration", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRegisterConfirmed() {
        Toast.makeText(MainActivity.this, "Registered! Login Now!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSuccess() {
        MainActivity.this.startActivity(new Intent(MainActivity.this, NeueFahrt1.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        Toast.makeText(MainActivity.this, whatProcess + exception.getMessage(), Toast.LENGTH_LONG).show();
    }


// testet, ob die App zum ersten Mal aufgerufen wurde.
  @Override
    protected void onResume() {
        super.onResume();

      SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

      if (sharedPrefs.getString(getString(R.string.sharedPrefsFirstRunString), "true").equals("true")) {

        System.out.println("Als firstrun identifiziert");

             SharedPreferences.Editor changeFirst = sharedPrefs.edit();
             changeFirst.putString(getString(R.string.sharedPrefsFirstRunString),"false").apply();

            Intent intent = new Intent(this, Onboarding.class);
            startActivity(intent);
            this.finish();

            /*SharedPreferences.Editor clear = sharedPrefs.edit();
            clear.clear().apply();*/
        }
    }

    public void onClickLogin(View v) throws Exception {
        //ruft NeueFahrt1 auf
        //Intent intent = new Intent(this, NeueFahrt1.class);
        //startActivity(intent);
        //this.finish();
        loginAction();
    }

    public void onClick(View v) {
        //ruft NeueFahrt1 auf
        Intent intent = new Intent(this, NeueFahrt1.class);

        startActivity(intent);
        this.finish();
    }

    public void onClick_ForgotPassword(View v) {
        //if password is forgotten (ruft jetzt erstmal Screen2 auf, solange es keine anderen gibt)
        Intent intent = new Intent(this, ForgotPassword1.class);
        startActivity(intent);
        this.finish();
    }
    public void onClickOnboarding(View v) {

        System.out.println("OnClickOnboarding");
        Intent intent = new Intent(this, Onboarding.class);
        startActivity(intent);
        this.finish();

    }
    public void onClick_CreateAccount (View v) {

        System.out.println("onClick_CreateAccount");
        Intent intent = new Intent(this, RegistrierungStep1.class);
        startActivity(intent);
        this.finish();
    }

    private void loginAction() throws Exception{

        EditText userOrEmail = findViewById(R.id.Username);
        EditText password = findViewById(R.id.Password);

        String username = userOrEmail.getText().toString();
        System.out.println("Username: " + username);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String emailInput = sharedPrefs.getString(getString(R.string.saveEmail), "enrico.boos@test.com");
        System.out.println(emailInput);
        String json = null;

        UUIDParams paramsUUID = new UUIDParams(username);
        GetUUIDAsync asyncRunnerToUser = new GetUUIDAsync();
        String uuid = asyncRunnerToUser.execute(paramsUUID).get();

        System.out.println("aus async gezogen = " + uuid);

        SharedPreferences.Editor saveSignUp = sharedPrefs.edit();
        saveSignUp.putString(getString(R.string.uuid),uuid).apply();


        String uuidDB = sharedPrefs.getString(getString(R.string.uuid), "keine UUID vorhanden");
        System.out.println("uuid aus DB von SharedPreferences "+ uuidDB);


        // do sign in and handles on interface
        awsLoginModel.signInUser(userOrEmail.getText().toString(), password.getText().toString());
    }


}