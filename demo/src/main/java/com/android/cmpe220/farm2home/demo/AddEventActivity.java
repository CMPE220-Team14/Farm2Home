package com.android.cmpe220.farm2home.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AddEventActivity extends AppCompatActivity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    // url to create new product
    private static String url_create_event = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/create_product.php";


    EditText inputEventName;
    EditText inputEventDay;
    EditText inputEventMonth;
    EditText inputEventVenue;
    EditText inputEventDesc;

    Button BtnCreate;
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
        inputEventName = (EditText) findViewById(R.id.eventName);
        inputEventDay = (EditText) findViewById(R.id.eventDay);
        inputEventMonth = (EditText) findViewById(R.id.eventMonth);
        inputEventVenue = (EditText) findViewById(R.id.eventVenue);
        inputEventDesc = (EditText) findViewById(R.id.eventDesc);

        // Create button
        Button btnCreateEvent = (Button) findViewById(R.id.buttonCreateEvent);

        // button click event
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // creating new product in background thread
                new CreateNewEvent().execute();
            }
        });

    }

    /**
     * Background Async Task to Create new product
     * */
    class CreateNewEvent extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddEventActivity.this);
            pDialog.setMessage("Creating Event..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {
            String name = inputEventName.getText().toString();
            String venue = inputEventVenue.getText().toString();
            String description = inputEventDesc.getText().toString();


            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("venue", venue));
            params.add(new BasicNameValuePair("description", description));

            // getting JSON Object
            // Note that create product url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(url_create_event,
                    "POST", params);

            // check log cat fro response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created product
                    Intent i = new Intent(getApplicationContext(), AllProductsActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}
