package com.example.handymanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HandymanRegLoginActivity extends AppCompatActivity {

    TextView handymanStatus, question;
    Button signInBtn, signUpBtn;
    EditText emailET, passswordET;

    FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handyman_reg_login);

        handymanStatus = (TextView) findViewById(R.id.statusHandyman);
        question = (TextView) findViewById(R.id.createAccount);
        signInBtn = (Button) findViewById(R.id.signInHandyman);
        signUpBtn = (Button) findViewById(R.id.signUpHandyman);
        emailET = (EditText) findViewById(R.id.handymanEmail);
        passswordET = (EditText) findViewById(R.id.handymanPassword);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        signUpBtn.setVisibility(View.INVISIBLE);
        signUpBtn.setEnabled(false);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInBtn.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtn.setVisibility(View.VISIBLE);
                signUpBtn.setEnabled(true);
                handymanStatus.setText(R.string.registration_for_handyman);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passswordET.getText().toString();

                RegisterHandyman(email, password);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passswordET.getText().toString();

                SignInHandyman(email, password);
            }
        });
    }

    private void SignInHandyman(String email, String password)
    {
        loadingBar.setTitle("Login handyman");
        loadingBar.setMessage("Wait for loading");
        loadingBar.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(HandymanRegLoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                    Intent handymanIntent = new Intent(HandymanRegLoginActivity.this, HandymanMapActivity.class);
                    startActivity(handymanIntent);
                }
                else
                {
                    Toast.makeText(HandymanRegLoginActivity.this, "An error occurred, please try again", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
    private void RegisterHandyman(String email, String password)
    {
        loadingBar.setTitle("Handyman registration");
        loadingBar.setMessage("Wait for loading");
        loadingBar.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(HandymanRegLoginActivity.this, "Registration succeeded", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                    Intent handymanIntent = new Intent(HandymanRegLoginActivity.this, HandymanMapActivity.class);
                    startActivity(handymanIntent);
                }
                else
                {
                    Toast.makeText(HandymanRegLoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}