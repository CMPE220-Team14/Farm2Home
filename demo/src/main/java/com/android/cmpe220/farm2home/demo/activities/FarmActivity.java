package com.android.cmpe220.farm2home.demo.activities;

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
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.adapter.FarmAdapter;
import com.android.cmpe220.farm2home.demo.constant.Constant;
import com.android.cmpe220.farm2home.demo.model.Farm;
import com.android.cmpe220.farm2home.demo.model.Product;

public class FarmActivity extends AppCompatActivity {
    private static final String TAG = "FarmActivity";
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm);

        Bundle data = getIntent().getExtras();
        product = (Product) data.getSerializable("product");

        ListView lvFarms = (ListView) findViewById(R.id.lvFarms);
        lvFarms.addHeaderView(getLayoutInflater().inflate(R.layout.farm_list_header, lvFarms, false));

        FarmAdapter farmAdapter = new FarmAdapter(this);
        farmAdapter.updateFarms(Constant.FARM_LIST);

        lvFarms.setAdapter(farmAdapter);

        lvFarms.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Farm farm = Constant.FARM_LIST.get(position - 1);
                Intent intent = new Intent(FarmActivity.this, ProductActivity.class);
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
