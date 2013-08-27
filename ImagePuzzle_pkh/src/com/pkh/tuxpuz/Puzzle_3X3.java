package com.pkh.tuxpuz;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.CustomizedClasses.pkh.CustomDialogSucces;
import com.CustomizedClasses.pkh.CustomizeArrayList;
import com.Database.pkh.PuzzleDatabase;
import com.fragment.pkh.Frag_example;
import com.fragment.pkh.MenuFragment;
import com.helperclasses.pkh.GestureSwipe_easy;
import com.helperclasses.pkh.SpriteSheet;
import com.korovyansk.android.slideout.SlideoutActivity;



public class Puzzle_3X3 extends Activity implements OnTouchListener{
	
	ImageView 	imv1,imv2,imv3,
				imv4,imv5,imv6,
				imv7,imv8,imv9;
	ImageView miniImageview;
	
	public static final int Board=3;
	public static CustomizeArrayList[][] mycustomlist=new CustomizeArrayList[Board][Board];
	
	ImageView [][]tileMatrix=new ImageView[Board][Board];
	Bitmap[][] bmp=new Bitmap[Board][Board];
	
	SpriteSheet mySheet;
			
	GestureDetector swipeDetect;
	public static Boolean isStarted=false;
	static int ImageId;
	static boolean isMuted=false;
	
	static PuzzleDatabase mdb;
	
	Button go_btn,browse_btn,home_btn,slider_btn;
	TextView nameTxt;
	static TextView movetaken;
	
	static Bitmap drawableID;
	SelectionClass selectimage;	
	static Resources resources;
	public static Context context;
	static int logoId;
	ImageView mute_btn;
	
	MediaPlayer ticksound,clicksound;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.puzzle_3x3);
	    mdb=new PuzzleDatabase(this);
	    mdb.openDB();
	    
	    context=this;
	    resources=getResources(); 
	    
	    ticksound=MediaPlayer.create(Puzzle_3X3.this, R.raw.tick);
	    clicksound=MediaPlayer.create(Puzzle_3X3.this, R.raw.click);
	    clicksound.setVolume(0, 0.5f);
	    nameTxt=(TextView) findViewById(R.id.nameText_3x3);
	    movetaken=(TextView) findViewById(R.id.moves_taken_3x3);
	    home_btn=(Button) findViewById(R.id.home_btn__3x3);
	    slider_btn=(Button) findViewById(R.id.slider_3x3);
	    mute_btn=(ImageView) findViewById(R.id.mute_3x3);
	    //Row 1
	    imv1=(ImageView) findViewById(R.id.img1);
	    imv2=(ImageView) findViewById(R.id.img2);
	    imv3=(ImageView) findViewById(R.id.img3);
	    //Row 2
	    imv4=(ImageView) findViewById(R.id.img4);
	    imv5=(ImageView) findViewById(R.id.img5);
	    imv6=(ImageView) findViewById(R.id.img6);
	    //Row 3
	    imv7=(ImageView) findViewById(R.id.img7);
	    imv8=(ImageView) findViewById(R.id.img8);
	    imv9=(ImageView) findViewById(R.id.img9);
	    //
	    //Tile Matrix for aranging purpose;
	    tileMatrix[0][0]=imv1;	tileMatrix[0][1]=imv2;	tileMatrix[0][2]=imv3;
	    
	    tileMatrix[1][0]=imv4;	tileMatrix[1][1]=imv5;	tileMatrix[1][2]=imv6;
	    
	    tileMatrix[2][0]=imv7;	tileMatrix[2][1]=imv8;	tileMatrix[2][2]=imv9;
	    
	  //  mySheet=new SpriteSheet(R.drawable.cowboy, 3, 3, getResources());
	    
	    //Sprite Sheet creator
	    Intent intent=getIntent();
	    ImageId=intent.getIntExtra("ImageID", 0);
	    Log.d("pkh tile", "Image Id ="+ImageId);
	    switch(ImageId){
	    		
	    	case R.id.normalTux:
	    		nameTxt.setText("Linux Tux");
	    		Log.d("hi", " tux normal tux ");
	    		mySheet=new SpriteSheet(R.drawable.normaltux,Board,Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.normal_wallpaper,this);
	    		
	    		logoId=R.drawable.normaltux_logo;
	    		break;
	    	case R.id.roundTux1:
	    		Log.d("hi", " tux rnd1 tux ");
	    		nameTxt.setText("Crow Tux");
	    		mySheet=new SpriteSheet(R.drawable.roundtux_1,Board,Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.roundtux1_wallpaper,this);
	    		
	    		logoId=R.drawable.roundtux1_logo;
	    		break;
	    	case R.id.rapperTux:
	    		nameTxt.setText("Dj Tux");
	    		mySheet=new SpriteSheet(R.drawable.rappertux, Board,Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.rapper_wallpaper,this);
	    		
	    		logoId=R.drawable.rappertux_logo;
	    		break;
	    	case R.id.GNUTux:
	    		nameTxt.setText("GNU");
	    		mySheet=new SpriteSheet(R.drawable.gnutux,Board,Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.gnutux_wallpaper,this);
	    		
	    		logoId=R.drawable.gnutux_logo;
	    		break;
	    }
	    
	    
	    //Bitmap Matrix
	    bmp[0][0]=mySheet.getTile(0);	bmp[0][1]=mySheet.getTile(1);	bmp[0][2]=mySheet.getTile(2);
	    bmp[1][0]=mySheet.getTile(3);	bmp[1][1]=mySheet.getTile(4);	bmp[1][2]=mySheet.getTile(5);
	    bmp[2][0]=mySheet.getTile(6);	bmp[2][1]=mySheet.getTile(7);	bmp[2][2]=mySheet.getTile(8);
	    
	    // My custom list with Bitmap image and ID
	    //before start the GoButton
	    mycustomlist[0][0]=mySheet.getCustomAraylist().get(0);
	    mycustomlist[0][1]=mySheet.getCustomAraylist().get(1);
	    mycustomlist[0][2]=mySheet.getCustomAraylist().get(2);
	    mycustomlist[1][0]=mySheet.getCustomAraylist().get(3);
	    mycustomlist[1][1]=mySheet.getCustomAraylist().get(4);
	    mycustomlist[1][2]=mySheet.getCustomAraylist().get(5);
	    mycustomlist[2][0]=mySheet.getCustomAraylist().get(6);
	    mycustomlist[2][1]=mySheet.getCustomAraylist().get(7);
	    mycustomlist[2][2]=mySheet.getCustomAraylist().get(8);
	   // before start the GoButton
	    imv1.setImageBitmap(bmp[0][0]);
	    imv2.setImageBitmap(bmp[0][1]);
	    imv3.setImageBitmap(bmp[0][2]);
	    
	    imv4.setImageBitmap(bmp[1][0]);
	    imv5.setImageBitmap(bmp[1][1]);
	    imv6.setImageBitmap(bmp[1][2]);
	    
	    imv7.setImageBitmap(bmp[2][0]);
	    imv8.setImageBitmap(bmp[2][1]);
	    imv9.setImageBitmap(bmp[2][2]);
	    
	    
		    //Go Button  onclick listener
		    go_btn=(Button) findViewById(R.id.go_start_3x3);
		    go_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(AnimProjt.isMuted){
						
					}
					else{
						
						clicksound.start();
					
					}
					
					//To set the images random, for every turn
					   Integer random_Array[]=new Integer[]{0,1,2,3,4,5,6,7,8};
						List l=Arrays.asList(random_Array);
					
						Collections.shuffle(Arrays.asList(random_Array));
					// end of -To set the images random, for every turn
					//
					
					 	mycustomlist[0][0]=mySheet.getCustomAraylist().get(random_Array[0]);
					    mycustomlist[0][1]=mySheet.getCustomAraylist().get(random_Array[1]);
					    mycustomlist[0][2]=mySheet.getCustomAraylist().get(random_Array[2]);
					    mycustomlist[1][0]=mySheet.getCustomAraylist().get(random_Array[3]);
					    mycustomlist[1][1]=mySheet.getCustomAraylist().get(random_Array[4]);
					    mycustomlist[1][2]=mySheet.getCustomAraylist().get(random_Array[5]);
					    mycustomlist[2][0]=mySheet.getCustomAraylist().get(random_Array[6]);
					    mycustomlist[2][1]=mySheet.getCustomAraylist().get(random_Array[7]);
					    mycustomlist[2][2]=mySheet.getCustomAraylist().get(random_Array[8]);
					    
					 
					    
					 /*   Log.d("pkh tile","check list"+mycustomlist[0][0].getImageID());
					    Log.d("pkh tile","check list"+mycustomlist[0][1].getImageID());
					    Log.d("pkh tile","check list"+mycustomlist[0][2].getImageID());
					    
					    Log.d("pkh tile","check list"+mycustomlist[1][0].getImageID());
					    Log.d("pkh tile","check list"+mycustomlist[1][1].getImageID());
					    Log.d("pkh tile","check list"+mycustomlist[1][2].getImageID());
					    
					    Log.d("pkh tile","check list"+mycustomlist[2][0].getImageID());
					    Log.d("pkh tile","check list"+mycustomlist[2][1].getImageID());
					    Log.d("pkh tile","check list"+mycustomlist[2][2].getImageID());*/
					    bmp[0][0]=mycustomlist[0][0].getBitmap();
					    bmp[0][1]=mycustomlist[0][1].getBitmap();
					    bmp[0][2]=mycustomlist[0][2].getBitmap();
					    bmp[1][0]=mycustomlist[1][0].getBitmap();
					    bmp[1][1]=mycustomlist[1][1].getBitmap();
					    bmp[1][2]=mycustomlist[1][2].getBitmap();
					    bmp[2][0]=mycustomlist[2][0].getBitmap();
					    bmp[2][1]=mycustomlist[2][1].getBitmap();
					    bmp[2][2]=mycustomlist[2][2].getBitmap();
					    
					 // after start the GoButton
					    imv1.setImageBitmap(mycustomlist[0][0].getBitmap());
					    imv2.setImageBitmap(mycustomlist[0][1].getBitmap());
					    imv3.setImageBitmap(mycustomlist[0][2].getBitmap());
					    
					    imv4.setImageBitmap(mycustomlist[1][0].getBitmap());
					    imv5.setImageBitmap(mycustomlist[1][1].getBitmap());
					    imv6.setImageBitmap(mycustomlist[1][2].getBitmap());
					    
					    imv7.setImageBitmap(mycustomlist[2][0].getBitmap());
					    imv8.setImageBitmap(mycustomlist[2][1].getBitmap());
					    imv9.setImageBitmap(mycustomlist[2][2].getBitmap());
					   
					// it well get the custom list to to GestureSwipe Class
					 swipeDetect=new GestureDetector(new GestureSwipe_easy(Puzzle_3X3.this,Board, tileMatrix,bmp,mycustomlist));
					 
					 //Boolean setting to get the touchlistener
					 isStarted=true;
					 GestureSwipe_easy.MOVE_COUNT=0;
					 movetaken.setText("Move :--");
				}
			});
		    
		   
		    
		    mute_btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(AnimProjt.isMuted){
						AnimProjt.isMuted=false;
						mute_btn.setImageResource(R.drawable.unmute_);
					}
					else{
						
						AnimProjt.isMuted=true;
						mute_btn.setImageResource(R.drawable.muted);
					}
				}
			});
	   
	    
	   
	    
	   
	    
	    imv1.setOnTouchListener(this);
	    imv2.setOnTouchListener(this);
	    imv3.setOnTouchListener(this);
	    imv4.setOnTouchListener(this);
	    imv5.setOnTouchListener(this);
	    imv6.setOnTouchListener(this);
	    imv7.setOnTouchListener(this);
	    imv8.setOnTouchListener(this);
	    imv9.setOnTouchListener(this);
	    // TODO Auto-generated method stub
	    
	    //Home button click listener
	    home_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			/*	Intent i=new Intent(getApplicationContext(), GeekSelector.class);
			//	i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);*/
				
				finish();
				mdb.closeDB();
				
			}
		});
	    
	    slider_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MenuFragment.setimage_ID=logoId;
				
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

				Log.d("kono", "kono width = "+width);
				
				SlideoutActivity.prepare(Puzzle_3X3.this, R.id.inner_content3x3, width);
				startActivity(new Intent(Puzzle_3X3.this,Frag_example.class));
				overridePendingTransition(0, 0);
				
				
			}
		});
	    
	    //Logic of moving tile
	  
	  
	}
	

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		if(isStarted){
		//Only execute after it started	
		
		// TODO Auto-generated method stub
		int id=v.getId();
		Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		switch(event.getAction())
		{
		  case MotionEvent.ACTION_DOWN:
		   // ** CODE **
			  Log.d("touch event", "chekcin down");
		    break;
		  case MotionEvent.ACTION_MOVE:
			  Log.d("touch event", "chekcin move");
			  
		    break;
		  case MotionEvent.ACTION_UP:
			  Log.d("touch event", "chekcin up");
		   // ** CODE **
			 // ticksound.start();
			//	vibrator.vibrate(100);
		    break;
		}
//		Log.d("pkh tile", "tile swipe img view Touch swiped");
		switch (id) {
		case R.id.img1:
			Log.d("pkh tile", "tile swipe img view 1 swiped");
			new GestureSwipe_easy(0, 0);
			break;
		case R.id.img2:
			Log.d("pkh tile", "tile swipe img view 2 swiped");
			new GestureSwipe_easy(0, 1);		
			break;
		case R.id.img3:
			Log.d("pkh tile", "tile swipe img view 3 swiped");
			new GestureSwipe_easy(0, 2);
			break;
		case R.id.img4:
			Log.d("pkh tile", "tile swipe img view 4 swiped");
			new GestureSwipe_easy(1, 0);
			break;
		case R.id.img5:
			Log.d("pkh tile", "tile swipe img view 5 swiped");
			new GestureSwipe_easy(1, 1);
			break;
		case R.id.img6:
			Log.d("pkh tile", "tile swipe img view 6 swiped");
			new GestureSwipe_easy(1, 2);
			break;
		case R.id.img7:
			Log.d("pkh tile", "tile swipe img view 7 swiped");
			new GestureSwipe_easy(2, 0);
			break;
		case R.id.img8:
			Log.d("pkh tile", "tile swipe img view 8 swiped");
			new GestureSwipe_easy(2, 1);
			break;
		case R.id.img9:
			Log.d("pkh tile", "tile swipe img view 9 swiped");
			new GestureSwipe_easy(2, 2);
			break;
		default:
			Log.d("pkh tile", "tile swipe [ other Views or Touched");
			return false;
			
		}
		try{
		swipeDetect.onTouchEvent(event);
		}catch(Exception e){
			
		}
		}
		
		return true;
	}
	
	public static void levelOpener() {
		// TODO Auto-generated constructor stub
		isStarted=false;
		
		ContentValues cv=new ContentValues();
		switch(ImageId){
		
    	case R.id.normalTux:
    		cv.put(PuzzleDatabase.level2, 1);
    		mdb.updateStatusdata(cv);
    		
    		
    		break;
    	case R.id.roundTux1:
    		cv.put(PuzzleDatabase.level3, 1);
    		mdb.updateStatusdata(cv);
    		
    		break;
    	case R.id.rapperTux:
    		cv.put(PuzzleDatabase.level4, 1);
    		mdb.updateStatusdata(cv);
    		
    		break;
    	case R.id.GNUTux:
    		cv.put(PuzzleDatabase.level5, 1);
    		cv.put(PuzzleDatabase.level13, 1);
    		mdb.updateStatusdata(cv);
    		
    		break;
    }
		
		CustomDialogSucces cds=new CustomDialogSucces(context, drawableID,logoId,resources);
		Log.d("pkh Move", "No of Move="+GestureSwipe_easy.MOVE_COUNT);
		
		cds.show();
		
		
	}
	public static void UI_update() {
		// TODO Auto-generated method stub
		movetaken.setText("Move :"+GestureSwipe_easy.MOVE_COUNT);

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		super.onBackPressed();
		
		isStarted=false;
		mdb.closeDB();
		Log.d("pkh tile", "back pressed 3x3");
		finish();
		
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("pkh tile", "Destroying 3x3");
		selectimage.closebitmap();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mdb.openDB();
		Log.d("pkh tile", "resume 3x3");
		 if(AnimProjt.isMuted){
				
				mute_btn.setImageResource(R.drawable.muted);
			}
			else{
				
				
				mute_btn.setImageResource(R.drawable.unmute_);
			}
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d("pkh tile", "start 3x3");
		 if(AnimProjt.isMuted){
				
				mute_btn.setImageResource(R.drawable.muted);
			}
			else{
				
				
				mute_btn.setImageResource(R.drawable.unmute_);
			}
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d("pkh tile", "pause 3x3");
		mdb.closeDB();
		
	}
	
	
	

}
