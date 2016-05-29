package com.android.cmpe220.farm2home.demo.farmer_activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FarmerLoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Defining views
    private EditText editTextUserName;
    private EditText editTextPassword;
    private AppCompatButton buttonLogin;

    //boolean variable to check user is logged in or not
    //initially it is false
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Initializing views
        editTextUserName = (EditText) findViewById(R.id.editTextUserNameToLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordToLogin);

        buttonLogin = (AppCompatButton) findViewById(R.id.buttonSignIn);

        //Adding click listener
        buttonLogin.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //In onresume fetching value from sharedpreference
      //  SharedPreferences sharedPreferences = getSharedPreferences(FarmerConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
//        loggedIn = sharedPreferences.getBoolean(FarmerConfig.LOGGEDIN_SHARED_PREF, false);

        //If we will get true
  //      if (loggedIn) {
            //We will start the Profile Activity
    //        Intent intent = new Intent(this, FarmerActivity.class);
      //      startActivity(intent);
       // }
    }

    private void login() {
        //Getting values from edit texts
        final String email = editTextUserName.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, FarmerConfig.LOGIN_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Boolean valueres = response.trim().equalsIgnoreCase(FarmerConfig.LOGIN_SUCCESS);

                        //If we are getting success from server
                        if (valueres) {
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = FarmerLoginActivity.this.getSharedPreferences(FarmerConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values tohttp://localhost/login.php
                            //
                            // ```
                            //
                            // http://localhost/login.php shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(FarmerConfig.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(FarmerConfig.EMAIL_SHARED_PREF, email);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(FarmerLoginActivity.this, FarmerActivity.class);
                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            Toast.makeText(FarmerLoginActivity.this, valueres.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        NetworkResponse response = error.networkResponse;

                        if (error instanceof TimeoutError) {
                            Log.e("Volley", "TimeoutError");
                        } else if (error instanceof NoConnectionError) {
                            Log.e("Volley", "NoConnectionError");
                        } else if (error instanceof AuthFailureError) {
                            Log.e("Volley", "AuthFailureError");
                        } else if (error instanceof ServerError) {
                            Log.e("Volley", "ServerError");
                        } else if (error instanceof NetworkError) {
                            Log.e("Volley", "NetworkError");
                        } else if (error instanceof ParseError) {
                            Log.e("Volley", "ParseError");
                        }
                        //You can handle error here if you want
                    }
                }

        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(FarmerConfig.KEY_EMAIL, email);
                params.put(FarmerConfig.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {
        //Calling the login function
        login();
    }
}
