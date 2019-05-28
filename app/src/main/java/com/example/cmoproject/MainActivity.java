package com.example.cmoproject;

import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout textInputEMail;
    private TextInputLayout textInputPassword;


    private Button  btnLogin;
    private Button  btnRegister;

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        textInputEMail = findViewById(R.id.text_email);
        textInputPassword = findViewById(R.id.text_password);

        btnLogin = findViewById(R.id.btn_login);

        btnRegister = findViewById(R.id.btn_register);

        viewModel.liveData.observe(this, new Observer<MainActivityViewModel.ResultType>() {
            @Override
            public void onChanged(MainActivityViewModel.ResultType resultType) {
                switch (resultType) {
                    case SUCCESS:
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this,"Sign In Problem",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));

            }
        });
    }


    @Override
    protected void onStart(){
        super.onStart();

        FirebaseUser currentUser = viewModel.getUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void startSignIn(){

        String email = textInputEMail.getEditText().getText().toString();
        String password = textInputPassword.getEditText().getText().toString();

        if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

            Toast.makeText(MainActivity.this,"Email or Password may be empty",Toast.LENGTH_LONG).show();

        }else{
            viewModel.login(email, password);
        }
    }


}