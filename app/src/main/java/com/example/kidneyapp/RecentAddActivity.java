package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kidneyapp.adapter.CardAdapter;
import com.example.kidneyapp.model.KidneyDataItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecentAddActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_add);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Define your API endpoint URL
        String apiUrl = "http://kidneyflaskapp-env.eba-ph5m7v3r.us-east-1.elasticbeanstalk.com/get_last_rows";

        // Initialize Volley request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Make a JSON array request to the API
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<KidneyDataItem> kidneyDataItems = new ArrayList<>();

                            // Parse the JSON response into a list of KidneyDataItem objects
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                KidneyDataItem kidneyDataItem = new KidneyDataItem();
                                kidneyDataItem.setBlood_type(jsonObject.getString("Blood_type"));
                                kidneyDataItem.setId(jsonObject.getString("Id"));
                                kidneyDataItem.setAge(jsonObject.getString("age"));
                                kidneyDataItems.add(kidneyDataItem);
                            }

                            // Set up the RecyclerView adapter
                            cardAdapter = new CardAdapter(kidneyDataItems);
                            recyclerView.setAdapter(cardAdapter);

                        } catch (JSONException e) {
                            Log.e("CardActivity", "Error parsing JSON: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("CardActivity", "Volley Error: " + error.getMessage());
                    }
                }
        );

        // Add the request to the request queue
        requestQueue.add(jsonArrayRequest);
    }
}