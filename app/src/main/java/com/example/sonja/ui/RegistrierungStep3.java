package com.example.sonja.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.wifi.p2p.WifiP2pManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sonja.ui.asyncTasks.GetUUIDAsync;
import com.example.sonja.ui.asyncTasks.PostCacheLocationAsync;
import com.example.sonja.ui.asyncTasks.PostCacheLocationsParams;
import com.example.sonja.ui.asyncTasks.PostUserAsync;
import com.example.sonja.ui.asyncTasks.PostUserParams;
import com.example.sonja.ui.asyncTasks.UUIDParams;
import com.example.sonja.ui.aws.AWSLoginHandler;
import com.example.sonja.ui.aws.AWSLoginModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class RegistrierungStep3 extends AppCompatActivity implements View.OnClickListener,AWSLoginHandler {

    private static final String TAG = "RegistrierungStep3.java";
    AWSLoginModel awsLoginModel;

    Button btnWeiter3;
    EditText signUpCarMarke;
    EditText signUpCarModell;
    EditText signUpCarFarbe;
    EditText signUpCarNummernschild;
    EditText signUpCarSitzplätze;
    EditText etMarke;
    EditText etModell;
    EditText etFarbe;
    EditText etNummernschild;
    EditText etAnzahlSP;
    RadioButton rbAutocheck;

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

        rbAutocheck = findViewById(R.id.rbAutocheck);

        /*rbAutocheck.addActionListener(new WifiP2pManager.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setEditable(true);

            }
        });*/

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

    public void radioButtonOnClick(View view){

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbAutocheck:
                if (checked)
                    System.out.println("radioButtonOnClick aufgerufen");
                    signUpCarSitzplätze.setEnabled(false);
                    signUpCarFarbe.setEnabled(false);
                    signUpCarModell.setEnabled(false);
                    signUpCarNummernschild.setEnabled(false);
                    signUpCarMarke.setEnabled(false);
                    break;

        }


    }

    public String adressToCoordinates(String adresse) { //gibt Koordinaten einer eingegeben Adresse zurück
        String latlong=null;
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses;
            addresses = geocoder.getFromLocationName(adresse, 1);
            if (addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
                Log.d(TAG, "Koordinaten Test:" + latitude + ","+ longitude);
                latlong = String.valueOf(latitude) +"," + String.valueOf(longitude);

            }
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return latlong;
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter3:

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

                String passwordInput = sharedPrefs.getString(getString(R.string.inputSignUpPassword), "");
                String usernameInput = sharedPrefs.getString(getString(R.string.inputSignUpUsername), "");
                String emailInput = sharedPrefs.getString(getString(R.string.saveEmail), "keine Email vorhanden");
                String userVorname = sharedPrefs.getString(getString(R.string.inputSignUpVorname),"");
                String userNachname = sharedPrefs.getString(getString(R.string.inputSignUpNachname),"");
                String userUsername = sharedPrefs.getString(getString(R.string.inputSignUpUsername),"");
                String userPassword = sharedPrefs.getString(getString(R.string.inputSignUpPassword),"");
                String userStadtHome = sharedPrefs.getString(getString(R.string.inputSignUpStadtHome),"");
                String userTreffpunktHome = sharedPrefs.getString(getString(R.string.inputSignUpTreffpunktHome),"");
                String userTreffpunktWork = sharedPrefs.getString(getString(R.string.inputSignUpTreffpunktWork),"");
                String userStrHome = sharedPrefs.getString(getString(R.string.inputSignUpStrHome),"");
                String userStrWork = sharedPrefs.getString(getString(R.string.inputSignUpStrWork),"");
                String userPLZHome = sharedPrefs.getString(getString(R.string.inputSignUpPLZHome),"");
                String userPLZWork = sharedPrefs.getString(getString(R.string.inputSignUpPLZWork),"");
                String userHandyNr = sharedPrefs.getString(getString(R.string.inputSignUpHandynr),"");
                String carMarke = sharedPrefs.getString(getString(R.string.inputSignUpCarMarke),"");
                String carFarbe = sharedPrefs.getString(getString(R.string.inputSignUpCarFarbe),"");
                String carModell = sharedPrefs.getString(getString(R.string.inputSignUpCarModell),"");
                String carNummernschild = sharedPrefs.getString(getString(R.string.inputSignUpCarNummernschild),"");
                String carSitzplaetze = sharedPrefs.getString(getString(R.string.inputSignUpCarNummernschild),"");



                try {
                    PostUserParams paramsToUser = new PostUserParams( emailInput, userVorname, userNachname,
                            userUsername, userPassword, userStadtHome, userTreffpunktHome, userTreffpunktWork,
                            userStrHome, userStrWork, userPLZHome, userPLZWork, userHandyNr, carMarke, carFarbe,
                            carModell, carNummernschild, carSitzplaetze);
                    PostUserAsync asyncRunnerToUser = new PostUserAsync();
                    asyncRunnerToUser.execute(paramsToUser);}

                catch (Exception e){
                    e.printStackTrace();
                }
                String uuid="";
                try{
                    UUIDParams paramsUUID = new UUIDParams(userUsername);
                    GetUUIDAsync asyncRunnerToUser = new GetUUIDAsync();
                    uuid = asyncRunnerToUser.execute(paramsUUID).get();
                }catch(Exception e){
                    e.printStackTrace();
                }

                try{

                    String adresseHome = userStrHome + ", " + userPLZHome + " " + userStadtHome;
                    System.out.println("Adresse Home: " + adresseHome);
                    String adresseWork = userStrWork + ", " + userPLZWork + " " + userStadtHome;
                    System.out.println("Adresse Work: " + adresseWork);

                    String homeCoordinates = adressToCoordinates(adresseHome);
                    String officeCoordinates = adressToCoordinates(adresseWork);
                    PostCacheLocationsParams params = new PostCacheLocationsParams(uuid, homeCoordinates, officeCoordinates);
                    PostCacheLocationAsync async = new PostCacheLocationAsync();
                    async.execute(params);
                }catch(Exception e){
                    e.printStackTrace();
                }

                awsLoginModel.registerUser(usernameInput, emailInput, passwordInput);

                // auf Registrierungsscreen Ende weiterleiten
                Intent intent = new Intent(this, RegistrierungEnde.class);
                startActivity(intent);
                this.finish();

                break;
        }

    }
}


