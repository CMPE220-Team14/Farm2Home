package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.app.AlertDialog;
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
import android.app.Dialog;

import com.android.cmpe220.farm2home.demo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pooja.prabhuswamy on 5/7/16.
 */
public class ConsumerRetrieveEvents extends AppCompatActivity {

    public String URL_GET_ALL;

    private ListView listView;

    private String JSON_STRING;
    public static final String TAG_JSON_ARRAY="Events";
    public static final String TAG_EVENTNAME = "EventName";
    public static final String TAG_DATE = "Date";
    public static final String TAG_FARMNAME = "FarmName";
    public static final String TAG_TIME = "Time";
    public static final String TAG_VENUE = "Venue";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_list_events);
        listView = (ListView) findViewById(R.id.listView);
        //listView.setOnItemClickListener(this);
        listView.addHeaderView(getLayoutInflater().inflate(R.layout.consumer_events_list_header, listView, false));
        getJSON();
    }


    private void showEvents(){
        JSONObject jsonObject = null;
        final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(TAG_JSON_ARRAY);
            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String events = jo.getString(TAG_EVENTNAME);
                String date = jo.getString(TAG_DATE);
                String farmname = jo.getString(TAG_FARMNAME);
                String time = jo.getString(TAG_TIME);
                String venue = jo.getString(TAG_VENUE);

                HashMap<String,String> eventslist = new HashMap<>();
                eventslist.put(TAG_EVENTNAME, events);
                eventslist.put(TAG_DATE, date);
                eventslist.put(TAG_FARMNAME, farmname);
                eventslist.put(TAG_TIME, time);
                eventslist.put(TAG_VENUE, venue);
                list.add(eventslist);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getApplicationContext(), list, R.layout.consumer_activity_retrieve_events,
                new String[]{TAG_EVENTNAME, TAG_DATE, TAG_FARMNAME, TAG_TIME, TAG_VENUE},
                new int[]{R.id.EVENT_NAME, R.id.EVENT_DATE, R.id.EVENT_FARM, R.id.EVENT_TIME, R.id.EVENT_VENUE});

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
                loading = ProgressDialog.show(ConsumerRetrieveEvents.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showEvents();
            }

            @Override
            protected String doInBackground(Void... params) {
                ConsumerRequestHandler rh = new ConsumerRequestHandler();
                URL_GET_ALL  = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/get_events_user.php";
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

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ConsumerRetrieveEvents.this);
            alertDialog.setTitle(R.string.eventtitle);
            alertDialog.setMessage(R.string.eventcheck);
            alertDialog.setCancelable(false);
            alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Intent modify_intent = new Intent(((Dialog) dialog).getContext(),
                            ConsumerListViewActivity.class);
                    startActivity(modify_intent);
                    return;
                }

            });
            alertDialog.show();
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
        Intent cartActivity = new Intent(this,ConsumerShoppingCartActivity.class);
        startActivity(cartActivity);
    }

    public void signout()
    {
        Intent signoutActivity = new Intent(this, ConsumerHomeActivity.class);
        startActivity(signoutActivity);
    }
}