package com.example.cmoproject.Register;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.cmoproject.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class RegisterViewModel extends ViewModel {

    enum ResultType{
        SUCCESS, ERROR,CHECKB,CHECKP,CHECKE,CHECKFN,CHECKLN,CHECKCFPW,CHECKPWDS, CHECKEV,
    }


    MutableLiveData<RegisterViewModel.ResultType> liveData = new MutableLiveData<>();

    private final FirebaseAuth mAuth;

    private FirebaseFirestore db;

    public RegisterViewModel(){
        mAuth = FirebaseAuth.getInstance();
    }


    public void Register(final String email, final String password, final String cfpw, final String first_name, final String last_name) {
        if (email.isEmpty() || password.isEmpty() || cfpw.isEmpty() || first_name.isEmpty() || last_name.isEmpty()) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKB);
        } else if (password.isEmpty()) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKP);
        } else if (email.isEmpty()) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKE);
        } else if (cfpw.isEmpty()) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKCFPW);
        } else if (!(password.equals(cfpw))) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKPWDS);
        } else if (first_name.isEmpty()) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKFN);
        } else if (last_name.isEmpty()) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKLN);
        } else if (!emailisValied(email)) {
            liveData.postValue(RegisterViewModel.ResultType.CHECKEV);
        } else {

                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    liveData.postValue(ResultType.ERROR);
                                } else {

                                                liveData.postValue(ResultType.SUCCESS);

                                                db = FirebaseFirestore.getInstance();

                                                DocumentReference addinfouser = db.collection("users").document(mAuth.getCurrentUser().getUid());

                                                User user = new User(email, last_name, first_name);

                                                addinfouser.set(user);


                    }
                }
            });
        }
    }

    static boolean emailisValied (String email){
        String regex = "^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public FirebaseUser getUser() {return mAuth.getCurrentUser();
        }

}
