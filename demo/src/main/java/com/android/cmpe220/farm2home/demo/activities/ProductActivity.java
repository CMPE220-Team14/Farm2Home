package com.android.cmpe220.farm2home.demo.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.constant.Constant;
import com.android.cmpe220.farm2home.demo.model.Product;
import com.android.cmpe220.farm2home.demo.model.Farm;
import com.android.cmpe220.farm2home.model.Cart;
import com.android.cmpe220.farm2home.util.CartHelper;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";

    TextView tvProductName;
    TextView tvFarmName;
    TextView tvProductDesc;
    Spinner spQuantity;
    Button bOrder;
    Product product;
    Farm farm;
    ImageView prodImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product);

        Bundle data = getIntent().getExtras();
        product = (Product) data.getSerializable("product");
        farm = (Farm) data.getSerializable("farm");

        Log.d(TAG, "Product hashCode: " + product.hashCode());
        Log.d(TAG, "Farm hashCode: " + farm.hashCode());

        //Retrieve views
        retrieveViews();

        //Set product properties
        setProperties();

        //Initialize quantity
        initializeQuantity();

        //On ordering of product
        onOrderProduct();
    }

    private void retrieveViews() {
        tvProductName = (TextView) findViewById(R.id.tvProductName);
        tvFarmName = (TextView) findViewById(R.id.tvFarmName);
        tvProductDesc = (TextView) findViewById(R.id.tvProductDesc);
        prodImage=(ImageView) findViewById(R.id.prodImage);
        spQuantity = (Spinner) findViewById(R.id.spQuantity);
        bOrder = (Button) findViewById(R.id.bOrder);
    }

    private void setProperties() {
        tvProductName.setText(product.getName());
        tvFarmName.setText(farm.getName());
        tvProductDesc.setText(product.getDescription());
        int i=product.getId();
        String uri = "@drawable/img"+i;  // where myresource.png is the file
        // extension removed from the String

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        prodImage= (ImageView)findViewById(R.id.prodImage);
        Drawable res = getResources().getDrawable(imageResource);
        prodImage.setImageDrawable(res);
    }

    private void initializeQuantity() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Constant.QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);
    }

    private void onOrderProduct() {
        bOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = CartHelper.getCart();
                Log.d(TAG, "Adding product: " + product.getName());
                cart.add(product, Integer.valueOf(spQuantity.getSelectedItem().toString()));
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

}
