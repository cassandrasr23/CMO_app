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

public class MainActivity extends AppCompatActivity {

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

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
             if(firebaseAuth.getCurrentUser()!= null){
                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
             }
            }
        };

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

        private void startSignIn(){

            String email = textInputEMail.toString();
            String password = textInputPassword.toString();

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

