package edu.nps.cs.parker.gait;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EnrollActy extends Activity {
	Button bNew, bExists;
	
	public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        
        addListenerOnButton(); //Create listener for button press
    }

	//Listen for button presses on menu
	public void addListenerOnButton() {
	//bNew = new training subject
		bNew = (Button) findViewById(R.id.buttonNew);
		bNew.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//onClick call NewSubjActivity
				Intent intent = new Intent(EnrollActy.this,NewSubjActivity.class);
				startActivity(intent);
			}
		});
			
		//b2 = existing subject
		bExists = (Button) findViewById(R.id.buttonExists);
		bExists.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//onClick call ExistSubjActivity
				Intent intent = new Intent(EnrollActy.this,ExistSubjActivity.class);
				startActivity(intent);
			}
		});
	}
}
