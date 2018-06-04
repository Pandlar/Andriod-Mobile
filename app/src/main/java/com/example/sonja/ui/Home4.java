package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home4 extends AppCompatActivity implements View.OnClickListener{

    Button btn_jetzt_bestätigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home4);

        btn_jetzt_bestätigen = findViewById(R.id.btn_jetzt_bestätigen);
        btn_jetzt_bestätigen.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Veroeffentlichen:

                // auf Home5 weiterleiten
                Intent intent = new Intent(this, Home5.class);
                startActivity(intent);
                this.finish();
        }}
}
