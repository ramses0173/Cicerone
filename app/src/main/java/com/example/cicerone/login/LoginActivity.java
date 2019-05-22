package com.example.cicerone.login;

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
import com.example.cicerone.main.MainActivity;
import com.example.cicerone.resetPass.PasswordActivity;
import com.example.cicerone.registration.RegistrationActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * Visualizza la schermata di Login
 */

public class LoginActivity extends AppCompatActivity implements LoginInterface.MvpView,View.OnClickListener  {

    static final int RC_SIGN_IN=1;
    private LoginPresenter loginPresenter;

    private EditText inputEmail, inputPassword;
    private TextInputLayout layout_email,layout_password;
    private SignInButton signin;
    private ProgressBar progressBar;
    private GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        loginPresenter = new LoginPresenter(this, mAuth, db);

        //findView methods
        signin = findViewById(R.id.sign_in_button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        layout_password= findViewById(R.id.layout_password) ;
        layout_email= findViewById(R.id.layout_email) ;
        TextView resetPassword = findViewById(R.id.tvForgotPassword);
        Button btnLogin = findViewById(R.id.Reg2);
        progressBar= findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        Button register = findViewById(R.id.Register);

        //GoogleSignIn
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("275319194457-rrmojsh1ar5cu0bu0ggfqec0id5mur7c.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleApiClient.connect();

        //OnClickListener

        register.setOnClickListener(this);

        resetPassword.setOnClickListener(this);

        btnLogin.setOnClickListener(this);

        signin.setOnClickListener(this);
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.clearDefaultAccountAndReconnect();
        }
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void showProgressBar(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        signin.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                loginPresenter.firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void sendErrorMessage() {
        Toast.makeText(this,getString(com.example.cicerone.R.string.Auth_Failed),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Register:
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                break;

            case R.id.Reg2:

                loginPresenter.signInWithEmailAndPasswordUser(layout_email,layout_password,inputEmail,inputPassword);
                break;

            case R.id.tvForgotPassword:
                startActivity(new Intent(LoginActivity.this,PasswordActivity.class));
                break;

            case R.id.sign_in_button:
                signIn();
                break;
        }

    }
}




