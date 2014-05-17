package com.urucas.bamaslimpia.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.urucas.bamaslimpia.R;
import com.urucas.utils.Utils;

public class SplashActivity extends Activity {

	private int RQS_GooglePlayServices = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				checkGooglePlayServices();
			}
		}, 3500);
	}
	
	private void checkGooglePlayServices() {
		
		int status=GooglePlayServicesUtil.isGooglePlayServicesAvailable(SplashActivity.this);
		// Log.i("services status", String.valueOf(status));
		if (status!=ConnectionResult.SUCCESS)
		{
			GooglePlayServicesUtil.getErrorDialog(status, this, RQS_GooglePlayServices).show();
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					System.exit(0);
				}
			}, 10000);
		}else{
			checkConnection();
		}
		
	}
	
	private void checkConnection() {
		if(!Utils.isConnected(SplashActivity.this)) {
			
			AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
			builder.setMessage(R.string.retry_text)
	               .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int id) {
	                    	dialog.cancel();  
	                    	checkConnection();
	               }
	         });         
			 AlertDialog alert = builder.create();
			 alert.show();	
			
		}else{
			initApp();
		}
	}
	
	private void initApp() {
		Intent intent = new Intent(SplashActivity.this, MapActivity.class);
		startActivity(intent);
		SplashActivity.this.finish();
	}
}
