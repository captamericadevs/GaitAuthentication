package edu.nps.cs.parker.gait;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NewSubjActivity extends Activity {
	String randomNum;
	TextView subjNum;
	Button bLogin;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		addListenerOnButton(); //Create listener for button press
		
		subjNum=(TextView)findViewById(R.id.subjNum); // create subject number object
		
		try {
		    
			// Create a secure random number generator using the SHA1PRNG algorithm
			SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");
			
			// Create two secure number generators with the same seed
			int seedByteCount = 5;
			byte[] seed = secureRandomGenerator.generateSeed(seedByteCount);

			SecureRandom secureRandom1 = SecureRandom.getInstance("SHA1PRNG");
			secureRandom1.setSeed(seed);
			
			randomNum = new BigInteger(17, secureRandom1).toString(10);
			
			subjNum.setText("Subject #: " + randomNum);
			
		    } catch (NoSuchAlgorithmException e) {
		}
	}
	
	//Listen for button presses on main menu
	public void addListenerOnButton() {
		//bLogin
			bLogin = (Button) findViewById(R.id.buttonLogin);
			bLogin.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					//onClick call existing subject activity
					Intent intent = new Intent(NewSubjActivity.this,ExistSubjActivity.class);
					startActivity(intent);
				}
			});
	}

}
