package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.kidneyapp.adapter.RecyclerAdapter;
import com.example.kidneyapp.model.KidneyRecipient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DisplayTableActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private RecyclerAdapter adapter;
    private List<KidneyRecipient> kidneyRecipientsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_table);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        kidneyRecipientsList = new ArrayList<>();

        // Get the JSON response string from the Intent
        Intent intent = getIntent();
        String jsonResponse = intent.getStringExtra("jsonData");

        try {
            if (jsonResponse != null && !jsonResponse.isEmpty()) {
                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray kidneyRecipients = jsonObject.getJSONArray("kidney_recipients");

                for (int i = 0; i < kidneyRecipients.length(); i++) {
                    JSONObject recipient = kidneyRecipients.getJSONObject(i);
                    double compatibilityScore = recipient.getDouble("compatibility_score");
                    int id = recipient.getInt("id");

                    KidneyRecipient kidneyRecipient = new KidneyRecipient(id, compatibilityScore);
                    kidneyRecipientsList.add(kidneyRecipient);
                }

                adapter = new RecyclerAdapter(kidneyRecipientsList);
                recyclerView.setAdapter(adapter);
            } else {
                // Handle the case where jsonResponse is null or empty
                Toast.makeText(this, "JSON data is missing or empty", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing exception here
            Toast.makeText(this, "Error parsing JSON data", Toast.LENGTH_SHORT).show();
        }
    }
}