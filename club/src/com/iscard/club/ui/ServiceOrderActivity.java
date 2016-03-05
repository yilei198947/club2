package com.iscard.club.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iscard.club.BaseActivity;
import com.iscard.club.R;
import com.iscard.club.bean.RequireGoodsBean;
import com.iscard.club.view.AddAndSubView;

public class ServiceOrderActivity extends BaseActivity implements OnClickListener{
	
	private CheckBox cashBox, integrationBox;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_order);
		initView();
		ArrayList<RequireGoodsBean> list = new ArrayList<RequireGoodsBean>();
		RequireGoodsBean bean = new RequireGoodsBean();
		RequireGoodsBean bean2 = new RequireGoodsBean();
		bean.setGoods_name("»úÂË");
		bean.setGoods_num(6);
		bean2.setGoods_name("·þÎñ");
		bean2.setGoods_num(3);
		list.add(bean);
		list.add(bean2);
		LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearlayout_num);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		for(RequireGoodsBean bean3:list) {
			LinearLayout view = (LinearLayout) LayoutInflater.from(ServiceOrderActivity.this).inflate(R.layout.item_require_goods, null);
			TextView name = (TextView) view.findViewById(R.id.require_name_tv);
			name.setText(bean3.getGoods_name());
			LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.num_linearlayout);
			AddAndSubView andSubView = new AddAndSubView(ServiceOrderActivity.this, bean3.getGoods_num());
			linearLayout.addView(andSubView);
			linearLayout1.addView(view, params);
		}
		cashBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg1) {
					integrationBox.setChecked(false);
					cashBox.setClickable(false);
				} else {
					integrationBox.setChecked(true);
					cashBox.setClickable(true);
				}
			}
		});
		
		integrationBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				// TODO Auto-generated method stub
				if(arg1) {
					cashBox.setChecked(false);
					integrationBox.setClickable(false);
				} else {
					cashBox.setChecked(true);
					integrationBox.setClickable(true);
				}
			}
		});
	}
	
	private void initView() {
		cashBox = (CheckBox) findViewById(R.id.cashBox);
		integrationBox = (CheckBox) findViewById(R.id.integrationBox);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.cashBox:
			
			break;

		default:
			break;
		}
	}

}
