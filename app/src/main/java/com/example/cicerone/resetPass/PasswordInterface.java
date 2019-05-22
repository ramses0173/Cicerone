package com.example.cicerone.resetPass;

import android.widget.EditText;

/**
 * Created by tommaso on 22/05/2019.
 */

public interface PasswordInterface {

    void sendPasswordResetEmail(EditText email);
}
