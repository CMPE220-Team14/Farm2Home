package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;

public class ConsumerFarmList extends Activity {
   TextView farm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_farm_list);
        farm1 = (TextView) findViewById(R.id.farm1);
                farm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ConsumerFarmList.this, ConsumerMainActivity.class);
                        startActivity(intent);
                    }
                });
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
