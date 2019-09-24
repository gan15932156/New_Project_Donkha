package com.example.logintest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.logintest.Activities.MainActivity;
import com.example.logintest.Activities.MainUser;
import com.example.logintest.GetSetClass.PreferenceUtils;
import com.example.logintest.R;

public class MeFragment extends Fragment {
    private Button btn_logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_me, container, false);
        Toast.makeText(getContext(), "This mee", Toast.LENGTH_SHORT).show();
        btn_logout = view.findViewById(R.id.btn_logout);

        PreferenceUtils utils = new PreferenceUtils();

        if (utils.getUsername(getContext()) == null ){
            Intent intent = new Intent(getContext(), MainUser.class);
            startActivity(intent);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, getContext());
                PreferenceUtils.saveUsername(null, getContext());
                PreferenceUtils.saveMember_id(null, getContext());
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
