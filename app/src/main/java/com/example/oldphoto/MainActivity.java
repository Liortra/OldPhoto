package com.example.oldphoto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.photoconvertorlibrary.Convertor;

public class MainActivity extends AppCompatActivity {

    private ImageView main_IMG_photo;
    private Button main_BTN_choose;
    private TextView main_TXT_headline;

    private Convertor convertor;

    private static final int IMG_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        listeners();
    }

    private void init() {
        main_IMG_photo = findViewById(R.id.main_IMG_photo);
        main_BTN_choose = findViewById(R.id.main_BTN_choose);
        main_TXT_headline = findViewById(R.id.main_TXT_headline);
        convertor = new Convertor(this);
    }

    private void listeners() {
        main_BTN_choose.setOnClickListener(v -> convertor.changePhoto());
    }

    //handle result of Runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        convertor.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        convertor.onActivityResult(requestCode,resultCode,data,main_IMG_photo);
    }
}