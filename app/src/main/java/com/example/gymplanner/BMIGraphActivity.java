package com.example.gymplanner;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class BMIGraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmigraph); // Make sure this references the correct layout XML

        // Get the container for the graph
        FrameLayout graphContainer = findViewById(R.id.graphContainer);  // Check if the ID is correct

        // Handle Back Button
        Button backButton = findViewById(R.id.backButton);  // Ensure the ID for the button is correct
        backButton.setOnClickListener(v -> finish()); // Navigate back to the previous activity

        // Custom graph view
        View graphView = new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                // Retrieve data passed from the previous activity
                Intent intent = getIntent();
                double weight = intent.getDoubleExtra("weight", 0);
                double height = intent.getDoubleExtra("height", 0);
                double bmi = intent.getDoubleExtra("bmi", 0);

                // Setup Paint
                Paint paint = new Paint();
                paint.setStrokeWidth(5);
                paint.setAntiAlias(true);
                paint.setTextSize(40);

                // Draw axes
                paint.setColor(Color.BLACK);
                canvas.drawLine(100, 800, 100, 200, paint); // Y-axis
                canvas.drawLine(100, 800, 800, 800, paint); // X-axis

                // Add labels
                paint.setColor(Color.BLACK);
                canvas.drawText("BMI Graph", 300, 150, paint);
                canvas.drawText("Height", 850, 820, paint);
                canvas.drawText("Weight", 50, 250, paint);

                // Plot BMI bar with appropriate color
                paint.setColor(getBMIColor(bmi));
                float bmiX = 200; // X-coordinate for BMI bar
                float bmiY = 800 - (float) (bmi * 20); // Calculate Y-coordinate based on BMI value
                canvas.drawRect(bmiX, bmiY, bmiX + 100, 800, paint);

                // Add legend
                paint.setTextSize(30);
                paint.setColor(Color.GREEN);
                canvas.drawText("Normal weight", 500, 250, paint);
                paint.setColor(Color.RED);
                canvas.drawText("Underweight", 500, 300, paint);
                paint.setColor(Color.YELLOW);
                canvas.drawText("Overweight", 500, 350, paint);
                paint.setColor(Color.parseColor("#FF8C00")); // Orange for obesity
                canvas.drawText("Obesity", 500, 400, paint);
            }
        };

        // Add the custom graph view to the container
        graphContainer.addView(graphView);
    }

    /**
     * Get the appropriate color based on BMI value.
     */
    private int getBMIColor(double bmi) {
        if (bmi < 18.5) {
            return Color.RED; // Underweight
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return Color.GREEN; // Normal weight
        } else if (bmi >= 25 && bmi < 29.9) {
            return Color.YELLOW; // Overweight
        } else {
            return Color.parseColor("#FF8C00"); // Obesity
        }
    }
}
