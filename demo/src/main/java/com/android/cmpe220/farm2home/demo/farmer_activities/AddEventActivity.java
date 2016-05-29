package com.android.cmpe220.farm2home.demo.farmer_activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class AddEventActivity extends AppCompatActivity {

    // url to create new product
    private static String REGISTEREVENT_URL = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/add_event.php";


    private EditText EventName;
    private EditText Day;
    private EditText Month;
    private EditText Venue;
    private EditText NoofPart;
    private EditText EventDesc;


    Spinner spnDay;
    Spinner spnMonth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_add_event);

        spnDay = (Spinner)findViewById(R.id.spinnerDay);
        String[] days = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
        ArrayAdapter<String> adapterDay = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        adapterDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDay.setAdapter(adapterDay);

        spnMonth = (Spinner)findViewById(R.id.spinnerMonth);
        String[] month = new String[]{"January", "February", "March", "April","May","June", "July","August","September","October","November","December"};
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, month);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(adapterMonth);

        // Edit Text
        EventName = (EditText) findViewById(R.id.eventName);
        Venue = (EditText) findViewById(R.id.eventVenue);
        EventDesc = (EditText) findViewById(R.id.eventDesc);
        NoofPart = (EditText) findViewById(R.id.participantCount);
//        Day = (EditText) findViewById(R.id.eventDay);
//        Month = (EditText) findViewById(R.id.eventMonth);

        // Create button
        Button btnCreateEvent = (Button) findViewById(R.id.buttonCreateEvent);

        // button click event
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                registerEvent();
            }
        });

    }

    private void registerEvent() {

        String name = EventName.getText().toString().trim().toLowerCase();
        String venue = Venue.getText().toString().trim().toLowerCase();
        String desc = EventDesc.getText().toString().trim().toLowerCase();
        String count = NoofPart.getText().toString().trim().toLowerCase();
        String day = spnDay.getSelectedItem().toString().trim().toLowerCase();
        String month = spnMonth.getSelectedItem().toString().trim().toLowerCase();

        SharedPreferences sharedPreferences = getSharedPreferences(FarmerConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String FarmName = sharedPreferences.getString(FarmerConfig.EMAIL_SHARED_PREF,"Not Available");

        register(name,venue,count,FarmName,desc);
    }
    private void register(String EventName, String Venue, String NoofPart, String FarmName, String Desc) {
        String urlSuffix = "?EventName="+EventName+"&Venue="+Venue+"&NoofPart="+NoofPart+"&FarmName="+FarmName+"&Desc="+Desc;
        class RegisterEvent extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(AddEventActivity.this);
                loading.setMessage("Registering events. Please wait...");
                loading.setIndeterminate(false);
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                navigatetomain();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTEREVENT_URL+s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                }catch(Exception e){
                    System.out.print(e.getMessage());
                    return null;
                }
            }
        }

        RegisterEvent ru = new RegisterEvent();
        ru.execute(urlSuffix);

    }

    public void navigatetomain()
    {
        Intent intent= new Intent(this,ReadData.class);
        startActivity(intent);
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


    }


}
