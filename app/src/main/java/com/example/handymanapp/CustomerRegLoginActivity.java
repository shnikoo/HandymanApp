package com.example.handymanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CustomerRegLoginActivity extends AppCompatActivity {

    TextView customerStatus, question;
    Button signInBtn, signUpBtn;
    EditText emailET, passwordET;

    FirebaseAuth mAuth;
    ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg_login);

        customerStatus = (TextView) findViewById(R.id.statusCustomer);
        question = (TextView) findViewById(R.id.createAccount);
        signInBtn = (Button) findViewById(R.id.signInCustomer);
        signUpBtn = (Button) findViewById(R.id.signUpCustomer);
        emailET = (EditText) findViewById(R.id.customerEmail);
        passwordET = (EditText) findViewById(R.id.customerPassword);

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
                customerStatus.setText("Registration for customers");
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterCustomer(email, password);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInCustomer(email, password);
            }
        });
    }

    private void SignInCustomer(String email, String password) {
        loadingBar.setTitle("Login customer");
        loadingBar.setMessage("Wait for loading");
        loadingBar.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerRegLoginActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                } else {
                    Toast.makeText(CustomerRegLoginActivity.this, "An error occurred, please try again", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterCustomer(String email, String password) {
        loadingBar.setTitle("Customer registration");
        loadingBar.setMessage("Wait for loading");
        loadingBar.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(CustomerRegLoginActivity.this, "Registration succeeded", Toast.LENGTH_LONG).show();
                } else
                {
                    Toast.makeText(CustomerRegLoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}