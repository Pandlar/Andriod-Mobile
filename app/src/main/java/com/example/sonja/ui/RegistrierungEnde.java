package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrierungEnde extends AppCompatActivity implements View.OnClickListener {

    Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_ende);

        // Buttons OnClickListener
        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);

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
