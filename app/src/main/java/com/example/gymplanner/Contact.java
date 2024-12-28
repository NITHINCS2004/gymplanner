package com.example.gymplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    private Button contactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize the button
        contactButton = findViewById(R.id.back);

        // Set click listener for the button
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to MainActivity
                Intent intent = new Intent(Contact.this, MainActivity.class);
                startActivity(intent);

                // Optionally finish the current activity to remove it from the stack
                finish();
            }
        });
    }
}
