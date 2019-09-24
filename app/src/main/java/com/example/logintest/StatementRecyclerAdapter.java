package com.example.logintest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.logintest.GetSetClass.Statement;

import java.util.ArrayList;

public class StatementRecyclerAdapter extends RecyclerView.Adapter<StatementRecyclerAdapter.StatementViewHoler> {
    private Context mContext;
    private ArrayList<Statement> mStatementArrayList;

    public StatementRecyclerAdapter(Context context,ArrayList<Statement> statements){
        mContext = context;
        mStatementArrayList = statements;
    }

    @NonNull
    @Override
    public StatementViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.statement_recyclerview,viewGroup,false);
        return new StatementViewHoler(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementViewHoler statementViewHoler, int i) {
        Statement st = mStatementArrayList.get(i);

        String action = st.getAction();
        String date = st.getRecord_date();
        String time = st.getRecord_time();
        String money = String.valueOf(st.getTrans_money());

        statementViewHoler.txt_action.setText(action);
        statementViewHoler.txt_date.setText(date+" "+time);
        statementViewHoler.txt_money.setText(money);

    }

    @Override
    public int getItemCount() {
        return mStatementArrayList.size();
    }


    public class StatementViewHoler extends RecyclerView.ViewHolder{
        public TextView txt_action,txt_money,txt_date;
        public StatementViewHoler(@NonNull View itemView) {
            super(itemView);
            txt_action = itemView.findViewById(R.id.txt_action_recyclerview);
            txt_date = itemView.findViewById(R.id.txt_state_date_recyclerview);
            txt_money = itemView.findViewById(R.id.txt_money_recyclerview);

        }
    }
}
