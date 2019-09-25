package com.example.logintest.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logintest.GetSetClass.Statement;
import com.example.logintest.Helper;
import com.example.logintest.R;

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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull StatementViewHoler statementViewHoler, int i) {
        final Statement st = mStatementArrayList.get(i);

        String action ;
        String date = st.getRecord_date();
        String time = st.getRecord_time();
        String money;
        if(st.getAction().equals("deposit")){
            action = "ฝาก";
            money = Helper.customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }else if(st.getAction().equals("withdraw")){
            action = "ถอน";
            money = Helper.customFormat("--###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.RED);
        }
        else{
            action = "โอน";
            money = Helper.customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }

        statementViewHoler.txt_action.setText(action);
        statementViewHoler.txt_date.setText(Helper.dateThai(date)+" เวลา "+time);
        statementViewHoler.txt_money.setText(money);

        statementViewHoler.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, st.getAccoint_id()+" "+st.getAccount_detail_id(), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(false);
                builder.setMessage(st.getAccoint_id()+" "+st.getAccount_detail_id());
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mStatementArrayList.size();
    }


    public class StatementViewHoler extends RecyclerView.ViewHolder{
        public TextView txt_action,txt_money,txt_date;
        public LinearLayout linearLayout;
        public StatementViewHoler(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linear_recyclerciew);
            txt_action = itemView.findViewById(R.id.txt_action_recyclerview);
            txt_date = itemView.findViewById(R.id.txt_state_date_recyclerview);
            txt_money = itemView.findViewById(R.id.txt_money_recyclerview);
        }
    }



}
