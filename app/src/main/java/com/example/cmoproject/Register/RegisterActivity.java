package com.example.cmoproject.Register;

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
import com.google.android.material.textfield.TextInputLayout;


public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout textInputEMail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmPassword;
    private TextInputLayout textInputFirstName;
    private TextInputLayout textInputLastName;


    private Button  btnRegister;

    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        textInputEMail = findViewById(R.id.text_email);
        textInputPassword = findViewById(R.id.text_password);
        textInputConfirmPassword = findViewById(R.id.text_cfpw);
        textInputFirstName = findViewById(R.id.text_firstname);
        textInputLastName=findViewById(R.id.text_last_name);

        btnRegister = findViewById(R.id.register);

        viewModel.liveData.observe(this, new Observer<RegisterViewModel.ResultType>() {
            @Override
            public void onChanged(RegisterViewModel.ResultType resultType) {
                switch (resultType) {
                    case SUCCESS:
                        startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                        finish();
                        break;
                    case ERROR:
                        Toast.makeText(RegisterActivity.this,"Register Problem",Toast.LENGTH_LONG).show();
                        break;
                    case CHECKE:
                        textInputFirstName.setError(null);
                        textInputLastName.setError(null);
                        textInputConfirmPassword.setError(null);
                        textInputPassword.setError(null);
                        textInputEMail.setError("check EMail!");
                        break;
                    case CHECKP:
                        textInputFirstName.setError(null);
                        textInputLastName.setError(null);
                        textInputConfirmPassword.setError(null);
                        textInputEMail.setError(null);
                        textInputPassword.setError("check Password");
                        break;
                    case CHECKB:
                        textInputFirstName.setError(null);
                        textInputLastName.setError(null);
                        textInputConfirmPassword.setError(null);
                        textInputEMail.setError("check EMail!");
                        textInputPassword.setError("check Password");
                        break;
                    case CHECKCFPW:
                        textInputFirstName.setError(null);
                        textInputLastName.setError(null);
                        textInputEMail.setError(null);
                        textInputPassword.setError(null);
                        textInputConfirmPassword.setError("check Password!");
                        break;
                    case CHECKPWDS:
                        textInputFirstName.setError(null);
                        textInputLastName.setError(null);
                        textInputEMail.setError(null);
                        Toast.makeText(RegisterActivity.this,"Passwords dont Macth",Toast.LENGTH_LONG).show();
                        textInputConfirmPassword.setError("check Password!");
                        textInputPassword.setError("check Password");
                        break;
                    case CHECKFN:
                        textInputLastName.setError(null);
                        textInputEMail.setError(null);
                        textInputPassword.setError(null);
                        textInputConfirmPassword.setError(null);
                        textInputFirstName.setError("check FirstName!");
                        break;
                    case CHECKLN:
                        textInputFirstName.setError(null);
                        textInputEMail.setError(null);
                        textInputPassword.setError(null);
                        textInputConfirmPassword.setError(null);
                        textInputLastName.setError("check Last Name");
                        break;
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.Register(textInputEMail.getEditText().getText().toString(),textInputPassword.getEditText().getText().toString(),textInputConfirmPassword.getEditText().getText().toString(),textInputFirstName.getEditText().getText().toString(),textInputLastName.getEditText().getText().toString());
            }
        });
    }
}
