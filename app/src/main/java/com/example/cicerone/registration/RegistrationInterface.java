package com.example.cicerone.registration;


import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public interface RegistrationInterface {

        void createUser(TextInputLayout lname, TextInputLayout lsurname, TextInputLayout lemail, TextInputLayout lpassword, final EditText name, final EditText surname, EditText email, EditText password);

}
