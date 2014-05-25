package com.pas.wwtg;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.TextureView;
import android.widget.TextView;

public class LookAtEvent extends FragmentActivity {

	SupportMapFragment mapFragment;
	  GoogleMap map;
	  Event eventex;
	 public void onCreate(Bundle savedInstanceState) {
		
	        super.onCreate(savedInstanceState);
	        Intent i=getIntent();
	        eventex=EventSingleton.markedevent.get(i.getStringExtra("marker_id"));
	        setContentView(R.layout.exist_event);
	       mapFragment = (SupportMapFragment) getSupportFragmentManager()
	 	           .findFragmentById(R.id.map_exist);
	 	       map = mapFragment.getMap();
	 	        if (map == null) {
	 	          finish();
	 	          return;
	 	        }
	 	        initmap();
	 	     
	 	       TextView title= (TextView)findViewById(R.id.title_exist);
	 	       title.setText(eventex.title);
	 	      TextView start= (TextView)findViewById(R.id.start_time_exist);
	 	     start.setText(eventex.start);
	 	     TextView end= (TextView)findViewById(R.id.end_time_exist); 
	 	    end.setText(eventex.end);
	 	    TextView description= (TextView)findViewById(R.id.description_exist);
	 	   description.setText(eventex.discription);
	 	    
	        
	        //TextView text= (TextView)findViewById(R.id.view_title);
	        
	        //text.setText(EventSingleton.markedevent.get(i.getStringExtra("marker_id")).end+"");
	       
	        
	        
	    }
	private void initmap() {
		 map.setMapType(GoogleMap.MAP_TYPE_NORMAL );
		  
		  CameraPosition cameraPosition = new CameraPosition.Builder()
	      .target(new LatLng(eventex.lat,  eventex.lng))
	      .zoom(17)
	      .bearing(45)
	      .tilt(20)
	      .build();
		  CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		    map.animateCamera(cameraUpdate);
		    
		
	}

}
