package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button addButton = findViewById(R.id.btn_add);
        Button searchButton = findViewById(R.id.btn_search);

        addButton.setOnClickListener(v -> {
            // Create an Intent to open the target activity
            Intent intent = new Intent(HomeActivity.this, AddDonorActivity.class); // Replace TargetActivity with the actual name of your target activity class

            // Start the target activity
            startActivity(intent);
        });

        searchButton.setOnClickListener(v -> {
            // Create an Intent to open the target activity
            Intent intent = new Intent(HomeActivity.this, MainActivity.class); // Replace TargetActivity with the actual name of your target activity class

            // Start the target activity
            startActivity(intent);
        });
    }

}