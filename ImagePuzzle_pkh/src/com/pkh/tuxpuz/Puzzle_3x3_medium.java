package com.pkh.tuxpuz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.CustomizedClasses.pkh.CustomDialogSucces;
import com.CustomizedClasses.pkh.CustomizeArrayList;
import com.CustomizedClasses.pkh.ShuffleClass;
import com.Database.pkh.PuzzleDatabase;
import com.fragment.pkh.Frag_example;
import com.fragment.pkh.MenuFragment;
import com.helperclass_medium.pkh.GestureSwipe_medium;
import com.helperclass_medium.pkh.SpriteSheet;
import com.korovyansk.android.slideout.SlideoutActivity;

public class Puzzle_3x3_medium extends Activity implements OnClickListener {
	ImageView 	imv1,imv2,imv3,
	imv4,imv5,imv6,
	imv7,imv8,imv9;
	public static final int Board=3;
	
	public static CustomizeArrayList[][] mycustomlist=new CustomizeArrayList[Board][Board];
	
	ImageView [][]tileMatrix=new ImageView[Board][Board];
	Bitmap[][] bmp=new Bitmap[Board][Board];
	
	SpriteSheet mySheet;
	public static Boolean isStarted=false;
	
	GestureSwipe_medium swipeMedium;
	
	Button go_btn,browse_btn,home_btn;
	
	
	static PuzzleDatabase mdb;
	static int ImageId;
	static TextView movetaken;
	ImageView mute_btn;
	static Bitmap drawableID;
	SelectionClass selectimage;
	static int logoId;
	static Resources resources;
	static Context context;
	TextView nameTxt;
	
	MediaPlayer ticksound,clicksound;

//End of variable intialization
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 requestWindowFeature(Window.FEATURE_NO_TITLE);
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.puzzle_3x3_medium);
	    mdb=new PuzzleDatabase(this);
	    mdb.openDB();
	   
	    context=this;
	    ticksound=MediaPlayer.create(Puzzle_3x3_medium.this, R.raw.tick);
	    clicksound=MediaPlayer.create(Puzzle_3x3_medium.this, R.raw.click);
	    
	    clicksound.setVolume(0, 0.5f);
	    movetaken=(TextView) findViewById(R.id.moves_taken_3x3_med);
	    mute_btn=(ImageView) findViewById(R.id.mute_3x3_med);
	    nameTxt=(TextView) findViewById(R.id.nameText_3x3_med);
	    home_btn=(Button) findViewById(R.id.home_btn__3x3_med);
	    //Row 1
	    imv1=(ImageView) findViewById(R.id.img1_3x3_med);
	    imv2=(ImageView) findViewById(R.id.img2_3x3_med);
	    imv3=(ImageView) findViewById(R.id.img3_3x3_med);
	    //Row 2
	    imv4=(ImageView) findViewById(R.id.img4_3x3_med);
	    imv5=(ImageView) findViewById(R.id.img5_3x3_med);
	    imv6=(ImageView) findViewById(R.id.img6_3x3_med);
	    //Row 3
	    imv7=(ImageView) findViewById(R.id.img7_3x3_med);
	    imv8=(ImageView) findViewById(R.id.img8_3x3_med);
	    imv9=(ImageView) findViewById(R.id.img9_3x3_med);
	    //
	    //Tile Matrix for aranging purpose;
	    tileMatrix[0][0]=imv1;	tileMatrix[0][1]=imv2;	tileMatrix[0][2]=imv3;
	    
	    tileMatrix[1][0]=imv4;	tileMatrix[1][1]=imv5;	tileMatrix[1][2]=imv6;
	    
	    tileMatrix[2][0]=imv7;	tileMatrix[2][1]=imv8;	tileMatrix[2][2]=imv9;
	    
	    //Sprite Sheet creator
	    mySheet=new SpriteSheet(R.drawable.cowboy, Board, Board, getResources());	
	    //Sprite Sheet creator
	    Intent intent=getIntent();
	    ImageId=intent.getIntExtra("ImageID", 0);
	    resources=getResources();  //this is for custom dialog toset wallpaper
	    Log.d("pkh tile", "Image Id ="+ImageId);
	    switch(ImageId){
	    		
	    	case R.id.scientistTux:
	    		nameTxt.setText("Scientist Tux");
	    		mySheet=new SpriteSheet(R.drawable.scientist, Board, Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.scientist_wallpaper,this);
	    		
	    		logoId=R.drawable.scientisttux_logo;
	    		break;
	    	case R.id.vikingTux:
	    		nameTxt.setText("Viking Tux");
	    		mySheet=new SpriteSheet(R.drawable.viking, Board,Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.viking_wallpaper,this);
	    		
	    		logoId=R.drawable.vikingtux_logo;
	    		break;
	    	case R.id.batmanTux:
	    		nameTxt.setText("Batman Tux");
	    		mySheet=new SpriteSheet(R.drawable.batman,Board,Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.batman_wallpaper,this);
	    		
	    		logoId=R.drawable.batmantux_logo;
	    		break;
	    	case R.id.chiti_RajiniTux:
	    		nameTxt.setText("Chiti_Rajini Tux");
	    		mySheet=new SpriteSheet(R.drawable.roborajintux, Board, Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.chiti_wallpaper,this);
	    		
	    		logoId=R.drawable.roborajinitux_logo;
	    		break;
	    	case R.id.roboTux:
	    		nameTxt.setText("Robot Tux");
	    		mySheet=new SpriteSheet(R.drawable.robot, Board, Board, getResources());
	    		selectimage=new SelectionClass();
	    		drawableID=selectimage.getBitmapFromAssets(SelectionClass.robot_wallpaper,this);
	    		
	    		logoId=R.drawable.robottux_logo;
	    		break;
	    }
	    //Bitmap Matrix
	    bmp[0][0]=mySheet.getTile(0);	bmp[0][1]=mySheet.getTile(1);	bmp[0][2]=mySheet.getTile(2);
	    bmp[1][0]=mySheet.getTile(3);	bmp[1][1]=mySheet.getTile(4);	bmp[1][2]=mySheet.getTile(5);
	    bmp[2][0]=mySheet.getTile(6);	bmp[2][1]=mySheet.getTile(7);	bmp[2][2]=mySheet.getTile(8);
	    
	    // TODO Auto-generated method stub
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
	    
	    mycustomlist[2][2]=mySheet.getCustomAraylist().get(9);   // sprite sheet giving black tile to Imagelist
	    
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
	    go_btn=(Button) findViewById(R.id.go_start_3x3_med);
	    go_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				GestureSwipe_medium.MOVE_COUNT=0;
				movetaken.setText("Move :0");
				// TODO Auto-generated method stub
				if(AnimProjt.isMuted){
					
				}
				else{
					
					clicksound.start();
				
				}
				//To set the images random, for every turn
				   Integer random_Array[]=new Integer[]{50,75,100,125,150,175,200,210,250,260};
					
				   int myrandomcount=new Random().nextInt(10) ;
				// end of -To set the images random, for every turn
					int shuffledarray[][]=new ShuffleClass(3, random_Array[myrandomcount]).getShuffledArr();
					
				
				//Chang the .get(value) only to collabarate
				    
					    mycustomlist[0][0]=mySheet.getCustomAraylist().get(shuffledarray[0][0]);
					    mycustomlist[0][1]=mySheet.getCustomAraylist().get(shuffledarray[0][1]);
					    mycustomlist[0][2]=mySheet.getCustomAraylist().get(shuffledarray[0][2]); 
					    
					    mycustomlist[1][0]=mySheet.getCustomAraylist().get(shuffledarray[1][0]);
					    mycustomlist[1][1]=mySheet.getCustomAraylist().get(shuffledarray[1][1]);
					    mycustomlist[1][2]=mySheet.getCustomAraylist().get(shuffledarray[1][2]);
					    
					    mycustomlist[2][0]=mySheet.getCustomAraylist().get(shuffledarray[2][0]);
					    mycustomlist[2][1]=mySheet.getCustomAraylist().get(shuffledarray[2][1]);
					    mycustomlist[2][2]=mySheet.getCustomAraylist().get(shuffledarray[2][2]);
					    
				    
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
				    
				    bmp[1][0]=mycustomlist[1][0].getBitmap();  //black image is in this bmp now
				    
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
				   
				    
				  swipeMedium=new GestureSwipe_medium(Puzzle_3x3_medium.this,Board, tileMatrix,bmp,mycustomlist);
				   
				 isStarted=true;
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
	    findViewById(R.id.slider_3x3_med).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MenuFragment.setimage_ID=logoId;
				
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

				Log.d("kono", "kono width = "+width);
				
				SlideoutActivity.prepare(Puzzle_3x3_medium.this, R.id.inner_content3x3med, width);
				startActivity(new Intent(Puzzle_3x3_medium.this,Frag_example.class));
				overridePendingTransition(0, 0);
				
				
			}
		});
	    
	    imv1.setOnClickListener(this);
	    imv2.setOnClickListener(this);
	    imv3.setOnClickListener(this);
	    imv4.setOnClickListener(this);
	    imv5.setOnClickListener(this);
	    imv6.setOnClickListener(this);
	    imv7.setOnClickListener(this);
	    imv8.setOnClickListener(this);
	    imv9.setOnClickListener(this);
	    
	    home_btn.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int id=v.getId();
		
		if(v.getId()==R.id.home_btn__3x3_med){
		
			/*Intent i=new Intent(getApplicationContext(), GeekSelector.class);
		//	i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(i);*/
			finish();
			
		}
		
		
		
		if(isStarted){
		switch (id) {
		case R.id.img1_3x3_med:
			Log.d("pkh tile", "tile swipe img view 1 swiped");
			swipeMedium.init(v,0, 0);
			break;
		case R.id.img2_3x3_med:
			Log.d("pkh tile", "tile swipe img view 2 swiped");
			swipeMedium.init(v,0, 1);		
			break;
		case R.id.img3_3x3_med:
			Log.d("pkh tile", "tile swipe img view 3 swiped");
			swipeMedium.init(v,0, 2);
			break;
		case R.id.img4_3x3_med:
			Log.d("pkh tile", "tile swipe img view 4 swiped");
			swipeMedium.init(v,1, 0);
			break;
		case R.id.img5_3x3_med:
			Log.d("pkh tile", "tile swipe img view 5 swiped");
			swipeMedium.init(v,1, 1);
			break;
		case R.id.img6_3x3_med:
			Log.d("pkh tile", "tile swipe img view 6 swiped");
			swipeMedium.init(v,1, 2);
			break;
		case R.id.img7_3x3_med:
			Log.d("pkh tile", "tile swipe img view 7 swiped");
			swipeMedium.init(v,2, 0);
			break;
		case R.id.img8_3x3_med:
			Log.d("pkh tile", "tile swipe img view 8 swiped");
			swipeMedium.init(v,2, 1);
			break;
		case R.id.img9_3x3_med:
			Log.d("pkh tile", "tile swipe img view 9 swiped");
			swipeMedium.init(v,2, 2);
			break;
			
		
		default:
			Log.d("pkh tile", "tile swipe [ other Views or Touched="+id);
			break;
			
		}
		}
	}
	public static void levelOpener() {
		// TODO Auto-generated constructor stub
		isStarted=false;
		
		ContentValues cv=new ContentValues();
		switch(ImageId){
		
    	case R.id.scientistTux:
    		cv.put(PuzzleDatabase.level10, 1);
    		mdb.updateStatusdata(cv);
    		
    		
    		break;
    	case R.id.vikingTux:
    		cv.put(PuzzleDatabase.level11, 1);
    		mdb.updateStatusdata(cv);
    		
    		break;
    	case R.id.batmanTux:
    		cv.put(PuzzleDatabase.level12, 1);
    		mdb.updateStatusdata(cv);
    		
    		break;
    	case R.id.chiti_RajiniTux:
    		cv.put(PuzzleDatabase.level15, 1);
    		cv.put(PuzzleDatabase.level16, 1);
    		mdb.updateStatusdata(cv);
    		
    		break;
    }
		
		CustomDialogSucces cds=new CustomDialogSucces(context, drawableID,logoId,resources);
		cds.show();
		
	}
	public static void UI_update() {
		// TODO Auto-generated method stub
		
		movetaken.setText("Move :"+GestureSwipe_medium.MOVE_COUNT);

	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		isStarted=false;
		mdb.closeDB();
		finish();
	/*	Intent i=new Intent(getApplicationContext(), GeekSelector.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
		startActivity(i);*/
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		selectimage.closebitmap();
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

}
