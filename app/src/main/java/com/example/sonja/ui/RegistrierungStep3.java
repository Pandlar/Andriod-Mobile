package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrierungStep3 extends AppCompatActivity implements View.OnClickListener {

    Button btnWeiter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step3);

        // Buttons OnClickListener
        btnWeiter3 = findViewById(R.id.btnWeiter3);
        btnWeiter3.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter3:

                // auf Registrierungsscreen Ende weiterleiten
                Intent intent = new Intent(this, RegistrierungEnde.class);
                startActivity(intent);
                this.finish();
        }
    }
}


