package com.helperclasses.pkh;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.CustomizedClasses.pkh.CustomizeArrayList;

public class SpriteSheet {
	Bitmap myBitmap,child1;
	Resources res;
	int image;
	int col_size;
	int row_size;
	int index_val=0;
	ArrayList<Bitmap> mylistimage=new ArrayList<Bitmap>();
	ArrayList<CustomizeArrayList> mycustomlist;

	public SpriteSheet(int resource_id,int column_count, int row_count,Resources resources) {
		// TODO Auto-generated constructor stub
		this.res=resources;
		image=resource_id;
		col_size=column_count;
		row_size=row_count;
		
		myBitmap=BitmapFactory.decodeResource(res, image);
		
		crop_Bitmap(row_size, col_size, myBitmap);
		
	}
	public SpriteSheet(Bitmap image,int column_count,int row_count,Resources resources) {
		// TODO Auto-generated constructor stub
		this.res=resources;
		myBitmap=image;
		col_size=column_count;
		row_size=row_count;
		
		crop_Bitmap(row_size, col_size, myBitmap);
	}
	
	private void crop_Bitmap(int row_size,int column_size,Bitmap myBitmap) {
		// TODO Auto-generated method stub
	 	mycustomlist=new ArrayList<CustomizeArrayList>();
	 	CustomizeArrayList mylistitem = null;
		
		
        int x=0;int y=0;int imageid=0;
        for(int j=myBitmap.getHeight()/row_size;j<=myBitmap.getHeight();j=j+myBitmap.getHeight()/row_size){
       	 
       	
       	 for(int i=myBitmap.getWidth()/column_size;i<=myBitmap.getWidth();i=i+myBitmap.getWidth()/column_size){
       		 //new Child bitmap to crop and save from the real image
       		 child1=Bitmap.createBitmap(myBitmap, x, y, myBitmap.getWidth()/column_size	, myBitmap.getHeight()/row_size) ;
       		 
       		 x=x+myBitmap.getWidth()/column_size;	// for column pos of x
       		  
       		mylistimage.add(child1);	// after croping image added to listview;
       		
       	//*******Custom Array list process**************************************8
       		mylistitem=new CustomizeArrayList();
       		
       			
       			mylistitem.setBitmap(child1);
       			mylistitem.setImageID(imageid);
       			imageid=imageid+1;
       			Log.d("pkh sheet", "sheet image id ="+imageid);
       		
       		mycustomlist.add(mylistitem);
       
       	//************************************************************************
       	
       		
       	 } 
       	   
       	 y=y+myBitmap.getHeight()/row_size;		//for column pos of Y
       	 x=0;
        }

	}
	public ArrayList<CustomizeArrayList> getCustomAraylist(){
		return mycustomlist;
	}
	
	public Bitmap getTile(int tile_id) {
		// TODO Auto-generated method stub
		
		return mylistimage.get(tile_id);
	}
	
	/**
	 * @return Total size of the image
	 */
	public int getTile_size() {
		// TODO Auto-generated method stub
		return mylistimage.size();
	}
	
	/**
	 * @param tile_id
	 * @return tile id starts with "ZERO"
	 */
	public int getTile_index(int tile_id) { // 
		// TODO Auto-generated method stub
		
		int tile_index=mylistimage.indexOf(mylistimage.get(tile_id));
		Log.d("pkh tile", "tile index ="+tile_index);
		return tile_index;
	}

}
