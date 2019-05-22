package com.example.cicerone.login;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Definisce l'interfaccia tra la View {@link LoginActivity} e il LoginPresenter {@link LoginInterface.Presenter}
 */

public interface LoginInterface {
    interface MvpView{
        void startMainActivity();
        void sendErrorMessage();

    }
    interface Presenter{
        void firebaseAuthWithGoogle(final GoogleSignInAccount account);
        void signInWithEmailAndPasswordUser(TextInputLayout layout_email, TextInputLayout layout_password, EditText email, final EditText password);
        void checkIfEmailVerified();

    }
}
