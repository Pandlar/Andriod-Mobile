package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hilfe extends AppCompatActivity implements View.OnClickListener{


    Button button_hilfe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hilfe);


        button_hilfe = findViewById(R.id.back_to_settings);
        button_hilfe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Hilfe.this, AccountSettings03.class);
        startActivity(intent);
        this.finish();

    }
}
