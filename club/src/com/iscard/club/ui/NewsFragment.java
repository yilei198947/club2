package com.iscard.club.ui;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.iscard.club.R;
import com.iscard.club.adapter.MyFragmentPagerAdapter;
import com.iscard.club.adapter.NoScrollViewPager;

/**
 * ÐÂÎÅ
 * 
 * @author YiLei
 * 
 * */
public class NewsFragment extends Fragment {
	
	Resources resources;
	private ImageView ivBottomLine;
	private int bottomLineWidth;
	private int offset = 0;
	private int position_one;
	private int position_two;
	public final static int num = 3;
	private int currIndex = 0;
	private TextView tvTabNews, tvTabActivity, tvTabShare;
	private NoScrollViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	Fragment newsFragmentNews, newsFragmentActivity, newsFragmentShare;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_news, null);
		resources = getResources();
		InitWidth(view);
		InitTextView(view);
		InitViewPager(view);
		TranslateAnimation animation = new TranslateAnimation(offset, offset, 0, 0);
		tvTabNews.setTextColor(resources.getColor(R.color.blue));
        animation.setFillAfter(true);
        animation.setDuration(300);
        ivBottomLine.startAnimation(animation);
		return view;
	}
	
	private void InitTextView(View parentView) {
		tvTabNews = (TextView) parentView.findViewById(R.id.news_tab1_tv);
		tvTabActivity = (TextView) parentView.findViewById(R.id.news_tab2_tv);
		tvTabShare = (TextView) parentView.findViewById(R.id.news_tab3_tv);
		
		tvTabNews.setOnClickListener(new MyOnClickListener(0));
		tvTabActivity.setOnClickListener(new MyOnClickListener(1));
		tvTabShare.setOnClickListener(new MyOnClickListener(2));
	}
	
	private void InitViewPager(View parentView) {
		mPager = (NoScrollViewPager) parentView.findViewById(R.id.vPager);
		fragmentsList = new ArrayList<Fragment>();
		
		newsFragmentNews = new NewsFragmentNews();
		newsFragmentActivity = new NewsFragmentActivity();
		newsFragmentShare = new NewsFragmentShare();
		
		fragmentsList.add(newsFragmentNews);
		fragmentsList.add(newsFragmentActivity);
		fragmentsList.add(newsFragmentShare);
		
		mPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), fragmentsList));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0);
	}
	
	private void InitWidth(View parentView) {
		ivBottomLine = (ImageView) parentView.findViewById(R.id.iv_bottom_line);
		bottomLineWidth = ivBottomLine.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW = dm.widthPixels;
		offset = (int) ((screenW / num - bottomLineWidth) / 3);
		int avg = (int) (screenW / num);
		position_one = avg + offset;
		position_two = avg + avg + offset;
	}
	
	public class MyOnClickListener implements OnClickListener {
		
		private int index = 0;
		
		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
		
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		
		@Override
		public void onPageSelected(int position) {
			Animation animation = null;
			switch (position) {
			case 0:
				if(currIndex == 1){
					animation = new TranslateAnimation(position_one, offset, 0, 0);
					tvTabActivity.setTextColor(resources.getColor(R.color.black));
					tvTabShare.setTextColor(resources.getColor(R.color.black));
				}
				if(currIndex == 2){
					animation = new TranslateAnimation(position_two, offset, 0, 0);
					tvTabActivity.setTextColor(resources.getColor(R.color.black));
					tvTabShare.setTextColor(resources.getColor(R.color.black));
				}
				tvTabNews.setTextColor(resources.getColor(R.color.blue));
				break;

			case 1:	
				if(currIndex == 0){
					animation = new TranslateAnimation(offset, position_one, 0, 0);
					tvTabNews.setTextColor(resources.getColor(R.color.black));
					tvTabShare.setTextColor(resources.getColor(R.color.black));
				}
				if(currIndex == 2){
					animation = new TranslateAnimation(position_two, position_one, 0, 0);
					tvTabNews.setTextColor(resources.getColor(R.color.black));
					tvTabShare.setTextColor(resources.getColor(R.color.black));
				}
				tvTabActivity.setTextColor(resources.getColor(R.color.blue));
				break;
			case 2:
				if(currIndex == 0){
					animation = new TranslateAnimation(offset, position_two, 0, 0);
					tvTabNews.setTextColor(resources.getColor(R.color.black));
					tvTabActivity.setTextColor(resources.getColor(R.color.black));
				}
				if(currIndex == 1){
					animation = new TranslateAnimation(position_one, position_two, 0, 0);
					tvTabNews.setTextColor(resources.getColor(R.color.black));
					tvTabActivity.setTextColor(resources.getColor(R.color.black));
				}
				tvTabShare.setTextColor(resources.getColor(R.color.blue));
				break;
			}
			currIndex = position;
            animation.setFillAfter(true);
            animation.setDuration(300);
            ivBottomLine.startAnimation(animation);
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			
		}
	
	}

}
