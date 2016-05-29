package com.android.cmpe220.farm2home.demo.consumer_activities;

/**
 * Created by UshaPraveena on 5/11/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.cmpe220.farm2home.demo.R;

public class ConsumerSignUpSignIn extends AppCompatActivity {

    Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sign_up_sign_in);

        // Get The Refference Of Buttons
        btnSignIn = (Button) findViewById(R.id.SignIn);
        btnSignUp = (Button) findViewById(R.id.buttonSignUp);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignIn = new Intent(ConsumerSignUpSignIn.this, ConsumerLoginActivity.class);
//                intentSignUP.putExtra(KEY, UserType);
                startActivity(intentSignIn);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  and Start The Activity
                Intent intentSignUp = new Intent(ConsumerSignUpSignIn.this, ConsumerSignUPActivity.class);
//                intentSignUP.putExtra(KEY, UserType);
                startActivity(intentSignUp);

            }
        });
    }

}
