package com.example.cicerone;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity  {

    private EditText inputEmail, inputPassword;
    private TextInputLayout layout_email,layout_password;
    private Button btnLogin;
    private Button register;
    private TextView resetPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseAuth authInstance = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authInstance.signOut();

    mAuth = FirebaseAuth.getInstance();

    inputEmail = (EditText) findViewById(R.id.email);
    inputPassword = (EditText) findViewById(R.id.password);
    layout_password= (TextInputLayout) findViewById(R.id.layout_password) ;
    layout_email=(TextInputLayout) findViewById(R.id.layout_email) ;
    resetPassword=(TextView)findViewById(R.id.tvForgotPassword);
    btnLogin = (Button)findViewById(R.id.Reg2);
    progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
    register = (Button)findViewById(R.id.Register);

        register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivity(i);
        }
    });

        resetPassword.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
        }
    });


        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String email = inputEmail.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            final String password = inputPassword.getText().toString();

            if (email.isEmpty()) {
                progressBar.setVisibility(View.INVISIBLE);
                layout_email.setError(getResources().getString(R.string.Toast1));
                layout_email.requestFocus();
                return;}

            if (password.isEmpty()) {
                progressBar.setVisibility(View.INVISIBLE);
                layout_password.setError(getResources().getString(R.string.Toast2));
                layout_password.requestFocus();

                return;}

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);
                        // there was an error
                        if (password.length() < 6) {
                            Toast.makeText(getApplicationContext(), R.string.TstPassShort, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.Authenticationf, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        checkIfEmailVerified();}

                }
            });
        }
    });}





    //metodo per controllare se l'email è stata verificata
    private void checkIfEmailVerified(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user.isEmailVerified()){
            Toast.makeText(LoginActivity.this, "Email verificata", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        } else{
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(LoginActivity.this, "Lìemail non è stata verificata", Toast.LENGTH_SHORT).show();
        }
    }}




