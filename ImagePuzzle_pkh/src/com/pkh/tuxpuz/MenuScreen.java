package com.pkh.tuxpuz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MenuScreen extends Activity implements OnClickListener {

	Button startbtn,mutebtn,helpbtn;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.menu_screen);
	   
	    startbtn=(Button) findViewById(R.id.start_btn_menu_screen);
	    mutebtn=(Button) findViewById(R.id.mute_btn_menu_screen);
	    helpbtn=(Button) findViewById(R.id.help_btn_menu_screen);
	    // TODO Auto-generated method stub
	    
	    
	    
	    startbtn.setOnClickListener(this);
	    mutebtn.setOnClickListener(this);
	    helpbtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch (id) {
		case R.id.start_btn_menu_screen:
			Intent i=new Intent(getApplicationContext(), GeekSelector.class);
			startActivity(i);
			
			break;
		case R.id.mute_btn_menu_screen:
			
			if(AnimProjt.isMuted){
				AnimProjt.isMuted=false;
				mutebtn.setText("SOUND ON");
//				mutebtn.setBackgroundResource(R.drawable.unmute_);
			}
			else{
				
				AnimProjt.isMuted=true;
				mutebtn.setText("SOUND OFF");
			}
			break;
		case R.id.help_btn_menu_screen:
			Intent helpIntent=new Intent(getApplicationContext(), HelpView.class);
			startActivity(helpIntent);
			
			break;

		default:
			break;
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		 if(AnimProjt.isMuted){
				
			 mutebtn.setText("SOUND OFF");
			}
			else{
				
				
				mutebtn.setText("SOUND ON");
			}
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		 if(AnimProjt.isMuted){
				
			 mutebtn.setText("SOUND OFF");
			}
			else{
				
				
				mutebtn.setText("SOUND ON");
			}
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		finish();
	}

}
