package com.android.cmpe220.farm2home.demo;

		import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SignUPActivity extends AppCompatActivity {

	private EditText editTextName;

	private EditText editTextPassword;
	private EditText editTextEmail;
	private EditText editTextAddress;
	private EditText editTextPhone;

	 public Button buttonRegister;


	private static final String REGISTER_URL = "http://ec2-52-39-72-190.us-west-2.compute.amazonaws.com/php_login/farm_register.php";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

		editTextName = (EditText) findViewById(R.id.editTextFarmName);

		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		editTextEmail = (EditText) findViewById(R.id.editTextEmailAddress);
		editTextAddress = (EditText) findViewById(R.id.editTextPostalAddress);
		editTextPhone = (EditText) findViewById(R.id.editTextPhoneNumber);
		buttonRegister = (Button) findViewById(R.id.buttonCreateAccount);

		buttonRegister.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				registerUser();
			}
		});
	}


	private void registerUser() {
		String name = editTextName.getText().toString().trim().toLowerCase();
		String password = editTextPassword.getText().toString().trim().toLowerCase();
		String email = editTextEmail.getText().toString().trim().toLowerCase();
		String address = editTextAddress.getText().toString().trim().toLowerCase();
		String phone = editTextPhone.getText().toString().trim().toLowerCase();

		register(name,password,email,address,phone);
	}

	private void register(String name, String password, String email, String address, String phone) {
		String urlSuffix = "?farmname="+name+"&password="+password+"&email="+email+"&phoneno="+phone+"&address="+address;
		class RegisterUser extends AsyncTask<String, Void, String>{

			ProgressDialog loading;


			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = new ProgressDialog(SignUPActivity.this);
				loading.setMessage("Loading products. Please wait...");
				loading.setIndeterminate(false);
				loading.setCancelable(false);
				loading.show();
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
			}

			@Override
			protected String doInBackground(String... params) {
				String s = params[0];
				BufferedReader bufferedReader = null;
				try {
					URL url = new URL(REGISTER_URL+s);
					HttpURLConnection con = (HttpURLConnection) url.openConnection();
					bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

					String result;

					result = bufferedReader.readLine();

					return result;
				}catch(Exception e){
					return null;
				}
			}
		}

		RegisterUser ru = new RegisterUser();
		ru.execute(urlSuffix);
		navigatetomain();
	}

	public void navigatetomain()
	{
		Intent intent= new Intent(this,HomeActivity.class);
		startActivity(intent);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();


	}
}

