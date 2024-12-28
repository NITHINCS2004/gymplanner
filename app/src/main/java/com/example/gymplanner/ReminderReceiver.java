package com.example.gymplanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class ReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Display a Toast message when the alarm is triggered
        Toast.makeText(context, "Alarm triggered!", Toast.LENGTH_LONG).show();

        // Vibrate the phone for 10 seconds (10,000 milliseconds)
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For Android O and above, use VibrationEffect
                vibrator.vibrate(android.os.VibrationEffect.createOneShot(10000, android.os.VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                // For older versions, use the deprecated method
                vibrator.vibrate(10000);
            }
        }

        // Play custom sound from the raw folder (ensure you have the audio file placed in res/raw folder)
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alert_sound); // Replace 'alert_sound' with your audio file name
        mediaPlayer.start(); // Start playing the sound

        // Release the media player once the sound has finished
        mediaPlayer.setOnCompletionListener(mp -> mp.release());
    }
}
