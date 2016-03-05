package com.iscard.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.iscard.club.BaseActivity;
import com.iscard.club.R;
import com.iscard.club.adapter.GoodsSortAdapter;

public class GoodsSortActivity extends BaseActivity {
	
	private GridView gridView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goods_sort);
		initView();
		GoodsSortAdapter adapter = new GoodsSortAdapter(getApplicationContext());
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
				startActivity(intent);
				finish();
			}
		});
//		setListAdapter(adapter);
	}
	
	private void initView() {
		gridView = (GridView) findViewById(R.id.gridView);
	}
	
	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(getApplicationContext(), GoodsDetailActivity.class);
		startActivity(intent);
	}*/

}
