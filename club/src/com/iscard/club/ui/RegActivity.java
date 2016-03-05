package com.iscard.club.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iscard.club.BaseActivity;
import com.iscard.club.R;
import com.iscard.club.bean.RequestCodeBean;
import com.iscard.club.dialog.DialogUtil;
import com.iscard.club.http.CLUBHttpUtils;
import com.iscard.club.http.HttpConfigUtils;
import com.iscard.club.http.HttpRequestFrom;
import com.iscard.club.http.JsonRequestCode;
import com.iscard.club.util.VerificationUtils;
import com.iscard.club.util.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

public class RegActivity extends BaseActivity implements OnClickListener {
	
	Dialog dialog;
	private Button regBtn;
	private EditText regAccount, regName, regPhone, regCode, regPWD, regConfirmPWD;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_reg);
		dialog = DialogUtil.dialogWait(getApplicationContext(), "注册中...");
		initView();
		validateET();
	}
	
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				break;

			case 0:
				Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.regBtn:
			if(ViewUtils.isEmpty(regAccount) || ViewUtils.isEmpty(regName)
			   || ViewUtils.isEmpty(regPhone) || ViewUtils.isEmpty(regCode)
			   || ViewUtils.isEmpty(regPWD) || ViewUtils.isEmpty(regConfirmPWD)) {
				Toast.makeText(getApplicationContext(), "输入框不能为空", Toast.LENGTH_SHORT).show();
			} else {
//				submitRegInfo();
			}
			break;

		default:
			break;
		}
	}
	
	private void submitRegInfo() {
		RequestParams params = new RequestParams();
		params.addBodyParameter(HttpRequestFrom.USER_NAME, ViewUtils.getTextString(regAccount));
		params.addBodyParameter(HttpRequestFrom.PWD_WORD, ViewUtils.getTextString(regPWD));
		params.addBodyParameter(HttpRequestFrom.NAME, ViewUtils.getTextString(regName));
		params.addBodyParameter(HttpRequestFrom.MOBILE, ViewUtils.getTextString(regPhone));
		params.addBodyParameter(HttpRequestFrom.SEC_CODE, ViewUtils.getTextString(regCode));
		params.addBodyParameter(HttpRequestFrom.CARD_TYPE, "");
		params.addBodyParameter(HttpRequestFrom.CARD_NUM, "");
		CLUBHttpUtils.submitData(HttpConfigUtils.getUrl(HttpConfigUtils.REG), params,
				new RequestCallBack<String>() {
			
			        @Override
			        public void onStart() {
			        	// TODO Auto-generated method stub
			        	super.onStart();
			        	dialog.show();
			        }
					
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						RequestCodeBean bean = JsonRequestCode
                                .getBean(arg0.result);
						try {
							JSONObject jsonObj = new JSONObject(bean
                                    .getData());
								Message message = Message.obtain(handler);
								message.what = Integer.valueOf(jsonObj.getString("status"));
								message.obj = jsonObj.getString("msg");
								message.sendToTarget();
						} catch (JSONException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
					
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});
	}
	
	private void initView() {
		regBtn = (Button) findViewById(R.id.regBtn);
		regAccount = (EditText) findViewById(R.id.regAccount);
		regName = (EditText) findViewById(R.id.regName);
		regPhone = (EditText) findViewById(R.id.regPhone);
		regCode = (EditText) findViewById(R.id.regCode);
		regPWD = (EditText) findViewById(R.id.regPWD);
		regConfirmPWD = (EditText) findViewById(R.id.regConfirmPWD);
		
		regBtn.setOnClickListener(this);
	}
	
	private void validateET() {
		regAccount.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus) {
					
				} else {
					if(VerificationUtils.checkUserName(regAccount.getText().toString()) || ViewUtils.isEmpty(regAccount)) {
						regBtn.setClickable(true);
					} else {
						regAccount.setError("用户名格式错误");
						regBtn.setClickable(false);
					}
				}
			}
		});
		
		regPhone.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus) {
					
				} else {
					if(VerificationUtils.checkMobile(regPhone.getText().toString()) || ViewUtils.isEmpty(regPhone)) {
						regBtn.setClickable(true);
					} else {
						regPhone.setError("手机号码错误");
						regBtn.setClickable(false);
					}
				}
			}
		});
		
		regPWD.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus) {
					
				} else {
					if(VerificationUtils.checkPWD(regPWD.getText().toString()) || ViewUtils.isEmpty(regPWD)) {
						regBtn.setClickable(true);
					} else {
						regPWD.setError("密码格式错误");
						regBtn.setClickable(false);
					}
				}
			}
		});
		
		regConfirmPWD.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus) {
					
				} else {
					if(VerificationUtils.checkPWD(regConfirmPWD.getText().toString()) || ViewUtils.isEmpty(regConfirmPWD)) {
						regBtn.setClickable(true);
					} else {
						
							regConfirmPWD.setError("密码格式错误");
							regBtn.setClickable(false);
					}
				}
			}
		});
		
	}

}
