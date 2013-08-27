package com.pkh.tuxpuz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HelpView extends Activity {

	/** Called when the activity is first created. */
	LinearLayout aboutus_linear,helpview_linear;
	boolean isviewadded=false;
	LayoutInflater inflate;
	View views;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help_view_xml);
	//	setContentView(R.layout.about_us_xml);
		aboutus_linear=(LinearLayout) findViewById(R.id.about_us_linear);
		helpview_linear=(LinearLayout) findViewById(R.id.helpview_linear);
		
		
		findViewById(R.id.play_help_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					
					helpview_linear.setVisibility(View.VISIBLE);
					aboutus_linear.setVisibility(View.GONE);
					
				
			}
		});
		findViewById(R.id.about_us_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				helpview_linear.setVisibility(View.GONE);
				aboutus_linear.setVisibility(View.VISIBLE);
				
				
				
			}
		});
		
		findViewById(R.id.facebook_like).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getApplicationContext(), "facebook", 0).show();
				startActivity(new Intent(getApplicationContext(), FacebookPage.class));
			}
		});
		findViewById(R.id.facebook_like_help).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getApplicationContext(), "facebook", 0).show();
				startActivity(new Intent(getApplicationContext(), FacebookPage.class));
			}
		});
		
		
		
	    // TODO Auto-generated method stub
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
