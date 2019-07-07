package com.example.cmoproject.Accounts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.cmoproject.AddContas.AddContas;
import com.example.cmoproject.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Accounts extends Fragment {

    private AccountsViewModel mViewModel;

    public static Accounts newInstance() {
        return new Accounts();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.accounts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AccountsViewModel.class);
        // TODO: Use the ViewModel

        FloatingActionButton fab = getView().findViewById(R.id.floating_action_button);

        final Intent myIntent = new Intent(getActivity(), AddContas.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(myIntent);
            }
        });
    }



}
