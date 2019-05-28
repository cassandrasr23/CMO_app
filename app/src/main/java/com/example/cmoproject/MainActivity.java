package com.example.cmoproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
<<<<<<< HEAD
import com.mikhaellopez.circularimageview.CircularImageView;
<<<<<<< HEAD
=======
import com.google.firebase.auth.FirebaseUser;
>>>>>>> master

public class MainActivity extends AppCompatActivity  {

    private TextInputLayout textInputEMail;
    private TextInputLayout textInputPassword;


    private Button  btnLogin;
    private Button  btnRegister;

    private FirebaseAuth mAuth;



=======
>>>>>>> master


    private TextInputLayout textInputEMail;
    private TextInputLayout textInputPassword;

    private Button  btn_login;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        textInputEMail = findViewById(R.id.text_email);
        textInputPassword = findViewById(R.id.text_password);

<<<<<<< HEAD
        btn_login = findViewById(R.id.btn_login);
<<<<<<< HEAD
        btn_login.setOnClickListener(this);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
=======
>>>>>>> master
=======
        btnLogin = findViewById(R.id.btn_login);
>>>>>>> master

        btnRegister = findViewById(R.id.btn_register);

<<<<<<< HEAD
<<<<<<< HEAD
    }
=======
>>>>>>> master


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

<<<<<<< HEAD
=======
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }

>>>>>>> master
=======

>>>>>>> master
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser currentUser) {
    }

    private void startSignIn(){

<<<<<<< HEAD
            String email = textInputEMail.getEditText().getText().toString();
            String password = textInputPassword.getEditText().getText().toString();
=======
            String email = textInputEMail.toString();
            String password = textInputPassword.toString();
>>>>>>> master

            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){

                Toast.makeText(MainActivity.this,"Email or Password may be empty",Toast.LENGTH_LONG).show();

            }else{
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()){
                        Toast.makeText(MainActivity.this,"Sign In Problem",Toast.LENGTH_LONG).show();
                        }
                        else {
                            startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        }
                    }
                });
            }
        }


}

