package com.iscard.club.util;

/**
 * �����ļ�������
 * 
 * @author YiLei
 * 
 */
public class ConfigFileUtils {
	
	public static final String HOST_JSON = "host.json";//url�����ļ�
	
	public static String getRes(String path) {
		return FileUtils.getConfig(path);
	}

}
