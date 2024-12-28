package com.example.gymplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the BMI Calculator button
        Button bmiCalculatorButton = findViewById(R.id.bmiCalculatorButton);

        // Set up the BMI Calculator button click listener
        bmiCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to BMICalculator activity
                Intent intent = new Intent(Welcome.this, BMICalculator.class);
                startActivity(intent);
            }
        });

        // Initialize the Water and Nutrient Reminder button
        Button waterReminderButton = findViewById(R.id.waterReminderButton);

        // Set up the Water and Nutrient Reminder button click listener
        waterReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to NutrientReminder activity
                Intent intent = new Intent(Welcome.this, NutrientReminder.class);
                startActivity(intent);
            }
        });
        Button gymScheduleButton = findViewById(R.id.gymScheduleButton);

        // Set up the Water and Nutrient Reminder button click listener
        gymScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to NutrientReminder activity
                Intent intent = new Intent(Welcome.this, GymSchedule.class);
                startActivity(intent);
            }
        });





        Button fitnessChallengeButton = findViewById(R.id.fitnessChallengeButton);
        fitnessChallengeButton.setOnClickListener(view -> {
            Intent intent = new Intent(Welcome.this, Fitness.class);
            startActivity(intent);
        });
    }
}
