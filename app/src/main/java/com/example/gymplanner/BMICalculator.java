package com.example.gymplanner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMICalculator extends AppCompatActivity {

    private EditText weightInput, heightInput;
    private Button calculateBMIButton, viewGraphButton, backButton;
    private TextView resultText;
    private SharedPreferences sharedPreferences;

    private double calculatedBMI; // Variable to store the calculated BMI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        weightInput = findViewById(R.id.weightInput);
        heightInput = findViewById(R.id.heightInput);
        calculateBMIButton = findViewById(R.id.calculateBMIButton);
        viewGraphButton = findViewById(R.id.viewGraphButton);
        resultText = findViewById(R.id.resultText);
        backButton = findViewById(R.id.backButton);

        sharedPreferences = getSharedPreferences("BMIHistory", MODE_PRIVATE);

        // Back button click listener
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMICalculator.this, Welcome.class);  // Change 'Welcome' to your Welcome Activity class name if needed
                startActivity(intent);
                finish(); // Finish the current activity to prevent returning to it on pressing back
            }
        });

        calculateBMIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightStr = weightInput.getText().toString();
                String heightStr = heightInput.getText().toString();

                if (weightStr.isEmpty() || heightStr.isEmpty()) {
                    Toast.makeText(BMICalculator.this, "Please enter both weight and height", Toast.LENGTH_SHORT).show();
                    return;
                }

                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                if (height <= 0 || weight <= 0) {
                    Toast.makeText(BMICalculator.this, "Enter valid weight and height", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Calculate BMI
                calculatedBMI = weight / (height * height);
                String category = getBMICategory(calculatedBMI);

                // Display the BMI and category in the resultText
                resultText.setText(String.format("Your BMI: %.2f\nCategory: %s", calculatedBMI, category));

                // Save the BMI history
                saveBMIHistory(calculatedBMI);
            }
        });

        viewGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pass the weight, height, and calculated BMI to the graph activity
                Intent intent = new Intent(BMICalculator.this, BMIGraphActivity.class);
                intent.putExtra("weight", Double.parseDouble(weightInput.getText().toString()));
                intent.putExtra("height", Double.parseDouble(heightInput.getText().toString()));
                intent.putExtra("bmi", calculatedBMI);
                startActivity(intent);
            }
        });
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    private void saveBMIHistory(double bmi) {
        String history = sharedPreferences.getString("bmiHistory", "");
        history = history.isEmpty() ? String.valueOf(bmi) : history + "," + bmi;

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bmiHistory", history);
        editor.apply();

        Toast.makeText(this, "BMI saved!", Toast.LENGTH_SHORT).show();
    }
}
