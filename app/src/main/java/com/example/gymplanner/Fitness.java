package com.example.gymplanner;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Fitness extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleText, counterText;
    private Button startButton;

    private final int[] exerciseImages = {
            R.drawable.exercise1, R.drawable.exercise2, R.drawable.exercise3,
            R.drawable.exercise4, R.drawable.exercise5, R.drawable.exercise6
    };

    private final String[] exerciseNames = {
            "Push-ups", "Squats", "Lunges",
            "Plank", "Jumping Jacks", "Sit-ups"
    };

    private int currentExerciseIndex = 0;
    private boolean isResting = false;
    private boolean isRunning = false;
    private int counter = 0;

    private final Handler handler = new Handler();
    private MediaPlayer startSound, completeSound; // Declare MediaPlayer instances

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);

        imageView = findViewById(R.id.displayImage);
        titleText = findViewById(R.id.exerciseName);
        counterText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(view -> {
            if (!isRunning) {
                isRunning = true;
                startWorkout(); // Start workout when button clicked
            }
        });
    }

    private void playStartSound() {
        // Play sound at the start of each exercise
        if (startSound != null) {
            startSound.release(); // Release previous instance if still active
        }
        startSound = MediaPlayer.create(this, R.raw.start_sound); // Reference your start sound file
        startSound.start();
        startSound.setOnCompletionListener(mp -> mp.release()); // Release after completion
    }

    private void playCompleteSound() {
        // Play sound at the end of each exercise
        if (completeSound != null) {
            completeSound.release(); // Release previous instance if still active
        }
        completeSound = MediaPlayer.create(this, R.raw.complete_sound); // Reference your complete sound file
        completeSound.start();
        completeSound.setOnCompletionListener(mp -> mp.release()); // Release after completion
    }

    private void startWorkout() {
        currentExerciseIndex = 0;
        isResting = false;
        counter = 30; // Start with 30 seconds for the first exercise
        updateUI();
        playStartSound(); // Play start sound for the first exercise
        handler.post(workoutRunnable);
    }

    private void updateUI() {
        if (isResting) {
            imageView.setImageResource(R.drawable.rest_image); // Set rest image
            titleText.setText(R.string.rest);
        } else {
            imageView.setImageResource(exerciseImages[currentExerciseIndex]);
            titleText.setText(exerciseNames[currentExerciseIndex]);
        }
        counterText.setText(String.valueOf(counter));
    }

    private final Runnable workoutRunnable = new Runnable() {
        @Override
        public void run() {
            if (counter > 0) {
                counter--;
                counterText.setText(String.valueOf(counter));
                handler.postDelayed(this, 1000); // Update every second
            } else {
                if (isResting) {
                    // Play the complete sound at the end of the exercise
                    playCompleteSound(); // Play sound when exercise ends
                    // Switch to the next exercise
                    currentExerciseIndex++;
                    if (currentExerciseIndex < exerciseImages.length) {
                        isResting = false;
                        counter = 30; // 30 seconds for the next exercise
                        updateUI();
                        playStartSound(); // Play sound for the new exercise
                        handler.post(this);
                    } else {
                        // Workout complete
                        isRunning = false;
                        titleText.setText(R.string.workout_complete);
                        imageView.setImageResource(R.drawable.complete_image); // Set complete image
                        counterText.setText("");
                    }
                } else {
                    // Play the complete sound at the end of the current exercise
                    playCompleteSound(); // Play complete sound before switching to rest
                    // Switch to rest
                    isResting = true;
                    counter = 15; // 15 seconds rest
                    updateUI();
                    handler.post(this);
                }
            }
        }
    };
}
