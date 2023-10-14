package com.example.kidneyapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kidneyapp.model.SpinnerItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddDonorActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3 ,editText4, editText5, editText6 ,editText7, editText8, editText9 ,editText10, editText11;
    private Spinner spinner1, spinner2, spinner3;

    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donor);

        // Initialize the requestQueue
        requestQueue = Volley.newRequestQueue(this);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);

        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);
        editText11 = findViewById(R.id.editTextId);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        Button myButton = findViewById(R.id.sendButton);

        // Create ArrayLists of SpinnerItem objects for each Spinner
        ArrayList<SpinnerItem> spinnerItems1 = new ArrayList<>();
        spinnerItems1.add(new SpinnerItem("Select Race", "")); // Hint or label item
        spinnerItems1.add(new SpinnerItem("White", "White")); // Replace with your item names and values
        spinnerItems1.add(new SpinnerItem("Brown", "Brown"));
        spinnerItems1.add(new SpinnerItem("Black", "Black"));
        spinnerItems1.add(new SpinnerItem("Asian", "Asian"));

        ArrayList<SpinnerItem> spinnerItems2 = new ArrayList<>();
        spinnerItems2.add(new SpinnerItem("Select Gender", "")); // Hint or label item
        spinnerItems2.add(new SpinnerItem("Male", "M")); // Replace with your item names and values
        spinnerItems2.add(new SpinnerItem("Female", "F"));

        ArrayList<SpinnerItem> spinnerItems3 = new ArrayList<>();
        spinnerItems3.add(new SpinnerItem("Select Blood Type", "")); // Hint or label item
        spinnerItems3.add(new SpinnerItem("A", "A")); // Replace with your item names and values
        spinnerItems3.add(new SpinnerItem("B", "B"));
        spinnerItems3.add(new SpinnerItem("AB", "AB"));
        spinnerItems3.add(new SpinnerItem("O", "O"));

        // Create ArrayAdapter instances for each Spinner using the common model class
        /*ArrayAdapter<SpinnerItem> adapter1 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerItems1
        );

        ArrayAdapter<SpinnerItem> adapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerItems2
        );

        ArrayAdapter<SpinnerItem> adapter3 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                spinnerItems3
        );*/

        // Create ArrayAdapter instances for all Spinners using the common model class
        ArrayAdapter<SpinnerItem> adapter1 = createSpinnerAdapter(spinnerItems1);
        ArrayAdapter<SpinnerItem> adapter2 = createSpinnerAdapter(spinnerItems2);
        ArrayAdapter<SpinnerItem> adapter3 = createSpinnerAdapter(spinnerItems3);


        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapters to the Spinners
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        myButton.setOnClickListener(view -> {
            boolean isAnyFieldEmpty = TextUtils.isEmpty(editText1.getText().toString()) ||
                    TextUtils.isEmpty(editText2.getText().toString()) ||
                    TextUtils.isEmpty(editText3.getText().toString()) ||
                    TextUtils.isEmpty(editText4.getText().toString()) ||
                    TextUtils.isEmpty(editText5.getText().toString()) ||
                    TextUtils.isEmpty(editText6.getText().toString()) ||
                    TextUtils.isEmpty(editText7.getText().toString()) ||
                    TextUtils.isEmpty(editText8.getText().toString()) ||
                    TextUtils.isEmpty(editText9.getText().toString()) ||
                    TextUtils.isEmpty(editText10.getText().toString()) ||
                    TextUtils.isEmpty(editText11.getText().toString());

            boolean isAnySpinnerEmpty = spinner1.getSelectedItemPosition() == 0 ||
                    spinner2.getSelectedItemPosition() == 0 ||
                    spinner3.getSelectedItemPosition() == 0;

            if (isAnyFieldEmpty || isAnySpinnerEmpty) {
                // Display a single error message if any field is empty
                Toast.makeText(AddDonorActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                return; // Exit the click event handler
            }
            // Display a toast message when the button is clicked
            Toast.makeText(AddDonorActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();

            String text1 = editText1.getText().toString();
            String text2 = editText2.getText().toString();
            String text3 = editText3.getText().toString();
            String text4 = editText4.getText().toString();
            String text5 = editText5.getText().toString();
            String text6 = editText6.getText().toString();
            String text7 = editText7.getText().toString();
            String text8 = editText8.getText().toString();
            String text9 = editText9.getText().toString();
            String text10 = editText10.getText().toString();
            Integer text11 = Integer.valueOf(editText11.getText().toString());

            SpinnerItem selectedSpinner1Item = (SpinnerItem) spinner1.getSelectedItem();
            SpinnerItem selectedSpinner2Item = (SpinnerItem) spinner2.getSelectedItem();
            SpinnerItem selectedSpinner3Item = (SpinnerItem) spinner3.getSelectedItem();

            // Create a JSON object with the collected data
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("Id", text11);
                jsonObject.put("age", text1);
                jsonObject.put("HLA_A1", text2);
                jsonObject.put("HLA_A2", text3);
                jsonObject.put("HLA_B1", text4);
                jsonObject.put("HLA_B2", text5);
                jsonObject.put("HLA_DR1", text6);
                jsonObject.put("HLA_DR2", text7);
                jsonObject.put("anti_HBc", text8);
                jsonObject.put("anti_HCV", text9);
                jsonObject.put("agHBs", text10);
                jsonObject.put("race", selectedSpinner1Item.getItemValueString());
                jsonObject.put("sex", selectedSpinner2Item.getItemValueString());
                jsonObject.put("Blood_type", selectedSpinner3Item.getItemValueString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Define your API endpoint URL
            String apiUrl = "http://kidneyflaskapp-env.eba-ph5m7v3r.us-east-1.elasticbeanstalk.com/addData"; // Replace with your API URL

            // Before making the API request, create and show a ProgressDialog
            ProgressDialog progressDialog = new ProgressDialog(AddDonorActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false); // Prevent users from dismissing the dialog
            progressDialog.show();

            // Send JSON data to the API using Volley
            JsonObjectRequest request = new JsonObjectRequest(
                    Request.Method.POST,
                    apiUrl,
                    jsonObject,
                    response -> {
                        // Dismiss the ProgressDialog when the response arrives
                        progressDialog.dismiss();
                        // Handle a successful response from the API here
                        try {
                            // Extract data from the JSON response, assuming it's an object with a key like "message"
                            String message = response.getString("message");

                            // Display a success message using an AlertDialog
                            AlertDialog.Builder builder = new AlertDialog.Builder(AddDonorActivity.this);
                            builder.setTitle("Success")
                                    .setMessage(message)
                                    .setPositiveButton("OK", (dialog, which) -> {
                                        // Handle OK button click if needed
                                        dialog.dismiss(); // Dismiss the dialog
                                    })
                                    .show();

                            // Optionally, you can navigate to another activity or perform additional actions here
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Handle JSON parsing error if necessary
                        }
                    },
                    error -> {
                        // Dismiss the ProgressDialog when there's an error
                        progressDialog.dismiss();
                        // Handle API errors here
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            String errorMessage = new String(error.networkResponse.data);

                            // Check if the status code indicates an error
                            if (statusCode >= 400) {
                                // Handle the error here and display an appropriate message
                                // You can parse the errorMessage JSON if it contains error details
                                Toast.makeText(AddDonorActivity.this, "ID Already Exists " + statusCode, Toast.LENGTH_SHORT).show();
                            } else {
                                // Handle other network-related errors (e.g., connection issues)
                                Toast.makeText(AddDonorActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // Handle other types of errors
                            Toast.makeText(AddDonorActivity.this, "Unknown Error", Toast.LENGTH_SHORT).show();
                            Log.e("YourTag", "Error message: " + error.getMessage());
                        }
                    }
            );

            // Add the request to the request queue
            requestQueue.add(request);
        });
    }
    // Create a common method to configure the ArrayAdapter
    private ArrayAdapter<SpinnerItem> createSpinnerAdapter(ArrayList<SpinnerItem> items) {
        return new ArrayAdapter<SpinnerItem>(
                this,
                android.R.layout.simple_spinner_item,
                items
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);

                // Modify the text appearance for the hint or label item
                if (position == 0) {
                    textView.setTextColor(Color.GRAY); // Change text color for hint
                } else {
                    textView.setTextColor(Color.WHITE); // Change text color for selected item
                }

                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = view.findViewById(android.R.id.text1);

                // Modify the text appearance for the hint or label item
                if (position == 0) {
                    textView.setTextColor(Color.GRAY); // Change text color for hint
                } else {
                    textView.setTextColor(Color.WHITE); // Change text color for other items
                }

                return view;
            }
        };
    }
}