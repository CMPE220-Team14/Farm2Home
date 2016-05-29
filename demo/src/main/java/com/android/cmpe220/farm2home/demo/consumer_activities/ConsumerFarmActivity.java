package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.adapter.ConsumerFarmAdapter;
import com.android.cmpe220.farm2home.demo.constant.ConsumerConstant;
import com.android.cmpe220.farm2home.demo.model.ConsumerFarm;
import com.android.cmpe220.farm2home.demo.model.ConsumerProduct;

public class ConsumerFarmActivity extends AppCompatActivity {
    private static final String TAG = "ConsumerFarmActivity";
    ConsumerProduct product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_farm);

        Bundle data = getIntent().getExtras();
        product = (ConsumerProduct) data.getSerializable("product");

        ListView lvFarms = (ListView) findViewById(R.id.lvFarms);
        lvFarms.addHeaderView(getLayoutInflater().inflate(R.layout.consumer_farm_list_header, lvFarms, false));

        ConsumerFarmAdapter farmAdapter = new ConsumerFarmAdapter(this);
        farmAdapter.updateFarms(ConsumerConstant.FARM_LIST);

        lvFarms.setAdapter(farmAdapter);

        lvFarms.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ConsumerFarm farm = ConsumerConstant.FARM_LIST.get(position - 1);
                Intent intent = new Intent(ConsumerFarmActivity.this, ConsumerProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("farm", farm);
                bundle.putSerializable("product", product);
                Log.d(TAG, "View farm: " + farm.getName());
                intent.putExtras(bundle);
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
