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
import com.android.cmpe220.farm2home.demo.adapter.ConsumerProductAdapter;
import com.android.cmpe220.farm2home.demo.constant.ConsumerConstant;
import com.android.cmpe220.farm2home.demo.model.ConsumerProduct;

public class ConsumerMainActivity extends AppCompatActivity{
    private static final String TAG = "ConsumerMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_main);

        ListView lvProducts = (ListView) findViewById(R.id.lvProducts);
        lvProducts.addHeaderView(getLayoutInflater().inflate(R.layout.consumer_product_list_header, lvProducts, false));

        ConsumerProductAdapter productAdapter = new ConsumerProductAdapter(this);
        productAdapter.updateProducts(ConsumerConstant.PRODUCT_LIST);

        lvProducts.setAdapter(productAdapter);

        lvProducts.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ConsumerProduct product = ConsumerConstant.PRODUCT_LIST.get(position - 1);
                Intent intent = new Intent(ConsumerMainActivity.this, ConsumerFarmActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                Log.d(TAG, "View product: " + product.getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the consumer_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.consumer_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
        Intent cartActivity = new Intent(this,ConsumerShoppingCartActivity.class);
        startActivity(cartActivity);
    }

    public void signout()
    {
        Intent signoutActivity = new Intent(this, ConsumerHomeActivity.class);
        startActivity(signoutActivity);
    }

    public void user()
    {
        Intent userActivity = new Intent(this, ConsumerUserProfile.class);
        startActivity(userActivity);
    }
}