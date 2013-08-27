package com.pkh.tuxpuz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ImagePuzzle extends Activity {

	RadioGroup myRadioGroup;
	TextView myTextView;
	Button goButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.image_puzzle);
        
        myRadioGroup	=(RadioGroup) findViewById(R.id.myRadioGroup);
        myTextView		=(TextView) findViewById(R.id.selected);
        goButton		=(Button) findViewById(R.id.go_btn);
        
        myRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==R.id.radio_btn_3x3){
					myTextView.setText("3x3 selected");
				}
				if(checkedId==R.id.radio_btn_4x4){
					myTextView.setText("4x4 selected");				
				}
				if(checkedId==R.id.radio_btn_5x5){
					myTextView.setText("5x5 selected");
				}
				if(checkedId==R.id.radio_btn_6x6){
					myTextView.setText("6x6 selected");
				}
			}
		});
        
        goButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int checkedid=myRadioGroup.getCheckedRadioButtonId();
				if(checkedid==R.id.radio_btn_3x3){
					Intent intent=new Intent(getApplicationContext(), Puzzle_3X3.class);
					startActivity(intent);
				}
				if(checkedid==R.id.radio_btn_4x4){
					myTextView.setText("4x4 selected btn");		
					Intent intent=new Intent(getApplicationContext(), Puzzle_4x4.class);
					startActivity(intent);
				}
				if(checkedid==R.id.radio_btn_5x5){
					myTextView.setText("5x5 selected btn");
					Intent intent=new Intent(getApplicationContext(), Puzzle_3x3_medium.class);
					startActivity(intent);
				}
				if(checkedid==R.id.radio_btn_6x6){
					myTextView.setText("6x6 selected btn");
					//Intent intent=new Intent(getApplicationContext(),Puzzle_4x4_medium.class);
					Intent intent=new Intent(getApplicationContext(),GeekSelector.class);
					startActivity(intent);
				}
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_puzzle, menu);
        return true;
    }
}
