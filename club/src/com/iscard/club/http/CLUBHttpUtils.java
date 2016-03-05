package com.iscard.club.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class CLUBHttpUtils {
	
	private static final int CONN_OUT = 5*1000;

    /**
     * 提交数据
     * 
     * @param url
     * @param params
     * @param callBack
     */
    public static void submitData(String url, RequestParams params,
            RequestCallBack<String> callBack) {
        HttpUtils utils = new HttpUtils(CONN_OUT);
        utils.send(HttpMethod.POST, url, params, callBack);
    }

}
