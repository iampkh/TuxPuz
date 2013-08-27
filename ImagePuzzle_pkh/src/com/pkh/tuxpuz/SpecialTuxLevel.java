package com.pkh.tuxpuz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.Database.pkh.PuzzleDatabase;

public class SpecialTuxLevel extends Activity implements OnClickListener{

	Intent normalIntent;
	/** Called when the activity is first created. */
	ImageButton marioTux_btn,roboTux_btn,ninjaTux_btn,cowboyTux_btn;
	PuzzleDatabase mdb=new PuzzleDatabase(this);
	Cursor c;
	int level13,level14,level15,level16;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.special_tux_level );
	    mdb.openDB();
	    
	    marioTux_btn=(ImageButton) findViewById(R.id.marioTux);
	    roboTux_btn=(ImageButton) findViewById(R.id.roboTux);
	    ninjaTux_btn=(ImageButton) findViewById(R.id.ninjaTux);
	    cowboyTux_btn=(ImageButton) findViewById(R.id.cowboyTux);
	    
	    marioTux_btn.setOnClickListener(this);
	    roboTux_btn.setOnClickListener(this);
	    ninjaTux_btn.setOnClickListener(this);
	    cowboyTux_btn.setOnClickListener(this);
	    // TODO Auto-generated method stub
	    c=mdb.getStatusdata();
	    
	    if(c.moveToFirst()){
	    	level13=c.getInt(13);
	    	level14=c.getInt(14);
	    	level15=c.getInt(15);
	    	level16=c.getInt(16);
	    	
	    }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id){
			
			//Intent for 4x4 medi puzzle
			case R.id.marioTux:
				if(level13==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4.class);
				normalIntent.putExtra("ImageID", R.id.marioTux);
				marioTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.roboTux:
				if(level14==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_3x3_medium.class);
				normalIntent.putExtra("ImageID", R.id.roboTux);
				roboTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.ninjaTux:
				if(level15==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4_medium.class);
				normalIntent.putExtra("ImageID", R.id.ninjaTux);
				ninjaTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			case R.id.cowboyTux:
				if(level16==1){
				normalIntent=new Intent(getApplicationContext(), Puzzle_4x4_medium.class);
				normalIntent.putExtra("ImageID", R.id.cowboyTux);
				cowboyTux_btn.setImageResource(R.drawable.trans);
				startActivity(normalIntent);
				}
				break;
			}
		
	}
	private void database_check() {
		// TODO Auto-generated method stub
		 c=mdb.getStatusdata();
		    
		    if(c.moveToFirst()){
		    	level13=c.getInt(13);
		    	level14=c.getInt(14);
		    	level15=c.getInt(15);
		    	level16=c.getInt(16);
		    	
		    }
		    if(level13==1)
		    	marioTux_btn.setImageResource(R.drawable.trans);
		    if(level14==1)
		    	roboTux_btn.setImageResource(R.drawable.trans);
		    if(level15==1)
		    	ninjaTux_btn.setImageResource(R.drawable.trans);
		    if(level16==1)
		    	cowboyTux_btn.setImageResource(R.drawable.trans);
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
