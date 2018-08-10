package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Passwort extends AppCompatActivity implements View.OnClickListener {

    Button back_to_settings_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwort);

        back_to_settings_pw = findViewById(R.id.passwort_aendern);
        back_to_settings_pw.setOnClickListener(this);
    }

    /**
     * Leitet weiter an einen Screen um das Passwort zu ändern.
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.passwort_aendern:
                Intent intent = new Intent(this, AccountSettings03.class);
                startActivity(intent);
                this.finish();
                break;

           /* case R.id.btn_hilfe:
                Intent intent2 = new Intent(this, Hilfe.class);
                startActivity(intent2);
                this.finish();
                break;

            case R.id.agbs:
                Intent intent3 = new Intent(this, Agb.class);
                startActivity(intent3);
                this.finish();
                break;
              */
        }
    }
}
