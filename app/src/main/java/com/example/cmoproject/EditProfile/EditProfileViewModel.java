package com.example.cmoproject.EditProfile;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cmoproject.Models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditProfileViewModel extends ViewModel {
    private final FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private final MutableLiveData<User> _userInfo = new MutableLiveData<>();
    LiveData<User> userInfo = _userInfo;

    private final MutableLiveData<String> _userEmail = new MutableLiveData<String>();
    LiveData <String> userEmail = _userEmail;

    enum ResultType{
        SUCCESS,CHECKB,CHECKP,CHECKE,CHECKFN,CHECKLN,CHECKCFPW,CHECKPWDS,CHECKEV
    }

    MutableLiveData<EditProfileViewModel.ResultType> liveData = new MutableLiveData<>();

    public EditProfileViewModel(){
        mAuth = FirebaseAuth.getInstance();
        _userEmail.setValue(mAuth.getCurrentUser().getEmail());


        db = FirebaseFirestore.getInstance();
        DocumentReference getusername = db.collection("users").document(getUser().getUid());
        getusername.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);

                _userInfo.setValue(user);
            }
        });

    }

    public void Edit(final String email, String password, String cfpw, final String first_name, final String last_name){
        if(email.isEmpty() || password.isEmpty()  || cfpw.isEmpty() || first_name.isEmpty() || last_name.isEmpty()){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKB);
        } else if (password.isEmpty()){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKP);
        } else if (email.isEmpty()){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKE);
        }else if (cfpw.isEmpty()){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKCFPW);
        }else if (!(password.equals(cfpw))){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKPWDS);
        }else if (first_name.isEmpty()){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKFN);
        }else if (last_name.isEmpty()){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKLN);

        }else if (!emailisValid(email)){
            liveData.postValue(EditProfileViewModel.ResultType.CHECKEV);
        }
        else {
            liveData.postValue(ResultType.SUCCESS);
            db = FirebaseFirestore.getInstance();
            DocumentReference infouser = db.collection("users").document(getUser().getUid());

            User user= new User(first_name,last_name,email);

            infouser.set(user);
        }
    }



    static boolean emailisValid (String email){
        String regex = "^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

}
