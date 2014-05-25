package com.example.oneactivitystate;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
final String STATE="State";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(STATE,"OnCreate");
	}
	@Override
	protected void onStart() {
		
		super.onStart();
		Log.d(STATE,"OnStart");
	}
	@Override
	protected void onResume() {
		
		super.onResume();
		Log.d(STATE,"OnResume");
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		Log.d(STATE,"OnDestroy");
	}
	@Override
	protected void onPause() {
		
		super.onPause();
		Log.d(STATE,"OnPause");
	}
	@Override
	protected void onStop() {
	
		super.onStop();
		Log.d(STATE,"OnStop");
	}
}
