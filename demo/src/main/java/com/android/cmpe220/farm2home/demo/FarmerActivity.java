package com.android.cmpe220.farm2home.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.android.cmpe220.farm2home.demo.R;

public class FarmerActivity extends AppCompatActivity {

    Button btnAddProduct, btnUpdateProduct, btnDeleteProduct;
    Button btnAddEvent, btnUpdateEvent, btnDeleteEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_farmer);

        btnAddProduct = (Button) findViewById(R.id.buttonAddProduct);
        btnUpdateProduct = (Button) findViewById(R.id.buttonUpdateProduct);
        btnDeleteProduct = (Button) findViewById(R.id.buttonDeleteProduct);
        btnAddEvent = (Button) findViewById(R.id.buttonAddEvent);
        btnUpdateEvent = (Button) findViewById(R.id.buttonUpdateEvent);
        btnDeleteEvent = (Button) findViewById(R.id.buttonDeleteEvent);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentAddProduct = new Intent(getApplicationContext(),AddProductActivity.class);
                startActivity(intentAddProduct);
            }
        });

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentUpdateProduct = new Intent(getApplicationContext(),AddProductActivity.class);
                startActivity(intentUpdateProduct);
            }
        });

        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentDeleteProduct = new Intent(getApplicationContext(),AddProductActivity.class);
                startActivity(intentDeleteProduct);
            }
        });


    }

}