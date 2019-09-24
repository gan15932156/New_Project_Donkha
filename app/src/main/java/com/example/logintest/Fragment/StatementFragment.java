package com.example.logintest.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.http.RequestQueue;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.logintest.Activities.MainActivity;
import com.example.logintest.Activities.MainUser;
import com.example.logintest.Database.WebSevConnect;
import com.example.logintest.GetSetClass.PreferenceUtils;
import com.example.logintest.GetSetClass.Statement;
import com.example.logintest.R;
import com.example.logintest.StatementRecyclerAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StatementFragment extends Fragment {
    private Spinner spn;
    private RecyclerView mRecyclerView;
    private StatementRecyclerAdapter mStatementAdapter;
    private ArrayList<Statement> mStatementList;
    private Context mContext;
    private Button btn_empty;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getContext();
        PreferenceUtils utils = new PreferenceUtils();
        View view = inflater.inflate(R.layout.fregment_statement, container, false);
        if (utils.getUsername(mContext) == null){
            Intent intent = new Intent(mContext, MainUser.class);
            startActivity(intent);
        }
        btn_empty = view.findViewById(R.id.btn_empty_recyclerview);
        spn = view.findViewById(R.id.spn_statement_recyclerview);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext, R.array.spn_action_statement, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

        mRecyclerView = view.findViewById(R.id.statement_recycler_view_horizontal);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mStatementList = new ArrayList<>();

        request_statement();

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String action = spn.getSelectedItem().toString();
                Toast.makeText(mContext, action, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setAdapter(null);
            }
        });
        return view;
    }

    public void request_statement(){
        String url = "http://18.140.49.199/Donkha/Service_app/get_statement";

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("member_id", PreferenceUtils.getMember_id(mContext)));

        String response = WebSevConnect.getHttpPost(url,params,mContext);
        try {
            JSONObject obj = new JSONObject(response);
            if(!obj.getBoolean("error")){

                JSONArray jsonArraySt = obj.getJSONArray("statement");
                for(int i = 0 ; i < jsonArraySt.length();i++){
                    JSONObject st = jsonArraySt.getJSONObject(i);
                    Log.d("TAG",st.getString("trans_money"));

                    mStatementList.add(new Statement(
                           st.getString("account_detail_id"),
                            st.getString("action"),
                            st.getString("record_date"),
                            st.getString("record_time"),
                            st.getDouble("trans_money")
                    ));
                }
                mStatementAdapter = new StatementRecyclerAdapter(mContext,mStatementList);
                mRecyclerView.setAdapter(mStatementAdapter);
            }
            else{
                Toast.makeText(mContext, obj.getString("message"), Toast.LENGTH_SHORT).show();
            }
        }
        catch (JSONException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show(); }
    }
}
