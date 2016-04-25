package com.android.cmpe220.farm2home.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class AddEventActivity extends AppCompatActivity {

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


    }

}
