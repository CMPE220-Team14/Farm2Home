package com.android.cmpe220.farm2home.demo.farmer_activities;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.cmpe220.farm2home.demo.R;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadData extends ListActivity {

    String url = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/get_all_products.php";
    ArrayList<HashMap<String, String>> Item_List;

    ArrayList<List> product_list;
    ProgressDialog PD;
    ListAdapter adapter;

    ListView listview = null;

    // JSON Node names
    public static final String Farm_Name = "FarmName";
    public static final String ITEM_NAME = "Productname";
    public static final String category = "Category";
    public static final String priceperlb = "Price";
    public static final String qtylb = "Qty";
    public static final String description = "prod_desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(R.layout.read);

        // listview = (ListView) findViewById(android.R.id.list);


        getListView().setOnItemClickListener(new ListitemClickListener());
        Item_List = new ArrayList<HashMap<String, String>>();

        ReadDataFromDB();
    }

    private void ReadDataFromDB() {
        PD = new ProgressDialog(this);
        PD.setMessage("Loading.....");
        PD.show();

        JsonObjectRequest jreq = new JsonObjectRequest(Method.GET, url,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int success = response.getInt("success");

                            if (success == 1) {
                                JSONArray ja = response.getJSONArray("Product");

                                for (int i = 0; i < ja.length(); i++) {

                                    JSONObject jobj = ja.getJSONObject(i);
                                    HashMap<String, String> item = new HashMap<String, String>();
                                    item.put(Farm_Name, jobj.getString(Farm_Name));
                                    item.put(ITEM_NAME,
                                            jobj.getString(ITEM_NAME));
                                    item.put(category,
                                            jobj.getString(category));
                                    item.put(priceperlb,
                                            jobj.getString(priceperlb));
                                    item.put(qtylb,
                                            jobj.getString(qtylb));
                                    item.put(description,
                                            jobj.getString(description));


                                    Item_List.add(item);

                                } // for loop ends

                                String[] from = {Farm_Name, ITEM_NAME};
                                int[] to = {R.id.item_id, R.id.ITEM_name};

                                adapter = new SimpleAdapter(
                                        getApplicationContext(), Item_List,
                                        R.layout.list_items, from, to);

                                setListAdapter(adapter);

                                //     listview.setOnItemClickListener(new ListitemClickListener());

                                PD.dismiss();

                            } // if ends

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                PD.dismiss();
            }
        });

        // Adding request to request queue
        MyApplication.getInstance().addToReqQueue(jreq);

    }


    //On List Item Click move to UpdateDelete Activity
    class ListitemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            Intent modify_intent = new Intent(ReadData.this,
                    UpdateDeleteProduct.class);

            modify_intent.putExtra("item", Item_List.get(position));

            startActivity(modify_intent);

        }



    }

    public void addData(View view) {

        Intent add_intent = new Intent(ReadData.this, NewProductActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(add_intent);
    }


}


