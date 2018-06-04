package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bewertung1 extends AppCompatActivity implements View.OnClickListener {

    Button btn_Veroeffentlichen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bewertung1);

        btn_Veroeffentlichen = findViewById(R.id.btn_Veroeffentlichen);
        btn_Veroeffentlichen.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Veroeffentlichen:

                // auf Bewertung2 weiterleiten
                Intent intent = new Intent(this, Bewertung2.class);
                startActivity(intent);
                this.finish();
        }}
}
