package com.example.cmoproject.Dashboard;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmoproject.Adapter.RecyclerViewAdapter;
import com.example.cmoproject.AddValue.AddValueDialog;
import com.example.cmoproject.Models.ShowValues;
import com.example.cmoproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.stream.IntStream;


public class Dashboard extends Fragment{

    private FirebaseFirestore db;
    private FirebaseAuth mAuth;


    private DashboardViewModel mViewModel;
    private RecyclerView myrecyclerview;
    RecyclerViewAdapter mAdapter;
    private boolean teste;

    ArrayList<ShowValues> expenseslist;

    private TextView total, reveneutrans, exepensestrans;




    public static Dashboard newInstance() {
        return new Dashboard();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        String i = mAuth.getCurrentUser().getUid();


        CollectionReference idAccount = db.collection("contas");

        CollectionReference idvalueac = db.collection("valorescontas");

        idAccount.whereEqualTo("id_user", i).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot document:task.getResult()){
                        idvalueac.whereEqualTo("ida", document.getId()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task1) {
                                if (task1.isSuccessful()){
                                    int d =0;
                                    int r =0;
                                    int size=0;

                                    expenseslist = new ArrayList<ShowValues>();
                                    ArrayList<ShowValues> reveneulist = new ArrayList<ShowValues>();

                                    for(QueryDocumentSnapshot doc:task1.getResult()){
                                        //number of all task in this
                                        size = task1.getResult().size();
                                        teste = doc.get("type").toString().equals("false");
                                        ShowValues listofvaluespositive = new ShowValues(String.valueOf(doc.get("categorie")),String.valueOf(doc.get("value")));
                                        ShowValues listofvaluesnegative = new ShowValues(String.valueOf(doc.get("categorie")),"-" +(doc.get("value")));


                                        if(teste == true){
                                            d++;
                                            //value of total expenses
                                            expenseslist.add(listofvaluesnegative);


                                        } else if (teste == false){
                                            //value of total reveneus
                                            r++;
                                            reveneulist.add(listofvaluespositive);
                                        }


                                    }
                                    Log.d("Valores das despesas: "+ d, "valor dos ganhos: " + r);
                                    exepensestrans.setText("Total of inserted expenses: "+d);
                                    reveneutrans.setText("Total of inserted reveneus: "+r);



                                    expenseslist.addAll(reveneulist);

                                    Log.d("new list size:", String.valueOf(expenseslist.size()));
                                    Log.d("Tamanho", String.valueOf(size));

                                    int sum = IntStream.of(expenseslist.indexOf("valeus")).sum();

                                    mAdapter.updateDataset(expenseslist);
                                }

                            }

                        });
                    }
                }
            }

        });


    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dashboard_fragment, container, false);
        FloatingActionButton fab = v.findViewById(R.id.floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        total = v.findViewById(R.id.total);
        exepensestrans = v.findViewById(R.id.transexpenses);
        reveneutrans = v.findViewById(R.id.transreveneus);


        myrecyclerview = (RecyclerView) v.findViewById(R.id.list_recyclerview);
        myrecyclerview.setHasFixedSize(true);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter =new RecyclerViewAdapter(expenseslist);
        myrecyclerview.setAdapter(mAdapter);

       return v;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void openDialog(){
       AddValueDialog addteste = new AddValueDialog();
       addteste.show(getFragmentManager(), "Add value");

    }

}
