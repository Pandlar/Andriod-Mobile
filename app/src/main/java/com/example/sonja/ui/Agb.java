package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Agb extends AppCompatActivity implements View.OnClickListener{

    Button agb_back_to_settings;

    @Override
    /**
     * erstellt die angegebene xhtml-Datei und weist dem Button einen onClickListener zu
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agb);

        agb_back_to_settings = findViewById(R.id.agb_back_settings);
        agb_back_to_settings.setOnClickListener(this);
    }

    @Override
    /**
     * beschreibt, was bei einem Click auf den Button funktioniert; die angegebene xhtml-Datei wird aufgerufen
     */
    public void onClick(View v) {
        Intent intent = new Intent(Agb.this, AccountSettings03.class);
        startActivity(intent);
        this.finish();

    }
}
