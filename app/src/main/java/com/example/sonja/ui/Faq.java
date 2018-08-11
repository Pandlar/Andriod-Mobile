package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * FAQ
 */
public class Faq extends AppCompatActivity implements View.OnClickListener{

    Button faq_back_to_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);


        faq_back_to_settings = findViewById(R.id.faq_back_settings);
        faq_back_to_settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Faq.this, AccountSettings03.class);
        startActivity(intent);
        this.finish();

    }
}
