package com.helperclasses.pkh;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.CustomizedClasses.pkh.CustomizeArrayList;
import com.pkh.tuxpuz.AnimProjt;
import com.pkh.tuxpuz.Puzzle_3X3;
import com.pkh.tuxpuz.Puzzle_4x4;
import com.pkh.tuxpuz.R;

public class GestureSwipe_easy extends SimpleOnGestureListener {

	public static final int SWIPE_MIN_DIST=50;
	public static final int SWIPE_THRESHOLD_VELOCITY=200;
	public static final int SwipeLeft	=0;
	public static final int SwipeRight	=1;
	public static final int SwipeTop	=2;
	public static final int SwipeBottom	=3;
	
	
	public static int MOVE_COUNT=0;
	
	ImageView[][] tileMatrix;
	Bitmap[][] bitmap;
	Bitmap[][] tmpBitmap=new Bitmap[1][1];
	
	CustomizeArrayList[][] mycustomList;
	CustomizeArrayList[][] tempcustomlist=new CustomizeArrayList[1][1];
	
	//View[][] temp;
	ImageView imageView;
	public static  int Xpos,Ypos,Board;
	
	Activity context;
	
	
	MediaPlayer ticksound;
	
	//End of initialization
	
	/**
	 * @param X_pos  (x,0) x denotes to X position
	 * @param Y_pos  (0,y) y denotes to Y position
	 * 
	 */
	public GestureSwipe_easy(int X_pos,int Y_pos) {
		// TODO Auto-generated constructor stub
		this.Xpos=X_pos;
		this.Ypos=Y_pos;
		Log.d("pkh tile", "tile swipe x&y="+Xpos+"  y"+Ypos);
	}
	/**
	 * @param board , specifies (3 or 4 or 5 or 6) (3 =3x3 ,4=4x4 ,5=5x5 )
	 * @param view View[][] has imageTile in  3x3 (or) 4x4 (0r) 5x5 matrix
	 */
	public GestureSwipe_easy(Activity context, int Board,ImageView[][] tileMatrix,Bitmap[][] bitmap,CustomizeArrayList[][] mycustomList) {
		// TODO Auto-generated constructor stub
		this.context=context;
		this.Board=Board;
		this.bitmap=bitmap;
		this.tileMatrix=tileMatrix;
		this.mycustomList=mycustomList;
		
		ticksound=MediaPlayer.create(context, R.raw.tick);
		ticksound.setVolume(0, 0.2f);
	}
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		super.onFling(e1, e2, velocityX, velocityY);
		// TODO Auto-generated method stub
		
		
			 if(e1.getX() - e2.getX() > SWIPE_MIN_DIST && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	           Log.d("pkh tile", "tile swipe right to left");
	           ImageShifter(SwipeLeft);
	           
	           MOVE_COUNT=MOVE_COUNT+1;
		         if(Board==3){
		   			Puzzle_3X3.UI_update();
		   			
		   		}
		   		if(Board==4){
		   			Puzzle_4x4.UI_update();
		   		}
				 return false; // Right to left
				 
	         }  
			 
			 else if (e2.getX() - e1.getX() > SWIPE_MIN_DIST && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	        	 Log.d("pkh tile", "tile swipe left to right");
	        	 ImageShifter(SwipeRight);
	        	 MOVE_COUNT=MOVE_COUNT+1;
		        	 if(Board==3){
		 	   			Puzzle_3X3.UI_update();
		 	   			
		 	   		}
		 	   		if(Board==4){
		 	   			Puzzle_4x4.UI_update();
		 	   		}
	        	 return false; // Left to right
	         }
			 
			 

	         if(e1.getY() - e2.getY() > SWIPE_MIN_DIST && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	            
	        	 Log.d("pkh tile", "tile swipe bottom to top");
	        	 ImageShifter(SwipeTop);
	        	 MOVE_COUNT=MOVE_COUNT+1;
		        	 if(Board==3){
		 	   			Puzzle_3X3.UI_update();
		 	   			
		 	   		}
		 	   		if(Board==4){
		 	   			Puzzle_4x4.UI_update();
		 	   		}
	        	 return false; // Bottom to top
	         } 
	         
	         else if (e2.getY() - e1.getY() > SWIPE_MIN_DIST && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
	             
	        	 Log.d("pkh tile", "tile swipe top to bottom");
	        	 ImageShifter(SwipeBottom);
	        	 MOVE_COUNT=MOVE_COUNT+1;
		        	 if(Board==3){
		 	   			Puzzle_3X3.UI_update();
		 	   			
		 	   		}
		 	   		if(Board==4){
		 	   		Puzzle_4x4.UI_update();
		 	   		}
	        	 return false; // Top to bottom
	         }
	        
	         return false;
     }
	
	private void tick_sound() {
		// TODO Auto-generated method stub
		if(AnimProjt.isMuted){
			
		}
		else{
			
			ticksound.start();
		
		}
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
					return false;
				}
				item=item+1;
			}
		}
		return true;
	}
	
	/**
	 * @param SwipeTo  Swipe from [left-right, right-left,top-bottom,bottom-top ]
	 */
	private void ImageShifter(int SwipeTo) {
		// TODO Auto-generated method stub
		//temp=new View[1][1];
		
		
		if(SwipeTo==SwipeLeft){
			
			try{
				Log.d("pkh tile", "tile swipe else performing x= "+Xpos+"  y"+Ypos);
				tick_sound();
				
				tmpBitmap[0][0]=bitmap[Xpos][Ypos];
				tempcustomlist[0][0]=this.mycustomList[Xpos][Ypos];
				
				bitmap[Xpos][Ypos]=bitmap[Xpos][Ypos-1];
				bitmap[Xpos][Ypos-1]=tmpBitmap[0][0];
				
				tileMatrix[Xpos][Ypos].setImageBitmap(bitmap[Xpos][Ypos]);
				tileMatrix[Xpos][Ypos-1].setImageBitmap(bitmap[Xpos][Ypos-1]);
				
				this.mycustomList[Xpos][Ypos]=mycustomList[Xpos][Ypos-1];
				this.mycustomList[Xpos][Ypos-1]=tempcustomlist[0][0];
				
				if(isGridOrdered()){
					Log.d("pkh tile", "tile swipe Ordered Your are the winner");
					popUp();// for Winner Dialog
				}
				return;
			}
			catch(Exception e){
				Log.d("pkh tile", "tile swipe left catch");
			}
			
		}
		if(SwipeTo==SwipeRight){
			/*if(Ypos==Board-1){
				//do logic for not swiping left to right
				return;
			}*/	
			try{
				Log.d("pkh tile", "tile swipe else performing x= "+Xpos+"  y"+Ypos);
				tick_sound();
				/*temp[0][0]=tileMatrix[Xpos][Ypos];
				tileMatrix[Xpos][Ypos]=tileMatrix[Xpos][Ypos-1];
				tileMatrix[Xpos][Ypos-1]=temp[0][0];*/
				tmpBitmap[0][0]=bitmap[Xpos][Ypos];
				tempcustomlist[0][0]=mycustomList[Xpos][Ypos];
				
				bitmap[Xpos][Ypos]=bitmap[Xpos][Ypos+1];
				bitmap[Xpos][Ypos+1]=tmpBitmap[0][0];
				
				tileMatrix[Xpos][Ypos].setImageBitmap(bitmap[Xpos][Ypos]);
				tileMatrix[Xpos][Ypos+1].setImageBitmap(bitmap[Xpos][Ypos+1]);
				
				mycustomList[Xpos][Ypos]=mycustomList[Xpos][Ypos+1];
				mycustomList[Xpos][Ypos+1]=tempcustomlist[0][0];
				
				if(isGridOrdered()){
					Log.d("pkh tile", "tile swipe Ordered Your are the winner");
					popUp();// for Winner Dialog
				}
				
				return;
			}
			catch(Exception e){
				Log.d("pkh tile", "tile swipe right catch");
			}
			
		}
		if(SwipeTo==SwipeTop){
			/*if(Xpos==0){
				//do logic for not swiping bottom to top
				return;
			}*/
			try{
				Log.d("pkh tile", "tile swipe else performing x= "+Xpos+"  y"+Ypos);
				tick_sound();
				/*temp[0][0]=tileMatrix[Xpos][Ypos];
				tileMatrix[Xpos][Ypos]=tileMatrix[Xpos][Ypos-1];
				tileMatrix[Xpos][Ypos-1]=temp[0][0];*/
				tmpBitmap[0][0]=bitmap[Xpos][Ypos];
				tempcustomlist[0][0]=mycustomList[Xpos][Ypos];
				
				bitmap[Xpos][Ypos]=bitmap[Xpos-1][Ypos];
				bitmap[Xpos-1][Ypos]=tmpBitmap[0][0];
				
				tileMatrix[Xpos][Ypos].setImageBitmap(bitmap[Xpos][Ypos]);
				tileMatrix[Xpos-1][Ypos].setImageBitmap(bitmap[Xpos-1][Ypos]);
				
				mycustomList[Xpos][Ypos]=mycustomList[Xpos-1][Ypos];
				mycustomList[Xpos-1][Ypos]=tempcustomlist[0][0];
				
				if(isGridOrdered()){
					Log.d("pkh tile", "tile swipe Ordered Your are the winner");
					popUp();// for Winner Dialog
				}
				return;
			}
			catch(Exception e){
				Log.d("pkh tile", "tile swipe top catch");
			}
		}
		if(SwipeTo==SwipeBottom){
			/*if(Xpos==Board-1){
				//do logic for not swiping top to bottom
				return;
			}*/
			try{
				Log.d("pkh tile", "tile swipe else performing x= "+Xpos+"  y"+Ypos);
				tick_sound();
				/*temp[0][0]=tileMatrix[Xpos][Ypos];
				tileMatrix[Xpos][Ypos]=tileMatrix[Xpos][Ypos-1];
				tileMatrix[Xpos][Ypos-1]=temp[0][0];*/
				tmpBitmap[0][0]=bitmap[Xpos][Ypos];
				tempcustomlist[0][0]=mycustomList[Xpos][Ypos];
				
				bitmap[Xpos][Ypos]=bitmap[Xpos+1][Ypos];
				bitmap[Xpos+1][Ypos]=tmpBitmap[0][0];
				
				tileMatrix[Xpos][Ypos].setImageBitmap(bitmap[Xpos][Ypos]);
				tileMatrix[Xpos+1][Ypos].setImageBitmap(bitmap[Xpos+1][Ypos]);
				
				mycustomList[Xpos][Ypos]=mycustomList[Xpos+1][Ypos];
				mycustomList[Xpos+1][Ypos]=tempcustomlist[0][0];
				
				if(isGridOrdered()){
					Log.d("pkh tile", "tile swipe Ordered Your are the winner");
					popUp();// for Winner Dialog
				}
				return;
			}
			catch(Exception e){
				Log.d("pkh tile", "tile swipe bottom catch");
			}
		}
		
	}
	
	private void popUp() {
		// TODO Auto-generated method stub
		Log.d("pkh tile", "tile swipe alert="+context);
		
		/*Dialog dialog=new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.succes_custom_dialog_layout);
		dialog.show();*/
		
		if(Board==3){
			Puzzle_3X3.levelOpener();
		}
		if(Board==4){
			Puzzle_4x4.levelOpener();
		}

	}
	

}
