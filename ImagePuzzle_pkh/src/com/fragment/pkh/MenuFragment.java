package com.fragment.pkh;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.korovyansk.android.slideout.SlideoutHelper;
import com.pkh.tuxpuz.R;

public class MenuFragment extends android.support.v4.app.Fragment implements OnClickListener{


Button howtoplay_btn,viewExample,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,nxt_btn;
	
	HorizontalScrollView hscrollview;
	TextView explview;
	
	public static int setimage_ID=0;
	
	ImageView display_image;
	LinearLayout viewTable;
	boolean scrollview_visiblity=false;
	boolean tableview_visibitlity=false;
	int a[];
	int loopnumber=0;
	SlideoutHelper  mSlideoutHelper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		View view=inflater.inflate(R.layout.activity_frag_example, null);
		hscrollview=(HorizontalScrollView)view. findViewById(R.id.howtoplay_scrollview);
		viewTable=(LinearLayout) view.findViewById(R.id.table_viewEg);
		hscrollview.setVisibility(ViewGroup.GONE);
		viewTable.setVisibility(ViewGroup.GONE);
		
		display_image=(ImageView) view.findViewById(R.id.disp_image);
		display_image.setImageResource(setimage_ID);
		howtoplay_btn=(Button)view. findViewById(R.id.how_to_play_btn);
		viewExample=(Button)view. findViewById(R.id.viewExamplebtn);
		
		explview=(TextView) view.findViewById(R.id.expltextview);
		explview.setText(Html.fromHtml("1.Try to bring the number '1' <br/>to its position"));
		
		
		
		nxt_btn=(Button)view. findViewById(R.id.nextView);
		
		btn1=(Button)view. findViewById(R.id.layer1);
		btn2=(Button)view. findViewById(R.id.layer2);
		btn3=(Button)view. findViewById(R.id.layer3);
	
		btn4=(Button) view.findViewById(R.id.layer4);
		btn5=(Button)view. findViewById(R.id.layer5);
		btn6=(Button) view.findViewById(R.id.layer6);
		
		btn7=(Button) view.findViewById(R.id.layer7);
		btn8=(Button)view. findViewById(R.id.layer8);
		btn9=(Button)view. findViewById(R.id.layer9);
		
		howtoplay_btn.setOnClickListener(this);
		viewExample.setOnClickListener(this);
		
		nxt_btn.setOnClickListener(this);
		return view;
		
	}
	
	private void example_loop() {
		// TODO Auto-generated method stub
		if(a[0]==0){
			btn1.setText("");
		}else			
			{btn1.setText(""+a[0]);
			
			}
		
		if(a[1]==0){
			btn2.setText("");
		}else			
			{	
		btn2.setText(""+a[1]);
			}
		
		if(a[2]==0){
			btn3.setText("");
		}else			
			{
		btn3.setText(""+a[2]);
			}
		
		if(a[3]==0){
			btn4.setText("");
		}else			
			{
		btn4.setText(""+a[3]);
			}
		
		if(a[4]==0){
			btn5.setText("");
		}else			
			{
		btn5.setText(""+a[4]);
			}
		
		if(a[5]==0){
			btn6.setText("");
		}else			
			{
		btn6.setText(""+a[5]);
			}
		
		if(a[6]==0){
			btn7.setText("");
		}else			
			{
		btn7.setText(""+a[6]);
			}
		
		if(a[7]==0){
			btn8.setText("");
		}else			
			{
		btn8.setText(""+a[7]);
			}
		
		if(a[8]==0){
			btn9.setText("");
		}else			
			{
		btn9.setText(""+a[8]);
			}

	}
	
	

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int i=v.getId();
		switch (i) {
		case R.id.how_to_play_btn:
			if(!scrollview_visiblity){
				hscrollview.setVisibility(ViewGroup.VISIBLE);
				scrollview_visiblity=true;
			}
			else{
				hscrollview.setVisibility(ViewGroup.GONE);
				scrollview_visiblity=false;
				
			}
			
			break;
		case R.id.viewExamplebtn:
			if(!tableview_visibitlity){
				viewTable.setVisibility(ViewGroup.VISIBLE);
				tableview_visibitlity=true;
			}
			else{
				viewTable.setVisibility(ViewGroup.GONE);
				tableview_visibitlity=false;
			}
			break;
		case R.id.nextView:
			
							switch (loopnumber) {
							case 0:
								a=new int[]{5,4,2,7,6,3,1,8,0};
								explview.setText(Html.fromHtml("1. Try to bring the  <br/>number '1' to its position"));
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 1:
								a=new int[]{5,4,2,7,6,3,1,0,8};
								example_loop();
								loopnumber=loopnumber+1;
								
								break;
							case 2:
								a=new int[]{5,4,2,7,0,3,1,6,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 3:
								a=new int[]{5,4,2,0,7,3,1,6,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 4:
								a=new int[]{5,4,2,1,7,3,0,6,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 5:
								a=new int[]{5,4,2,1,7,3,6,0,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 6:
								a=new int[]{5,4,2,1,0,3,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 7:
								a=new int[]{5,0,2,1,4,3,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 8:
								a=new int[]{0,5,2,1,4,3,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 9:
								a=new int[]{1,5,2,0,4,3,6,7,8};
								explview.setText(Html.fromHtml("2.Try to bring the number <br/> '3rd & 2nd'  as shown in <br/>3rd image of above example"));
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 10:
								a=new int[]{1,5,2,4,0,3,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 11:
								a=new int[]{1,5,2,4,3,0,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 12:
								a=new int[]{1,5,0,4,3,2,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 13:
								a=new int[]{1,0,5,4,3,2,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 14:
								a=new int[]{1,3,5,4,0,2,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 15:
								a=new int[]{1,3,5,4,2,0,6,7,8};
								explview.setText(Html.fromHtml("3. Now bring the number <br/>'3rd & 2nd'buttons to their <br/> positions as shown above"));
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 16:
								a=new int[]{1,3,0,4,2,5,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 17:
								a=new int[]{1,0,3,4,2,5,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 18:
								a=new int[]{1,2,3,4,0,5,6,7,8};
								explview.setText(Html.fromHtml("4. Try to bring the number <br/>'7th & 4th' as shown in 5th image<br/> of above example"));
								example_loop();
								loopnumber=loopnumber+1;
								break;
								
								
							case 19:
								a=new int[]{1,2,3,0,4,5,6,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
								
							case 20:
								a=new int[]{1,2,3,6,4,5,0,7,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 21:
								a=new int[]{1,2,3,6,4,5,7,0,8};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 22:
								a=new int[]{1,2,3,6,4,5,7,8,0};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 23:
								a=new int[]{1,2,3,6,4,0,7,8,5};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 24:
								a=new int[]{1,2,3,6,0,4,7,8,5};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 25:
								a=new int[]{1,2,3,6,8,4,7,0,5};
								example_loop();
								loopnumber=loopnumber+1;
								break;
								
								
							case 26:
								a=new int[]{1,2,3,6,8,4,0,7,5};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 27:
								a=new int[]{1,2,3,0,8,4,6,7,5};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 28:
									a=new int[]{1,2,3,8,0,4,6,7,5};
									example_loop();
									loopnumber=loopnumber+1;
									break;
									
									
								case 29:
									a=new int[]{1,2,3,8,7,4,6,0,5};
									example_loop();
									loopnumber=loopnumber+1;
									break;
									
								case 30:
									a=new int[]{1,2,3,8,7,4,0,6,5};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 31:
									a=new int[]{1,2,3,0,7,4,8,6,5};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 32:
									a=new int[]{1,2,3,7,0,4,8,6,5};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 33:
									a=new int[]{1,2,3,7,4,0,8,6,5};
									explview.setText(Html.fromHtml("5. Now bring the number <br/>'7th &4th' to their positions<br/>as shown above"));
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 34:
									a=new int[]{1,2,3,7,4,5,8,6,0};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 35:
									a=new int[]{1,2,3,7,4,5,8,0,6};
									example_loop();
									loopnumber=loopnumber+1;
									break;
									
									
								case 36:
									a=new int[]{1,2,3,7,4,5,0,8,6};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 37:
									a=new int[]{1,2,3,0,4,5,7,8,6};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 38:
									a=new int[]{1,2,3,4,0,5,7,8,6};
									explview.setText(Html.fromHtml("6. Now Move the remaining <br/>buttons to their positions"));
									example_loop();
									loopnumber=loopnumber+1;
									break;
								case 39:
									a=new int[]{1,2,3,4,5,0,7,8,6};
									example_loop();
									loopnumber=loopnumber+1;
									break;
								
								
							
							case 40:
								a=new int[]{1,2,3,4,5,6,7,8,0};
								example_loop();
								loopnumber=loopnumber+1;
								break;
							case 41:
								
								
								explview.setText("YOU COMPLETES THE GAME");
								loopnumber=0;
								break;
								
								
				
							default:
								break;
							}
			break;
			

		default:
			break;
		}
		
	}
}
