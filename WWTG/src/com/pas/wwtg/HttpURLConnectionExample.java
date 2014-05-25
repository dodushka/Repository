package com.pas.wwtg;
 
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;
 
public class HttpURLConnectionExample {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	//HttpURLConnectionExample http = new HttpURLConnectionExample();
 
	
 
	// HTTP GET request
	public String sendGet(String str) throws Exception {
 
		
		
		
		String url = "http://wtgser.azurewebsites.net/api/"+str;
       Log.d("tag", url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        String t;
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		StringBuilder sb = new StringBuilder();

	    String line = null;
		   while ((line = in.readLine()) != null)
		    {  Log.d("gfr",line + "\n");
		        sb.append(line + "\n");
		    }
		    t = sb.toString();
 
		//print result
		return t;
 
	}
 
	// HTTP POST request
	public String LoginPass(String login,String pass) throws Exception {
 String result="";
		HttpClient client = new DefaultHttpClient();  
	    HttpPost post = new HttpPost("http://wtgser.azurewebsites.net/api/users/postme");   
	    post.setHeader("Content-type", "application/json");
	JSONObject obj = new JSONObject();
	obj.put("login", login);
	obj.put("pass", pass);
	    post.setEntity(new StringEntity(obj.toString(), "UTF-8"));
	    Log.d("maf",obj.toString());
	    HttpResponse response = client.execute(post);  
	    HttpEntity entity = response.getEntity();
	    InputStream inputStream = entity.getContent();
	    // json is UTF-8 by default
	    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    while ((line = reader.readLine()) != null)
	    {
	        sb.append(line );
	    }
	    result = sb.toString();
	    
	    
	    
	    
	    
	    
	    
	   

		//print result
		return result;
		
 
	}
 
}