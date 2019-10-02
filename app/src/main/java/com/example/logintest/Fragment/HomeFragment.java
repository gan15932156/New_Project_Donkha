package com.example.logintest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.logintest.Activities.MainActivity;
import com.example.logintest.Activities.MainUser;
import com.example.logintest.GetSetClass.PreferenceUtils;
import com.example.logintest.R;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_home, container, false);

        PreferenceUtils utils = new PreferenceUtils();
        if (utils.getUsername(getContext()) == null ){
            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        return view;
    }
}
