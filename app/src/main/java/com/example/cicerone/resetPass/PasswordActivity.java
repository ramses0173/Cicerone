package com.example.cicerone.resetPass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cicerone.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText emailReset;
    private FirebaseAuth user;
    private PasswordPresenter passwordPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        emailReset = findViewById(R.id.etPassEmail);
        Button resetPass = findViewById(R.id.ResetPass);
        user = FirebaseAuth.getInstance();
        passwordPresenter = new PasswordPresenter(this, user);

        resetPass.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ResetPass:
                passwordPresenter.sendPasswordResetEmail(emailReset);
                break;
        }
    }
}

