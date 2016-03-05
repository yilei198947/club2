package com.iscard.club.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.iscard.club.R;

public class HobbyDialog extends Dialog {

	public HobbyDialog(Context context) {
		super(context);
	}

	public HobbyDialog(Context context, int theme) {
		super(context, theme);
	}

	public static class Builder {

		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private View     contentView;
		private GridView listView;
		private DialogInterface.OnClickListener positiveButtonClickListener;
		private DialogInterface.OnClickListener negativeButtonClickListener;
		private OnItemClickListener onItemClickListener;

		private String[]  items;
		private boolean[] checkedItems;
		private int[]     imgs;
		
		
		private boolean   showSelectAll;
		private boolean   isMultiChoice = false;

		private int itemHeight;
		
		public Builder(Context context) {
			this.context = context;
		}

		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}

		public GridView getListView() {
			return listView;
		}

		public boolean[] getCheckedItems() {
			if(listView != null){
				CheckAdapter adapter = (CheckAdapter)listView.getAdapter();
				checkedItems = adapter.getCheckedItem();
			}
			return checkedItems;
		}

		/**
		 * Set the Dialog message from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		/**
		 * Set the Dialog title from resource
		 * 
		 * @param title
		 * @return
		 */
		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}

		/**
		 * Set the Dialog title from String
		 * 
		 * @param title
		 * @return
		 */

		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}

		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		/**
		 * Set the positive button resource and it's listener
		 * 
		 * @param positiveButtonText
		 * @return
		 */
		public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}

		public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}

		/**
		 * @param items
		 * @param checkedItems
		 * @param onItemClickListener
		 *            Listening the item click event.
		 * @param showSelectAll
		 *            Whether to display the full checkbox.
		 * @return
		 */
		public Builder setMultiChoiceItems(String[] items, boolean[] checkedItems,int[] imgs,
				OnItemClickListener onItemClickListener, boolean showSelectAll) {
			this.isMultiChoice = true;
			this.items = items;
			
			if(checkedItems !=null){
				this.checkedItems = checkedItems;
			}else{
				this.checkedItems = new boolean[items.length];
			}
			
			this.onItemClickListener = onItemClickListener;
			this.showSelectAll = showSelectAll;
			this.imgs = imgs;
			return this;
		}
	
		public HobbyDialog create() {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final HobbyDialog dialog = new HobbyDialog(context, R.style.Dialog);
			View layout = inflater.inflate(R.layout.dialog_multichoice_layout, null);

			// set the dialog title
			TextView multichoicTitle = (TextView) layout.findViewById(R.id.multichoic_title);
			multichoicTitle.setText(title);
			CheckBox checkAll = (CheckBox) layout.findViewById(R.id.chk_selectall);

			Button positiveButton = (Button) layout.findViewById(R.id.positiveButton);
			Button negativeButton = (Button) layout.findViewById(R.id.negativeButton);

			listView = (GridView) layout.findViewById(R.id.multichoiceList);
			
			dialog.addContentView(layout, new LayoutParams(LayoutParams.WRAP_CONTENT,
					LayoutParams.WRAP_CONTENT));

			// set the confirm button
			if (positiveButtonText != null) {
				positiveButton.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					positiveButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							positiveButtonClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
							dialog.dismiss();
						}
					});
				} else {
					positiveButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				positiveButton.setVisibility(View.GONE);
			}
			// set the cancel button
			if (negativeButtonText != null) {
				negativeButton.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					negativeButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
							dialog.dismiss();
						}
					});
				} else {
					negativeButton.setOnClickListener(new View.OnClickListener() {
						public void onClick(View v) {
							dialog.dismiss();
						}
					});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				negativeButton.setVisibility(View.GONE);
			}

			// is the Multichoice(多选框)
			if (isMultiChoice == true) {

				final CheckAdapter checkAdapter = new CheckAdapter(context, items, checkedItems);
				
				listView.setAdapter(checkAdapter);
				
              
				/////////////////////////////////////////////////
				//setHeight();
				
				
				//listView.setItemsCanFocus(true);
				//listView.setSelection(0);
				
				
				if (onItemClickListener != null) {
					listView.setOnItemClickListener(onItemClickListener);
				} else {
					listView.setOnItemClickListener(new OnMultiItemClick());
				}
				
				// show the all selectButton or not
				if (showSelectAll) {
					// checkAll.setVisibility( View.VISIBLE);
					checkAll.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
							int count = listView.getAdapter().getCount();
							if (isChecked) {
								// update the status of all checkbox to checked
								if (count > 0) {
									for (int i = 0; i < count; i++) {
										CheckBox itemCheckBox = (CheckBox) listView.getAdapter()
												.getView(i, null, null).findViewById(R.id.chk_selectone);
										itemCheckBox.setChecked(true);
									}
								}
							} else {
								// update the status of checkbox to unchecked
								if (count > 0) {
									for (int i = 0; i < count; i++) {
										CheckBox itemCheckBox = (CheckBox) listView.getAdapter()
												.getView(i, null, null).findViewById(R.id.chk_selectone);
										itemCheckBox.setChecked(false);
									}
								}
							}
						}
					});

				} else {
					checkAll.setVisibility(View.GONE);
				}
			} else {
				checkAll.setVisibility(View.GONE);
			}

			// set the content message
			if (message != null) {
				// ((TextView)layout.findViewById(R.id.message)).setText(message);
			} else if (contentView != null) {
				// if no message set add the contentView to the dialog body
				((LinearLayout) layout.findViewById(R.id.content)).removeAllViews();
				((LinearLayout) layout.findViewById(R.id.content)).addView(contentView, new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}

		
		
		/** 
	     * 设置高度 
	     */  
	    private void setHeight() {  
	        // 获取listView的适配器  
	        ListAdapter adapter = listView.getAdapter();  
	        // 获取视图的个数  
	        int count = adapter.getCount();  
	        // 总高度  
	        int totalHeight = 0;  
	        // 循环获取视图  
	        for (int i = 0; i < count; i++) {  
	            // 通过i获取每个视图  
	            View view = adapter.getView(i, null, listView);  
	            // 重新测量view的高度  
	            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);  
	            // 获取测量后的高度添加到总高度  
	            totalHeight += view.getMeasuredHeight();  
	        }  
	        // 总高度加上所有分割线的总高度  
	        totalHeight += 2 * (count - 1);  
	        // 获取listView的布局属性  
	        LayoutParams params = listView.getLayoutParams();  
	        // 设置高度  
	        params.height = totalHeight;  
	        // 重新设置布局属性  
	        listView.setLayoutParams(params);  
	    }  
	    
	    
	    
	    
		class OnMultiItemClick implements OnItemClickListener {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int positon, long arg3) {
				// TODO Auto-generated method stub
				CheckBox box = (CheckBox) view.findViewById(R.id.chk_selectone);
				if (box.isChecked()) {
					box.setChecked(false);
				} else {
					box.setChecked(true);
				}
			}
		}
		
		////////////////////////////////////////////////////////////
		class CheckAdapter extends BaseAdapter {

			private class ViewHolder {
//				public ImageView img;
				public TextView  name;
				public CheckBox  checkBox;
			}

			private String[] names;
			private LayoutInflater mInflater;
			private Context mContext;
			private boolean[] checkedItem;

			public CheckAdapter(Context context, String[] names, boolean[] checkedItem) {
				this.names = names;
				this.checkedItem = checkedItem;
				this.mContext = context;
				mInflater = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			}

			@Override
			public int getCount() {
				return names.length;
			}

			@Override
			public Object getItem(int position) {
				return names[position];
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			public View getItemView(int position) {
				return lmap.get(position);
			}

			public boolean[] getCheckedItem() {
				
				for (int i = 0; i < getCount(); i++) {
					View view = this.getView(i, null, null);
					CheckBox box = (CheckBox)view.findViewById(R.id.chk_selectone);
					checkedItem[i] = box.isChecked();
				}
				
				return checkedItem;
			}

			SparseArray<View> lmap = new SparseArray<View>();

			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				View view;
				if (lmap.get(position) == null) {
					view = mInflater.inflate(R.layout.dialog_multichoice_item, null);
					
					//itemHeight = WidgetController.getHeight(view);
					
					holder = new ViewHolder();
					
//					holder.img = (ImageView) view.findViewById(R.id.contact_img);
					holder.name = (TextView) view.findViewById(R.id.contact_name);
					holder.checkBox = (CheckBox) view.findViewById(R.id.chk_selectone);
					lmap.put(position, view);
					
					
					
					if (names != null && names.length > 0) {
						String name = (String) names[position];
						holder.name.setText(name);
						
						if(checkedItem != null ){
							holder.checkBox.setChecked(checkedItem[position]);
						}else{
							holder.checkBox.setChecked(false);
						}
						
//						if(imgs != null){
//							holder.img.setImageResource(imgs[position]);
//						}
						
					}
					
					view.setTag(holder);
				} else {
					view = lmap.get(position);
					holder = (ViewHolder) view.getTag();
				}
				return view;
			}

		}
	}
	
	
}
