package com.example.logintest.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.logintest.Fragment.HomeFragment;
import com.example.logintest.Fragment.MeFragment;
import com.example.logintest.Fragment.StatementFragment;
import com.example.logintest.GetSetClass.PreferenceUtils;
import com.example.logintest.R;

public class MainUser extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        Fragment fragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null ;
            switch (menuItem.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_statement:
                    selectedFragment = new StatementFragment();
                    break;
                case R.id.nav_me:
                    selectedFragment = new MeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,selectedFragment).commit();
            return true;
        }
    };
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.qr_code_scan,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.scan_qr_code:
                startActivity(new Intent(this, Qr_code_scan.class));
                return true;
        }
        return  super.onOptionsItemSelected(item);
    }

}
