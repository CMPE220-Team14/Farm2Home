package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.cmpe220.farm2home.demo.R;

public class ConsumerBillingActivity extends AppCompatActivity {
    Button google_wallet;
    Button cod;
    public String amountFinal="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_payment);

        google_wallet = (Button) findViewById(R.id.google_wallet);
        cod = (Button) findViewById(R.id.cod);

        google_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumerBillingActivity.this, ConsumerGoogleWallet.class);
                startActivity(intent);
            }
        });
        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ConsumerBillingActivity.this).setTitle(R.string.checkout_title).setMessage(R.string.checkout_text).setCancelable(false).setNeutralButton("OK",null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the consumer_menu; this adds items to the action bar if it is present.
    //   getMenuInflater().inflate(R.consumer_menu.menu_home, consumer_menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public void payment( View view)
    {
        String button_test;
        button_test =((Button) view).getText().toString();
        button_test=button_test.trim();
        if (button_test.equals("Use Google Wallet"))
        {
            Intent intent= new Intent(this,ConsumerGoogleWallet.class);
            String totalValue=amountFinal;
            intent.putExtra("Totalvalue",totalValue);
            startActivity(intent);
        }
        else if(button_test.equals("Cash on Delivery"))
        {
            new AlertDialog.Builder(this).setTitle(R.string.checkout_title).setMessage(R.string.checkout_text).setCancelable(false).setNeutralButton("OK",null).show();

        }

        else if(button_test.equals("PAYPAL"))
        {

            Intent intent=new Intent(this,ConsumerPayPal_sdk.class);
            startActivity(intent);
        }
    }
}
