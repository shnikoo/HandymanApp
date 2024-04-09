package com.example.handymanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    Button handymanBtn, customerBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        handymanBtn = (Button) findViewById(R.id.handymanBtn);
        customerBtn = (Button) findViewById(R.id.customerBtn);

        handymanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent handymanIntent = new Intent(WelcomeActivity.this, HandymanRegLoginActivity.class);
                startActivity(handymanIntent);
            }
        });

        customerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customerIntent = new Intent(WelcomeActivity.this, CustomerRegLoginActivity.class);
                startActivity(customerIntent);
            }
        });



    }
}