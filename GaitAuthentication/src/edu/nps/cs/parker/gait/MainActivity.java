package edu.nps.cs.parker.gait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button b1, b2, b3; //Main App buttons
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		b3 = (Button) findViewById(R.id.button3);
		
		addListenerOnButton(); //Create listener for button press
	}
	
	//Listen for button presses on main menu
	public void addListenerOnButton() {
		//b1 = Enroll
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//onClick call enrollment activity
				Intent intent = new Intent(MainActivity.this,EnrollActy.class);
				startActivity(intent);
			}
		});
		
		//b2 = Authenticate
		b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO: Add Activity for classifier
			}
		});
		
		//b3 = Exit
		b3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//TODO: Add exit data
				finish();
			}
		});
	}
	
}
