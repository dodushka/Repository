package com.example.p0151_contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
	TextView tvColor, tvSize;
final int MENU_COLOR_RED = 1;
final int MENU_COLOR_GREEN = 2;
final int MENU_COLOR_BLUE = 3;
final int MENU_SIZE_22 = 4;
final int MENU_SIZE_26 = 5;
final int MENU_SIZE_30 = 6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        tvColor=(TextView)findViewById(R.id.tvColor);
        tvSize=(TextView)findViewById(R.id.tvSize);
		registerForContextMenu(tvColor);
		registerForContextMenu(tvSize);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	
	
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		switch(v.getId()){
		case R.id.tvSize:{
			menu.add(0, MENU_SIZE_22, 1, "Size22");
			menu.add(0, MENU_SIZE_26, 2, "Size26");
			menu.add(0, MENU_SIZE_30, 3, "Size30");
			break;}
		
		case R.id.tvColor:{
			menu.add(0, MENU_COLOR_BLUE, 1, "BLUE");
			menu.add(0, MENU_COLOR_GREEN, 2, "GREEN");
			menu.add(0, MENU_COLOR_RED, 3, "RED");
			break;}
		}
		
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
switch (item.getItemId()) {
case MENU_COLOR_BLUE:
	tvColor.setTextColor(Color.BLUE);
	break;
case MENU_COLOR_RED:
	tvColor.setTextColor(Color.RED);
	break;
case MENU_COLOR_GREEN:
	tvColor.setTextColor(Color.GREEN);
	break;
case MENU_SIZE_22:
	tvSize.setTextSize(22);
	break;
case MENU_SIZE_26:
	tvSize.setTextSize(26);
	break;
case MENU_SIZE_30:
	tvSize.setTextSize(30);
	break;
default:
	break;
}

		return super.onContextItemSelected(item);
	}
	
	
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
