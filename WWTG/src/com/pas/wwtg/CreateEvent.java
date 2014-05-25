package com.pas.wwtg;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class CreateEvent extends FragmentActivity {
	Adapter adapter;
	EditText textDate;
	  int DIALOG_DATE = 1;
	  int myYear = 2013;
	  int myMonth = 02;
	  int myDay = 03;
	  //Event event=new Event();
	 ArrayList<SportInfo> sport = new ArrayList<SportInfo>();
	SupportMapFragment mapFragment;
	  GoogleMap map;
	  final String TAG = "myLogs";
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.new_event);
	  mapFragment = (SupportMapFragment) getSupportFragmentManager()
	           .findFragmentById(R.id.map_for_event);
	       map = mapFragment.getMap();
	        if (map == null) {
	          finish();
	          return;
	        }
fill();
	        init();
	    }

	private void init() {
		
		 adapter = new Adapter(this, EventSingleton.getTargets());

		    // настраиваем список
		    ListView lvMain = (ListView) findViewById(R.id.lvMain);
		    lvMain.setAdapter(adapter);
		
	}
	public void dateclick(View view){
		 textDate=(EditText)view;
		showDialog(DIALOG_DATE);
		
		
		
		
		 }
	
	
	
	
	 void fill(){for(int i=0;i<100;i++){
	sport.add(new SportInfo("Game",false));
}
	 
	 
	
	 }
	 public Dialog onCreateDialog(int n) {
		    AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    // Get the layout inflater
		    LayoutInflater inflater = this.getLayoutInflater();

		    // Inflate and set the layout for the dialog
		    // Pass null as the parent view because its going in the dialog layout
		    builder.setView(inflater.inflate(R.layout.custom_dialog, null));
		    // Add action buttons
		        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	   
		                   DatePicker dp=(DatePicker)((AlertDialog) dialog).findViewById(R.id.datePicker1);
		                   TimePicker tp=(TimePicker)((AlertDialog) dialog).findViewById(R.id.timePicker1);
		                   textDate.setText( dp.getDayOfMonth() + "-" + dp.getMonth() + "-" + dp.getYear() +" ");}
		                 /*  if (textDate.getId()==R.id.starttime){event.start=new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
		                  event.start.time=new Time();
		                   event.start.sethour=tp.getCurrentHour();
		                   event.start.time.minute=tp.getCurrentMinute();}
		                    if(textDate.getId()==R.id.endtime){event.end_Date.date=new Date(dp.getYear(),dp.getMonth(),dp.getDayOfMonth());
		                  event.end.time=new Time();
		                   event.end.time.ho=tp.getCurrentHour();
		               event.end=tp.getCurrentMinute();  */
		                   
		               
		           }     
		        
		               
		        		
		        		
		        		
		        		
		        		);
		          builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                  
		               }
		           });      
		    return builder.create();
		}
	    
	    OnDateSetListener myCallBack = new OnDateSetListener() {

	    public void onDateSet(DatePicker view, int year, int monthOfYear,
	        int dayOfMonth) {
	      myYear = year;
	      myMonth = monthOfYear;
	      myDay = dayOfMonth;
	      textDate.setText( myDay + "-" + myMonth + "-" + myYear);
	    }
	    };
	}
	

