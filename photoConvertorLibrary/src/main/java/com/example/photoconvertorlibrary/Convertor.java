package com.example.photoconvertorlibrary;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Build;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Convertor {

    private final Activity activity;
    private static final int IMG_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    public Convertor(Activity activity){
        this.activity = activity;
    }

    public void changePhoto(){
        //check Runtime permission
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                //permission not granted, request it
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                //show popup for runtime permission
                activity.requestPermissions(permissions, PERMISSION_CODE);
            }else{ //permission already granted
                pickImageFromGallery();
            }
        }else{ //system os is less than marshmallow
            pickImageFromGallery();
        }
    }

    private void pickImageFromGallery() {
        //Intent
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, IMG_PICK_CODE);
    }

    public void changeColorFilter(ImageView main_img_photo, Uri data) {
        main_img_photo.setImageURI(data);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        main_img_photo.setColorFilter(filter);
    }

    //handle result of Runtime permission
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                pickImageFromGallery();
            else {
                Toast.makeText(activity, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    //handle result of picked image
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, ImageView main_IMG_photo) {
        if (resultCode == activity.RESULT_OK && requestCode == IMG_PICK_CODE) {
            changeColorFilter(main_IMG_photo,data.getData());
        }
    }
}
