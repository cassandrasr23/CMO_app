package com.example.cmoproject.Login;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends ViewModel {

    enum ResultType{
       SUCCESS, ERROR,CHECKB,CHECKP,CHECKE
   }

    MutableLiveData<ResultType> liveData = new MutableLiveData<>();

    private final FirebaseAuth mAuth;

    public MainActivityViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }


    public void login(String email, String password){
        if(email.isEmpty() && password.isEmpty()){
            liveData.postValue(ResultType.CHECKB);
        } else if (password.isEmpty()){
            liveData.postValue(ResultType.CHECKP);
        } else if (email.isEmpty()){
            liveData.postValue(ResultType.CHECKE);
        }else{
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    liveData.postValue(ResultType.ERROR);
                }
                else {
                    liveData.postValue(ResultType.SUCCESS);

                }
            }
            });
        }
    }
    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }
}
