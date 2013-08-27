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

public class Puzzle_4x4 extends Activity implements OnTouchListener{
		ImageView 	imv1,imv2,imv3,imv4,
					imv5,imv6,imv7,imv8,
					imv9,imv10,imv11,imv12,
					imv13,imv14,imv15,imv16;
			public static final int Board=4;
			public static CustomizeArrayList[][] mycustomlist=new CustomizeArrayList[Board][Board];
			
			
			
			ImageView [][]tileMatrix=new ImageView[Board][Board];
			Bitmap[][] bmp=new Bitmap[Board][Board];
			
			SpriteSheet mySheet;
			
			GestureDetector swipeDetect;
			public static Boolean isStarted=false;
			static Button go_btn,home_btn,slider_btn;
			static PuzzleDatabase mdb;
			static int ImageId;
			static boolean isMuted=false;
			
			static Bitmap drawableID;
			SelectionClass selectimage;
			
			static int logoId;
			static Resources resources;
			static Context context;
			TextView nameTxt;
			static TextView movetaken;
			MediaPlayer clicksound;
			ImageView mute_btn;
			
			
			
			/** Called when the activity is first created. */
			@Override
			public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.puzzle_4x4);
			mdb=new PuzzleDatabase(this);
			
			mdb.openDB();
			  clicksound=MediaPlayer.create(Puzzle_4x4.this, R.raw.click);
			  clicksound.setVolume(0, 0.5f);
			context=this;
			resources=getResources();
			nameTxt=(TextView) findViewById(R.id.nameText_4x4);
			 movetaken=(TextView) findViewById(R.id.moves_taken_4x4);
			home_btn=(Button) findViewById(R.id.home_btn_4x4);
			mute_btn=(ImageView) findViewById(R.id.mute_4x4);
			
			//Row 1
			imv1=(ImageView) findViewById(R.id.img1_4x4);
			imv2=(ImageView) findViewById(R.id.img2_4x4);
			imv3=(ImageView) findViewById(R.id.img3_4x4);
			imv4=(ImageView) findViewById(R.id.img4_4x4);
			//Row 2
			imv5=(ImageView) findViewById(R.id.img5_4x4);
			imv6=(ImageView) findViewById(R.id.img6_4x4);
			imv7=(ImageView) findViewById(R.id.img7_4x4);
			imv8=(ImageView) findViewById(R.id.img8_4x4);
			//Row 3
			imv9=(ImageView) findViewById(R.id.img9_4x4);
			imv10=(ImageView) findViewById(R.id.img10_4x4);
			imv11=(ImageView) findViewById(R.id.img11_4x4);
			imv12=(ImageView) findViewById(R.id.img12_4x4);
			//Row4
			imv13=(ImageView) findViewById(R.id.img13_4x4);
			imv14=(ImageView) findViewById(R.id.img14_4x4);
			imv15=(ImageView) findViewById(R.id.img15_4x4);
			imv16=(ImageView) findViewById(R.id.img16_4x4);
			//
			//Tile Matrix for aranging purpose;
			tileMatrix[0][0]=imv1;	tileMatrix[0][1]=imv2;	tileMatrix[0][2]=imv3;  tileMatrix[0][3]=imv4;
			
			tileMatrix[1][0]=imv5;	tileMatrix[1][1]=imv6;	tileMatrix[1][2]=imv7;  tileMatrix[1][3]=imv8;
			
			tileMatrix[2][0]=imv9;	tileMatrix[2][1]=imv10;	tileMatrix[2][2]=imv11; tileMatrix[2][3]=imv12;
			
			tileMatrix[3][0]=imv13;	tileMatrix[3][1]=imv14;	tileMatrix[3][2]=imv15; tileMatrix[3][3]=imv16;
			
			
			
			//Sprite Sheet creator
//			mySheet=new SpriteSheet(R.drawable.cowboy,Board, Board, getResources());	
			 //Sprite Sheet creator
		    Intent intent=getIntent();
		     ImageId=intent.getIntExtra("ImageID", 0);
		    Log.d("pkh tile", "Image Id ="+ImageId);
		    switch(ImageId){
		    		
		    	case R.id.childTux1:
		    		nameTxt.setText("Baby Tux");
		    		mySheet=new SpriteSheet(R.drawable.childtux_1, Board, Board, getResources());
		    		selectimage=new SelectionClass();
		    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.baby1_wallpaper,this);
		    		
		    		logoId=R.drawable.childtux1_logo;
		    		break;
		    	case R.id.childTux2:
		    		nameTxt.setText("Lie Linux");
		    		mySheet=new SpriteSheet(R.drawable.childtux_2, Board, Board, getResources());
		    		selectimage=new SelectionClass();
		    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.baby2_wallpaper,this);
		    		
		    		logoId=R.drawable.childtux2_logo;
		    		break;
		    	case R.id.roundTux2:
		    		nameTxt.setText("Eagle Tux");
		    		mySheet=new SpriteSheet(R.drawable.roundtux_2, Board, Board, getResources());
		    		selectimage=new SelectionClass();
		    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.roundtux2_wallpaper,this);
		    		
		    		logoId=R.drawable.roundtux2_logo;
		    		break;
		    	case R.id.paxTux:
		    		nameTxt.setText("Soldier Tux");
		    		mySheet=new SpriteSheet(R.drawable.paxtux, Board, Board, getResources());
		    		selectimage=new SelectionClass();
		    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.paxtux_wallpaper,this);
		    		
		    		logoId=R.drawable.paxtux_logo;
		    		break;
		    	case R.id.marioTux:
		    		nameTxt.setText("Mario Tux");
		    		mySheet=new SpriteSheet(R.drawable.mario, Board, Board, getResources());
		    		selectimage=new SelectionClass();
		    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.mario_wallpaper,this);
		    		
		    		logoId=R.drawable.mariotux_logo;
		    		break;
		    		
		    }
			//Bitmap Matrix
			bmp[0][0]=mySheet.getTile(0);	bmp[0][1]=mySheet.getTile(1);	bmp[0][2]=mySheet.getTile(2);	bmp[0][3]=mySheet.getTile(3);
			bmp[1][0]=mySheet.getTile(4);	bmp[1][1]=mySheet.getTile(5);	bmp[1][2]=mySheet.getTile(6);	bmp[1][3]=mySheet.getTile(7);
			bmp[2][0]=mySheet.getTile(8);	bmp[2][1]=mySheet.getTile(9);	bmp[2][2]=mySheet.getTile(10);	bmp[2][3]=mySheet.getTile(11);
			bmp[3][0]=mySheet.getTile(12);	bmp[3][1]=mySheet.getTile(13);	bmp[3][2]=mySheet.getTile(14);	bmp[3][3]=mySheet.getTile(15);
			
			// My custom list with Bitmap image and ID
		    mycustomlist[0][0]=mySheet.getCustomAraylist().get(0);
		    mycustomlist[0][1]=mySheet.getCustomAraylist().get(1);
		    mycustomlist[0][2]=mySheet.getCustomAraylist().get(2);
		    mycustomlist[0][3]=mySheet.getCustomAraylist().get(3);
		    mycustomlist[1][0]=mySheet.getCustomAraylist().get(4);
		    mycustomlist[1][1]=mySheet.getCustomAraylist().get(5);
		    mycustomlist[1][2]=mySheet.getCustomAraylist().get(6);
		    mycustomlist[1][3]=mySheet.getCustomAraylist().get(7);
		    mycustomlist[2][0]=mySheet.getCustomAraylist().get(8);
		    mycustomlist[2][1]=mySheet.getCustomAraylist().get(9);
		    mycustomlist[2][2]=mySheet.getCustomAraylist().get(10);
		    mycustomlist[2][3]=mySheet.getCustomAraylist().get(11);
		    mycustomlist[3][0]=mySheet.getCustomAraylist().get(12);
		    mycustomlist[3][1]=mySheet.getCustomAraylist().get(13);
		    mycustomlist[3][2]=mySheet.getCustomAraylist().get(14);
		    mycustomlist[3][3]=mySheet.getCustomAraylist().get(15);
		    
			
			
			
			imv1.setImageBitmap(bmp[0][0]);
			imv2.setImageBitmap(bmp[0][1]);
			imv3.setImageBitmap(bmp[0][2]);
			imv4.setImageBitmap(bmp[0][3]);
			
			imv5.setImageBitmap(bmp[1][0]);
			imv6.setImageBitmap(bmp[1][1]);
			imv7.setImageBitmap(bmp[1][2]);
			imv8.setImageBitmap(bmp[1][3]);
			
			imv9.setImageBitmap(bmp[2][0]);
			imv10.setImageBitmap(bmp[2][1]);
			imv11.setImageBitmap(bmp[2][2]);
			imv12.setImageBitmap(bmp[2][3]);
			
			imv13.setImageBitmap(bmp[3][0]);
			imv14.setImageBitmap(bmp[3][1]);
			imv15.setImageBitmap(bmp[3][2]);
			imv16.setImageBitmap(bmp[3][3]);
			
			 //Go Button  onclick listener
		    go_btn=(Button) findViewById(R.id.go_start_4x4);
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
					   Integer random_Array[]=new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
						List l=Arrays.asList(random_Array);
					
						Collections.shuffle(Arrays.asList(random_Array));
					// end of -To set the images random, for every turn
					//
					
						  	mycustomlist[0][0]=mySheet.getCustomAraylist().get(random_Array[0]);
						    mycustomlist[0][1]=mySheet.getCustomAraylist().get(random_Array[1]);
						    mycustomlist[0][2]=mySheet.getCustomAraylist().get(random_Array[2]);
						    mycustomlist[0][3]=mySheet.getCustomAraylist().get(random_Array[3]);
						    mycustomlist[1][0]=mySheet.getCustomAraylist().get(random_Array[4]);
						    mycustomlist[1][1]=mySheet.getCustomAraylist().get(random_Array[5]);
						    mycustomlist[1][2]=mySheet.getCustomAraylist().get(random_Array[6]);
						    mycustomlist[1][3]=mySheet.getCustomAraylist().get(random_Array[7]);
						    mycustomlist[2][0]=mySheet.getCustomAraylist().get(random_Array[8]);
						    mycustomlist[2][1]=mySheet.getCustomAraylist().get(random_Array[9]);
						    mycustomlist[2][2]=mySheet.getCustomAraylist().get(random_Array[10]);
						    mycustomlist[2][3]=mySheet.getCustomAraylist().get(random_Array[11]);
						    mycustomlist[3][0]=mySheet.getCustomAraylist().get(random_Array[12]);
						    mycustomlist[3][1]=mySheet.getCustomAraylist().get(random_Array[13]);
						    mycustomlist[3][2]=mySheet.getCustomAraylist().get(random_Array[14]);
						    mycustomlist[3][3]=mySheet.getCustomAraylist().get(random_Array[15]);
					    
					 
					    
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
					    bmp[0][3]=mycustomlist[0][3].getBitmap();
					    
					    bmp[1][0]=mycustomlist[1][0].getBitmap();
					    bmp[1][1]=mycustomlist[1][1].getBitmap();
					    bmp[1][2]=mycustomlist[1][2].getBitmap();
					    bmp[1][3]=mycustomlist[1][3].getBitmap();
					    
					    bmp[2][0]=mycustomlist[2][0].getBitmap();
					    bmp[2][1]=mycustomlist[2][1].getBitmap();
					    bmp[2][2]=mycustomlist[2][2].getBitmap();
					    bmp[2][3]=mycustomlist[2][3].getBitmap();
					    
					    bmp[3][0]=mycustomlist[3][0].getBitmap();
					    bmp[3][1]=mycustomlist[3][1].getBitmap();
					    bmp[3][2]=mycustomlist[3][2].getBitmap();
					    bmp[3][3]=mycustomlist[3][3].getBitmap();
					    
					 // after start the GoButton
					    imv1.setImageBitmap(mycustomlist[0][0].getBitmap());
					    imv2.setImageBitmap(mycustomlist[0][1].getBitmap());
					    imv3.setImageBitmap(mycustomlist[0][2].getBitmap());
					    imv4.setImageBitmap(mycustomlist[0][3].getBitmap());
					    
					    imv5.setImageBitmap(mycustomlist[1][0].getBitmap());
					    imv6.setImageBitmap(mycustomlist[1][1].getBitmap());
					    imv7.setImageBitmap(mycustomlist[1][2].getBitmap());
					    imv8.setImageBitmap(mycustomlist[1][3].getBitmap());
					    
					    imv9.setImageBitmap(mycustomlist[2][0].getBitmap());
					    imv10.setImageBitmap(mycustomlist[2][1].getBitmap());
					    imv11.setImageBitmap(mycustomlist[2][2].getBitmap());
					    imv12.setImageBitmap(mycustomlist[2][3].getBitmap());
					   
					    imv13.setImageBitmap(mycustomlist[3][0].getBitmap());
					    imv14.setImageBitmap(mycustomlist[3][1].getBitmap());
					    imv15.setImageBitmap(mycustomlist[3][2].getBitmap());
					    imv16.setImageBitmap(mycustomlist[3][3].getBitmap());
					// it well get the custom list to to GestureSwipe Class
					 swipeDetect=new GestureDetector(new GestureSwipe_easy(Puzzle_4x4.this,Board, tileMatrix,bmp,mycustomlist));
					 
					 //Boolean setting to get the touchlistener
					 isStarted=true;
					 GestureSwipe_easy.MOVE_COUNT=0;
					 movetaken.setText("Move :--");
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
			imv10.setOnTouchListener(this);
			imv11.setOnTouchListener(this);
			imv12.setOnTouchListener(this);
			imv13.setOnTouchListener(this);
			imv14.setOnTouchListener(this);
			imv15.setOnTouchListener(this);
			imv16.setOnTouchListener(this);
			// TODO Auto-generated method stub
			home_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i=new Intent(getApplicationContext(), GeekSelector.class);
			//	i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);*/
				
				finish();
				
			}
		});
			 findViewById(R.id.slider_4x4).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						MenuFragment.setimage_ID=logoId;
						
						int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

						Log.d("kono", "kono width = "+width);
						SlideoutActivity.prepare(Puzzle_4x4.this,R.id.inner_content4x4, width);
						startActivity(new Intent(Puzzle_4x4.this,Frag_example.class));
						
						/*SlideoutActivity.prepare(Puzzle_4x4.this, R.id.inner_content, width);
						startActivity(new Intent(Puzzle_4x4.this,Frag_example.class));*/
						overridePendingTransition(0, 0);
						
						
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
		   
			
			//Logic of moving tile
			
			
			}
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if(isStarted){
					int id=v.getId();
					/*Vibrator vibrator=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
					vibrator.vibrate(10);*/
					//Log.d("pkh tile", "tile swipe img view Touch swiped");
					switch (id) {
					
					case R.id.img1_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(0, 0);
						break;
					case R.id.img2_4x4:
						Log.d("pkh tile", "tile swipe img view 2 swiped");
						new GestureSwipe_easy(0, 1);		
						break;
					case R.id.img3_4x4:
						Log.d("pkh tile", "tile swipe img view 3 swiped");
						new GestureSwipe_easy(0, 2);
						break;
					case R.id.img4_4x4:
						Log.d("pkh tile", "tile swipe img view 4 swiped");
						new GestureSwipe_easy(0, 3);
						break;
					
					case R.id.img5_4x4:
						Log.d("pkh tile", "tile swipe img view 5 swiped");
						new GestureSwipe_easy(1, 0);
						break;
					case R.id.img6_4x4:
						Log.d("pkh tile", "tile swipe img view 6 swiped");
						new GestureSwipe_easy(1, 1);
						break;
					case R.id.img7_4x4:
						Log.d("pkh tile", "tile swipe img view 7 swiped");
						new GestureSwipe_easy(1, 2);
						break;
					case R.id.img8_4x4:
						Log.d("pkh tile", "tile swipe img view 8 swiped");
						new GestureSwipe_easy(1, 3);
						break;
					
					case R.id.img9_4x4:
						Log.d("pkh tile", "tile swipe img view 9 swiped");
						new GestureSwipe_easy(2, 0);
						break;
					case R.id.img10_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(2, 1);
						break;
					case R.id.img11_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(2, 2);
						break;
					case R.id.img12_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(2, 3);
						break;
						
					case R.id.img13_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(3, 0);
						break;
					case R.id.img14_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(3, 1);
						break;
					case R.id.img15_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(3, 2);
						break;
					case R.id.img16_4x4:
						Log.d("pkh tile", "tile swipe img view 1 swiped");
						new GestureSwipe_easy(3, 3);
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
			
			public static void UI_update() {
				// TODO Auto-generated method stub
				movetaken.setText("Move:"+GestureSwipe_easy.MOVE_COUNT);

			}
			
			public static void levelOpener() {
				// TODO Auto-generated constructor stub
				isStarted=false;
				
				ContentValues cv=new ContentValues();
				switch(ImageId){
				
		    	case R.id.childTux1:
		    		cv.put(PuzzleDatabase.level6, 1);
		    		mdb.updateStatusdata(cv);
		    		
		    		
		    		break;
		    	case R.id.childTux2:
		    		cv.put(PuzzleDatabase.level7, 1);
		    		mdb.updateStatusdata(cv);
		    		
		    		break;
		    	case R.id.roundTux2:
		    		cv.put(PuzzleDatabase.level8, 1);
		    		mdb.updateStatusdata(cv);
		    		
		    		break;
		    	case R.id.paxTux:
		    		cv.put(PuzzleDatabase.level9, 1);
		    		cv.put(PuzzleDatabase.level14, 1);
		    		mdb.updateStatusdata(cv);
		    		
		    		break;
		    }
				CustomDialogSucces cds=new CustomDialogSucces(context, drawableID,logoId,resources);
				cds.show();
			}
			@Override
			public void onBackPressed() {
				// TODO Auto-generated method stub
				super.onBackPressed();
				
				isStarted=false;
				mdb.closeDB();
				finish();
				/*Intent i=new Intent(getApplicationContext(), GeekSelector.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);*/
			}
			@Override
			protected void onResume() {
				// TODO Auto-generated method stub
				super.onResume();
				mdb.openDB();
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
				mdb.closeDB();
			}
			@Override
			protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			selectimage.closebitmap();
			}
			
				
			}
			
