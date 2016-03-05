package com.iscard.club;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.iscard.club.ui.HomeFragment;
import com.iscard.club.ui.MallFragment;
import com.iscard.club.ui.NewsFragment;
import com.iscard.club.ui.PersonalCenterFragment;

/**
 * 项目主界面，包含四个fragment
 * 
 * @author YiLei
 * 
 * */
public class MainActivity extends FragmentActivity {
	
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;
	private RadioGroup rg;
	@SuppressWarnings("unused")
    private RadioButton rbHome, rbNews, rbMall, rbPersonalCenter;
	Fragment homeFragment, newsFragment, mallFragment, personalCenterFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		initView();
		
		fragmentManager = getSupportFragmentManager();
		rg = (RadioGroup) findViewById(R.id.rg_main);
		((RadioButton)rg.findViewById(R.id.home_rb)).setChecked(true);
		
		transaction = fragmentManager.beginTransaction();
		Fragment fragment = new HomeFragment();
		transaction.replace(R.id.activity_main_content, fragment);
		transaction.commit();
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.home_rb:
					transaction = fragmentManager.beginTransaction();
					homeFragment = new HomeFragment();
					transaction.replace(R.id.activity_main_content, homeFragment);
					transaction.commit();
					break;

				case R.id.news_rb:
					transaction = fragmentManager.beginTransaction();
					newsFragment = new NewsFragment();
					transaction.replace(R.id.activity_main_content, newsFragment);
					transaction.commit();
					break;
					
				case R.id.mall_rb:
					transaction = fragmentManager.beginTransaction();
					mallFragment = new MallFragment();
					transaction.replace(R.id.activity_main_content, mallFragment);
					transaction.commit();
					break;
					
				case R.id.personal_center_rb:
					transaction = fragmentManager.beginTransaction();
					personalCenterFragment = new PersonalCenterFragment();
					transaction.replace(R.id.activity_main_content, personalCenterFragment);
					transaction.commit();
					break;
				}
			}
		});
	}
	
	@Override
    protected void onResume() {
        super.onResume();
        int id = getIntent().getIntExtra("fragid", 0);
        if(id == 3) {
            transaction = fragmentManager.beginTransaction();
            mallFragment = new MallFragment();
            transaction.replace(R.id.activity_main_content, mallFragment);
            transaction.commit();
            rbMall.setChecked(true);
        }
    }
	
	private void initView() {
		rbMall = (RadioButton) findViewById(R.id.mall_rb);
	}
	
}
