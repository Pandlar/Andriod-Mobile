package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class RegistrierungStep1 extends AppCompatActivity implements View.OnClickListener{

    Button btnWeiter1;
    RadioButton rbAGB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrierung_step1);

        // Buttons OnClickListener
        btnWeiter1 = findViewById(R.id.btnWeiter1);
        btnWeiter1.setOnClickListener(this);


        rbAGB = findViewById(R.id.rbAGB);
        rbAGB.getBackground().setAlpha(1);

    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnWeiter1:

                // auf Registrierungsscreen Step 2 weiterleiten
                Intent intent = new Intent(this, RegistrierungStep2.class);
                startActivity(intent);
                this.finish();
        }
    }
}
