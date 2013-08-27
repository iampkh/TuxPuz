package com.pkh.tuxpuz;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.helperclasses.pkh.SpriteSheet;

public class AnimProjt extends Activity {

	ImageView imgview;
	SpriteSheet spsheet;
	int x=0;
	Timer timer=new Timer();
	TimerTask tt;
	boolean isrunning=true;
	Button go1,go2;
	ImageView soundSetting;
	public static boolean isMuted=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_anim_page);
		imgview=(ImageView) findViewById(R.id.imgpage);
		
		startAnimate();
		
		go1=(Button) findViewById(R.id.go1_);
		go1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent geekstart=new Intent(getApplicationContext(), MenuScreen.class);
				//geekstart.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(geekstart);
				finish();
			}
		});
	
		
		
	}
	private void startAnimate() {
		// TODO Auto-generated method stub

		spsheet=new SpriteSheet(R.drawable.cowboy_anim, 8, 1, getResources());
		
		tt=new TimerTask() {

		     public void run() {
		             runOnUiThread(new Runnable() {

		                 public void run() {
		                     x++;
		                      if (x >=8) {
		                    	  x=0;
		                          //timer.cancel();
		                          //timer = null;
		                      } 
		                      else
		                    	  Log.d("sheet", "sheetcount="+x);
		                          imgview.setImageBitmap(spsheet.getTile(x));
		                     }
		                 });

		             }
		         };
		
		
		timer.scheduleAtFixedRate(tt, 0, 100);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		// timer.cancel();
       // timer = null;
		//finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		timer.cancel();
	        timer = null;
	        
	}

	


}
