package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Agb extends AppCompatActivity implements View.OnClickListener{

    Button agb_back_to_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agb);

        agb_back_to_settings = findViewById(R.id.agb_back_settings);
        agb_back_to_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Agb.this, AccountSettings03.class);
        startActivity(intent);
        this.finish();

    }
}
