package com.jxcube.gift_o_matic.models;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jxcube.gift_o_matic.RegisterEmail;

public class User {
	public String email;
	public String username;
	public String password;
	
	public User(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public void save() {
		JSONObject userData = new JSONObject();
		try {
			userData.put("email", this.email);
			userData.put("username", this.username);
			userData.put("password", this.password);
			RequestQueue queue = Volley.newRequestQueue(RegisterEmail.getContext());
			String url = "http://192.168.1.129:3000/api/user";
			JsonObjectRequest request = new JsonObjectRequest
					(Request.Method.POST, url, userData, new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							// TODO Auto-generated method stub
							
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							
						}
					});
		} catch (JSONException e) {
			
		}
	}
	
	public static User create(String email, String username, String password) throws Exception {
		User newUser = new User(email, username, password);
		newUser.save();
		return newUser;
	}
	
	public static User create(Map<String,String> userData) throws Exception {
		User newUser = new User(userData.get("email"), userData.get("username"), userData.get("passwrod"));
		try {
			newUser.save();
			return newUser;
		} catch(Exception e) {
			return null;			
		}
	}
}
