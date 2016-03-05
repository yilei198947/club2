package com.iscard.club.ui;

import com.iscard.club.MainActivity;
import com.iscard.club.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class GoodsDetailActivity extends FragmentActivity implements OnClickListener{
	
	private FragmentManager fragmentManager;
	private FragmentTransaction transaction;
	private RadioGroup rg;
	private RadioButton goodsInfoRB, specsInfoRB;
	private TextView collTV;
	private Button buyBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goods_detail);
		initView();
		fragmentManager = getSupportFragmentManager();
		rg = (RadioGroup) findViewById(R.id.rg_goods_info);
		((RadioButton)rg.findViewById(R.id.rb_goods_info)).setChecked(true);
		
		transaction = fragmentManager.beginTransaction();
		Fragment fragment = new GoodsDetailFragment();
		transaction.replace(R.id.activity_goodsinfo_content, fragment);
		transaction.commit();
		
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rb_goods_info:
					transaction = fragmentManager.beginTransaction();
					Fragment goodsDetailFragment = new GoodsDetailFragment();
					transaction.replace(R.id.activity_goodsinfo_content, goodsDetailFragment);
					transaction.commit();
					goodsInfoRB.setBackgroundResource(R.color.green);
					specsInfoRB.setBackgroundResource(R.color.white);
					break;

				case R.id.rb_specs_info:
					transaction = fragmentManager.beginTransaction();
					Fragment specsFragment = new GoodsSpecsFragment();
					transaction.replace(R.id.activity_goodsinfo_content, specsFragment);
					transaction.commit();
					goodsInfoRB.setBackgroundResource(R.color.white);
					specsInfoRB.setBackgroundResource(R.color.green);
					break;
				}
			}
		});
	}
	
	private void initView() {
		goodsInfoRB = (RadioButton) findViewById(R.id.rb_goods_info);
		specsInfoRB = (RadioButton) findViewById(R.id.rb_specs_info);
		collTV = (TextView) findViewById(R.id.collTV);
		buyBtn = (Button) findViewById(R.id.buyBtn);
		
		collTV.setOnClickListener(this);
		buyBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buyBtn:
			Intent intent = new Intent(getApplicationContext(), ServiceOrderActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(getApplicationContext(), MallFragment.class);
			startActivity(intent);
			this.finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
			intent.putExtra("fragid", 3);
			startActivity(intent);
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
