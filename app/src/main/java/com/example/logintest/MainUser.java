package com.example.logintest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainUser extends AppCompatActivity {
    private TextView txtname;
    private Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        txtname = findViewById(R.id.txtName);
        btnlogout = findViewById(R.id.btnUserLogout);

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, MainUser.this);
                PreferenceUtils.saveUsername(null, MainUser.this);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        txtname.setText("Hello "+PreferenceUtils.getUsername(this));
        Toast.makeText(this, "Hello"+PreferenceUtils.getUsername(this), Toast.LENGTH_SHORT).show();
    }
}
