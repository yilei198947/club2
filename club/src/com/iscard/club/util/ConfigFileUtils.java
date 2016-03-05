package com.iscard.club.util;

/**
 * 配置文件工具类
 * 
 * @author YiLei
 * 
 */
public class ConfigFileUtils {
	
	public static final String HOST_JSON = "host.json";//url配置文件
	
	public static String getRes(String path) {
		return FileUtils.getConfig(path);
	}

}
