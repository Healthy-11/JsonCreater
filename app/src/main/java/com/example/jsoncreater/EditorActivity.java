package com.example.jsoncreater;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class EditorActivity extends AppCompatActivity {

    EditText tVFirstName;
    EditText tVLastName;
    EditText tVCity;
    EditText tVEmail;

    Button done_btn;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        tVFirstName = findViewById(R.id.tVFirstName);
        tVLastName = findViewById(R.id.tVLastName);
        tVCity = findViewById(R.id.TVCity);
        tVEmail = findViewById(R.id.TVEmail);

        done_btn = findViewById(R.id.done_btn);

        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    JSONObject json = new JSONObject();
                    json.put("firstname", tVFirstName.getText().toString());
                    json.put("lastname", tVLastName.getText().toString());
                    json.put("city", tVCity.getText().toString());
                    json.put("email", tVEmail.getText().toString());
                    System.out.println(json.toString());

                } catch (JSONException e) {

                    e.printStackTrace();
                }


            }
        });

    }
}