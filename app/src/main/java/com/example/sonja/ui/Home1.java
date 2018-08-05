package com.example.sonja.ui;

        import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.design.widget.BottomNavigationView;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;

public class Home1 extends AppCompatActivity implements View.OnClickListener{

    ImageButton btn_Fahrt_anlegen;
    Button kasten_chronik;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener(){

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(Home1.this, Home3.class);
                    startActivityForResult(intentHome, 0);
                    return true;
                case R.id.navigation_offer:
                    Intent intentOffer = new Intent(Home1.this, NeueFahrt1.class);
                    startActivityForResult(intentOffer, 0);
                    return true;
                case R.id.navigation_profile:
                    Intent intentProfile = new Intent(Home1.this, account01.class);
                    startActivityForResult(intentProfile, 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        System.out.println("Home1.java aufgerufen");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        btn_Fahrt_anlegen = findViewById(R.id.btn_Fahrt_anlegen);
        btn_Fahrt_anlegen.setOnClickListener(this);

        kasten_chronik = findViewById(R.id.kasten_chronik);
        kasten_chronik.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Fahrt_anlegen:
                // auf NeueFahrt1 weiterleiten
                Intent intent = new Intent(this, NeueFahrt1.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.kasten_chronik:
                // auf Home2 weiterleiten
                Intent intent2 = new Intent(this, Home2.class);
                startActivity(intent2);
                this.finish();
        }}



}