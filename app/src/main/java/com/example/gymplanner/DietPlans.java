package com.example.gymplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DietPlans extends AppCompatActivity {

    // Declare the Back button
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plans); // Ensure this matches your layout file name

        // Initialize the Back button
        backButton = findViewById(R.id.back);

        // Set a click listener for the back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When back button is clicked, navigate to MainActivity
                Intent intent = new Intent(DietPlans.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Close the current activity to prevent it from staying in the stack
            }
        });
    }
}
