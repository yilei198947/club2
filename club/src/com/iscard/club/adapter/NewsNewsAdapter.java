package com.iscard.club.adapter;

import com.iscard.club.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsNewsAdapter extends BaseAdapter {
	
	private Context context;
	
	public NewsNewsAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.adapter_news_news, parent, false);
			holder = new ViewHolder();
			holder.newsImg = (ImageView) convertView.findViewById(R.id.newsImg);
			holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
			holder.txtContent = (TextView) convertView.findViewById(R.id.txtContent);
			holder.txtDate = (TextView) convertView.findViewById(R.id.txtDate);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.newsImg.setImageResource(R.drawable.ic_launcher);
		holder.txtTitle.setText("2015JEEP全地形");
		holder.txtContent.setText("一辆JEEP四驱车");
		holder.txtDate.setText("2015-05-20");
		return convertView;
	}
	
	class ViewHolder {
		private ImageView newsImg = null;
		private TextView txtTitle = null;
		private TextView txtContent = null;
		private TextView txtDate = null;
	}

}
