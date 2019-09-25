package com.example.logintest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.logintest.GetSetClass.Statement;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        Statement st = mStatementArrayList.get(i);

        String action ;
        String date = st.getRecord_date();
        String time = st.getRecord_time();
        String money;
        if(st.getAction().equals("deposit")){
            action = "ฝาก";
            money = customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }else if(st.getAction().equals("withdraw")){
            action = "ถอน";
            money = customFormat("--###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.RED);
        }
        else{
            action = "โอน";
            money = customFormat("++###,###.###",st.getTrans_money());
            statementViewHoler.txt_money.setTextColor(Color.parseColor("#00b806"));
        }

        statementViewHoler.txt_action.setText(action);
        statementViewHoler.txt_date.setText(dateThai(date)+" เวลา "+time);
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

    public static String dateThai(String strDate)
    {
        String Months[] = {
                "ม.ค", "ก.พ", "มี.ค", "เม.ย",
                "พ.ค", "มิ.ย", "ก.ค", "ส.ค",
                "ก.ย", "ต.ค", "พ.ย", "ธ.ค"};

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0,month=0,day=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

        }
        catch (ParseException e) { e.printStackTrace(); }
        return String.format("%s %s %s", day,Months[month],year+543);
    }

    public String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }

}
