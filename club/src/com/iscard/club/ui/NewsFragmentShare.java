package com.iscard.club.ui;

import com.iscard.club.R;
import com.iscard.club.adapter.NewsNewsAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NewsFragmentShare extends Fragment {
	
	View shareFragmentNews;
	private ListView shareListView;
	NewsNewsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		shareFragmentNews = inflater.inflate(R.layout.news_fragment_share, null);
		initView();
		return shareFragmentNews;
	}
	
	private void initView() {
		shareListView = (ListView) shareFragmentNews.findViewById(R.id.shareListView);
		adapter = new NewsNewsAdapter(getActivity());
		shareListView.setAdapter(adapter);
	}

}
