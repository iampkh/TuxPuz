package com.CustomizedClasses.pkh;

import java.io.IOException;

import android.app.Activity;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.helperclass_medium.pkh.GestureSwipe_medium;
import com.helperclasses.pkh.GestureSwipe_easy;
import com.pkh.tuxpuz.AnimProjt;
import com.pkh.tuxpuz.Puzzle_3X3;
import com.pkh.tuxpuz.Puzzle_3x3_medium;
import com.pkh.tuxpuz.Puzzle_4x4;
import com.pkh.tuxpuz.R;
import com.pkh.tuxpuz.SpecialTuxLevel;

public class CustomDialogSucces extends Dialog implements OnClickListener{

	Button setwallpaper,next,reload,menu_succes;
	 WallpaperManager wallpaperManager;
	 Bitmap wallpaper;
	 Resources resource;
	 ImageView imgview_succes;
	 int logoId;
	public Context context;
	 Intent normalIntent;
	 MediaPlayer clicksound;
	 
	public CustomDialogSucces(Context context,Bitmap wallpaper,int logoId,Resources resource) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.succes_custom_dialog_layout);
		setCancelable(true);
		this.wallpaper=wallpaper;
		this.resource=resource;
		this.logoId=logoId;
		this.context=context;
		
		clicksound=MediaPlayer.create(context, R.raw.click);
		//for wall paper settings
		 wallpaperManager = WallpaperManager.getInstance(context);
		
		setwallpaper=(Button) findViewById(R.id.setwallpaper_btn);
		next=(Button) findViewById(R.id.next_success);
		reload=(Button) findViewById(R.id.replay_success);
		menu_succes=(Button) findViewById(R.id.menu_success);
		
		imgview_succes=(ImageView) findViewById(R.id.imageView_success_3x3);
		imgview_succes.setImageResource(logoId);
		
		if(logoId==R.drawable.mariotux_logo||logoId==R.drawable.robottux_logo||logoId==R.drawable.ninjatux_logo||logoId==R.drawable.cowboytux_logo){
			next.setBackgroundResource(R.drawable.small_btn_hover);
		}
		setwallpaper.setOnClickListener(this);
		next.setOnClickListener(this);
		reload.setOnClickListener(this);
		menu_succes.setOnClickListener(this);
		
		clicksound.setVolume(0, 0.5f);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.setwallpaper_btn:
			if(AnimProjt.isMuted){
				
			}
			else{
				
				clicksound.start();
			
			}
			
            try {
				wallpaperManager.setBitmap(wallpaper);
				Toast.makeText(context, "Wallpaper is attached", 0).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.next_success:
			GestureSwipe_easy.MOVE_COUNT=0;
			GestureSwipe_medium.MOVE_COUNT=0;
if(AnimProjt.isMuted){
				
			}
			else{
				
				clicksound.start();
			
			}
						
						switch(logoId){
			    		
						    	case R.drawable.normaltux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_3X3.class);
									normalIntent.putExtra("ImageID", R.id.roundTux1);
									//normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
									
															    		
						    		break;
						    	case R.drawable.roundtux1_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_3X3.class);
									normalIntent.putExtra("ImageID", R.id.rapperTux);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	case R.drawable.rappertux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_3X3.class);
									normalIntent.putExtra("ImageID", R.id.GNUTux);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		break;
						    	case R.drawable.gnutux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, SpecialTuxLevel.class);
						    	//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									//normalIntent.putExtra("ImageID", R.id.roundTux1);
									context.startActivity(normalIntent);
						    		break;
						    	
						    	case R.drawable.childtux1_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_4x4.class);
									normalIntent.putExtra("ImageID", R.id.childTux2);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		break;
						    	case R.drawable.childtux2_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_4x4.class);
									normalIntent.putExtra("ImageID", R.id.roundTux2);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	case R.drawable.roundtux2_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_4x4.class);
									normalIntent.putExtra("ImageID", R.id.paxTux);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	case R.drawable.paxtux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, SpecialTuxLevel.class);
									//normalIntent.putExtra("ImageID", R.id.roundTux1);
						    	//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	
						    	case R.drawable.scientisttux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_3x3_medium.class);
									normalIntent.putExtra("ImageID", R.id.vikingTux);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		break;
						    	case R.drawable.vikingtux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_3x3_medium.class);
									normalIntent.putExtra("ImageID", R.id.batmanTux);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	case R.drawable.batmantux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, Puzzle_3x3_medium.class);
									normalIntent.putExtra("ImageID", R.id.chiti_RajiniTux);
								//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	case R.drawable.roborajinitux_logo:
						    		dismiss();
						    		normalIntent=new Intent(context, SpecialTuxLevel.class);
									//normalIntent.putExtra("ImageID", R.id.roundTux1);
						    	//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
									context.startActivity(normalIntent);
						    		
						    		break;
						    	
						    	
							}
						((Activity) context).finish();
	    
					
			break;
		case R.id.replay_success:
			GestureSwipe_easy.MOVE_COUNT=0;
			GestureSwipe_medium.MOVE_COUNT=0;
			
if(AnimProjt.isMuted){
				
			}
			else{
				
				clicksound.start();
			
			}
			
			dismiss();
			
			break;
		case R.id.menu_success:
			GestureSwipe_easy.MOVE_COUNT=0;
			GestureSwipe_medium.MOVE_COUNT=0;
if(AnimProjt.isMuted){
				
			}
			else{
				
				clicksound.start();
			
			}
			
			dismiss();
			
			((Activity) context).finish();
			
    		
			
			break;

		default:
			break;
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		GestureSwipe_easy.MOVE_COUNT=0;
		GestureSwipe_medium.MOVE_COUNT=0;
	}

}
