package com.pkh.tuxpuz;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.app.Activity;
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

public class Puzzle_4x4_medium extends Activity implements OnClickListener{

	ImageView 	imv1,imv2,imv3,imv4,imv5,imv6,
	imv7,imv8,imv9,imv10,imv11,imv12,imv13,imv14,imv15,imv16;

	public static final int Board=4;
	public static CustomizeArrayList[][] mycustomlist=new CustomizeArrayList[Board][Board];

	ImageView [][]tileMatrix=new ImageView[Board][Board];
	Bitmap[][] bmp=new Bitmap[Board][Board];

	SpriteSheet mySheet;
	public static Boolean isStarted=false;
	static TextView movetaken;
	ImageView mute_btn;
	GestureSwipe_medium swipeMedium;

	Button go_btn,browse_btn,home_btn;

	static PuzzleDatabase mdb;
	static int ImageId;
	MediaPlayer clicksound;
	static Bitmap drawableID;
	SelectionClass selectimage;

	static int logoId;
	static Resources resources;
	static Context context;
	TextView nameTxt;

	//End of variable intialization
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puzzle_4x4_medium);
		mdb=new PuzzleDatabase(this);
		mdb.openDB();

		clicksound=MediaPlayer.create(Puzzle_4x4_medium.this, R.raw.click);
		clicksound.setVolume(0, 0.5f);
		context=this;
		resources=getResources();
		movetaken=(TextView) findViewById(R.id.moves_taken_4x4_med);
		mute_btn=(ImageView) findViewById(R.id.mute_4x4_med);
		nameTxt=(TextView) findViewById(R.id.nameText_4x4_med);
		home_btn=(Button) findViewById(R.id.home_btn_4x4_med);

		//Row 1
		imv1=(ImageView) findViewById(R.id.img1_4x4_med);
		imv2=(ImageView) findViewById(R.id.img2_4x4_med);
		imv3=(ImageView) findViewById(R.id.img3_4x4_med);
		imv4=(ImageView) findViewById(R.id.img4_4x4_med);
		//Row 2

		imv5=(ImageView) findViewById(R.id.img5_4x4_med);
		imv6=(ImageView) findViewById(R.id.img6_4x4_med);
		imv7=(ImageView) findViewById(R.id.img7_4x4_med);
		imv8=(ImageView) findViewById(R.id.img8_4x4_med);
		//Row 3

		imv9 =(ImageView) findViewById(R.id.img9_4x4_med);
		imv10=(ImageView) findViewById(R.id.img10_4x4_med);
		imv11=(ImageView) findViewById(R.id.img11_4x4_med);
		imv12=(ImageView) findViewById(R.id.img12_4x4_med);
		//Row 4
		imv13=(ImageView) findViewById(R.id.img13_4x4_med);
		imv14=(ImageView) findViewById(R.id.img14_4x4_med);
		imv15=(ImageView) findViewById(R.id.img15_4x4_med);
		imv16=(ImageView) findViewById(R.id.img16_4x4_med);

		//Tile Matrix for aranging purpose;
		//Tile Matrix for aranging purpose;
		tileMatrix[0][0]=imv1;	tileMatrix[0][1]=imv2;	tileMatrix[0][2]=imv3;  tileMatrix[0][3]=imv4;

		tileMatrix[1][0]=imv5;	tileMatrix[1][1]=imv6;	tileMatrix[1][2]=imv7;  tileMatrix[1][3]=imv8;

		tileMatrix[2][0]=imv9;	tileMatrix[2][1]=imv10;	tileMatrix[2][2]=imv11; tileMatrix[2][3]=imv12;

		tileMatrix[3][0]=imv13;	tileMatrix[3][1]=imv14;	tileMatrix[3][2]=imv15; tileMatrix[3][3]=imv16;

		//Sprite Sheet creator
		//mySheet=new SpriteSheet(R.drawable.cowboy,Board, Board, getResources());	
		//Sprite Sheet creator
		Intent intent=getIntent();
		ImageId=intent.getIntExtra("ImageID", 0);
		Log.d("pkh tile", "Image Id ="+ImageId);
		switch(ImageId){

		case R.id.marioTux:
			nameTxt.setText("Mario Tux");
			mySheet=new SpriteSheet(R.drawable.mario, Board, Board, getResources());
			selectimage=new SelectionClass();
			drawableID=selectimage.getBitmapFromAssets(SelectionClass.mario_wallpaper,this);

			logoId=R.drawable.mariotux_logo;
			break;
		case R.id.roboTux:
			nameTxt.setText("Robot Tux");
			mySheet=new SpriteSheet(R.drawable.robot, Board, Board, getResources());
			selectimage=new SelectionClass();
			drawableID=selectimage.getBitmapFromAssets(SelectionClass.robot_wallpaper,this);

			logoId=R.drawable.robottux_logo;
			break;
		case R.id.ninjaTux:
			nameTxt.setText("Ninja Tux");
			mySheet=new SpriteSheet(R.drawable.ninja, Board, Board, getResources());
			selectimage=new SelectionClass();
			drawableID=selectimage.getBitmapFromAssets(SelectionClass.ninja_wallpaper,this);

			logoId=R.drawable.ninjatux_logo;
			break;
		case R.id.cowboyTux:
			nameTxt.setText("Cowboy Tux");
			mySheet=new SpriteSheet(R.drawable.cowboy, Board, Board, getResources());
			selectimage=new SelectionClass();
			drawableID=selectimage.getBitmapFromAssets(SelectionClass.cowboy_wallpaper,this);

			logoId=R.drawable.cowboytux_logo;
			break;
		}
		//Bitmap Matrix
		bmp[0][0]=mySheet.getTile(0);	bmp[0][1]=mySheet.getTile(1);	bmp[0][2]=mySheet.getTile(2);	bmp[0][3]=mySheet.getTile(3);
		bmp[1][0]=mySheet.getTile(4);	bmp[1][1]=mySheet.getTile(5);	bmp[1][2]=mySheet.getTile(6);	bmp[1][3]=mySheet.getTile(7);
		bmp[2][0]=mySheet.getTile(8);	bmp[2][1]=mySheet.getTile(9);	bmp[2][2]=mySheet.getTile(10);	bmp[2][3]=mySheet.getTile(11);
		bmp[3][0]=mySheet.getTile(12);	bmp[3][1]=mySheet.getTile(13);	bmp[3][2]=mySheet.getTile(14);	bmp[3][3]=mySheet.getTile(15);

		// TODO Auto-generated method stub
		// My custom list with Bitmap image and ID
		//before start the GoButton
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

		mycustomlist[3][3]=mySheet.getCustomAraylist().get(16);   // sprite sheet giving black tile to Imagelist

		// before start the GoButton

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
		go_btn=(Button) findViewById(R.id.go_start_4x4_med);
		go_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				GestureSwipe_medium.MOVE_COUNT=0;
				movetaken.setText("Move :0");
				if(AnimProjt.isMuted){

				}
				else{

					clicksound.start();

				}
				//To set the images random, for every turn
			/*	Integer random_Array[]=new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14};
				List l=Arrays.asList(random_Array);

				Collections.shuffle(Arrays.asList(random_Array));*/
				 Integer random_Array[]=new Integer[]{50,75,100,125,150,175,200,210,250,260};
					
				   int myrandomcount=new Random().nextInt(10) ;
				// end of -To set the images random, for every turn
					int shuffledarray[][]=new ShuffleClass(4, random_Array[myrandomcount]).getShuffledArr();
				// end of -To set the images random, for every turn
				//

				mycustomlist[0][0]=mySheet.getCustomAraylist().get(shuffledarray[0][0]);
				mycustomlist[0][1]=mySheet.getCustomAraylist().get(shuffledarray[0][1]);
				mycustomlist[0][2]=mySheet.getCustomAraylist().get(shuffledarray[0][2]);
				mycustomlist[0][3]=mySheet.getCustomAraylist().get(shuffledarray[0][3]);
				
				mycustomlist[1][0]=mySheet.getCustomAraylist().get(shuffledarray[1][0]);
				mycustomlist[1][1]=mySheet.getCustomAraylist().get(shuffledarray[1][1]);
				mycustomlist[1][2]=mySheet.getCustomAraylist().get(shuffledarray[1][2]);
				mycustomlist[1][3]=mySheet.getCustomAraylist().get(shuffledarray[1][3]);
				
				mycustomlist[2][0]=mySheet.getCustomAraylist().get(shuffledarray[2][0]);
				mycustomlist[2][1]=mySheet.getCustomAraylist().get(shuffledarray[2][1]);
				mycustomlist[2][2]=mySheet.getCustomAraylist().get(shuffledarray[2][2]);
				mycustomlist[2][3]=mySheet.getCustomAraylist().get(shuffledarray[2][3]);
			
				mycustomlist[3][0]=mySheet.getCustomAraylist().get(shuffledarray[3][0]);
				mycustomlist[3][1]=mySheet.getCustomAraylist().get(shuffledarray[3][1]);
				mycustomlist[3][2]=mySheet.getCustomAraylist().get(shuffledarray[3][2]);
				mycustomlist[3][3]=mySheet.getCustomAraylist().get(shuffledarray[3][3]);

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

				bmp[1][0]=mycustomlist[1][0].getBitmap();  //black image is in this bmp now

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

				swipeMedium=new GestureSwipe_medium(Puzzle_4x4_medium.this,Board, tileMatrix,bmp,mycustomlist);

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

		findViewById(R.id.slider_4x4_med).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MenuFragment.setimage_ID=logoId;

				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

				Log.d("kono", "kono width = "+width);

				SlideoutActivity.prepare(Puzzle_4x4_medium.this, R.id.inner_content4x4med, width);
				startActivity(new Intent(Puzzle_4x4_medium.this,Frag_example.class));
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
		imv10.setOnClickListener(this);
		imv11.setOnClickListener(this);
		imv12.setOnClickListener(this);
		imv13.setOnClickListener(this);
		imv14.setOnClickListener(this);
		imv15.setOnClickListener(this);
		imv16.setOnClickListener(this);

		home_btn.setOnClickListener(this);
	}

	public static void UI_update() {
		// TODO Auto-generated method stub
		movetaken.setText("Move :"+GestureSwipe_medium.MOVE_COUNT);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub


		int id=v.getId();

		if(v.getId()==R.id.home_btn_4x4_med){

			/*Intent i=new Intent(getApplicationContext(), GeekSelector.class);
			startActivity(i);*/
			finish();

		}
		if(isStarted){
			switch (id) {
			case R.id.img1_4x4_med:
				Log.d("pkh tile", "tile swipe img view 1 swiped");
				swipeMedium.init(v,0, 0);
				break;
			case R.id.img2_4x4_med:
				Log.d("pkh tile", "tile swipe img view 2 swiped");
				swipeMedium.init(v,0, 1);		
				break;
			case R.id.img3_4x4_med:
				Log.d("pkh tile", "tile swipe img view 3 swiped");
				swipeMedium.init(v,0, 2);
				break;
			case R.id.img4_4x4_med:
				Log.d("pkh tile", "tile swipe img view 4 swiped");
				swipeMedium.init(v,0, 3);
				break;
			case R.id.img5_4x4_med:
				Log.d("pkh tile", "tile swipe img view 5 swiped");
				swipeMedium.init(v,1, 0);
				break;
			case R.id.img6_4x4_med:
				Log.d("pkh tile", "tile swipe img view 6 swiped");
				swipeMedium.init(v,1, 1);
				break;
			case R.id.img7_4x4_med:
				Log.d("pkh tile", "tile swipe img view 7 swiped");
				swipeMedium.init(v,1, 2);
				break;
			case R.id.img8_4x4_med:
				Log.d("pkh tile", "tile swipe img view 8 swiped");
				swipeMedium.init(v,1, 3);
				break;
			case R.id.img9_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,2, 0);
				break;
			case R.id.img10_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,2, 1);
				break;
			case R.id.img11_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,2, 2);
				break;
			case R.id.img12_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,2, 3);
				break;
			case R.id.img13_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,3, 0);
				break;
			case R.id.img14_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,3, 1);
				break;
			case R.id.img15_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,3, 2);
				break;
			case R.id.img16_4x4_med:
				Log.d("pkh tile", "tile swipe img view 9 swiped");
				swipeMedium.init(v,3, 3);
				break;
			case R.id.home_btn_4x4_med:
				Intent i=new Intent(getApplicationContext(), GeekSelector.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(i);



			default:
				Log.d("pkh tile", "tile swipe [ other Views or Touched="+id);
				break;

			}
		}
	}
	public static void levelOpener() {
		// TODO Auto-generated constructor stub
		isStarted=false;

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
