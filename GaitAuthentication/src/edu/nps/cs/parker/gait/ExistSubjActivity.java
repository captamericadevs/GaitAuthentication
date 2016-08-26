package edu.nps.cs.parker.gait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ExistSubjActivity extends Activity {
	Button bCollection;
	private EditText subjID;
	public String userID;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exists);
		
		addListenerOnButton(); //Create listener for button press
		// get edittext component
		subjID = (EditText) findViewById(R.id.iSubjID);
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	//Listen for button presses on main menu
	public void addListenerOnButton() {
	//bCollection
		bCollection = (Button) findViewById(R.id.buttonCollection);
		bCollection.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//onClick call collection type activity
				userID = subjID.getText().toString();
				if(userID.length() == 5 && isInteger(userID)) {
					Intent intent = new Intent(ExistSubjActivity.this,CollectionTypeActivity.class);
					intent.putExtra("userID", subjID.getText().toString());
					startActivity(intent);
				}
			}
			});
	}

}
