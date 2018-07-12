package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword1 extends AppCompatActivity implements View.OnClickListener {

    Button btn_passBack;
    EditText editText_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        btn_passBack = findViewById(R.id.btn_passback);
        btn_passBack.setOnClickListener(this);

        editText_email = findViewById(R.id.editText_email);
        editText_email.setText("muster@mustermail.de");

    }

    public void onClick(View v) {
        //ruft ... auf
        Intent intent = new Intent(this, ForgotPassword2.class);

        startActivity(intent);
        this.finish();
    }
}
