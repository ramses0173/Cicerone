package com.example.cicerone.resetPass;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cicerone.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class PasswordPresenter implements PasswordInterface {

    private final Context mContext;
    private FirebaseAuth mAuth;

    public PasswordPresenter(Context context, FirebaseAuth auth) {
        mContext=context;
        mAuth = auth;
    }

    @Override
    public void sendPasswordResetEmail(EditText email) {
        String userEmail = email.getText().toString().trim();
        if (userEmail.isEmpty()) {
            email.setError(mContext.getResources().getString(R.string.Toast1));
            email.requestFocus();
        }
        else{
            mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(mContext, R.string.Emailsent, Toast.LENGTH_SHORT).show();
                        ((Activity)mContext).finish();
                        mContext.startActivity(new Intent(mContext,LoginActivity.class));
                    }
                    else{
                        Toast.makeText(mContext, R.string.EmailNotRegistered, Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

    }
}
