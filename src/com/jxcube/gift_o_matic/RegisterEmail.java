package com.jxcube.gift_o_matic;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jxcube.gift_o_matic.models.User;

public class RegisterEmail extends Activity {
	private static final String TAG = "RegisterEmail";
	private static RegisterEmail instance = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		if (instance == null)
			instance = this;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_email, menu);
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
	
	public static RegisterEmail getContext() {
		return instance;
	}
	
	/*
	 * This is a 'click' handler called when
	 * the user submit the registration form
	 */
	public void registerWithEmail(final View v) {
		// TODO: implement the handler
		Log.v(TAG, "clicked!");
		
		
		Map<String,String> formData = new HashMap<String,String>();
		formData.put("password", ((EditText) findViewById(R.id.password_field)).getText().toString());
		formData.put("password_repeat", ((EditText) findViewById(R.id.password_repeat_field)).getText().toString());
		
		if (!formData.get("password").equals(formData.get("password_repeat"))) {
			Log.d(TAG, "password & password repeat didn't match");
		} else {
			formData.put("email", ((EditText) findViewById(R.id.email_field)).getText().toString());
			formData.put("username", ((EditText) findViewById(R.id.username_field)).getText().toString());
			
			try {
				User.create(formData);				
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			
			RequestQueue queue = Volley.newRequestQueue(this);
			String url = "http://192.168.1.129:3000/api/user";
			JSONObject userData = new JSONObject(formData);
			JsonObjectRequest request = new JsonObjectRequest
					(Request.Method.POST, url, userData, new Response.Listener<JSONObject>() {
						@Override
						public void onResponse(JSONObject response) {
							// TODO Auto-generated method stub
							Button b = (Button) v;
							b.setText("success");
						}
					}, new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							Button b = (Button) v;
							b.setText(error.getMessage());
						}
					});
			queue.add(request);
			
		}
		
		
		
	}
}
