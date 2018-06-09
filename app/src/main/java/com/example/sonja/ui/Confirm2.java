package com.example.sonja.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Confirm2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm2);
    }

   /* public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_weiter_ok:
                // auf Screen3 weiterleiten
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
                break;
        }
    }  */
   public void onClickConfirm (View v) {
       System.out.println("onClickConfirm aufgerufen");
       Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
       this.finish();
   }

    // When clicking back you get redirected to starting screen.
    public void onBackPressed(){
        System.out.println("Confirm onBackPressed() aufgerufen.");
        Intent intent = new Intent(this, Confirm.class);
        startActivity(intent);
        this.finish();
    }


}
