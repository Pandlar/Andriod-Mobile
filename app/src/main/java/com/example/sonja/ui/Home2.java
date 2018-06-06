package com.example.sonja.ui;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Home2 extends AppCompatActivity implements View.OnClickListener{

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button:

                // auf Home3 weiterleiten
                Intent intent = new Intent(this, Home3.class);
                startActivity(intent);
                this.finish();
        }}
}