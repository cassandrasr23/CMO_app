package com.example.cmoproject.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cmoproject.AddTeste;
import com.example.cmoproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Dashboard extends Fragment{

    private DashboardViewModel mViewModel;


    public static Dashboard newInstance() {
        return new Dashboard();
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
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void openDialog(){
       AddTeste addteste = new AddTeste();
       addteste.show(getFragmentManager(), "Add value");

    }

}
