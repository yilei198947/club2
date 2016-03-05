package com.iscard.club.ui;

import com.iscard.club.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MallFragment extends Fragment implements OnClickListener {
	View view = null;
	private TextView searchTV;
	private ImageView serviceIV;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_mall, null);
		initView();
		return view;
	}
	
	private void initView() {
		searchTV = (TextView) view.findViewById(R.id.searchTV);
		serviceIV = (ImageView) view.findViewById(R.id.service_iv);
		
		searchTV.setOnClickListener(this);
		serviceIV.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.searchTV:
			
			break;

		case R.id.service_iv:
			Intent intent = new Intent(getActivity(), GoodsSortActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

}
