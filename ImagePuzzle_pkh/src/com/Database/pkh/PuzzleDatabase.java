package com.Database.pkh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;



public class PuzzleDatabase {
	SQLiteDatabase sdb;
	MyHelperClass mdbh;
	Context mycon;
	public static final String DB_NAME="Puzzle";
	public static final String STATUS_TABLE="Status";
	public static final String STAR_COUNT_TABLE="StarCount";
	
	public static final String level1="level1",level2="level2",level3="level3",level4="level4",level5="level5",level6="level6",
			level7="level7",level8="level8",level9="level9",level10="level10",level11="level11",level12="level12",
					level13="level13",level14="level14",level15="level15",level16="level16";
	public static final String starCount="starcount";
	
	class MyHelperClass extends SQLiteOpenHelper
	{

		public MyHelperClass(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("create table if not exists Status(id INTEGER,level1 INTEGER,level2 INTEGER,level3 INTEGER,level4 INTEGER,level5 INTEGER,level6 INTEGER,level7 INTEGER,level8 INTEGER,level9 INTEGER,level10 INTEGER,level11 INTEGER,level12 INTEGER,level13 INTEGER,level14 INTEGER,level15 INTEGER,level16 INTEGER);");
			db.execSQL("create table if not exists StarCount(id INTEGER,starcount INTEGER);");
			
			//inserting default while creating database
			db.execSQL("INSERT INTO Status(id,level1,level2,level3,level4, level5,level6,level7,level8, level9,level10,level11,level12, level13,level14,level15,level16) "+" Values (1,1,0,0,0 ,0,0,0,0, 0,0,0,0, 0,0,0,0);");
			db.execSQL("INSERT INTO StarCount(id,starcount)"+"Values (1,0);");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void openDB() {
		// TODO Auto-generated method stub
		sdb=mdbh.getWritableDatabase();
	}
	public long updateStatusdata(ContentValues cv) {
		// TODO Auto-generated method stub
		return sdb.update(STATUS_TABLE, cv, "id=1",null);
	}
	public long updateStarCount(ContentValues cv) {
		// TODO Auto-generated method stub
		return sdb.update(STAR_COUNT_TABLE, cv, "id=1",null);
	}
	public Cursor getStatusdata() {
		// TODO Auto-generated method stub
		return sdb.query(STATUS_TABLE, null, null, null, null, null, null);
	}
	public Cursor getStarcount() {
		// TODO Auto-generated method stub
		return sdb.query(STAR_COUNT_TABLE, null, null, null, null, null, null);
	}
	public void closeDB() {
		// TODO Auto-generated method stub
		sdb.close();
	}
	public PuzzleDatabase(Context con) {
		// TODO Auto-generated constructor stub
		mdbh=new MyHelperClass(con, DB_NAME, null,1);
	}

}
