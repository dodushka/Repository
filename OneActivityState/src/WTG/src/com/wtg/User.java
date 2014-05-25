package com.wtg;

import java.util.List;

public class User {

	int user_id;
	Location location;
	List<Interests> interests;
	public int getId() {  
		  return user_id;  
		 }  
		 public void setId(int id) {  
		  this.user_id = id;  
		 } 
		 public Location getLocation() {  
			  return location;  
			 }  
			 public void setId(Location location) {  
			  this.location = location;  
			 } 
			 public List<Interests> getInterests() {  
				  return interests;  
				 }  
				 public void setInterests(List<Interests> interests) {  
				  this.interests = interests;  
				 }  

}
