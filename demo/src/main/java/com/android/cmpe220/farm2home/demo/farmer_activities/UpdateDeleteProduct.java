package com.android.cmpe220.farm2home.demo.farmer_activities;

/**
 * Created by karthik on 5/7/16.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateDeleteProduct extends AppCompatActivity {


    public static final String Farm_Name = "Farmname";
    public static final String ITEM_NAME = "Productname";
    public static final String Category = "category";
    public static final String priceperlb = "PricePerLb";
    public static final String qtylb = "Quantity_in_Lb";
    public static final String description = "prod_desc";

    public static  String Farm_Name_inp;
    public static  String Product_NAME_inp;
    public static  String category_inp;
    public static  String priceperlb_inp;
    public static  String qtylb_inp;
    public static  String description_inp;

    private EditText Productname;
    private EditText PricePerLb;
    private EditText prod_desc;
    private EditText Quantity_in_Lb;
    private Spinner category;
    public Button buttonRegister;

    private String[] arraySpinner;
    ProgressDialog PD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_delete_product);

        PD = new ProgressDialog(this);
        PD.setMessage("please wait.....");
        PD.setCancelable(false);

        Productname = (EditText) findViewById(R.id.upinputProduct);
        PricePerLb = (EditText) findViewById(R.id.upinputPriceProd);
        prod_desc = (EditText) findViewById(R.id.upinputDescProd);
        Quantity_in_Lb = (EditText) findViewById(R.id.upinputQuantityProd);
        //	FarmName = (EditText) findViewById(R.id.editTextPhoneNumber);
        category = (Spinner) findViewById(R.id.upspinnerCategory);

        buttonRegister = (Button) findViewById(R.id.upbtnUpdateProd);

        Intent i = getIntent();

        HashMap<String, String> item = (HashMap<String, String>) i
                .getSerializableExtra("item");

        Farm_Name_inp = item.get(ReadData.Farm_Name).trim();
         Product_NAME_inp= item.get(ReadData.ITEM_NAME).trim();
        priceperlb_inp= item.get(ReadData.priceperlb).trim();
        category_inp=item.get(ReadData.priceperlb).trim();
        qtylb_inp=item.get(ReadData.qtylb).trim();
        description_inp=item.get(ReadData.description).trim();

        Productname.setText(Product_NAME_inp);
        PricePerLb.setText(priceperlb_inp);
        prod_desc.setText(priceperlb_inp);
        Quantity_in_Lb.setText(qtylb_inp);
        this.arraySpinner = new String[] {
                "Vegetable", "Fruit", "Diary"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        category.setAdapter(adapter);

        //To set the category in the spinner
        if (!category_inp.equals(null)) {
            int spinnerPosition = adapter.getPosition(category_inp);
            category.setSelection(spinnerPosition);
        }



        // item_name_et.setText(item_name);

    }

    public void update(View view) {
        PD.show();

       Product_NAME_inp=Productname.getText().toString().trim();
        priceperlb_inp=PricePerLb.getText().toString().trim();
        category_inp=category.getSelectedItem().toString().trim().toLowerCase();
        qtylb_inp=Quantity_in_Lb.getText().toString().trim();
        description_inp=prod_desc.getText().toString().trim();
        String update_url = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/update_product.php";

        Map<String, String> params = new HashMap<String, String>();
        params.put(Farm_Name, Farm_Name_inp);
        params.put(ITEM_NAME,Product_NAME_inp);
        params.put(priceperlb,priceperlb_inp);
        params.put(qtylb,qtylb_inp);
        params.put(description,description_inp);
        params.put(Category,category_inp);

        CustomRequest update_request = new CustomRequest(Request.Method.POST, update_url, params, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try{
                    int success = response.getInt("success");

                    if (success == 1) {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "Updated Successfully",
                                Toast.LENGTH_SHORT).show();
                        // redirect to readdata
                        MoveToReadData();

                    } else {
                        PD.dismiss();
                        Toast.makeText(getApplicationContext(),
                                "failed to update", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
                catch (Exception e){
                    PD.dismiss();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                PD.dismiss();
            }
        });


        // Adding request to request queue
        MyApplication.getInstance().addToReqQueue(update_request);
    }


    public void delete(View view) {
        PD.show();
        String delete_url = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/delete_product.php?Farmname=" + Farm_Name_inp + "&Productname=" + Product_NAME_inp;

        JsonObjectRequest delete_request = new JsonObjectRequest(delete_url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            int success = response.getInt("success");

                            if (success == 1) {
                                PD.dismiss();
                                Toast.makeText(getApplicationContext(),
                                        "Deleted Successfully",
                                        Toast.LENGTH_SHORT).show();
                                // redirect to readdata
                                MoveToReadData();
                            } else {
                                PD.dismiss();
                                Toast.makeText(getApplicationContext(),
                                        "failed to delete", Toast.LENGTH_SHORT)
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToReqQueue(delete_request);

    }

    private void MoveToReadData() {
        Intent read_intent = new Intent(UpdateDeleteProduct.this, ReadData.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(read_intent);

    }
}
