package com.android.cmpe220.farm2home.demo.consumer_activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.cmpe220.farm2home.demo.R;
import com.android.cmpe220.farm2home.demo.farmer_activities.Classify;

public class ConsumerHomeActivity extends Activity {
	Button btnWelcome;
//	Button btnSignIn,btnSignUp;
//	LoginDataBaseAdapter loginDataBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// create a instance of SQLite Database
		// Get The Refference Of Buttons
//	     btnSignIn=(Button)findViewById(R.id.buttonSignIN);
//	     btnSignUp=(Button)findViewById(R.id.buttonSignUP);
		btnWelcome = (Button) findViewById(R.id.buttonWelcome);
		// Set OnClick Listener on SignUp button
		btnWelcome.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/// Create Intent for SignUpActivity  and Start The Activity
				Intent intentClassify = new Intent(getApplicationContext(), Classify.class);
				startActivity(intentClassify);
			}
		});
	}
//	// Methos to handleClick Event of Sign In Button
//	public void signIn(View V)
//	   {
//			final Dialog dialog = new Dialog(HomeActivity.this);
//			dialog.setContentView(R.layout.login);
//		    dialog.setTitle("Login");
//
//		    // get the Refferences of views
//		    final EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
//		    final EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);
//
//			Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
//
//			// Set On ClickListener
//			btnSignIn.setOnClickListener(new View.OnClickListener() {
//
//				public void onClick(View v) {
//					// get The User name and Password
//					String userName=editTextUserName.getText().toString();
//					String password=editTextPassword.getText().toString();
//
//					// fetch the Password form database for respective user name
//					String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);
//
//					// check if the Stored password matches with  Password entered by user
//					if(password.equals(storedPassword))
//					{
//						Toast.makeText(HomeActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
//						dialog.dismiss();
//                        navigatetohome();
//					}
//					else
//					{
//						Toast.makeText(HomeActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
//					}
//				}
//			});
//
//			dialog.show();
//	}
//    public void navigatetohome()
//    {
//        Intent intent= new Intent(this,FarmerActivity.class);
//        startActivity(intent);
//    }
//
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//	    // Close The Database
//		loginDataBaseAdapter.close();
//	}
}
