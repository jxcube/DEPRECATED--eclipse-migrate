package com.jxcube.gift_o_matic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // This will hide the action bar
        getActionBar().hide();
        
        // Display the activity
        setContentView(R.layout.splash);
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
    
    /*
     * This method is a 'click' event listener
     * called when the user clicks the e-mail
     * button.
     */
    public void displayRegistrationForm(View v) {
    	// TODO: Start registration form activity
    	Intent intent = new Intent(this, RegisterEmail.class);
    	startActivity(intent);
    }
}
