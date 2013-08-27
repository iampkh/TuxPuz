package com.fragment.pkh;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.korovyansk.android.slideout.SlideoutHelper;
import com.pkh.tuxpuz.R;


public class Frag_example extends FragmentActivity {
	Button viewhelp,viewExample,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,nxt_btn;
	
	HorizontalScrollView hscrollview;
	
	LinearLayout viewTable;
	boolean scrollview_visiblity=false;
	boolean tableview_visibitlity=false;
	int a[];
	int loopnumber=0;
	SlideoutHelper  mSlideoutHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frag_example);
		
		hscrollview=(HorizontalScrollView) findViewById(R.id.howtoplay_scrollview);
		viewTable=(LinearLayout) findViewById(R.id.table_viewEg);
		
		viewhelp=(Button) findViewById(R.id.how_to_play_btn);
		viewExample=(Button) findViewById(R.id.viewExamplebtn);
		
		nxt_btn=(Button) findViewById(R.id.nextView);
		
		
		
		hscrollview.setVisibility(ViewGroup.GONE);
		viewTable.setVisibility(ViewGroup.GONE);
		
		SlideoutHelper  mSlideoutHelper = new SlideoutHelper(this);
		    mSlideoutHelper.activate();
		    getSupportFragmentManager().beginTransaction().add(com.korovyansk.android.slideout.R.id.slideout_placeholder, new MenuFragment(), "menu").commit();
		  
		  //  getSupportFragmentManager().beginTransaction().add(com.korovyansk.android.slideout.R.id.slideout_placeholder, new MenuFragment());
		    mSlideoutHelper.open();
		
	
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Log.d("pkh tile", "Backpressed Fragment");
		finish();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d("pkh tile", "Destroying Fragment");
	}
	
	


	

}
