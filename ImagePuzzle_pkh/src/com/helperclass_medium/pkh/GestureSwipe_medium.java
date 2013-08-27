package com.helperclass_medium.pkh;


import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.CustomizedClasses.pkh.CustomizeArrayList;
import com.pkh.tuxpuz.AnimProjt;
import com.pkh.tuxpuz.Puzzle_3x3_medium;
import com.pkh.tuxpuz.Puzzle_4x4_medium;
import com.pkh.tuxpuz.R;

public class GestureSwipe_medium {

	View view;
	public int X;
	public int Y;
	int myItemID;
	Activity context;
	int Board;
	Bitmap[][] bitmap;
	public static int MOVE_COUNT=0;
	 
	Bitmap[][] tmpBitmap=new Bitmap[1][1];
	ImageView[][] tileMatrix;
	 
	CustomizeArrayList[][] mycustomList;
	CustomizeArrayList[][] tempcustomlist=new CustomizeArrayList[1][1];
	MediaPlayer ticksound;
	
	public GestureSwipe_medium(Activity context, int Board,ImageView[][] tileMatrix,Bitmap[][] bitmap,CustomizeArrayList[][] mycustomList) {
		// TODO Auto-generated constructor stub
		
		this.context=context;
		this.Board=Board;
		this.bitmap=bitmap;
		this.tileMatrix=tileMatrix;
		this.mycustomList=mycustomList;
		
		ticksound=MediaPlayer.create(context, R.raw.tick);
		ticksound.setVolume(0, 0.2f);
	}
	public void init(View v,int rowPos,int colPos) {
		// TODO Auto-generated constructor stub
		this.view=v;
		this.X=rowPos;
		this.Y=colPos;
		
		onTouchedonView();
		
	}
	private void onTouchedonView() {
		// TODO Auto-generated method stub
		ImageShifter();
	}
	/**
	 * @return returns "True" if grid ordering is correct ,(or)  returns "False"
	 */
	public Boolean isGridOrdered() {
		// TODO Auto-generated method stub
		int item=0;
		for(int i=0;i<Board;i++){
			
			for(int j=0;j<Board;j++){
				
				int myitem=mycustomList[i][j].getImageID();
				Log.d("pkh tile", "tile swipe grid check - item="+item+" myitem="+myitem);
				if(item!=myitem){
					if(item==(Board*Board)-1)
						return true;
					else
						return false;
					
				}
				
				item=item+1;
			}
		}
		return true;
	}
	private void popUp() {
		// TODO Auto-generated method stub
		/*Log.d("pkh tile", "tile swipe alert="+context);
		Toast.makeText(context, "You won", 0).show();
		Dialog dialog=new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.succes_custom_dialog_layout);
		dialog.show();*/
		
		
		if(Board==3){
			Puzzle_3x3_medium.levelOpener();
		}
		if(Board==4){
			Puzzle_4x4_medium.levelOpener();
		}
		

	}
	
	private void ImageShifter() {
		// TODO Auto-generated method stub
		
		try{
			myItemID=mycustomList[X][Y].getImageID();
			int leftid=0;
			int rightid=0;
			int topid=0;
			int bottomid=0;
			Log.d("pkh tile", "check in try");
		try{  leftid  =mycustomList[X][Y-1].getImageID();Log.d("pkh tile", "check in try left="+leftid); }catch(Exception e){Log.d("pkh tile", "error in try left");}
		try{  rightid =mycustomList[X][Y+1].getImageID();Log.d("pkh tile", "check in try right="+rightid); }catch(Exception e){Log.d("pkh tile", "error in try right");}
		try{  topid   =mycustomList[X-1][Y].getImageID();Log.d("pkh tile", "check in try top="+topid); }catch(Exception e){Log.d("pkh tile", "error in try top");}
		try{  bottomid=mycustomList[X+1][Y].getImageID();Log.d("pkh tile", "check in try bottom="+bottomid); }catch(Exception e){Log.d("pkh tile", "error in try bottom");}
		
		if(leftid==(Board*Board)+1){
			tick_sound();
			 MOVE_COUNT=MOVE_COUNT+1;
	         if(Board==3){
	   			Puzzle_3x3_medium.UI_update();
	   			
	   		}
	   		if(Board==4){
	   			Puzzle_4x4_medium.UI_update();
	   		}
			tmpBitmap[0][0]=bitmap[X][Y];
			tempcustomlist[0][0]=this.mycustomList[X][Y];
			
			this.bitmap[X][Y]=this.bitmap[X][Y-1];
			this.bitmap[X][Y-1]=this.tmpBitmap[0][0];
			
			Log.d("pkh tile", "check in leftid");
			this.tileMatrix[X][Y].setImageBitmap(bitmap[X][Y]);
			this.tileMatrix[X][Y-1].setImageBitmap(bitmap[X][Y-1]);
			
			
			this.mycustomList[X][Y]=mycustomList[X][Y-1];
			this.mycustomList[X][Y-1]=tempcustomlist[0][0];
			
			if(isGridOrdered()){
				Log.d("pkh tile", "tile swipe Ordered Your are the winner");
				popUp();// for Winner Dialog
			}
			return;
		}
		else if(rightid==(Board*Board)+1){
			tick_sound();
			 MOVE_COUNT=MOVE_COUNT+1;
	         if(Board==3){
	   			Puzzle_3x3_medium.UI_update();
	   			
	   		}
	   		if(Board==4){
	   			Puzzle_4x4_medium.UI_update();
	   		}
			tmpBitmap[0][0]=bitmap[X][Y];
			tempcustomlist[0][0]=this.mycustomList[X][Y];
			
			this.bitmap[X][Y]=this.bitmap[X][Y+1];
			this.bitmap[X][Y+1]=this.tmpBitmap[0][0];
			
			Log.d("pkh tile", "check in leftid");
			this.tileMatrix[X][Y].setImageBitmap(bitmap[X][Y]);
			this.tileMatrix[X][Y+1].setImageBitmap(bitmap[X][Y+1]);
			
			
			this.mycustomList[X][Y]=mycustomList[X][Y+1];
			this.mycustomList[X][Y+1]=tempcustomlist[0][0];
			
			if(isGridOrdered()){
				Log.d("pkh tile", "tile swipe Ordered Your are the winner");
				popUp();// for Winner Dialog
			}
			return;
			
		}
		else if(topid==(Board*Board)+1){
			tick_sound();
			 MOVE_COUNT=MOVE_COUNT+1;
	         if(Board==3){
	   			Puzzle_3x3_medium.UI_update();
	   			
	   		}
	   		if(Board==4){
	   			Puzzle_4x4_medium.UI_update();
	   		}
			tmpBitmap[0][0]=bitmap[X][Y];
			tempcustomlist[0][0]=this.mycustomList[X][Y];
			
			this.bitmap[X][Y]=this.bitmap[X-1][Y];
			this.bitmap[X-1][Y]=this.tmpBitmap[0][0];
			
			Log.d("pkh tile", "check in leftid");
			this.tileMatrix[X][Y].setImageBitmap(bitmap[X][Y]);
			this.tileMatrix[X-1][Y].setImageBitmap(bitmap[X-1][Y]);
			
			
			this.mycustomList[X][Y]=mycustomList[X-1][Y];
			this.mycustomList[X-1][Y]=tempcustomlist[0][0];
			
			if(isGridOrdered()){
				Log.d("pkh tile", "tile swipe Ordered Your are the winner");
				popUp();// for Winner Dialog
			}
			return;
		
		}
		else if(bottomid==(Board*Board)+1){
			tick_sound();
			 MOVE_COUNT=MOVE_COUNT+1;
	         if(Board==3){
	        	 Log.d("pkh tile", "ui text");
	   			Puzzle_3x3_medium.UI_update();
	   			
	   		}
	   		if(Board==4){
	   			Puzzle_4x4_medium.UI_update();
	   		}
			tmpBitmap[0][0]=bitmap[X][Y];
			tempcustomlist[0][0]=this.mycustomList[X][Y];
			
			this.bitmap[X][Y]=this.bitmap[X+1][Y];
			this.bitmap[X+1][Y]=this.tmpBitmap[0][0];
			
			Log.d("pkh tile", "check in leftid");
			this.tileMatrix[X][Y].setImageBitmap(bitmap[X][Y]);
			this.tileMatrix[X+1][Y].setImageBitmap(bitmap[X+1][Y]);
			
			
			this.mycustomList[X][Y]=mycustomList[X+1][Y];
			this.mycustomList[X+1][Y]=tempcustomlist[0][0];
			
			if(isGridOrdered()){
				Log.d("pkh tile", "tile swipe Ordered Your are the winner");
				popUp();// for Winner Dialog
			}
			return;
		}
	
	}
	catch(Exception e){
		
	}
	
	}
	private void tick_sound() {
		// TODO Auto-generated method stub
		if(AnimProjt.isMuted){
			
		}
		else{
			
			ticksound.start();
		
		}
	}
	

}
