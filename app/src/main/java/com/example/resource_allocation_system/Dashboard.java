package com.example.resource_allocation_system;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class Dashboard extends AppCompatActivity {

    SwitchCompat switchMode;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        switchMode = findViewById(R.id.switchMode);
        sharedPreferences = getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("nightMode", false);

        if(nightMode){
            switchMode.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        switchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nightMode){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", false);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor = sharedPreferences.edit();
                    editor.putBoolean("nightMode", true);
                }
                editor.apply();
            }
        });

        TextView handleAbout = findViewById(R.id.aboutName);
        TextView handleResource = findViewById(R.id.resourceid);
        TextView handleCourses = findViewById(R.id.courseName);
        TextView handleUserLogout = findViewById(R.id.logoutName);

        // Handle About Page
        handleAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the NextActivity
                Intent intent = new Intent(Dashboard.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        // Handle Resources page
        handleResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the NextActivity
                Intent intent = new Intent(Dashboard.this, ResourceActivity.class);
                startActivity(intent);
            }
        });
        // Handle Courses page
        handleCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the NextActivity
                Intent intent = new Intent(Dashboard.this, CourseActivity.class);
                startActivity(intent);
            }
        });


        // Handle user logout
        handleUserLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the NextActivity
                Intent intent = new Intent(Dashboard.this, AuthActivity.class);
                startActivity(intent);
            }
        });
    }
}