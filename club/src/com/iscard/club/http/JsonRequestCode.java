package com.iscard.club.http;

import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

import com.iscard.club.bean.RequestCodeBean;

public class JsonRequestCode {
	
	public static final String STATUS_SUCCEED = "1"; // ����ɹ�
	public static final String STATUS_FAILURE = "0"; // ����ʧ��

	public static final String STATUS_FAILURE_MSG = "���ӷ�����ʧ�ܣ�����ϵ����Ա";
	public static final String STATUS_SUCCEED_MSG = "�ɹ�";
	
	public static RequestCodeBean getBean(String json) {
		RequestCodeBean bean = new RequestCodeBean();
		try {
			JSONObject object = new JSONObject(json);
			bean.setStatus(object.get("status").toString());
			if (object.has("data")) {
				bean.setData(object.get("data").toString());
			}
			String Msg = null;
			if (object.has("msg")) {
				Msg = object.get("msg").toString();
				bean.setReqMsg(Msg);
			}
			if (bean.getStatus().equals(STATUS_SUCCEED)) {
				bean.setMsg(STATUS_SUCCEED_MSG);
			} else {
				if (TextUtils.isEmpty(Msg)) {
					bean.setMsg(STATUS_FAILURE_MSG);
				} else {
					bean.setMsg(object.get("msg").toString());
				}
			}
		} catch (JSONException e) {
			// TODO: handle exception
			bean.setStatus(STATUS_FAILURE);
			bean.setMsg(STATUS_FAILURE_MSG);
			e.printStackTrace();
		}
		
		return bean;
	}

}
