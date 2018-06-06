package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bewertung2 extends AppCompatActivity implements View.OnClickListener{

    Button btn_Veroeffentlichen2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung2);

        btn_Veroeffentlichen2 = findViewById(R.id.btn_Veroeffentlichen2);
        btn_Veroeffentlichen2.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Veroeffentlichen2:

                // auf Home1 weiterleiten
                Intent intent = new Intent(this, Home1.class);
                startActivity(intent);
                this.finish();
    }
}}
