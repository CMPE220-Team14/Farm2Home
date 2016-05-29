package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import com.android.cmpe220.farm2home.demo.R;


public class ConsumerPayPal_selection extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_pay_pal_selection);

        Button button_paypal_sdk= (Button) findViewById(R.id.button_paypal_sdk);
        button_paypal_sdk.setOnClickListener(this);

        Button button_paypal_mpl= (Button) findViewById(R.id.button_paypal_mpl);
        button_paypal_mpl.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_paypal_sdk:
                // call paypal sdk intent
                startActivity(new Intent(ConsumerPayPal_selection.this,ConsumerPayPal_sdk.class));

                break;

            case R.id.button_paypal_mpl:
        //call mpl activity

                startActivity(new Intent(ConsumerPayPal_selection.this,ConsumerMainActivity.class));

                break;

            default:

                break;



        }

    }
}
