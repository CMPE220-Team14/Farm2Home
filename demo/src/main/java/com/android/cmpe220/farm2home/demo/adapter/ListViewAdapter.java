package com.android.cmpe220.farm2home.demo.adapter;

/**
 * Created by pooja.prabhuswamy on 5/1/16.
 */

import static com.android.cmpe220.farm2home.demo.constant.Constant.Product;
import static com.android.cmpe220.farm2home.demo.constant.Constant.Quantity;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;

public class ListViewAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    TextView txtFirst;
    TextView txtSecond;
    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }


    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }


    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }


    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }




    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub



        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.activity_retrieve_products, null);

            txtFirst=(TextView) convertView.findViewById(R.id.ITEM_NAME);
            txtSecond=(TextView) convertView.findViewById(R.id.ITEM_QUANTITY);
        }

        HashMap<String, String> map=list.get(position);
        txtFirst.setText(map.get(Product));
        txtSecond.setText(map.get(Quantity));

        return convertView;
    }
}
