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

    /**
     * constructor
     * @param activity - The target activity.
     */
    public Convertor(Activity activity){
        this.activity = activity;
    }

    /**
     * Activate the logic of the library.
     * Asking for permission and ask to pick an image
     */
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

    /**
     * Picking an image from you gallery
     */
    private void pickImageFromGallery() {
        //Intent
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, IMG_PICK_CODE);
    }

    /**
     * Changing the color of the image that was pick from the gallery to black and white
     * @param main_img_photo - The ImageView in your activity
     * @param data - The image that picked from the gallery
     */
    public void changeColorFilter(ImageView main_img_photo, Uri data) {
        main_img_photo.setImageURI(data);
        ColorMatrix matrix = new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        main_img_photo.setColorFilter(filter);
    }

    /**
     * Handle result of Runtime permission
     * @param requestCode - Application specific request code to match with a result
     * @param permissions - The requested permissions. Must me non-null and not empty.
     * @param grantResults - List of integer result code returned. Must me non-null and not empty.
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                pickImageFromGallery();
            else {
                Toast.makeText(activity, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Handle result of picked image
     * @param requestCode - Application specific request code to match with a result
     * @param  - The integer result code returned
     * @param data - The IntentSender to launch.
     * @param main_IMG_photo - The ImageView to put the photo from your gallery.
     */
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, ImageView main_IMG_photo) {
        if (resultCode == activity.RESULT_OK && requestCode == IMG_PICK_CODE) {
            changeColorFilter(main_IMG_photo,data.getData());
        }
    }
}
