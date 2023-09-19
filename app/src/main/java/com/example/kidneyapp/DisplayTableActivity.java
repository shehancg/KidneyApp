package com.example.kidneyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DisplayTableActivity extends AppCompatActivity {
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_table);
        /*Button downloadButton = findViewById(R.id.downloadButton);*/

        // Get the JSON response string from the Intent
        Intent intent = getIntent();
        // Get the JSON response string (you can pass it from the previous activity)
        String jsonResponse = intent.getStringExtra("jsonData");

        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray kidneyRecipients = jsonObject.getJSONArray("kidney_recipients");

            // Get the TableLayout from the layout
            TableLayout tableLayout = findViewById(R.id.tableLayout);

            for (int i = 0; i < kidneyRecipients.length(); i++) {
                JSONObject recipient = kidneyRecipients.getJSONObject(i);
                double compatibilityScore = recipient.getDouble("compatibility_score");
                int id = recipient.getInt("id");

                // Create a new row
                TableRow row = new TableRow(this);

                TextView idTextView = new TextView(this);
                idTextView.setText(String.valueOf(id));
                idTextView.setGravity(Gravity.CENTER);
                idTextView.setTextSize(18);
                idTextView.setPadding(16, 16, 16, 16);

                // Create TextViews for each column
                TextView scoreTextView = new TextView(this);
                scoreTextView.setText(String.valueOf(compatibilityScore));
                scoreTextView.setGravity(Gravity.CENTER);
                idTextView.setTextSize(18); // Set text size
                idTextView.setPadding(16, 16, 16, 16);

                // Add TextViews to the row
                row.addView(idTextView);
                row.addView(scoreTextView);

                // Add the row to the table
                tableLayout.addView(row);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Set a click listener for the download button
        /*downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Generate and save the Excel file
                    generateExcelFile();
                    Toast.makeText(DisplayTableActivity.this, "Excel file downloaded", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DisplayTableActivity.this, "Error generating Excel file", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
    private void generateExcelFile() {
        // Create a new Excel workbook
        Workbook workbook = new XSSFWorkbook();

        // Create a new sheet
        Sheet sheet = workbook.createSheet("KidneyData");

        try {
            // Get the JSON response string from the Intent
            Intent intent = getIntent();
            String jsonResponse = intent.getStringExtra("jsonData");

            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray kidneyRecipients = jsonObject.getJSONArray("kidney_recipients");

            // Create the header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Compatibility Score");

            // Fill in data from the JSON response
            for (int i = 0; i < kidneyRecipients.length(); i++) {
                JSONObject recipient = kidneyRecipients.getJSONObject(i);
                double compatibilityScore = recipient.getDouble("compatibility_score");
                int id = recipient.getInt("id");

                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(id);
                row.createCell(1).setCellValue(compatibilityScore);
            }

            // Save the workbook to a file in the "Documents" directory of internal storage
            File excelFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "kidney_data.xlsx");
            FileOutputStream outputStream = new FileOutputStream(excelFile);
            workbook.write(outputStream);
            outputStream.close();

            Toast.makeText(DisplayTableActivity.this, "Excel file downloaded to internal storage", Toast.LENGTH_SHORT).show();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

}