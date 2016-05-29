package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

public class ConsumerSimpleArrayAdapter extends ArrayAdapter<String> {

    Context context;
    int textViewResourceId;
    private static final String TAG = "ConsumerSimpleArrayAdapter" ;
    HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

    public ConsumerSimpleArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourceId = textViewResourceId;
        for (int i = 0; i < objects.size(); ++i) {
            hashMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return hashMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void add(String object){
        hashMap.put(object,hashMap.size());
        this.notifyDataSetChanged();
    }
}