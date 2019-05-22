package com.example.cicerone.registration;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cicerone.login.LoginActivity;
import com.example.cicerone.login.LoginInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout layout_email,layout_password,layout_name,layout_surname;
    private EditText inputEmail,inputPassword,inputName,inputSurname;
    private static final String TAG = "RegistrationActivity";
    private RegistrationPresenter registrationPresenter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        registrationPresenter = new RegistrationPresenter(this, mAuth);

        Button signUp = findViewById(R.id.Reg2);
        inputName = findViewById(R.id.name);
        inputSurname = findViewById(R.id.surname);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        layout_name =findViewById(R.id.layout_signup_name);
        layout_surname =findViewById(R.id.layout_signup_surname);
        layout_password=  findViewById(R.id.layout_signup_password) ;
        layout_email= findViewById(R.id.layout_signup_email);

        signUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Reg2:
                    registrationPresenter.createUser(layout_name,layout_surname,layout_email,layout_password,inputName,inputSurname,inputEmail,inputPassword);
                break;
        }

    }
}









