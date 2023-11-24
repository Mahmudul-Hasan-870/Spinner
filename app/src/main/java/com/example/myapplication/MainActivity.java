package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner districtSpinner;
    private Spinner upazilaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        districtSpinner = findViewById(R.id.districtSpinner);
        upazilaSpinner = findViewById(R.id.upazilaSpinner);

        // Create ArrayAdapter for districts
        ArrayAdapter<CharSequence> districtAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.districts,
                android.R.layout.simple_spinner_item
        );
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(districtAdapter);

        // Set upazilas based on selected district
        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                populateUpazilas(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        // Create ArrayAdapter for upazilas
        ArrayAdapter<CharSequence> upazilaAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item
        );
        upazilaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        upazilaSpinner.setAdapter(upazilaAdapter);

        // Submit button click listener
        Button submitBtn = findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSubmit();
            }
        });
    }

    private void populateUpazilas(int districtPosition) {
        String[] upazilas;

        switch (districtPosition) {
            case 0: // Dhaka
                upazilas = getResources().getStringArray(R.array.upazilas_dhaka);
                break;
            case 1: // Chittagong
                upazilas = getResources().getStringArray(R.array.upazilas_chittagong);
                break;
            default:
                upazilas = new String[0];
        }

        ArrayAdapter<String> upazilaAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                upazilas
        );
        upazilaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        upazilaSpinner.setAdapter(upazilaAdapter);
    }

    private void validateAndSubmit() {
        String selectedDistrict = districtSpinner.getSelectedItem().toString();
        String selectedUpazila = upazilaSpinner.getSelectedItem().toString();

        // Validate district
        if (selectedDistrict.equals("Select District")) {
            Toast.makeText(this, "Please select a district", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate upazila
        if (selectedUpazila.equals("Select Upazila")) {
            Toast.makeText(this, "Please select an upazila", Toast.LENGTH_SHORT).show();
            return;
        }

        // If all validations pass, you can proceed with submitting the data
        // TODO: Implement your submission logic here

        // For example, you might want to display a success message or send the data to a server
        // You can replace the following with your actual logic
        String submissionMessage = "Submitted:\nDistrict: " + selectedDistrict + "\nUpazila: " + selectedUpazila;
        Toast.makeText(this, submissionMessage, Toast.LENGTH_SHORT).show();
    }
}
