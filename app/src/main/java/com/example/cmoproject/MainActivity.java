package com.example.cmoproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mikhaellopez.circularimageview.CircularImageView;
<<<<<<< HEAD

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputEMail;
    private TextInputLayout textInputPassword;

    private Button  btn_login;
    private Button  btn_register;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

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

        btn_login = findViewById(R.id.btn_login);
<<<<<<< HEAD
        btn_login.setOnClickListener(this);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
=======
>>>>>>> master

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
             if(firebaseAuth.getCurrentUser()!= null){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
             }
            }
        };

<<<<<<< HEAD
    }

    //https://stackoverflow.com/questions/25905086/multiple-buttons-onclicklistener-android
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.btn_login:
                startSignIn();
                break;

            case R.id.btn_register:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            default:
                break;
        }
    }

=======
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }

>>>>>>> master
    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
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
                    }
                });
            }
        }
    }

