package com.example.cicerone.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cicerone.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegistrationPresenter implements RegistrationInterface {
    private FirebaseAuth mAuth;
    private final Context mContext;

    RegistrationPresenter(Context context, FirebaseAuth auth) {
        mContext = context;
        mAuth = auth;
    }


    @Override
    public void createUser(TextInputLayout lname,TextInputLayout lsurname,TextInputLayout lemail,TextInputLayout lpassword,final EditText name, final EditText surname, EditText email, EditText password) {
        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        final String name1 = name.getText().toString().trim();
        final String surname1 = surname.getText().toString().trim();

        if (email1.isEmpty()) {
            lemail.setError(mContext.getResources().getString(R.string.Toast1));
            lemail.requestFocus();
            return;}
        if(name1.isEmpty()){
            lname.setError(mContext.getResources().getString(R.string.errorName));
            lname.requestFocus();
            return;
        }
        if(surname1.isEmpty()){
            lsurname.setError(mContext.getResources().getString(R.string.errorSurname));
            lsurname.requestFocus();
            return;
        }

        if (password1.isEmpty()) {
            lpassword.setError(mContext.getResources().getString(R.string.Toast2));
            lpassword.requestFocus();
            return;}

        if (password1.length() < 6) {
            lpassword.setError(mContext.getResources().getString(R.string.TstPassShort));
            lpassword.requestFocus();
            return;}

        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener((Activity)mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(mContext, R.string.Failure, Toast.LENGTH_SHORT).show();
                } else {
                    //manda email di verifica e poi ritorna sulla login page
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(mContext, R.string.Emailsent, Toast.LENGTH_SHORT).show();
                                    Intent goToLogin = new Intent(mContext,LoginActivity.class);
                                    goToLogin.putExtra("name",name1);
                                    goToLogin.putExtra("surname",surname1);
                                    mContext.startActivity(goToLogin);
                                    ((Activity)mContext).finish();
                                }
                            }
                        });
                    }
                }
            }

        });
    }
}
