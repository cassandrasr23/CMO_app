package com.example.cmoproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cmoproject.Categories.Categories;
import com.example.cmoproject.Models.AccountsValues;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * TODO: document your custom view class.
 */
public class AddTeste extends AppCompatDialogFragment {

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private EditText valuetext;

    private View view;

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;

    private boolean type;
    private String namecat;

    @Override
    public Dialog onCreateDialog(Bundle savedINstanceState){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mAuth= FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.sample_add_teste,null);

        radioGroup1 = view.findViewById(R.id.opc);
        radioGroup2 = view.findViewById(R.id.categories);

        valuetext =view.findViewById(R.id.valuetoadd);

        builder.setView(view)
                .setTitle("Add Value")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    addValue();
                    addValueDB();

                    }
                });



        return builder.create();
    }

    public void addValue (){
        int selectedId = radioGroup1.getCheckedRadioButtonId();
        int selectedId2 = radioGroup2.getCheckedRadioButtonId();

        RadioButton selectedRadioButton = (RadioButton) view.findViewById(selectedId);
        RadioButton reveneu = (RadioButton) view.findViewById(R.id.radio_reveneu);


        RadioButton chossencat = view.findViewById(selectedId2);



        RadioButton cat  = (RadioButton) view.findViewById(R.id.homecat);

        if(selectedRadioButton == reveneu){
            type = true;
        } else{
            type =false;
        }
        if (selectedId2 == R.id.homecat){
           namecat = Categories.HOME.name();
        } else if (selectedId2 == R.id.cat2){
            namecat =Categories.CAR.name();
        } else if (selectedId2 == R.id.cat3){
            namecat =Categories.WORK.name();
        }



    }
    public void addValueDB (){

        int value = Integer.parseInt(valuetext.getText().toString());


        //get current user id;
        String idUser = mAuth.getCurrentUser().getUid();


        //get id from document where de id_user equals idUser;
        CollectionReference idAccount = db.collection("contas");


        idAccount.whereEqualTo("id_user", idUser).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        Log.d("IDC", document.getId());
                        AccountsValues accountsValues = new AccountsValues(namecat, value, type, document.getId());
                        DocumentReference addValueTodb = db.collection("valorescontas").document();
                        addValueTodb.set(accountsValues);


                    }

                }
            }
        });



    }













}







