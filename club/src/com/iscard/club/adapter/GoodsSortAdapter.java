package com.iscard.club.adapter;

import com.iscard.club.R;
import com.iscard.club.adapter.NewsNewsAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsSortAdapter extends BaseAdapter {
	
	private Context context;
	
	public GoodsSortAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 20;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.goods_sort_item, parent, false);
			holder = new ViewHolder();
			holder.goodsIV = (ImageView) convertView.findViewById(R.id.goods_iv);
			holder.goodsName = (TextView) convertView.findViewById(R.id.goods_name);
			holder.priceTV = (TextView) convertView.findViewById(R.id.goods_price);
			holder.integralTV = (TextView) convertView.findViewById(R.id.goods_integral);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.goodsIV.setImageResource(R.drawable.goods);
		holder.goodsName.setText("双液晶智能人机交互系统");
		holder.priceTV.setText("$3999");
		holder.integralTV.setText("积分：4500");
		return convertView;
	}
	
	class ViewHolder {
		private ImageView goodsIV = null;
		private TextView goodsName = null;
		private TextView priceTV = null;
		private TextView integralTV = null;
	}

}
