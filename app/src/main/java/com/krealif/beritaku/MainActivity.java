package com.krealif.beritaku;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText inputUsername, inputPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                if (verifyLogin(username, password)) {
                    Intent intent = new Intent(view.getContext(), UserActivity.class);
                    startActivity(intent);
                } else {
                    showAlertDialog();
                }
                inputUsername.getText().clear();
                inputPassword.getText().clear();
                inputUsername.requestFocus();
            }
        });

    }

    private boolean verifyLogin(String username, String password) {
        if (username.equals("pakjoko") && password.equals("yangpentingcuan")) {
            return true;
        } else {
            return false;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Password Salah");
        alertBuilder.setMessage("Pastikan Anda sudah mengisikan username dan password dengan benar.");

        alertBuilder.setPositiveButton("Oke", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertBuilder.show();
    }
}