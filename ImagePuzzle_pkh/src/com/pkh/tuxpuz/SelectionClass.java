package com.pkh.tuxpuz;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;

public class SelectionClass extends Activity {
	
	public static final String normal_wallpaper		="normal_wallpaper_image.png";
	public static final String roundtux1_wallpaper	="roundtux1_wallpaper_image.png";
	public static final String rapper_wallpaper		="rapper_wallpaper_image.png";
	public static final String gnutux_wallpaper		="gnu_wallpaper_image.png";
	
	public static final String roundtux2_wallpaper	="roundtux2_wallpaper_image.png";
	public static final String paxtux_wallpaper		="paxtux_wallpaper_image.png";
	public static final String baby1_wallpaper		="babytux1_wallpaper_image.png";
	public static final String baby2_wallpaper		="babytux2_wallpaper_image.png";
	
	public static final String scientist_wallpaper	="scientist_wallpaper_image.png";
	public static final String viking_wallpaper		="viking_wallpaper_image.png";
	public static final String batman_wallpaper		="batman_wallpaper_image.png";
	public static final String chiti_wallpaper		="chitirajini_wallpaper_image.png";
	
	public static final String mario_wallpaper		="mario_wallpaper_image.png";
	public static final String robot_wallpaper		="robot_wallpaper_image.png";
	public static final String ninja_wallpaper		="ninja_wallpaper_image.png";
	public static final String cowboy_wallpaper		="cowboy_wallpaper_image.png";
	 Bitmap bitmap;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.selection_page);
	    
	   
	}
	 public  Bitmap getBitmapFromAssets(String fileName,Context context) {
	     AssetManager assetManager = context.getAssets();

	     InputStream istr = null;
		try {
			istr = assetManager.open(fileName);
			  bitmap  = BitmapFactory.decodeStream(istr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	     return bitmap;
	 }
	 
	 public void closebitmap() {
		// TODO Auto-generated method stub
		 bitmap.recycle();
		 bitmap=null;
	}



}
