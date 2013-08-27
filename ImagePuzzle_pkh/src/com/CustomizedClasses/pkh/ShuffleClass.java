package com.CustomizedClasses.pkh;

import java.util.Random;

import android.util.Log;

public class ShuffleClass {

	int myrandom[][];
	int shuffle[][];
	int board;
	
	int max;
	int min;
	int addvalue=0;
	Random rand=new Random();
	int randomNumx;
	int randomNumy;
	boolean ismovedleft=false,isthrowncatch_left=false;
	boolean ismovedright=false,isthrowncatch_right=false;
	boolean ismovedright_2=true;
	

	int posx=0;
	int posy=0;


	public ShuffleClass(int Board,int no_of_loop) {
		board=Board;
		max=2;
		min=0;
		
		 myrandom=new int[Board][Board];
		 shuffle=new int[Board][Board];

		posx=board-1;
		posy=board-1;

		// TODO Auto-generated constructor stub
		for(int i=0;i<board;i++){

			for(int j=0;j<board;j++){

				
				myrandom[i][j]=addvalue;
				Log.d("shuffle", "shuffle value=["+i+"]["+j+"]="+addvalue);
				if(addvalue==((board*board)-2)){
					addvalue=addvalue+2;
				}
				else{
					addvalue=addvalue+1;
				}
				


			}
		}

		shuffledarray(Board, no_of_loop);

	}
	
	public int[][] getShuffledArr() {
		// TODO Auto-generated method stub
		
		return shuffle;

	}
	
	private void moveleft() {
		
		 int temp[][]=new int[1][1];
		 temp[0][0]=myrandom[posx][posy];
		// TODO Auto-generated method stub
		 myrandom[posx][posy]=myrandom[posx][posy-1];
		 myrandom[posx][posy-1]=temp[0][0];

		 posy=posy-1;
		 ismovedleft=true;
	}
	private void moveright() {
		// TODO Auto-generated method stub
		int temps[][]=new int[1][1];
		temps[0][0]=myrandom[posx][posy];

		 myrandom[posx][posy]=myrandom[posx][posy+1];
		 myrandom[posx][posy+1]=temps[0][0];

		 posy=posy+1;
		ismovedright=true;

	}
	private void moveup() {
		// TODO Auto-generated method stub
		 int temp[][]=new int[1][1];
		 temp[0][0]=myrandom[posx][posy];
		myrandom[posx][posy]=myrandom[posx-1][posy];
		 myrandom[posx-1][posy]=temp[0][0];
		 posx=posx-1;
		 
		
		 

	}
	private void movedown() {
		// TODO Auto-generated method stub
		 int temp[][]=new int[1][1];
		 temp[0][0]=myrandom[posx][posy];
		myrandom[posx][posy]=myrandom[posx+1][posy];
		 myrandom[posx+1][posy]=temp[0][0];
		 posx=posx+1;
		 
		
	}
	
	private void moveleftdown() {
		// TODO Auto-generated method stub
		
			 if(!ismovedleft){
					moveleft();

				 }
			 else{
				 movedown();
				 ismovedleft=false;
			 }
			

	}
	private void moveleftup() {
		// TODO Auto-generated method stub
		
		 if(!ismovedleft){
				moveleft();

			 }
		 else{
			 moveup();
			 ismovedleft=false;
		 }
		
	}
	private void moverightup() {
		
			 if(!ismovedright){
					moveright();

				 }
			 else{
				 moveup();
				 ismovedright=false;
			 
			}
		// TODO Auto-generated method stub

	}
	private void moverightdown() {
		// TODO Auto-generated method stub
		
			 if(!ismovedright){
					moveright();

				 }
			 else{
				 movedown();
				 ismovedright=false;
			 
			}

	}

	private void shuffledarray(int n,int no_of_loop) {
		
		int count=no_of_loop;
		
		shuffle=myrandom;
		for(int i=0;i<board;i++){

			for(int j=0;j<board;j++){

		Log.d("shuffle", "shuffle random ["+i+"]=["+j+"] ="+shuffle[i][j]);
			}
		}
		Log.d("shuffle", "shuffle random =-------------------------------------------------");
		// TODO Auto-generated method stub
//		 randomNumx = rand.nextInt(posx) + posx-1;
		
				for(int k=0;k<=count;k++){
					
					try{
						moveleftup();
			
					}catch(Exception exception){
						try{
							moveright();
						}catch(Exception excep){
							try{
							moveleftdown();
							}catch(Exception ex){
								try{
								moveup();
								}catch(Exception e){
									try{
									moverightdown();
									}catch(Exception es){
										if(!ismovedright)
										     movedown();
										else moveright();
									}
								}
							}
						}
						
					}
					
						
					
					shuffle=myrandom;
					
					for(int i=0;i<board;i++){

						for(int j=0;j<board;j++){

					Log.d("shuffle", "shuffle random ["+i+"]=["+j+"] ="+shuffle[i][j]);
						}
					}
					Log.d("shuffle", "shuffle random =-------------------------------------------------");
				}
				

	}


}
