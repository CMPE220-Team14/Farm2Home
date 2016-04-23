package com.android.cmpe220.farm2home.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.cmpe220.farm2home.demo.R;

public class Classify extends AppCompatActivity {

    Button btnFarmer,btnCustomer;
    public final static String KEY = "User";
    int UserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_classify);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set OnClick Listener on SignUp button
        btnFarmer=(Button)findViewById(R.id.buttonFarmer);
        btnCustomer=(Button)findViewById(R.id.buttonCustomer);
        btnFarmer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 UserType = 1;
                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), SignUpSignIn.class);
                intentSignUP.putExtra(KEY,UserType);
                startActivity(intentSignUP);

            }
        });

        btnCustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                UserType = 2;
                // TODO Auto-generated method stub
                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), SignUpSignIn.class);
                intentSignUP.putExtra(KEY,UserType);
                startActivity(intentSignUP);

            }
        });
    }


}
