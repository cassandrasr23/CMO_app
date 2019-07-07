package com.example.cmoproject.Home;

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

public class HomeActivityViewModel extends ViewModel {

    private final FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private final MutableLiveData<String> _userEmail = new MutableLiveData<String>();
    private final MutableLiveData<User> _userInfo = new MutableLiveData<>();


    LiveData <String> userEmail = _userEmail;
    LiveData<User> userInfo = _userInfo;

    public HomeActivityViewModel() {
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

    public FirebaseUser getUser() {
        return mAuth.getCurrentUser();
    }

    public void LogOut (){
        FirebaseAuth.getInstance().signOut();
    }

}
