package com.android.cmpe220.farm2home.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SignUpSignIn extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up_sign_in);

        // Get The Refference Of Buttons
	     btnSignIn=(Button)findViewById(R.id.buttonSignIn);
	     btnSignUp=(Button)findViewById(R.id.buttonSignUp);

         btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignIn = new Intent(getApplicationContext(), NewLoginActivity.class);
//                intentSignUP.putExtra(KEY, UserType);
                startActivity(intentSignIn);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUp = new Intent(getApplicationContext(), SignUPActivity.class);
//                intentSignUP.putExtra(KEY, UserType);
                startActivity(intentSignUp);

            }
        });
    }

}
