package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import android.widget.Spinner;

/**
 * Created by pooja.prabhuswamy on 5/1/16.
 */
public class ConsumerRetrieveFarmList extends AppCompatActivity{

    public static String ProductURL = null;

    private ListView listView;

    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY="Farm";
    public static final String TAG_FARMNAME = "FarmName";
    public static final String TAG_PRICE = "PricePerLb";
    public static final String TAG_DISTANCE= "Distance";

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_farmlist_layout);
        listView = (ListView) findViewById(R.id.listView);
        //listView.setOnItemClickListener(this);
        listView.addHeaderView(getLayoutInflater().inflate(R.layout.consumer_farm_list_header, listView, false));
        getJSON();
    }


    private void showFarms(){
        JSONObject jsonObject = null;
        final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String farmname = jo.getString(TAG_FARMNAME);
                String distance = "2.5";
                String price = jo.getString(TAG_PRICE);

                HashMap<String,String> farms = new HashMap<>();
                farms.put(TAG_FARMNAME, farmname);
                farms.put(TAG_PRICE, price);
                farms.put(TAG_DISTANCE, distance);
                list.add(farms);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), list, R.layout.consumer_activity_retrieve_farmlist,
                new String[]{TAG_FARMNAME, TAG_PRICE, TAG_DISTANCE},
                new int[]{R.id.FarmName, R.id.ITEM_PRICE, R.id.Distance});

        //ListViewAdapter adapters = new ListViewAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ListitemClickListener());
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ConsumerRetrieveFarmList.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showFarms();
            }

            @Override
            protected String doInBackground(Void... params) {
                ConsumerRequestHandler rh = new ConsumerRequestHandler();
                ProductURL = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/get_farmlist_user.php?Productname="+getIntent().getStringExtra("Product");
                String s = rh.sendGetRequest(ProductURL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    class ListitemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            Intent modify_intent = new Intent(ConsumerRetrieveFarmList.this,
                    ConsumerProductActivity.class);

            modify_intent.putExtra("Farm", ((TextView)view.findViewById(R.id.FarmName)).getText().toString());
            modify_intent.putExtra("Product",getIntent().getStringExtra("Product"));
            modify_intent.putExtra("Price", ((TextView)view.findViewById(R.id.ITEM_PRICE)).getText().toString());

            //     modify_intent.putExtra("item", Item_List.get(position));

            startActivity(modify_intent);

        }

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