
package com.example.sonja.ui;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Home3 extends AppCompatActivity implements View.OnClickListener{

    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home3);

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button2:

                // auf Home4 weiterleiten
                Intent intent = new Intent(this, Home4.class);
                startActivity(intent);
                this.finish();
        }}
}