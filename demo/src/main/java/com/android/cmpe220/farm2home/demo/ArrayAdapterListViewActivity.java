package com.android.cmpe220.farm2home.demo;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayAdapterListViewActivity extends ActionBarActivity {

    private static final String TAG = "ArrayAdapterListViewActivity";
    EditText editText;
    Button addButton;
    TextView textView;
    SimpleArrayAdapter adapter;
    ListView listview;
    ArrayList<String> arrayList;
    Runnable run;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_array_adapter);


      //  textView = (TextView) findViewById(R.id.textView);

        listview = (ListView) findViewById(R.id.listview);

        String[] values = new String[] { "Fruits",
                "Vegetables",
                "Dairy",
                "Poultry" };

        arrayList = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            arrayList.add(values[i]);
        }
        adapter = new SimpleArrayAdapter(this,
                android.R.layout.simple_list_item_1, arrayList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                if(item.equals("Vegetables")) {
                    sampleload();
                }else
                {
                    new AlertDialog.Builder(ArrayAdapterListViewActivity.this).setTitle("Waiting for Content").setMessage("Hey,Still the Products are not added.Please check back later").setCancelable(false).setNeutralButton("OK",null).show();
                }
            }

        });


    }
    public void sampleload()
    {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}