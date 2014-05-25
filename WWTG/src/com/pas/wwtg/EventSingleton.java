package com.pas.wwtg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EventSingleton {
	static EventSingleton es=new EventSingleton();
	static ArrayList<Event> ev=new ArrayList<Event>();
	static Map<Integer, Tag> map = new HashMap<Integer, Tag>();
	 static Map<String, Event> markedevent = new HashMap<String, Event>();
	private EventSingleton(){}
    
	static public EventSingleton  getInstance(){
		
		return es;
	}
	
	 String getpath(String key){
		return map.get(key).path;
	
		 
	 }
 void putEntry(int key,Tag value){
	 map.put(key, value);
	 
	 
	 
 }
 static void putMarker(String key,Event value){
	 markedevent .put(key, value);
	 
	 
	 
 }
static	public ArrayList<Tag> getTargets(){
		return new ArrayList<Tag>(map.values());

	}
}
