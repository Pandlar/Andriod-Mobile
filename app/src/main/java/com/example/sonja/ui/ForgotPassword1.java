package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPassword1 extends AppCompatActivity implements View.OnClickListener {

    Button btn_passBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        btn_passBack = findViewById(R.id.btn_passback);
        btn_passBack.setOnClickListener(this);

    }

    public void onClick(View v) {
        //ruft ... auf
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        this.finish();
    }
}
