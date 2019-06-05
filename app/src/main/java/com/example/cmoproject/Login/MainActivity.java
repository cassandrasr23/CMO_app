package com.example.cmoproject.Login;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cmoproject.Home.HomeActivity;
import com.example.cmoproject.R;
import com.example.cmoproject.Register.RegisterActivity;
import com.google.android.material.textfield.TextInputLayout;
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
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(MainActivity.this,"Sign In Problem",Toast.LENGTH_LONG).show();
                        break;
                    case CHECKE:
                        textInputPassword.setError(null);
                        textInputEMail.setError("check EMail!");
                        break;
                    case CHECKP:
                        textInputEMail.setError(null);
                        textInputPassword.setError("check Password");
                        break;
                    case CHECKB:
                        textInputEMail.setError("check EMail!");
                        textInputPassword.setError("check Password");
                        break;
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login(textInputEMail.getEditText().getText().toString(),textInputPassword.getEditText().getText().toString());
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

    }

}