package com.example.sonja.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.auth.core.StartupAuthResult;
import com.amazonaws.mobile.auth.core.StartupAuthResultHandler;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AWSMobileClient.getInstance().initialize(Splash.this, new AWSStartupHandler() {
            @Override
            public void onComplete(AWSStartupResult awsStartupResult) {
                IdentityManager identityManager = IdentityManager.getDefaultIdentityManager();
                identityManager.resumeSession(Splash.this, new StartupAuthResultHandler() {
                    @Override
                    public void onComplete(StartupAuthResult authResults) {
                        if (authResults.isUserSignedIn()) {
                            startActivity(new Intent(Splash.this, NeueFahrt1.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        } else {
                            startActivity(new Intent(Splash.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        }
                    }
                }, 3000);
            }
        }).execute();

    }

}
