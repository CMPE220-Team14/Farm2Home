package com.android.cmpe220.farm2home.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private static final String TAG = "ProductAdapter";

    private List<Product> products = new ArrayList<Product>();

    private final Context context;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    public void updateProducts(List<Product> products) {
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tvName;
        TextView tvPrice;
        TextView FarmName;
        Spinner ITEM_QUANTITY;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.adapter_product, parent, false);
            tvName = (TextView) convertView.findViewById(R.id.tvProductName);
            tvPrice = (TextView) convertView.findViewById(R.id.tvProductPrice);
            FarmName = (TextView) convertView.findViewById(R.id.FarmName);
            convertView.setTag(new ViewHolder(tvName, tvPrice, FarmName));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            tvName = viewHolder.tvProductName;
            tvPrice = viewHolder.tvProductPrice;
            FarmName = viewHolder.FarmName;
        }

        final Product product = getItem(position);
        tvName.setText(product.getName());
        tvPrice.setText("$"+String.valueOf(product.getPrice()));
//        bView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProductActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("product", product);
//                Log.d(TAG, "This product hashCode: " + product.hashCode());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvProductName;
        public final TextView tvProductPrice;
        public final TextView FarmName;

        public ViewHolder(TextView tvProductName, TextView tvProductPrice, TextView FarmName) {
            this.tvProductName = tvProductName;
            this.tvProductPrice = tvProductPrice;
            this.FarmName = FarmName;
        }
    }
}
