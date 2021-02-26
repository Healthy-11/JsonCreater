package com.example.jsoncreater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditorActivity extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    EditText tVFirstName;
    EditText tVLastName;
    EditText tVCity;
    EditText tVEmail;
    ImageView imageView;

    JSONArray jsonArray = new JSONArray();

    Button done_btn;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        prefs = getApplicationContext().getSharedPreferences("JsonArrayPrefs", MODE_PRIVATE);
        editor = prefs.edit();

        tVFirstName = findViewById(R.id.tVFirstName);
        tVLastName = findViewById(R.id.tVLastName);
        tVCity = findViewById(R.id.TVCity);
        tVEmail = findViewById(R.id.TVEmail);
        imageView = findViewById(R.id.imageView);

        done_btn = findViewById(R.id.done_btn);

        String strJson = prefs.getString("JsonArrayPrefs","None");


        if (strJson != null) {
            try {
                jsonArray = new JSONArray(strJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                        {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                            }
                        }
                        else
                        {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, 100);
                        }
                    }
                } catch (ActivityNotFoundException e) {
                    // display error state to the user
                }
            }
        });

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    JSONObject json = new JSONObject();
                    json.put("firstname", tVFirstName.getText().toString());
                    json.put("lastname", tVLastName.getText().toString());
                    json.put("city", tVCity.getText().toString());
                    json.put("email", tVEmail.getText().toString());

                    jsonArray.put(json);

                    editor.putString("JsonArrayPrefs", jsonArray.toString());
                    editor.apply();

                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

        }
    }
}