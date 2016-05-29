package com.android.cmpe220.farm2home.demo.farmer_activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;


public class FarmerActivity extends Activity {

    Button btnAddProduct, btnUpdateProduct;
    Button btnAddEvent, btnUpdateEvent;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_farmer);

        textView = (TextView) findViewById(R.id.FarmerName);

        //Fetching email from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(FarmerConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String UserName = sharedPreferences.getString(FarmerConfig.EMAIL_SHARED_PREF, "Not Available");

        //Showing the current logged in email to textview
        textView.setText("Welcome " + UserName);

        btnAddProduct = (Button) findViewById(R.id.buttonAddProduct);
        btnUpdateProduct = (Button) findViewById(R.id.buttonUpdateProduct);
        btnAddEvent = (Button) findViewById(R.id.buttonAddEvent);
        btnUpdateEvent = (Button) findViewById(R.id.buttonUpdateEvent);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentAddProduct = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(intentAddProduct);
            }
        });

        btnUpdateProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentUpdateProduct = new Intent(getApplicationContext(), ReadData.class);
                startActivity(intentUpdateProduct);
            }
        });

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentAddEvent = new Intent(getApplicationContext(), AddEventActivity.class);
                startActivity(intentAddEvent);
            }
        });
        btnUpdateEvent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentUpdateEvent = new Intent(getApplicationContext(), ReadData.class);
                startActivity(intentUpdateEvent);
            }
        });
    }

    //Logout function
    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(FarmerConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(FarmerConfig.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(FarmerConfig.EMAIL_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.commit();

                        //Starting login activity
                        Intent intent = new Intent(FarmerActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.miSignout) {
            //calling logout method when the logout button is clicked
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
}

