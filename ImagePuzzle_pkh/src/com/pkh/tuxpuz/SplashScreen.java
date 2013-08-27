package com.pkh.tuxpuz;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends Activity {
	 private static final int SPLASH_TIME = 2 * 1000;// 3 seconds
	 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.splash_screen);
	        try {
	        new android.os.Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					 Intent intent = new Intent(SplashScreen.this,AnimProjt.class);
		                startActivity(intent);
		 
		                SplashScreen.this.finish();
		 
		                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					
				}
			}, SPLASH_TIME);
	         
	        
	        } catch(Exception e){}
	    }
	 
	     
	    @Override
	    public void onBackPressed() {
	        this.finish();
	        super.onBackPressed();
	    }
	}