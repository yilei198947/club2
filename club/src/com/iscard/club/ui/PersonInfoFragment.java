package com.iscard.club.ui;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iscard.club.R;
import com.iscard.club.dialog.DialogUtil;
import com.iscard.club.dialog.HobbyDialog;
import com.iscard.club.util.SetAddressUtil;
import com.iscard.club.util.TimeUtil;

public class PersonInfoFragment extends Fragment implements OnClickListener {
	
	private String arrs[] = { "�黭", "��Ӱ", "����", "����", "��ʳ", "��Ʒ", "ʱ��", "��", "֪ʶ����" ,
			
			
			"��ʷ�Ļ�", "�ط��Ļ�" ,"��ժ����","�Լ���","��������","����"      };
	private boolean boos[] = { true, false, false, true, true, false, false, false, true,true,true ,true,true,true,true};
	private int[]  imgs ={
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher,
			R.drawable.ic_launcher
	};
	View view = null;
	private TextView hobbyBtn;
	private HobbyDialog.Builder hobbyBuilder;
	private TextView bornTV;
	
	//ʡ����
	private Spinner province_spinner;
	private Spinner city_spinner;
	private Spinner area_spinner;
	
	private ArrayAdapter<CharSequence> province_adapter;
	private ArrayAdapter<CharSequence> city_adapter;
	private ArrayAdapter<CharSequence> area_adapter;
	
	private Integer provinceId, cityId;
	private String strProvince, strCity, strArea;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_per_info, container, false);
		initView();
		loadSpinner();
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.hobbyBtn:
			hobbyBuilder = new HobbyDialog.Builder(getActivity());
			HobbyDialog hobbyDialog = hobbyBuilder.setTitle("ѡ��")
			.setMultiChoiceItems(arrs, null ,imgs, null, true)
			.setPositiveButton("ȷ��", new PositiveClickListener()).setNegativeButton("ȡ��", null).create();
	        hobbyDialog.show();
			break;

		case R.id.bornTV:
			DialogUtil.dateDialog(getActivity(), new OnDateSetListener() {
				
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					bornTV.setText(TimeUtil.getTime(TimeUtil.stringToDate(year+"-"+(monthOfYear+1)+"-"+dayOfMonth, "yyyy-MM-dd"), "yyyy-MM-dd"));
				}
			});
			break;
		default:
			break;
		}
	}
	
	private void initView() {
		hobbyBtn = (TextView) view.findViewById(R.id.hobbyBtn);
		bornTV = (TextView) view.findViewById(R.id.bornTV);
		province_spinner = (Spinner) view.findViewById(R.id.province_spinner);
		city_spinner = (Spinner) view.findViewById(R.id.city_spinner);
		area_spinner = (Spinner) view.findViewById(R.id.area_spinner);
		
		hobbyBtn.setOnClickListener(this);
		bornTV.setOnClickListener(this);
	}
	
	class PositiveClickListener implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			String s = "��ѡ����:";
			boos = hobbyBuilder.getCheckedItems();
			for (int i = 0; i < boos.length; i++) {
				if (boos[i]) {
					s += i + ":" + arrs[i] + "  ";
				} 
			}
			alert(getActivity(), s);
		}
	}
	
	public void alert(Context context, String str) {
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}
	
	//ʡ��������
	private void loadSpinner() {
		//��ʡ�ݵ�����
		province_spinner.setPrompt("��ѡ��ʡ��");
		province_adapter = ArrayAdapter.createFromResource(getActivity(), R.array.province_item, android.R.layout.simple_spinner_item);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        province_spinner.setAdapter(province_adapter);
        //��Ӽ�����һ��ʼ��ʱ����У������������ǲ���ʾ�Ķ��Ǹ���ʡ�����ݽ�������
        province_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				provinceId = province_spinner.getSelectedItemPosition();
				strProvince = province_spinner.getSelectedItem().toString();
				
				if(true) {
					city_spinner.setPrompt("��ѡ�����");//���ñ���
					select(city_spinner, city_adapter, SetAddressUtil.city[provinceId]);//����һ�������ݰ�
					
					city_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							// TODO Auto-generated method stub
							cityId = city_spinner
									.getSelectedItemPosition();//�õ�city��id
							strCity = city_spinner
									.getSelectedItem()
									.toString();//�õ�city������
							
							if(true) {
								area_spinner.setPrompt("��ѡ������");
								
								switch (provinceId) {
								case 0:
									select(area_spinner,
											area_adapter,
											SetAddressUtil.areaOfShanXi2[cityId]);
									break;

								default:
									break;
								}
								
								area_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

									@Override
									public void onItemSelected(
											AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
										// TODO Auto-generated method stub
										strArea = area_spinner
												.getSelectedItem()
												.toString();
									}

									@Override
									public void onNothingSelected(
											AdapterView<?> arg0) {
										// TODO Auto-generated method stub
										
									}
								});
							}
						}

						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	/*ͨ��������̬�����������*/
	private void select(Spinner spin, ArrayAdapter<CharSequence> adapter, int arry) {
		//ע�������arry����������һ�����Σ���������һ�����飡
		adapter = ArrayAdapter.createFromResource(getActivity(), arry,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		// spin.setSelection(0,true);
	}

}
