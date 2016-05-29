package com.android.cmpe220.farm2home.demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.model.ConsumerFarm;

import java.util.ArrayList;
import java.util.List;

public class ConsumerFarmAdapter extends BaseAdapter {
    private static final String TAG = "ConsumerFarmAdapter";

    private List<ConsumerFarm> farms = new ArrayList<ConsumerFarm>();

    private final Context context;

    public ConsumerFarmAdapter(Context context) {
        this.context = context;
    }

    public void updateFarms(List<ConsumerFarm> farms) {
        this.farms.addAll(farms);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return farms.size();
    }

    @Override
    public ConsumerFarm getItem(int position) {
        return farms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView fName;
        TextView fDistance;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.consumer_adapter_farm, parent, false);
            fName = (TextView) convertView.findViewById(R.id.tvFarmName);
            fDistance = (TextView) convertView.findViewById(R.id.tvFarmDistance);
            convertView.setTag(new ViewHolder(fName, fDistance));
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            fName = viewHolder.tvFarmName;
            fDistance = viewHolder.tvFarmDistance;
        }

        final ConsumerFarm farm = getItem(position);
        fName.setText(farm.getName());
        fDistance.setText(String.valueOf(farm.getDistance()));

        return convertView;
    }

    private static class ViewHolder {
        public final TextView tvFarmName;
        public final TextView tvFarmDistance;

        public ViewHolder(TextView tvFarmName, TextView tvFarmDistance) {
            this.tvFarmName = tvFarmName;
            this.tvFarmDistance = tvFarmDistance;
        }
    }
}
