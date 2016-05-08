package com.android.cmpe220.farm2home.demo.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

/**
 * Created by pooja.prabhuswamy on 5/7/16.
 */
public class UserProfile extends AppCompatActivity{

    public String URL_GET_ALL;
    private ListView listView;

    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY="Users";

    public static final String TAG_USERNAME = "Username";
    public static final String TAG_EMAIL = "Email";
    public static final String TAG_PHONE = "Phone_number";
    public static final String TAG_ADDRESS = "Address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        listView = (ListView) findViewById(R.id.listView);
        //listView.setOnItemClickListener(this);
        listView.addHeaderView(getLayoutInflater().inflate(R.layout.user_list_header, listView, false));
        getJSON();
    }


    private void showProducts(){
        JSONObject jsonObject = null;
        final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String username = jo.getString(TAG_USERNAME);
                String email = jo.getString(TAG_EMAIL);
                String phone = jo.getString(TAG_PHONE);
                String address = jo.getString(TAG_ADDRESS);

                HashMap<String,String> userinfo = new HashMap<>();
                userinfo.put(TAG_USERNAME, username);
                userinfo.put(TAG_EMAIL, email);
                userinfo.put(TAG_PHONE, phone);
                userinfo.put(TAG_ADDRESS, address);
                list.add(userinfo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), list, R.layout.activity_retrieve_user,
                new String[]{TAG_USERNAME, TAG_EMAIL, TAG_PHONE, TAG_ADDRESS},
                new int[]{R.id.Username, R.id.Email, R.id.Phone, R.id.Address});

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
                loading = ProgressDialog.show(UserProfile.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showProducts();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                URL_GET_ALL  = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/get_user_info.php";
                String s = rh.sendGetRequest(URL_GET_ALL);
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

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(UserProfile.this);
            alertDialog.setTitle(R.string.eventtitle);
            alertDialog.setMessage(R.string.eventcheck);
            alertDialog.setCancelable(false);
            alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent modify_intent = new Intent(((Dialog) dialog).getContext(),
                            ArrayAdapterListViewActivity.class);
                    startActivity(modify_intent);
                    return;
                }

            });
            alertDialog.show();

        }

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
