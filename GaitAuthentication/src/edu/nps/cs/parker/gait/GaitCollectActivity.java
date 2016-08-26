package edu.nps.cs.parker.gait;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GaitCollectActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private String databaseName = "GaitDB"; //this is the name of the Gait Database file
	private String userID;
	Button sendButton;
	
	TextView xCoor; // declare X axis object
	TextView yCoor; // declare Y axis object
	TextView zCoor; // declare Z axis object
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gait);
        
        Intent sender=getIntent();
        userID = sender.getExtras().getString("userID");
        databaseName = databaseName + userID;
        
        xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		
		addListenerOnButton(); //Create listener for button press
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		/*	More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
		//Create our database by opening it and closing it
		DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext(), databaseName);
		databaseAdapter.open();
		databaseAdapter.close();
    }
	
	//Listen for button presses on main menu
	public void addListenerOnButton() {
		sendButton = (Button) findViewById(R.id.buttonSend);
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext(), databaseName);
				databaseAdapter.copyFile();
			}
		});
	}
		
	public void onAccuracyChanged(Sensor sensor,int accuracy){	
	}
	
	public void onSensorChanged(SensorEvent event){
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			// assign directions
			float x=event.values[0];
			float y=event.values[1];
			float z=event.values[2];
			
			xCoor.setText("X: "+x);
			yCoor.setText("Y: "+y);
			zCoor.setText("Z: "+z);
			
			//write data to database file
			DatabaseAdapter databaseAdapter = new DatabaseAdapter(getApplicationContext(), databaseName);
	        databaseAdapter.open();
	        databaseAdapter.createRecordGait(x, y, z);
	        databaseAdapter.close();
		}
	}
	
	@Override
	protected void onPause() {
		sensorManager.unregisterListener(this);
		super.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this);
		super.onStop();
	}

}
