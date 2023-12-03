package com.example.resource_allocation_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginAuth = (MaterialButton) findViewById(R.id.loginbtn);
        TextView registerBtn = findViewById(R.id.registerbtn);
        TextView resetBtn = findViewById(R.id.resetbtn);

        //Login
        loginAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin@gmail.com") && password.getText().toString().equals("admin@123")) {
                    // Correct login
                    Intent intent = new Intent(AuthActivity.this, Dashboard.class);
                    startActivity(intent); // Start the Dashboard activity
                } else {
                    // Incorrect login
                    // You can add code here to handle incorrect login (e.g., show an error message)
                }
            }
        });

//        signup logic
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the NextActivity
                Intent intent = new Intent(AuthActivity.this, Register.class);
                startActivity(intent);
            }
        });

        //reset password logic
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the NextActivity
                Intent intent = new Intent(AuthActivity.this, ResetActivity.class);
                startActivity(intent);
            }
        });



    }
}