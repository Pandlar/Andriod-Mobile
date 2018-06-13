package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrierungStep2 extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step2);

        // Buttons OnClickListener
        btnWeiter2 = findViewById(R.id.btnWeiter2);
        btnWeiter2.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter2:

                // auf Registrierungsscreen Step 3 weiterleiten
                Intent intent = new Intent(this, RegistrierungStep3.class);
                startActivity(intent);
                this.finish();
        }
    }
}



