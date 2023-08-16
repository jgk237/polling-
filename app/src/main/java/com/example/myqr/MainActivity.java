package com.example.myqr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
//        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bg));
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.home_action_bar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new Handler().postDelayed(new Runnable() {

            @Override

            public void run() {

                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(i);
                finish();

            }

        }, 3*1000);
                

    }
}