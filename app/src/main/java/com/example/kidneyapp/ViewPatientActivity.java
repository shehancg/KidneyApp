package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.kidneyapp.model.PatientData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPatientActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        textView = findViewById(R.id.textView); // Initialize the textView with the appropriate ID from your layout

        // Get the Patient ID from the intent
        String patientId = getIntent().getStringExtra("patientId");

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://kidneyflaskapp-env.eba-ph5m7v3r.us-east-1.elasticbeanstalk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create the ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // Make the API request
        Call<PatientData> call = apiService.getDataById(patientId);
        call.enqueue(new Callback<PatientData>() {
            @Override
            public void onResponse(Call<PatientData> call, Response<PatientData> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Bind the data to your UI elements
                    PatientData patientData = response.body();
                    bindDataToUI(patientData);
                } else {
                    textView.setText("Failed to fetch data");
                }
            }

            @Override
            public void onFailure(Call<PatientData> call, Throwable t) {
                textView.setText("Failed to fetch data");
            }
        });
    }

    private void bindDataToUI(PatientData patientData) {
        // Update UI elements with the fetched data
        String sexText = patientData.getSex().equals("M") ? "Male" : "Female";

        String displayText = "ID:  " + patientData.getId() +
                "\nAge:   " + patientData.getAge() +
                "\nRace:  " + patientData.getRace() +
                "\nSex:   " + sexText +
                "\nBlood Type: " + patientData.getBlood_type() +
                "\nHLA_A1:  " + patientData.getHLA_A1() +
                "\nHLA_A2:  " + patientData.getHLA_A2() +
                "\nHLA_B1:  " + patientData.getHLA_B1() +
                "\nHLA_B2:  " + patientData.getHLA_B2() +
                "\nHLA_DR1:  " + patientData.getHLA_DR1() +
                "\nHLA_DR2:  " + patientData.getHLA_DR2() +
                "\nanti_HBc:  " + patientData.getAnti_HBc() +
                "\nanti_HCV:  " + patientData.getAnti_HCV() +
                "\nagHBs:   " + patientData.getAgHBs() ;

        textView.setText(displayText);
    }
}
