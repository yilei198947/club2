package com.iscard.club.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.iscard.club.R;

public class PersonInfoActivty extends FragmentActivity {
	
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;
	private RadioGroup rg;
	private RadioButton perInfoRB, carInfoRB;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_person_info);
		initView();
		fragmentManager = getSupportFragmentManager();
		rg = (RadioGroup) findViewById(R.id.rg_per_info);
		((RadioButton)rg.findViewById(R.id.rb_per_info)).setChecked(true);
		
		transaction = fragmentManager.beginTransaction();
		Fragment fragment = new PersonInfoFragment();
		transaction.replace(R.id.activity_perinfo_content, fragment);
		transaction.commit();
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_per_info:
					transaction = fragmentManager.beginTransaction();
					Fragment perFragment = new PersonInfoFragment();
					transaction.replace(R.id.activity_perinfo_content, perFragment);
					transaction.commit();
					perInfoRB.setBackgroundResource(R.color.green);
					carInfoRB.setBackgroundResource(R.color.white);
					break;

				case R.id.rb_car_info:
					transaction = fragmentManager.beginTransaction();
					Fragment carFragment = new CarsInfoFragment();
					transaction.replace(R.id.activity_perinfo_content, carFragment);
					transaction.commit();
					perInfoRB.setBackgroundResource(R.color.white);
					carInfoRB.setBackgroundResource(R.color.green);
					break;
				}
			}
		});
	}
	
	private void initView() {
		perInfoRB = (RadioButton) findViewById(R.id.rb_per_info);
		carInfoRB = (RadioButton) findViewById(R.id.rb_car_info);
	}

}
