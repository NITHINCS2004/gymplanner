package com.example.gymplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {
    EditText eusername, epwd;
    Button login, backButton;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        eusername = findViewById(R.id.username);
        epwd = findViewById(R.id.password);
        login = findViewById(R.id.loginButton);
        backButton = findViewById(R.id.backButton); // Initialize the back button

        // Initially hide the back button
        backButton.setVisibility(View.GONE);

        // Retrieve the registered email and password passed from SignUpActivity
        String regemail = getIntent().getStringExtra("email");
        String regpwd = getIntent().getStringExtra("userpwd");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered email and password
                String newemail = eusername.getText().toString();
                String newpwd = epwd.getText().toString();

                // Check if the entered email and password match the registered credentials
                if (regemail != null && regemail.equals(newemail) && regpwd != null && regpwd.equals(newpwd)) {
                    // If credentials are correct, proceed to the Welcome screen
                    Toast.makeText(SigninActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SigninActivity.this, Welcome.class);
                    startActivity(intent);
                    eusername.setText("");
                    epwd.setText("");
                } else {
                    count++;
                    Toast.makeText(SigninActivity.this, "Login Failed " + count, Toast.LENGTH_LONG).show();

                    // Disable login and show back button after 3 failed attempts
                    if (count == 3) {
                        login.setEnabled(false);
                        backButton.setVisibility(View.VISIBLE); // Show backButton
                        Toast.makeText(SigninActivity.this, "Login Failed 3 times. Try again later.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the SignUpActivity
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
