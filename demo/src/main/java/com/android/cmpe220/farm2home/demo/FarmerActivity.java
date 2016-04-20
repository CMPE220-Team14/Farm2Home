package com.android.cmpe220.farm2home.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FarmerActivity extends Activity {

    Button btnAddProduct, btnUpdateProduct, btnDeleteProduct;
    Button btnAddEvent, btnUpdateEvent, btnDeleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_farmer);

        btnAddProduct = (Button) findViewById(R.id.buttonAddProduct);
           btnAddEvent = (Button) findViewById(R.id.buttonAddEvent);
        btnUpdateEvent = (Button) findViewById(R.id.buttonUpdateEvent);
        btnDeleteEvent = (Button) findViewById(R.id.buttonDeleteEvent);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentAddProduct = new Intent(getApplicationContext(),MainScreenActivity.class);
                startActivity(intentAddProduct);
            }
        });


    }

}