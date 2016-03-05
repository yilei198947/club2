package com.iscard.club.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iscard.club.R;
import com.iscard.club.adapter.NewsNewsAdapter;

public class NewsFragmentActivity extends Fragment {
	
	View activityFragmentNews;
	private ListView activityListView;
	NewsNewsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		activityFragmentNews = inflater.inflate(R.layout.news_fragment_activity, null);
		initView();
		return activityFragmentNews;
	}
	
	private void initView() {
		activityListView = (ListView) activityFragmentNews.findViewById(R.id.activityListView);
		adapter = new NewsNewsAdapter(getActivity());
		activityListView.setAdapter(adapter);
	}

}
