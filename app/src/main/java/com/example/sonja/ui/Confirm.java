package com.example.sonja.ui;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Confirm extends AppCompatActivity implements View.OnClickListener {

    TextView abfahrt1, ankunft1, sitze1;
    TextView abfahrt2, ankunft2, sitze2;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        abfahrt1 = findViewById(R.id.txt_abfahrt1);
        ankunft1 = findViewById(R.id.txt_ankunft1);
        sitze1 = findViewById(R.id.txt_sitze1);

        abfahrt1.setText("08:00 Uhr");
        ankunft1.setText("09:00 Uhr");
        sitze1.setText("3");

        abfahrt2 = findViewById(R.id.txt_abfahrt2);
        ankunft2 = findViewById(R.id.txt_ankunft2);
        sitze2 = findViewById(R.id.txt_sitze2);

        abfahrt2.setText("16:00 Uhr");
        ankunft2.setText("17:00 Uhr");
        sitze2.setText("3");

        // Buttons OnClickListener
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
    }



    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_confirm:
                // auf Screen3 weiterleiten
                Intent intent = new Intent(this, Confirm2.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }

        // When clicking back you get redirected to starting screen.
        public void onBackPressed() {
            System.out.println("Neue Fahrt2 onBackPressed() aufgerufen.");
            Intent intent = new Intent(this, NeueFahrt2.class);
            startActivity(intent);
            this.finish();
        }
    }

