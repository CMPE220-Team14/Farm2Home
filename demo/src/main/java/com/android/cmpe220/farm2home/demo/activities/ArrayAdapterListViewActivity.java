package com.android.cmpe220.farm2home.demo.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;

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
                "Livestock",
                "Farm Events"};

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
                if (item.equals("Fruits")) {
                    ProductLoad("Fruits");
                } else if (item.equals("Vegetables")) {
                    ProductLoad("Vegetables");
                } else if(item.equals("Dairy")) {
                    ProductLoad("Dairy");
                }else if(item.equals("Livestock")) {
                    ProductLoad("Livestock");
                }else if (item.equals("Farm Events")) {
                    EventLoad();
                }else
                {
                    new AlertDialog.Builder(ArrayAdapterListViewActivity.this).setTitle("Waiting for Content").setMessage("Hey,Still the Products are not added.Please check back later").setCancelable(false).setNeutralButton("OK",null).show();
                }
            }

        });


    }
    public void EventLoad(){
        Intent eventIntent = new Intent(this,RetrieveEvents.class);
        startActivity(eventIntent);

    }
    public void ProductLoad(String value){

        Intent intent= new Intent(this,RetriveProductsBasedOnCategory.class);
        intent.putExtra("Category", value);
        startActivity(intent);
    }
    public void FruitLoad()
    {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void DairyLoad()
    {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void LivestockLoad()
    {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
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
        Intent cartActivity = new Intent(this,ShoppingCartActivity.class);
        startActivity(cartActivity);
    }

    public void signout()
    {
        Intent signoutActivity = new Intent(this, HomeActivity.class);
        startActivity(signoutActivity);
    }

    public void user()
    {
        Intent userActivity = new Intent(this, UserProfile.class);
        startActivity(userActivity);
    }
}