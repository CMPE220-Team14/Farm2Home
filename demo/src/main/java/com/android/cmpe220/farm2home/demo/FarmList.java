package com.android.cmpe220.farm2home.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FarmList extends Activity {
   TextView farm1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farm_list);
        farm1 = (TextView) findViewById(R.id.farm1);
                farm1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(FarmList.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
    }
}
