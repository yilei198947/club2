package com.iscard.club.ui;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends BaseActivity implements OnClickListener {
	
	Dialog dialog;
	private EditText accountET, pwdET;
	private Button loginBtn;
	private TextView forgetPWD, regNewAccount;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		dialog = DialogUtil.dialogWait(getApplicationContext(), "登录中...");
		initView();
		validateET();
	}
	
	private void initView() {
		accountET = (EditText)findViewById(R.id.accountET);
		pwdET = (EditText) findViewById(R.id.pwdEt);
		loginBtn = (Button) findViewById(R.id.loginBtn);
		forgetPWD = (TextView) findViewById(R.id.forgetTV);
		regNewAccount = (TextView) findViewById(R.id.regTV);
		
		loginBtn.setOnClickListener(this);
		forgetPWD.setOnClickListener(this);
		regNewAccount.setOnClickListener(this);
		forgetPWD.setOnClickListener(this);
	}
	
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(getApplicationContext(), (CharSequence) msg.obj, Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}
		};
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.loginBtn:
			if(ViewUtils.isEmpty(accountET) || ViewUtils.isEmpty(pwdET)) {
				Toast.makeText(getApplicationContext(), "输入框不能为空", Toast.LENGTH_SHORT).show();
			} else {
//				submitLoginInfo();
			}
			break;

		case R.id.regTV:
			Intent intent = new Intent(this, RegActivity.class);
			startActivity(intent);
			break;
			
		case R.id.forgetTV:
			Intent intent1 = new Intent(this, ForgetPwdActivity.class);
			startActivity(intent1);
			break;
		default:
			break;
		}
	}
	
	private void submitLoginInfo() {
		RequestParams params = new RequestParams();
		params.addBodyParameter(HttpRequestFrom.USER_NAME, ViewUtils.getTextString(accountET));
		params.addBodyParameter(HttpRequestFrom.PWD_WORD, ViewUtils.getTextString(pwdET));
		CLUBHttpUtils.submitData(HttpConfigUtils.getUrl(HttpConfigUtils.LOGIN),
				params, new RequestCallBack<String>() {
			
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
	
	private void validateET() {
		accountET.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus) {
					
				} else {
					if(VerificationUtils.checkUserName(accountET.getText().toString()) || ViewUtils.isEmpty(accountET)) {
						loginBtn.setClickable(true);
					} else {
						accountET.setError("用户名格式错误");
						loginBtn.setClickable(false);
					}
				}
			}
		});
		
		pwdET.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(hasFocus) {
					
				} else {
					if(VerificationUtils.checkPWD(pwdET.getText().toString()) || ViewUtils.isEmpty(pwdET)) {
						loginBtn.setClickable(true);
					} else {
						pwdET.setError("密码格式错误");
						loginBtn.setClickable(false);
					}
				}
			}
		});
		
	}

}
