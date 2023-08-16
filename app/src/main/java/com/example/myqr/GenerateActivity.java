package com.example.myqr;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class GenerateActivity extends AppCompatActivity {
    TextInputEditText text;
    Button bt_generate,download;
    ImageView im_qr;
    OutputStream output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.home_action_bar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setBackgroundDrawable(getDrawable(R.color.white));

        text = findViewById(R.id.link);
        bt_generate = findViewById(R.id.bt_generateQR);
        im_qr=findViewById(R.id.qr_code);
        download = findViewById(R.id.download);

        bt_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = text.getText().toString().trim();

                if(link.isEmpty()){
                    Toast.makeText(GenerateActivity.this, "Please enter valid text", Toast.LENGTH_SHORT).show();
                }else{
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try{
                        BitMatrix matrix = writer.encode(link, BarcodeFormat.QR_CODE,350,350);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap map = encoder.createBitmap(matrix);
                        im_qr.setImageBitmap(map);                        download.setVisibility(View.VISIBLE);
                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(text.getApplicationWindowToken(),0);
                    }catch(WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream outStream = null;
                BitmapDrawable drawable = (BitmapDrawable) im_qr.getDrawable();
                Bitmap map = drawable.getBitmap();

                File filepath = Environment.getExternalStorageDirectory();
                File dir  = new File(filepath.getAbsoluteFile()+"/QRCodes/");
                dir.mkdirs();
                File file =new File(dir,System.currentTimeMillis()+".jpg");

                try{
                    outStream = new FileOutputStream(file);
                }catch (FileNotFoundException e){
                    Toast.makeText(GenerateActivity.this, "problem1", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                map.compress(Bitmap.CompressFormat.JPEG,100,output);
                try {
                    outStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}