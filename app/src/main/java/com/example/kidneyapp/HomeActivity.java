package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button addButton = findViewById(R.id.btn_add);
        Button searchButton = findViewById(R.id.btn_search);
        Button recentAdded = findViewById(R.id.btn_recent);
        Button checkPatientId = findViewById(R.id.checkid); // Corrected variable name

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the target activity
                Intent intent = new Intent(HomeActivity.this, AddDonorActivity.class);

                // Start the target activity
                startActivity(intent);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the target activity
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);

                // Start the target activity
                startActivity(intent);
            }
        });

        recentAdded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open the target activity
                Intent intent = new Intent(HomeActivity.this, RecentAddActivity.class);

                // Start the target activity
                startActivity(intent);
            }
        });

        checkPatientId.setOnClickListener(new View.OnClickListener() { // Corrected variable name
            @Override
            public void onClick(View v) {
                // Create an Intent to open the target activity
                Intent intent = new Intent(HomeActivity.this, CheckPatientId.class);

                // Start the target activity
                startActivity(intent);
            }
        });
    }
}