package com.android.cmpe220.farm2home.demo;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AllProductsActivity extends ListActivity {

	String url = "http://localhost/php/get_all_products.php?farmname=meera";
	ArrayList<HashMap<String, String>> Item_List;
	ProgressDialog PD;
	ListAdapter adapter;

	// JSON Node names
	public static final String ITEM_ID = "FarmName";
	public static final String ITEM_NAME = "ProductName";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Item_List = new ArrayList<HashMap<String, String>>();

		PD = new ProgressDialog(this);
		PD.setMessage("Loading.....");
		PD.setCancelable(false);

		getListView().setOnItemClickListener(new ListitemClickListener());

		ReadDataFromDB();
	}

	private void ReadDataFromDB() {
		PD.show();
		JsonObjectRequest jreq;
		jreq = new JsonObjectRequest(0, url,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							int success = response.getInt("success");

							if (success == 1) {
								JSONArray ja = response.getJSONArray("products");

								for (int i = 0; i < ja.length(); i++) {

									JSONObject jobj = ja.getJSONObject(i);
									HashMap<String, String> item = new HashMap<String, String>();
									item.put(ITEM_ID, jobj.getString(ITEM_ID));
									item.put(ITEM_NAME,
											jobj.getString(ITEM_NAME));

									Item_List.add(item);

								} // for loop ends

								String[] from = { ITEM_ID, ITEM_NAME };
								int[] to = { R.id.ITEM_ID, R.id.item_name };

								adapter = new SimpleAdapter(
										getApplicationContext(), Item_List,
										R.layout.view_products, from, to);

								setListAdapter(adapter);

								PD.dismiss();

							} // if ends

						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				PD.dismiss();
			}
		});

		// Adding request to request queue
		Volley_Application.getInstance().addToReqQueue(jreq);

	}



	//On List Item Click move to UpdateDelete Activity
	class ListitemClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
								long id) {

			Intent modify_intent = new Intent(AllProductsActivity.this,
					FarmerActivity.class);

			modify_intent.putExtra("item", Item_List.get(position));

			startActivity(modify_intent);

		}

	}
}
