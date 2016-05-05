package com.android.cmpe220.farm2home.demo.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.constant.Constant;
import com.android.cmpe220.farm2home.demo.model.Product;
import com.android.cmpe220.farm2home.demo.model.Farm;
import com.android.cmpe220.farm2home.model.Cart;
import com.android.cmpe220.farm2home.util.CartHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = "ProductActivity";

    public static String URL_GET_ALL = null;

    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY="QuantityResult";
    public static final String TAG_QUANTITY = "Quantity_in_Lb";

    TextView tvProductName;
    TextView tvFarmName;
    TextView tvProductDesc;
    Spinner spQuantity;
    Button bOrder;
    //Product product;
    String product;
    //Farm farm;
    String farm;
    //String price = getIntent().getStringExtra("Price").toString();

    BigDecimal bigdecimal = new BigDecimal("9.99");
    Product product_get_desc = new Product(1, product, bigdecimal, "Product:");
    ImageView prodImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product);

        product = getIntent().getStringExtra("Product");
        farm = getIntent().getStringExtra("Farm");

        URL_GET_ALL = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/get_quantity_test.php?Productname="+product;

        //Retrieve views
        retrieveViews();

        //Set product properties
        setProperties();

        getJSON();

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

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ProductActivity.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                initializeQuantity();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void setProperties() {
        /*tvProductName.setText(product.getName());
        tvFarmName.setText(farm.getName());
        tvProductDesc.setText(product.getDescription());
        int i=product.getId();*/

        tvProductName.setText(product);
        tvFarmName.setText(farm);
        product_get_desc.setName(product);
        tvProductDesc.setText("This vegetable is the most famous one.");
        String uri = "@drawable/img"+1;  // where myresource.png is the file
        // extension removed from the String

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        prodImage= (ImageView)findViewById(R.id.prodImage);
        Drawable res = getResources().getDrawable(imageResource);
        prodImage.setImageDrawable(res);
    }

    private void initializeQuantity() {
        /*ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, Constant.QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);*/

        JSONObject jsonObject = null;
        final List<Integer> QUANTITY_LIST = new ArrayList<Integer>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String quantity = jo.getString(TAG_QUANTITY);
                for(int j = 0; j <= Integer.parseInt(quantity.toString()); j++)
                    QUANTITY_LIST.add(j);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(
                this,android.R.layout.simple_spinner_item,QUANTITY_LIST);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spQuantity.setAdapter(dataAdapter);
    }

    private void onOrderProduct() {
        bOrder.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart cart = CartHelper.getCart();
                Log.d(TAG, "Adding product: " + product_get_desc.getName());
                cart.add(product_get_desc, Integer.valueOf(spQuantity.getSelectedItem().toString()));
                Intent intent = new Intent(ProductActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miCart:
                showCart();
                return true;
            case R.id.miProfile:
                /*Toast.makeText(getApplicationContext(),
                        "Profile",
                        Toast.LENGTH_SHORT).show();*/
                case R.id.miSignout:
                    signout();
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showCart()
    {
        Intent cartActivity = new Intent(this,ShoppingCartActivity.class);
        startActivity(cartActivity);
    }

    public void signout()
    {
        Intent signoutActivity = new Intent(this, HomeActivity.class);
        startActivity(signoutActivity);
    }

}
