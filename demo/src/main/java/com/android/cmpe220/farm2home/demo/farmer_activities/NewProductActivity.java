package com.android.cmpe220.farm2home.demo.farmer_activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.cmpe220.farm2home.demo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewProductActivity extends AppCompatActivity {

    private static final String REGISTER_URL = "http://192.168.2.5/php/create_product.php";
    public Button buttonRegister;
    private EditText Productname;
    private EditText PricePerLb;
    private EditText prod_desc;
    private EditText Quantity_in_Lb;
    private Spinner category;
    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);


        this.arraySpinner = new String[]{
                "Vegetable", "Fruit", "Diary"
        };
        Spinner s = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);


        Productname = (EditText) findViewById(R.id.inputProduct);

        PricePerLb = (EditText) findViewById(R.id.inputPriceProd);
        prod_desc = (EditText) findViewById(R.id.inputDescProd);
        Quantity_in_Lb = (EditText) findViewById(R.id.inputQuantityProd);
        //	FarmName = (EditText) findViewById(R.id.editTextPhoneNumber);
        category = (Spinner) findViewById(R.id.spinnerCategory);

        buttonRegister = (Button) findViewById(R.id.btnCreateProd);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }


    private void registerUser() {
        //	String name=Productname.getText().toString().trim().toLowerCase();
        String name = Productname.getText().toString().trim().toLowerCase();
        String Price = PricePerLb.getText().toString().trim().toLowerCase();
        String desc = prod_desc.getText().toString().trim().toLowerCase();
        String Quantity = Quantity_in_Lb.getText().toString().trim().toLowerCase();
        String cat = category.getSelectedItem().toString().trim().toLowerCase();

        SharedPreferences sharedPreferences = getSharedPreferences(FarmerConfig.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String FarmName = sharedPreferences.getString(FarmerConfig.EMAIL_SHARED_PREF, "Not Available");

        //	Productname.setText(FarmName);

        register(name, Price, desc, Quantity, cat, FarmName);
    }

    private void register(String Productname, String PricePerLb, String prod_desc, String Quantity_in_Lb, String category, String FarmName) {
        String urlSuffix = "?Productname=" + Productname + "&PricePerLb=" + PricePerLb + "&prod_desc=" + prod_desc + "&Quantity_in_Lb=" + Quantity_in_Lb + "&FarmName=" + FarmName + "&category=" + category;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(NewProductActivity.this);
                loading.setMessage("Registering products. Please wait...");
                loading.setIndeterminate(false);
                loading.setCancelable(false);
                loading.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                navigatetomain();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                    return null;
                }
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(urlSuffix);

    }

    public void navigatetomain() {
        Intent intent = new Intent(this, ReadData.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


    }
}

