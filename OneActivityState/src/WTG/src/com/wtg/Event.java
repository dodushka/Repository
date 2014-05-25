package com.wtg;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.text.format.Time;

public class Event {

	String title;
	int eve_id ;
	int user_id ;
	int int_id ;
    String discription;
    
    double lat ;
    double lng;
    String start;
    String end ;
    int[]mas;
		
    
    Event(){}
    
    Event(String title,int eve_id,int user_id,int int_id,String discription,double lat,double lng,String start_Date1,String end_Date1,int[]mas){
    	this.title=title;
    	this.eve_id=eve_id ;
    	this.user_id=user_id;
        this.int_id=int_id ;
        this.discription=discription;
        this.lat=lat ;
        this.lng=lng;
        this.start=start_Date1;
        this.end=end_Date1 ;
        this.mas=mas;
    	
    	
    	
    }
   
		
		
		
		
	}

