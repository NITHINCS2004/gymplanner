package com.example.gymplanner;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GymSchedule extends AppCompatActivity {
    EditText meddate;
    Button insert, fetch;
    Spinner workoutSpinner;  // Renamed from medname to workoutSpinner
    Spinner day;
    Switch switch1;
    TextView medtxt;
    DatabaseConnection dbconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gym_schedule);

        workoutSpinner = findViewById(R.id.workoutSpinner);  // Corrected ID for the workout spinner
        meddate = findViewById(R.id.date);
        insert = findViewById(R.id.insert);
        fetch = findViewById(R.id.fetch);
        day = findViewById(R.id.spinner);
        switch1 = findViewById(R.id.switch1);
        medtxt = findViewById(R.id.medtext);
        dbconnection = new DatabaseConnection(this);

        fetch.setVisibility(View.INVISIBLE);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    fetch.setVisibility(View.INVISIBLE);
                    insert.setVisibility(View.VISIBLE);
                    workoutSpinner.setVisibility(View.VISIBLE);  // Show workoutSpinner when switch is off
                    medtxt.setVisibility(View.VISIBLE);
                } else {
                    fetch.setVisibility(View.VISIBLE);
                    insert.setVisibility(View.INVISIBLE);
                    workoutSpinner.setVisibility(View.INVISIBLE);  // Hide workoutSpinner when switch is on
                    medtxt.setVisibility(View.INVISIBLE);
                }
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String workoutName = workoutSpinner.getSelectedItem().toString();  // Get selected item from spinner
                String date = meddate.getText().toString();
                String time = day.getSelectedItem().toString();

                boolean insertResult = dbconnection.insertvalues(workoutName, date, time);
                if (insertResult) {
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_LONG).show();
                    workoutSpinner.setSelection(0);  // Reset the spinner selection
                    meddate.setText(" ");
                } else {
                    Toast.makeText(getApplicationContext(), "Please select a different date and time as an entry already exists.", Toast.LENGTH_LONG).show();
                }
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = meddate.getText().toString();
                String time = day.getSelectedItem().toString();
                String med = "";
                Cursor c = dbconnection.FetchData(date, time);
                if (c.moveToFirst()) {
                    do {
                        med += c.getString(c.getColumnIndexOrThrow("MedicineName")) + "\n";
                    } while (c.moveToNext());
                    Toast.makeText(getApplicationContext(), med, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No Entries in Database", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
