package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckPatientId extends AppCompatActivity {
    private  EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_patient_id);

        inputText = findViewById(R.id.inputtext);
        Button searchButton = findViewById(R.id.search);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the EditText
                String id = inputText.getText().toString();

                // Check if the EditText is empty
                if (id.isEmpty()) {
                    // Show a toast message for validation
                    Toast.makeText(CheckPatientId.this, "Please enter a patient ID", Toast.LENGTH_SHORT).show();
                } else {
                    // Create an intent to start AnotherActivity
                    Intent intent = new Intent(CheckPatientId.this, ViewPatientActivity.class);

                    // Pass the ID as an extra with the intent
                    intent.putExtra("patientId", id);

                    // Start the other activity
                    startActivity(intent);
                }
            }
        });
    }
}
