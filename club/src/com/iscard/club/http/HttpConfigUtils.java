package com.iscard.club.http;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iscard.club.util.ConfigFileUtils;

public class HttpConfigUtils {
	
	public static final String LOGIN = "";//��¼
	public static final String REG = "";//ע��
	private static Map<String, String> hosts = null;
	
	/**
     * ��hots�ж�ȡurl
     * 
     * @param key
     *            �贫�ݵ�����hosts�е�key
     * @return
     */
	public static String getUrl(String key) {
		if(hosts == null) {
			Gson gson = new Gson();
			hosts = gson.fromJson(
					ConfigFileUtils.getRes(ConfigFileUtils.HOST_JSON),
					new TypeToken<Map<String, String>>() {
                    }.getType());
		}
		
		String url = hosts.get(key);
		return url != null ? url : key;
	}

}
