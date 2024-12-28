package com.example.gymplanner;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class NutrientReminder extends AppCompatActivity {

    private EditText etAlarm;
    private Button setReminderButton;
    private TextView reminderClock; // TextView to show the countdown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient_remainder);

        // Initialize UI elements
        etAlarm = findViewById(R.id.etAlarm);
        setReminderButton = findViewById(R.id.setReminderButton);
        reminderClock = findViewById(R.id.reminderClock); // Initialize the clock TextView

        // Set OnClickListener for the button
        setReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Parse the entered time in seconds
                    int time = Integer.parseInt(etAlarm.getText().toString());

                    // Start a countdown timer
                    new CountDownTimer(time * 1000, 1000) { // Set countdown time in milliseconds
                        @Override
                        public void onTick(long millisUntilFinished) {
                            // Format the remaining time in minutes:seconds format
                            int minutes = (int) (millisUntilFinished / 1000) / 60;
                            int seconds = (int) (millisUntilFinished / 1000) % 60;
                            String timeLeft = String.format("%02d:%02d", minutes, seconds);
                            reminderClock.setText(timeLeft); // Update the clock TextView
                        }

                        @Override
                        public void onFinish() {
                            reminderClock.setText("00:00"); // Reset to 00:00 when the countdown finishes
                            Toast.makeText(NutrientReminder.this, "Time's up! Take your nutrient!", Toast.LENGTH_SHORT).show();
                        }
                    }.start(); // Start the countdown timer

                    // Set the alarm to trigger after the specified time (in seconds)
                    Intent i = new Intent(NutrientReminder.this, ReminderReceiver.class);
                    PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, PendingIntent.FLAG_IMMUTABLE);
                    AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long triggerTime = System.currentTimeMillis() + time * 1000; // Time in milliseconds
                    am.set(AlarmManager.RTC_WAKEUP, triggerTime, pi);

                    Toast.makeText(NutrientReminder.this, "Alarm set for " + time + " seconds", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(NutrientReminder.this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
