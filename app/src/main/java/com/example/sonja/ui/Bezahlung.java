package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bezahlung extends AppCompatActivity implements View.OnClickListener{

    Button ok_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezahlung);


        ok_button = findViewById(R.id.okay);
        ok_button.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        //ruft AccountSettings03 wieder auf
        Intent intent = new Intent(this, AccountSettings03.class);
        startActivity(intent);
        this.finish();
    }



}
