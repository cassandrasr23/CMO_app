package com.example.cmoproject.EditProfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cmoproject.R;
import com.example.cmoproject.Settings.SettingsActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileActivity extends AppCompatActivity {

    private Button back;
    private Button edit;
    private EditProfileViewModel viewModel;

    private FirebaseFirestore db;

    private TextInputLayout textInputEMail;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmPassword;
    private TextInputLayout textInputFirstName;
    private TextInputLayout textInputLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        textInputEMail = findViewById(R.id.text_email);
        textInputPassword = findViewById(R.id.text_password);
        textInputConfirmPassword = findViewById(R.id.text_cfpw);
        textInputFirstName = findViewById(R.id.text_first_name);
        textInputLastName=findViewById(R.id.text_last_name);
        back=findViewById(R.id.Back);
        edit=findViewById(R.id.edit);

       /* viewModel.userEmail.observe(this, new Observer<String>(){
            @Override
            public void onChanged(String email) {
                textInputEMail.getEditText().setText(email);
                //textInputEMail.getEditText().setEnabled(false);
            }
        });*/

       /* viewModel.userInfo.observe(this, user -> {
            if(user != null) {
                textInputFirstName.getEditText().setText(user.first_name);
                textInputLastName.getEditText().setText(user.last_name);
            }
        });
        */

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, SettingsActivity.class);
                startActivity(intent);

            }
        });
    }
}
