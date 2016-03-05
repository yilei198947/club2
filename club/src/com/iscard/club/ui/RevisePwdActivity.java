package com.iscard.club.ui;

import android.os.Bundle;

import com.iscard.club.BaseActivity;
import com.iscard.club.R;

public class RevisePwdActivity extends BaseActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_revise_pwd);
		
		actionBar.setIcon(R.drawable.ic_app_home_back);
	}
	
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			this.finish();
//			return true;
//		default:
//			return super.onOptionsItemSelected(item);
//		}
//	}

}
