package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.adapter.ConsumerCartItemAdapter;
import com.android.cmpe220.farm2home.demo.model.ConsumerCartItem;
import com.android.cmpe220.farm2home.demo.model.ConsumerProduct;
import com.android.cmpe220.farm2home.model.Cart;
import com.android.cmpe220.farm2home.model.Saleable;
import com.android.cmpe220.farm2home.util.CartHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ConsumerShoppingCartActivity extends AppCompatActivity {
    private static final String TAG = "ConsumerShoppingCartActivity";

    ListView lvCartItems;
    Button bClear;
    Button bShop;
    Button bPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_activity_cart);

        lvCartItems = (ListView) findViewById(R.id.lvCartItems);
        LayoutInflater layoutInflater = getLayoutInflater();

        final Cart cart = CartHelper.getCart();
        final TextView tvTotalPrice = (TextView) findViewById(R.id.tvTotalPrice);
        tvTotalPrice.setText("$"+String.valueOf(cart.getTotalPrice()));

        lvCartItems.addHeaderView(layoutInflater.inflate(R.layout.consumer_cart_header, lvCartItems, false));

        final ConsumerCartItemAdapter cartItemAdapter = new ConsumerCartItemAdapter(this);

        cartItemAdapter.updateCartItems(getCartItems(cart));

        lvCartItems.setAdapter(cartItemAdapter);

        bClear = (Button) findViewById(R.id.bClear);
        bShop = (Button) findViewById(R.id.bShop);
        bPay = (Button) findViewById(R.id.bPay);

        bClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clearing the shopping cart");
                cart.clear();
                cartItemAdapter.updateCartItems(getCartItems(cart));
                cartItemAdapter.notifyDataSetChanged();
                tvTotalPrice.setText(String.valueOf(cart.getTotalPrice()));
            }
        });

        bShop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumerShoppingCartActivity.this, ConsumerListViewActivity.class);
                startActivity(intent);
            }
        });

        bPay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumerShoppingCartActivity.this, ConsumerBillingActivity.class);
                startActivity(intent);
            }
        });

    }

    private List<ConsumerCartItem> getCartItems(Cart cart) {
        List<ConsumerCartItem> cartItems = new ArrayList<ConsumerCartItem>();
        Log.d(TAG, "Current shopping cart: " + cart);

        Map<Saleable, Integer> itemMap = (Map<Saleable, Integer>) cart.getItemWithQuantity();

        for (Entry<Saleable, Integer> entry : itemMap.entrySet()) {
            ConsumerCartItem cartItem = new ConsumerCartItem();
            cartItem.setProduct((ConsumerProduct) entry.getKey());
            cartItem.setQuantity(entry.getValue());
            cartItems.add(cartItem);
        }

        Log.d(TAG, "Cart item list: " + cartItems);
        return cartItems;
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