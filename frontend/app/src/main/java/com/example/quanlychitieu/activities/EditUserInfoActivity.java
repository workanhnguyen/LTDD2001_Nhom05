package com.example.quanlychitieu.activities;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.Cloudinary;
import com.cloudinary.ProgressCallback;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.cloudinary.utils.ObjectUtils;
import com.example.quanlychitieu.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditUserInfoActivity extends AppCompatActivity {
    CircleImageView setAvarta;

    int SELECT_IMAGE_CODE = 1;
    Uri uri;

    Cloudinary cloudinary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);

        setAvarta = findViewById(R.id.circleImageView);

        Map config = ObjectUtils.asMap(
                "cloud_name", "dc9dlukr6",
                "api_key", "527716331128638",
                "api_secret", "wwhvtJvGDDDQJFvK7WFQ6YIvBeE"
        );
        cloudinary = new Cloudinary(config);

        setAvarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
                if (uri != null) {
                    // If an image is selected, upload it to Cloudinary
                    uploadImageToCloudinary(uri);
                } else {
                    // Handle the case when no image is selected
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.edit_info);
            actionBar.setElevation(0);
        }
    }

    public void uploadImageToCloudinary(Uri imageUri) {
        try {
            // Get the image bitmap from the Uri
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

            // Convert the bitmap to a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] imageData = byteArrayOutputStream.toByteArray();

            // Upload the image to Cloudinary
            cloudinary.uploader().upload(imageData, ObjectUtils.emptyMap()
            );

        } catch (IOException e) {
            e.printStackTrace();
            // Handle image loading or conversion errors
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            uri = data.getData();
            setAvarta.setImageURI(uri);
        } else {
            Toast.makeText(getApplicationContext(), "No image selected", Toast.LENGTH_SHORT).show();
        }
    }
}