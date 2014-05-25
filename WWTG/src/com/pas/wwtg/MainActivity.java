package com.pas.wwtg;


import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity implements OnMarkerClickListener {
	 
	
	GoogleMap map;
	MyTask mt;
	SharedPreferences sPref;
   
	String login,pass;
  SupportMapFragment mapFragment;
  int _id;
  final String TAG = "myLogs";
  int DIALOG_DATE=1;
  String say;
  Context context;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  context=this;  

    mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.map);
    map = mapFragment.getMap();
   MarkerOptions mo=new MarkerOptions();
   mo.position(new LatLng(46.48490634900521, 30.734341777861122));
   mo.title("Hello world");
    map.addMarker(mo);
    if(loadText("login")==""){
    	showDialog(DIALOG_DATE);}
    	else {mt = new MyTask();
	    mt.execute(loadText("login"),loadText("pass"));}
      Log.d("login",loadText("login"));
      GetJSON ev=new GetJSON ();
      ev.execute();
     
      Toast.makeText(this, String.valueOf(_id)+"nnnn", Toast.LENGTH_SHORT).show();
     // TextView text=(TextView)findViewById(R.id.tv);
		// text.setText(String.valueOf(event.size()+10));
    if (map == null) {
      finish();
      return;
    }
    init();
  }

  private void init() {
	    
	    map.setOnMarkerClickListener(this);
	  map.setMapType(GoogleMap.MAP_TYPE_NORMAL );
	  
	  CameraPosition cameraPosition = new CameraPosition.Builder()
      .target(new LatLng(46.48490634900521, 30.734341777861122))
      .zoom(13)
      .bearing(45)
      .tilt(20)
      .build();
    CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
    map.animateCamera(cameraUpdate);
    
    
	  map.setOnMapClickListener(new OnMapClickListener() {
	      
	      @Override
	      public void onMapClick(LatLng latLng) {
	        Log.d(TAG, "onMapClick: " + latLng.latitude + "," + latLng.longitude);
	      }
	    });
	      
	      map.setOnMapLongClickListener(new OnMapLongClickListener() {
	      
	      @Override
	      public void onMapLongClick(LatLng latLng) {
	        Log.d(TAG, "onMapLongClick: " + latLng.latitude + "," + latLng.longitude);
	      }
	    });
	      
	      map.setOnCameraChangeListener(new OnCameraChangeListener() {
	      
	      @Override
	      public void onCameraChange(CameraPosition camera) {
	        Log.d(TAG, "onCameraChange: " + camera.target.latitude + "," + camera.target.longitude);
	      }
	    });
    }


  
  public void click_plus_button(View view) {
		// TODO Auto-generated method stub
	  Intent intent = new Intent(this, CreateEvent.class);
	    startActivity(intent);
	}
  
  
  public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	  }
  class MyTask extends AsyncTask<String, String, String> {

	    @Override
	    protected void onPreExecute() {
	      super.onPreExecute();
	      
	    }

	    @Override
	    protected String doInBackground(String... params) {
	    	  HttpURLConnectionExample http=new HttpURLConnectionExample();
	    	  login=params[0];
	    	  pass=params[1];
	  		try {
	  			
	  			JSONObject jsonObj = new JSONObject(http.LoginPass(params[0],params[1]));
				int aJsonInteger = jsonObj.getInt("user_id");
				
				//JSONArray jArray = jsonObj.getJSONArray("interes");
				_id=aJsonInteger;
	  		    return String.valueOf(aJsonInteger);
	  		 
	  		} catch (Exception e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}        
	             
	      return null;
	    }

	    @Override
	    protected void onPostExecute(String result) {
	    	saveText("login", login);
			saveText("pass",pass);
	      super.onPostExecute(result);
	       
	      TextView tv =(TextView)findViewById(R.id.tv);
	      //tv.setText(result);
	    }
	  }
  
  class GetJSON extends AsyncTask<Void ,String[],String[]>{

	

	@Override
	protected String[] doInBackground(Void... params) {
		HttpURLConnectionExample http=new HttpURLConnectionExample();
		try {String m=http.sendGet("events/getall");
		     String t=http.sendGet("tags/getall");
		     String[] mas={m,t};
			return mas;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	  
	   protected void onPostExecute(String[] result) {
	    	
		      super.onPostExecute(result);
		   if(result!=null)    
		  getTarget(result[0],result[1]);
		    }
	  
	  
	  
  }
  
  

  public LatLng getLocation()
  {
  /* // Get the location manager
   LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
   Criteria criteria = new Criteria();
   String bestProvider = locationManager.getBestProvider(criteria, false);
   Location location = locationManager.getLastKnownLocation(bestProvider);
   Double lat,lon;
   try {
     lat = location.getLatitude ();
     lon = location.getLongitude ();
     return new LatLng(lat, lon);
   }
   catch (NullPointerException e){
       e.printStackTrace();
     return null;
   }*/
	  return new LatLng(46.48490634900521, 30.734341777861122);
  }

  


public Dialog onCreateDialog(int n) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    // Get the layout inflater
	    LayoutInflater inflater = this.getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    
	  
	    builder.setView(inflater.inflate(R.layout.login, null));
	    
	    // Add action buttons
	        builder.setPositiveButton("Sign", new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	               EditText login=( EditText)((AlertDialog) dialog).findViewById(R.id.login); 
	               EditText pass=( EditText)((AlertDialog) dialog).findViewById(R.id.pass); 
	               if(!pass.getText().equals(""))   {  
	            	   if(!login.getText().equals("")){
	                  mt = new MyTask();
	            	    mt.execute(login.getText().toString(),pass.getText().toString());}}
	               }
	           });
	               
	    return builder.create();
	}
  
  
  
  void saveText(String tag ,String string) {
	    sPref = getPreferences(MODE_PRIVATE);
	    Editor ed = sPref.edit();
	    ed.putString(tag, string);
	    ed.commit();
	    Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
	  }
	  
	  String loadText(String tag) {
	    sPref = getPreferences(MODE_PRIVATE);
	    return sPref.getString(tag, "");
	    
	    
	  }
	  
	  public void setTargets(){
		  //объект с Event
		  
		 
		  
		  
		  for(Event event:EventSingleton.ev){
			  String mDrawableName;
			  if (event.int_id>3){
			   mDrawableName = EventSingleton.map.get(event.int_id).path;}
			  
			  
			  
			  else { mDrawableName = EventSingleton.map.get(3).path;}
			  int resID = getResources().getIdentifier(mDrawableName , "drawable", getPackageName());
			 Marker  marker;
			 marker=map.addMarker(new MarkerOptions().position(new LatLng(event.lat, event.lng)).icon(
					  BitmapDescriptorFactory.fromResource(resID)));
			
			  EventSingleton.putMarker(marker.getId(), event);
			  
			  
		  }
		  
		
		  
	  }
	  
	  
	  public void getTarget(String json,String jsonid){
		  EventSingleton evsing=EventSingleton.getInstance();
		  ArrayList<Event> ev=new ArrayList<Event>();
		  TextView tv =(TextView)findViewById(R.id.tv);
		  Log.d("mod",String.valueOf(6));
	      

	      JSONArray jsonTags ;
		  
		  
		  
		  
		  
	      JSONArray jsonArray;
		try {
			jsonTags=new JSONArray(jsonid);
			for(int i=0;i<jsonTags.length();i++){
				JSONObject cp=jsonTags.getJSONObject(i);
				int int_id = cp.getInt("int_id");
				String name = cp.getString("name");
				String path = cp.getString("path");
	      evsing.putEntry(int_id, new Tag(int_id,name,path));
				
			}
			
			jsonArray = new JSONArray(json);
		for(int i=0;i<jsonArray.length();i++){
				JSONObject c=jsonArray.getJSONObject(i);
				int eve_id = c.getInt("eve_id");
				int user_id = c.getInt("user_id");
				int int_id = c.getInt("int_id");
              String title = c.getString("title");
              String description = c.getString("description");
              JSONObject location = c.getJSONObject("location");
              double lat =location.getDouble("lat");
              double lng= location.getDouble("lng");
              String start_Date = c.getString("start_date");
              String end_Date = c.getString("end_date");
              JSONArray subscription=c.getJSONArray("subscription");
              int mas[]=new int[subscription.length()];
              for(int t=0;t<subscription.length();t++){
              	mas[t]=subscription.getJSONObject(t).getInt("user_id"); }
              	  ev.add(new Event(title,eve_id, user_id, int_id, description, lat, lng, start_Date, end_Date, mas));
              	tv.setText(jsonid); 
			}
			
			
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
              
             
		           
             evsing.ev=ev;
			      setTargets();
			
			
		}

	@Override
	public boolean onMarkerClick(Marker marker) {
		Intent intent = new Intent(this, LookAtEvent.class); 
	    intent.putExtra("marker_id", marker.getId());
	    
	    startActivity(intent);
		
		return true;
	} 
	  
	  
	  }

