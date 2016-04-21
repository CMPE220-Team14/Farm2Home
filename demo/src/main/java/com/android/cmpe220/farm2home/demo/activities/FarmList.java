package com.android.cmpe220.farm2home.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;

public class FarmList extends Activity {
   TextView farm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_list);
        farm1 = (TextView) findViewById(R.id.farm1);
                farm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FarmList.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
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
                Toast.makeText(getApplicationContext(),
                        "Profile",
                        Toast.LENGTH_SHORT).show();
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
}
