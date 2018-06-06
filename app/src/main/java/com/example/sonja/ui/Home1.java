package com.example.sonja.ui;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Home1 extends AppCompatActivity implements View.OnClickListener{

    Button btn_Fahrt_anlegen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        btn_Fahrt_anlegen = findViewById(R.id.btn_Fahrt_anlegen);
        btn_Fahrt_anlegen.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Fahrt_anlegen:

                // auf Home2 weiterleiten
                Intent intent = new Intent(this, Home2.class);
                startActivity(intent);
                this.finish();
        }}
}