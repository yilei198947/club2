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
import android.widget.LinearLayout;

public class PersonalCenterFragment extends Fragment implements OnClickListener{
	View view = null;
	private ImageView personalIMG;
	private LinearLayout perInfoLayout, revisePwdLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_personal_center, null);
		initView();
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
	}
	
	private void initView() {
		personalIMG = (ImageView) view.findViewById(R.id.personal_center_img);
		perInfoLayout = (LinearLayout) view.findViewById(R.id.per_info_layout);
		revisePwdLayout = (LinearLayout) view.findViewById(R.id.revise_pwd_layout);
		
		personalIMG.setOnClickListener(this);
		perInfoLayout.setOnClickListener(this);
		revisePwdLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.personal_center_img:
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			break;

		case R.id.per_info_layout:
			Intent intent1 = new Intent(getActivity(), PersonInfoActivty.class);
			startActivity(intent1);
			break;
			
		case R.id.revise_pwd_layout:
			Intent intent2 = new Intent(getActivity(), RevisePwdActivity.class);
			startActivity(intent2);
			break;
		default:
			break;
		}
	}

}
