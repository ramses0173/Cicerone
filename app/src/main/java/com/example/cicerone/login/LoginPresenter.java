package com.example.cicerone.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cicerone.main.MainActivity;
import com.example.cicerone.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * Responsabile per la gestione delle azioni dalla View
 */

public class LoginPresenter implements LoginInterface.Presenter {

    private LoginInterface.MvpView mView;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private final Context mContext;


    LoginPresenter(Context context, FirebaseAuth auth, FirebaseFirestore database){
        mView = (LoginInterface.MvpView) context;
        mContext = context;
        mAuth = auth;
        db = database;
    }

    @Override
    public void firebaseAuthWithGoogle(final GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity)mContext, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String uid = mAuth.getCurrentUser().getUid();
                            String email = mAuth.getCurrentUser().getEmail();
                            String name = account.getGivenName();
                            String surname = account.getFamilyName();
                            String photoUrl = account.getPhotoUrl().toString();
                            User user = new User(name,surname,email,uid);
                            user.setFotoprofilo(photoUrl);
                            db.collection("utenti").document(user.getUid()).set(user);
                            Toast.makeText(mContext, "autenticazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                            mView.startMainActivity();
                        } else {
                            mView.sendErrorMessage();
                        }
                    }
                });
    }

    @Override
    public void signInWithEmailAndPasswordUser(TextInputLayout layout_email, TextInputLayout layout_password, EditText email, final EditText password) {
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        if (email1.isEmpty()) {

            layout_email.setError(mContext.getResources().getString(R.string.Toast1));
            layout_email.requestFocus();
            return;}

        if (password1.isEmpty()) {
            layout_password.setError(mContext.getResources().getString(R.string.Toast2));
            layout_password.requestFocus();

            return;}

        mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener((Activity)mContext, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    // there was an error
                    if (password.length() < 6) {
                        Toast.makeText(mContext, R.string.TstPassShort, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, R.string.Authenticationf, Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    checkIfEmailVerified();}

            }
        });
    }

    @Override
    public void checkIfEmailVerified(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if(user.isEmailVerified()){
                Toast.makeText(mContext, "Email verificata", Toast.LENGTH_SHORT).show();
                Intent receive = ((Activity)mContext).getIntent();
                if(receive != null){
                    String nome = receive.getStringExtra("name");
                    String cognome = receive.getStringExtra("surname");
                    User userDatabase = new User(nome,cognome,user.getEmail(),user.getUid());
                    db.collection("utenti").document(user.getUid()).set(userDatabase);
                    mContext.startActivity(new Intent(mContext,MainActivity.class));
            } else{
                Toast.makeText(mContext, "L'email non è stata verificata", Toast.LENGTH_SHORT).show();
            }
        }
    }
    }
}

