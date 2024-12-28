package com.example.gymplanner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    EditText username, pwd;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);  // Make sure you have the correct layout file name

        // Initialize views
        username = findViewById(R.id.username);
        pwd = findViewById(R.id.password);
        signup = findViewById(R.id.signupButton);  // Corrected ID to match your XML

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = username.getText().toString();
                String userpwd = pwd.getText().toString();

                // Check if username is empty
                if (email.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Username cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validate password
                if (!isvalidPassword(userpwd)) {
                    Toast.makeText(SignupActivity.this, "Password must contain at least 8 characters, including uppercase, lowercase, numbers, and special characters", Toast.LENGTH_LONG).show();
                    return;
                }

                // If everything is valid, move to the Sign In activity
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("userpwd", userpwd);
                startActivity(intent);
            }
        });
    }

    // Regular expressions for validating password strength
    Pattern lowercase = Pattern.compile("^.*[a-z].*$");
    Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
    Pattern number = Pattern.compile("^.*[0-9].*$");
    Pattern special = Pattern.compile("^.*[@#$%^&*(){},.;/].*$");

    // Method to check password validity
    private boolean isvalidPassword(String userpwd) {
        // Password must be at least 8 characters long
        if (userpwd.length() < 8) {
            return false;
        }
        // Check for lowercase letter
        if (!lowercase.matcher(userpwd).matches()) {
            return false;
        }
        // Check for uppercase letter
        if (!uppercase.matcher(userpwd).matches()) {
            return false;
        }
        // Check for numeric character
        if (!number.matcher(userpwd).matches()) {
            return false;
        }
        // Check for special character
        if (!special.matcher(userpwd).matches()) {
            return false;
        }

        return true;
    }
}
