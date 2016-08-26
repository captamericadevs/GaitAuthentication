package edu.nps.cs.parker.gait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

public class CollectionTypeActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	RadioGroup options;
	String userID;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        
        Intent sender=getIntent();
        userID = sender.getExtras().getString("userID");
        
        //Get the collection options button group
        options = (RadioGroup)findViewById(R.id.collectoptions);
        options.setOnCheckedChangeListener(this);
    }

	//Listen for a change on selection
	public void onCheckedChanged(RadioGroup group, int radioBtnId)
	{
		//If gait collection selected
		if (radioBtnId == R.id.gaitcollect)
		{
			//Start accelerometer collection activity
			Intent collIntent = new Intent(this,GaitCollectActivity.class);
			collIntent.putExtra("userID", userID);
			startActivity(collIntent);
		}
		else if (radioBtnId == R.id.textcollect)
		{
			//Start text input collection activity
			Intent collIntent = new Intent(this,TextCollectActivity.class);
			collIntent.putExtra("userID", userID);
			startActivity(collIntent);
		}
		else if (radioBtnId == R.id.sigcollect)
		{
			//Start signal collection activity
			Intent collIntent = new Intent(this,SigCollectActivity.class);
			collIntent.putExtra("userID", userID);
			startActivity(collIntent);
		}
	}
}
