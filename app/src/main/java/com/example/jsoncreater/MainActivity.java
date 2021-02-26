package com.example.jsoncreater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button editor_btn;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getApplicationContext().getSharedPreferences("JsonArrayPrefs", MODE_PRIVATE);
        editor = prefs.edit();

        editor_btn = findViewById(R.id.openEditorDialog_btn);

        //textView.setText(prefs.getString("JsonArrayPrefs","None"));

        editor_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
    }
}