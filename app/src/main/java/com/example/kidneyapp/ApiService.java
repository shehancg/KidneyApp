package com.example.kidneyapp;
import com.example.kidneyapp.model.PatientData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface ApiService {

    @GET("get_data_by_id/{patientId}")
    Call<PatientData> getDataById(@Path("patientId") String patientId);
}
