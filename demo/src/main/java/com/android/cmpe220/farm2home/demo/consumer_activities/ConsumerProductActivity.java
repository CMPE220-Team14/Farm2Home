package com.android.cmpe220.farm2home.demo.consumer_activities;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.model.ConsumerProduct;
import com.android.cmpe220.farm2home.model.Cart;
import com.android.cmpe220.farm2home.util.CartHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ConsumerProductActivity extends AppCompatActivity {
    private static final String TAG = "ConsumerProductActivity";

    public static String URL_GET_ALL = null;

    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY="QuantityResult";
    public static final String TAG_QUANTITY = "Quantity_in_Lb";
    public static final String TAG_DESC = "prod_desc";
    public static final String TAG_FARMNAME = "FarmName";
    String desc;

    TextView tvProductName;
    TextView tvFarmName;
    TextView tvProductDesc;
    Spinner spQuantity;
    Button bOrder;
    //Product product;
    String product;
    //Farm farm;
    String farm;
    String price, description;
    Integer priceVal;
    ConsumerProduct product_Val = new ConsumerProduct();

    BigDecimal bigdecimal;
    ImageView prodImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.consumer_activity_product);

        product = getIntent().getStringExtra("Product");
        farm = getIntent().getStringExtra("Farm");
        price = getIntent().getStringExtra("Price");
        priceVal = Integer.parseInt(price);
        bigdecimal = new BigDecimal(priceVal);
        product_Val.setName(product);
        product_Val.setPrice(bigdecimal);

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
                loading = ProgressDialog.show(ConsumerProductActivity.this,"Fetching Data","Wait...",false,false);
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
                ConsumerRequestHandler rh = new ConsumerRequestHandler();
                URL_GET_ALL = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/get_quantity_test.php?Productname="+getIntent().getStringExtra("Product");
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
        String uri = "@drawable/"+product.toLowerCase();  // where myresource.png is the file
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
                String farmname = jo.getString(TAG_FARMNAME);
                if (farm.equals(farmname)) {
                    String quantity = jo.getString(TAG_QUANTITY);
                    desc = jo.getString(TAG_DESC);
                    for (int j = 0; j <= Integer.parseInt(quantity.toString()); j++)
                        QUANTITY_LIST.add(j);
                }
            }

            tvProductDesc.setText(desc);

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
                Log.d(TAG, "Adding product: " + product);
                cart.add(product_Val, Integer.valueOf(spQuantity.getSelectedItem().toString()));
                Intent intent = new Intent(ConsumerProductActivity.this, ConsumerShoppingCartActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the consumer_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.consumer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miCart:
                showCart();
                return true;
            case R.id.miUser:
                user();
                break;
            case R.id.miSignout:
                signout();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showCart()
    {
        Intent cartActivity = new Intent(this,ConsumerShoppingCartActivity.class);
        startActivity(cartActivity);
    }

    public void signout()
    {
        Intent signoutActivity = new Intent(this, ConsumerHomeActivity.class);
        startActivity(signoutActivity);
    }

    public void user()
    {
        Intent userActivity = new Intent(this, ConsumerUserProfile.class);
        startActivity(userActivity);
    }

}