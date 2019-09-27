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

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.logintest.R.layout.dialog_show_statement;

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
    public void onBindViewHolder(@NonNull StatementViewHoler statementViewHoler, final int i) {
        final Statement st = mStatementArrayList.get(i);

        final String action ;
        final String date = st.getRecord_date();
        final String time = st.getRecord_time();
        final String money;
        if(st.getAction().equals("deposit")){
            action = "ฝาก";
            money = Helper.customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }
        else if(st.getAction().equals("withdraw")){
            action = "ถอน";
            money = Helper.customFormat("--###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.RED);
        }
        else if(st.getAction().equals("tranfer_money")){
            action = "โอน";
            money = Helper.customFormat("--###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.RED);
        }
        else if(st.getAction().equals("recive_money")){
            action = "รับเงินโอน";
            money = Helper.customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }
        else if(st.getAction().equals("open_account")){
            action = "เปิดบัญชี";
            money = Helper.customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }
        else if(st.getAction().equals("add_interest")){
            action = "เพิ่มดอกเบี้ย";
            money = Helper.customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
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
               AlertDialog.Builder mBuilder = new AlertDialog.Builder(v.getContext());
               LayoutInflater layoutInflater = LayoutInflater.from(v.getContext());
               final View view = layoutInflater.inflate(dialog_show_statement,null);
               final TextView txt_action = view.findViewById(R.id.dialog_txt_statement_action);
               final TextView txt_money = view.findViewById(R.id.dialog_txt_statement_monry);
               final TextView txt_date = view.findViewById(R.id.dialog_txt_statement_date);
               final TextView txt_id = view.findViewById(R.id.dialog_txt_statement_id);
               txt_action.setText(action);
               txt_money.setText(Helper.customFormat("###,###.###",st.getTrans_money())+" บาท");
               txt_date.setText(Helper.dateThai(date)+" เวลา "+time);
               txt_id.setText(st.getTrans_id());

               mBuilder.setView(view);
               mBuilder.show();
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
