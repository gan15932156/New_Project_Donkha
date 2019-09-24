package com.example.logintest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StatementFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PreferenceUtils utils = new PreferenceUtils();

        View view = inflater.inflate(R.layout.fregment_statement, container, false);
        if (utils.getUsername(getContext()) == null){
            Intent intent = new Intent(getContext(), MainUser.class);
            startActivity(intent);
        }
        return view;
    }
}
