package com.pkh.tuxpuz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

import com.Database.pkh.PuzzleDatabase;

public class GeekSelector extends Activity implements OnClickListener{
	
	Button splTux_btn,splTux_btn2,splTux_btn3;
	ImageButton normalTux_btn,roundTux1_btn,rapperTux_btn,GNUTux_btn,childTux_btn,childTux2_btn,
	roundTux2_btn,paxTux_btn,scientistTux_btn,vikingTux_btn,batmanTux_btn,chitiRajini_btn;
	
	PuzzleDatabase mdb;
	Cursor c;
	
	int level1,level2,level3,level4, level5,level6,level7,level8, level9,level10,level11,level12, level13,level14,level15,level16;
	
	Intent normalIntent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.geek_slection_page);
	    mdb=new PuzzleDatabase(this);
	    mdb.openDB();
	    
	    splTux_btn		=(Button) findViewById(R.id.splTux);
	    splTux_btn2		=(Button) findViewById(R.id.splTux2);
	    splTux_btn3		=(Button) findViewById(R.id.splTux3);
	    normalTux_btn	=(ImageButton) findViewById(R.id.normalTux);
	    roundTux1_btn	=(ImageButton) findViewById(R.id.roundTux1);
	    roundTux2_btn	=(ImageButton) findViewById(R.id.roundTux2);
	    rapperTux_btn	=(ImageButton) findViewById(R.id.rapperTux);
	    GNUTux_btn		=(ImageButton) findViewById(R.id.GNUTux);
	    childTux_btn	=(ImageButton) findViewById(R.id.childTux1);
	    childTux2_btn	=(ImageButton) findViewById(R.id.childTux2);
	    paxTux_btn		=(ImageButton) findViewById(R.id.paxTux);
	    scientistTux_btn=(ImageButton) findViewById(R.id.scientistTux);
	    vikingTux_btn	=(ImageButton) findViewById(R.id.vikingTux);
	    batmanTux_btn	=(ImageButton) findViewById(R.id.batmanTux);
	    chitiRajini_btn =(ImageButton) findViewById(R.id.chiti_RajiniTux);
	    
	    
	    
	    
	    
	    splTux_btn.setOnClickListener(this);
	    splTux_btn2.setOnClickListener(this);
	    splTux_btn3.setOnClickListener(this);
	    normalTux_btn.setOnClickListener(this);
	    roundTux1_btn.setOnClickListener(this);
	    roundTux2_btn.setOnClickListener(this);
	    rapperTux_btn.setOnClickListener(this);
	    GNUTux_btn.setOnClickListener(this);
	    childTux_btn.setOnClickListener(this);
	    childTux2_btn.setOnClickListener(this);
	    paxTux_btn.setOnClickListener(this);
	    scientistTux_btn.setOnClickListener(this);
	    vikingTux_btn.setOnClickListener(this);
	    batmanTux_btn.setOnClickListener(this);
	    chitiRajini_btn.setOnClickListener(this);
	    
	    
	    c=mdb.getStatusdata();
	    if(c.moveToFirst()){
	    	level1=c.getInt(1);
	    	level2=c.getInt(2);
	    	level3=c.getInt(3);
	    	level4=c.getInt(4);
	    	
	    	level5=c.getInt(5);
	    	level6=c.getInt(6);
	    	level7=c.getInt(7);
	    	level8=c.getInt(8);
	    	
	    	level9=c.getInt(9);
	    	level10=c.getInt(10);
	    	level11=c.getInt(11);
	    	level12=c.getInt(12);
	    	
	    	level13=c.getInt(13);
	    	level14=c.getInt(14);
	    	level15=c.getInt(15);
	    	level16=c.getInt(16);
	    	
	    }
	    
	    
	    
	    
	
	    // TODO Auto-generated method stub
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id){
			case R.id.splTux:
				Intent splTuxIntent=new Intent(getApplicationContext(), SpecialTuxLevel.class);
				//splTuxIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(splTuxIntent);
				break;
			case R.id.splTux2:
				Intent splTuxIntent2=new Intent(getApplicationContext(), SpecialTuxLevel.class);
				//splTuxIntent2.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(splTuxIntent2);
				break;
			case R.id.splTux3:
				Intent splTuxIntent3=new Intent(getApplicationContext(), SpecialTuxLevel.class);
			//	splTuxIntent3.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(splTuxIntent3);
				break;
				
			//Intent for 3x3 easy puzzle
			case R.id.normalTux:
				//if(level2==1){
					normalIntent=new Intent(getApplicationContext(), Puzzle_3X3.class);
					normalIntent.putExtra("ImageID", R.id.normalTux);
				//	normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
					startActivity(normalIntent);
				//}
				
				break;
			case R.id.roundTux1:
				if(level2==1){
					roundTux1_btn.setImageResource(R.drawable.trans);
				normalIntent=new Intent(getApplicationContext(), Puzzle_3X3.class);
				normalIntent.putExtra("ImageID", R.id.roundTux1);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				
				startActivity(normalIntent);
				}
				break;
			case R.id.rapperTux:
				if(level3==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3X3.class);
				normalIntent.putExtra("ImageID", R.id.rapperTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				roundTux2_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.GNUTux:
				if(level4==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3X3.class);
				normalIntent.putExtra("ImageID", R.id.GNUTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				GNUTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			
			//****************  4 x 4 easy   ***************
			case R.id.childTux1:
				if(level5==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4.class);
				normalIntent.putExtra("ImageID", R.id.childTux1);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				childTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.childTux2:
				if(level6==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4.class);
				normalIntent.putExtra("ImageID", R.id.childTux2);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				childTux2_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.roundTux2:
				if(level7==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4.class);
				normalIntent.putExtra("ImageID", R.id.roundTux2);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				roundTux2_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.paxTux:
				if(level8==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4.class);
				normalIntent.putExtra("ImageID", R.id.paxTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				paxTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			
			//*****************************  3 x 3 medium   ******************
			case R.id.scientistTux:
				if(level9==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3x3_medium.class);
				normalIntent.putExtra("ImageID", R.id.scientistTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				scientistTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.vikingTux:
				if(level10==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3x3_medium.class);
				normalIntent.putExtra("ImageID", R.id.vikingTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				vikingTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.batmanTux:
				if(level11==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3x3_medium.class);
				normalIntent.putExtra("ImageID", R.id.batmanTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				batmanTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.chiti_RajiniTux:
				if(level12==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3x3_medium.class);
				normalIntent.putExtra("ImageID", R.id.chiti_RajiniTux);
//				normalIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				chitiRajini_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
				
		}
	}
	private void database_check() {
		// TODO Auto-generated method stub
		 
		mdb.openDB();
	    c=mdb.getStatusdata();
	    c.moveToFirst();
	    if(c.moveToFirst()){
	    	level1=c.getInt(1);
	    	level2=c.getInt(2);
	    	level3=c.getInt(3);
	    	level4=c.getInt(4);
	    	
	    	level5=c.getInt(5);
	    	level6=c.getInt(6);
	    	level7=c.getInt(7);
	    	level8=c.getInt(8);
	    	
	    	level9=c.getInt(9);
	    	level10=c.getInt(10);
	    	level11=c.getInt(11);
	    	level12=c.getInt(12);
	    	
	    	level13=c.getInt(13);
	    	level14=c.getInt(14);
	    	level15=c.getInt(15);
	    	level16=c.getInt(16);
	    }
	    if(level2==1)
			roundTux1_btn.setImageResource(R.drawable.trans);
	    if(level3==1)
			rapperTux_btn.setImageResource(R.drawable.trans);
	    if(level4==1)
			GNUTux_btn.setImageResource(R.drawable.trans);
	    
	    if(level5==1)
			childTux_btn.setImageResource(R.drawable.trans);
	    if(level6==1)
			childTux2_btn.setImageResource(R.drawable.trans);
	    if(level7==1)
			roundTux2_btn.setImageResource(R.drawable.trans);
	    if(level8==1)
			paxTux_btn.setImageResource(R.drawable.trans);
	    
	    if(level9==1)
			scientistTux_btn.setImageResource(R.drawable.trans);
	    if(level10==1)
			vikingTux_btn.setImageResource(R.drawable.trans);
	    if(level11==1)
			batmanTux_btn.setImageResource(R.drawable.trans);
	    if(level12==1)
			chitiRajini_btn.setImageResource(R.drawable.trans);

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
		database_check();
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		mdb.closeDB();
		
		finish();
	
	}

}
