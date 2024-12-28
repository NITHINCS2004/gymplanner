package com.example.gymplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup onClick listener for Workout Plans Chip
        Chip workoutChip = findViewById(R.id.workout);
        workoutChip.setOnClickListener(v -> {
            // Redirect to WorkoutPlansActivity
            Intent intent = new Intent(MainActivity.this, WorkoutPlansActivity.class);
            startActivity(intent);
        });

        // Setup onClick listener for Healthy Tips Button
        Button healthyTipsButton = findViewById(R.id.healthy);  // Ensure this matches the ID in your XML
        healthyTipsButton.setOnClickListener(v -> {
            // Redirect to HealthyTipsActivity
            Intent intent = new Intent(MainActivity.this, HealthyTips.class);
            startActivity(intent);
        });

        Button healthyTipsButton1 = findViewById(R.id.diet);  // Ensure this matches the ID in your XML
        healthyTipsButton1.setOnClickListener(v -> {
            // Redirect to HealthyTipsActivity
            Intent intent = new Intent(MainActivity.this, DietPlans.class);
            startActivity(intent);
        });
        Button healthyTipsButton2 = findViewById(R.id.progress);  // Ensure this matches the ID in your XML
        healthyTipsButton2.setOnClickListener(v -> {
            // Redirect to HealthyTipsActivity
            Intent intent = new Intent(MainActivity.this, Contact.class);
            startActivity(intent);
        });
        Button signupButton = findViewById(R.id.signup); // Make sure the ID matches the one in XML
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to SignUpActivity
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
