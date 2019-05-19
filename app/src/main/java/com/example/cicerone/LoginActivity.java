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

import com.example.cicerone.model.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;


public class LoginActivity extends AppCompatActivity  {

    private SignInButton signin;
    static final int RC_SIGN_IN=1;
    private GoogleSignInClient mGoogleSignInClient;

    private EditText inputEmail, inputPassword;
    private TextInputLayout layout_email,layout_password;
    private Button btnLogin;
    private Button register;
    private TextView resetPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseAuth authInstance = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        authInstance.signOut();

        mAuth = FirebaseAuth.getInstance();

        signin = findViewById(R.id.sign_in_button);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        layout_password= findViewById(R.id.layout_password) ;
        layout_email= findViewById(R.id.layout_email) ;
        resetPassword= findViewById(R.id.tvForgotPassword);
        btnLogin =findViewById(R.id.Reg2);
        progressBar= findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        register = findViewById(R.id.Register);


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

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("275319194457-rrmojsh1ar5cu0bu0ggfqec0id5mur7c.apps.googleusercontent.com")
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGoogleApiClient.connect();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

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
    });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();

            }
        });
    }



    //metodo per controllare se l'email è stata verificata
    private void checkIfEmailVerified(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if(user.isEmailVerified()){
                Toast.makeText(LoginActivity.this, "Email verificata", Toast.LENGTH_SHORT).show();
                addUserToDatabase(user);
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            } else{
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, "L'email non è stata verificata", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addUserToDatabase(FirebaseUser userAuth) {
        Intent receive = getIntent();
        if(receive != null){
        String nome = receive.getStringExtra("name");
        String cognome = receive.getStringExtra("surname");
        User userDatabase = new User(nome,cognome,userAuth.getEmail(),userAuth.getUid());
        db.collection("utenti").document(userAuth.getUid()).set(userDatabase);
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.clearDefaultAccountAndReconnect();
        }
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                             String uid = mAuth.getCurrentUser().getUid();
                             String email = mAuth.getCurrentUser().getEmail();
                             String name = acct.getGivenName();
                             String surname = acct.getFamilyName();
                             String photoUrl = acct.getPhotoUrl().toString();
                            User user = new User(name,surname,email,uid);
                            user.setFotoprofilo(photoUrl);
                            db.collection("utenti").document(uid).set(user);

                            Toast.makeText(LoginActivity.this, "autenticazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "autenticazione fallita", Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user.

                        }

                        // ...
                    }
                });
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
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }






}




