package com.example.myqr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    MaterialButton bt_scan,bt_generate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.home_action_bar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.white));
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bt_scan = findViewById(R.id.scan_bt);
        bt_generate = findViewById(R.id.generate_bt);

        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(HomeActivity.this);
                integrator.setOrientationLocked(true);
                integrator.setPrompt("Focus to a QR code");
                integrator.setBeepEnabled(false);
                integrator.initiateScan();
            }
        });

        bt_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(HomeActivity.this,GenerateActivity.class);
                startActivity(k);
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode , int resultCode , @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Cancelled Operation", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(result.getContents()));
                startActivity(intent);
            }
        }
    }
}