package com.iscard.club.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.os.Environment;

public class FileUtils {
	
	private static final String SDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static final String CLUB_DIR = SDPATH + "/" + "club";
	
	/**
	 * 获取club目录路径
	 * 
	 * @return
	 */
	public static String getClubDirPath() {
		return CLUB_DIR;
	}
	
	/**
	 * 获取assets中文件内容
	 * 
	 * @param name
	 * @return
	 */
	public static String getConfig(String name) {
		InputStream is = null;
		String res = "";
		try {
			is = new FileInputStream(new File(FileUtils.getClubDirPath() + "/config/" + name));
			int size = is.available();
			byte[] bytes = new byte[size];
			is.read(bytes);
			res = new String(bytes);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(null != is) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		
		return res;
	}

}
