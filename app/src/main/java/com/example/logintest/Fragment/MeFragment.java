package com.example.logintest.Fragment;

import android.content.Context;
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
import com.example.logintest.GetSetClass.PreferenceUtils;
import com.example.logintest.R;

public class MeFragment extends Fragment {
    private Button btn_logout;
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fregment_me, container, false);
        mContext = getContext();
        Toast.makeText(mContext, "This mee", Toast.LENGTH_SHORT).show();
        btn_logout = view.findViewById(R.id.btn_logout);

        PreferenceUtils utils = new PreferenceUtils();

        if (utils.getUsername(mContext) == null ){
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, mContext);
                PreferenceUtils.saveUsername(null, mContext);
                PreferenceUtils.saveMember_id(null, mContext);
                PreferenceUtils.saveAccount_id(null, mContext);
                Intent intent = new Intent(mContext,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        return view;
    }


}
