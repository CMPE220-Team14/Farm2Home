package com.android.cmpe220.farm2home.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.model.ConsumerCartItem;
import com.android.cmpe220.farm2home.model.Cart;
import com.android.cmpe220.farm2home.util.CartHelper;

import java.util.Collections;
import java.util.List;

public class ConsumerCartItemAdapter extends BaseAdapter {
    private static final String TAG = "ConsumerCartItemAdapter";

    private List<ConsumerCartItem> cartItems = Collections.emptyList();

    private final Context context;

    public ConsumerCartItemAdapter(Context context) {
        this.context = context;
    }

    public void updateCartItems(List<ConsumerCartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return cartItems.size();
    }

    @Override
    public ConsumerCartItem getItem(int position) {
        return cartItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        TextView tvName;
        TextView tvUnitPrice;
        TextView tvQuantity;
        TextView tvPrice;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.consumer_adapter_cart_item, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.tvCartItemName);
            tvUnitPrice = (TextView) convertView.findViewById(R.id.tvCartItemUnitPrice);
            tvQuantity = (TextView) convertView.findViewById(R.id.tvCartItemQuantity);
            tvPrice = (TextView) convertView.findViewById(R.id.tvCartItemPrice);
            convertView.setTag(new ViewHolder(tvName, tvUnitPrice, tvQuantity, tvPrice));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvCartItemName;
            tvUnitPrice = viewHolder.tvCartItemUnitPrice;
            tvQuantity = viewHolder.tvCartItemQuantity;
            tvPrice = viewHolder.tvCartItemPrice;
        }

        final Cart cart = CartHelper.getCart();
        final ConsumerCartItem cartItem = getItem(position);
        tvName.setText(cartItem.getProduct().getName());
        tvUnitPrice.setText("$"+String.valueOf(cartItem.getProduct().getPrice()));
        tvQuantity.setText(String.valueOf(cartItem.getQuantity()));
        tvPrice.setText("$"+String.valueOf(cart.getCost(cartItem.getProduct())));
        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvCartItemName;
        public final TextView tvCartItemUnitPrice;
        public final TextView tvCartItemQuantity;
        public final TextView tvCartItemPrice;

        public ViewHolder(TextView tvCartItemName, TextView tvCartItemUnitPrice, TextView tvCartItemQuantity, TextView tvCartItemPrice) {

            this.tvCartItemName = tvCartItemName;
            this.tvCartItemUnitPrice = tvCartItemUnitPrice;
            this.tvCartItemQuantity = tvCartItemQuantity;
            this.tvCartItemPrice = tvCartItemPrice;
        }
    }

}
