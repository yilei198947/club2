package com.iscard.club.util;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * 操作View的工具类
 * 
 * @author YiLei
 * 
 */
public class ViewUtils {
	
	/**
     * 获取TextView EditTextView 中的文本信息
     * 
     * 文本内容为空时返回“”
     * 
     * @param view
     * @return String
     */
    public static <T extends TextView> String getTextString(T view) {
        return !TextUtils.isEmpty(view.getText().toString().trim()) ? view
                .getText().toString() : "";
    }

    /**
     * 判断TextView EditTextView 中内容是否为空
     * 
     * 
     * 
     * 
     * @param view
     * @return * null : true<>
     * 
     *         Not null : false
     */
    public static <T extends TextView> boolean isEmpty(T view) {
        return TextUtils.isEmpty(view.getText().toString().trim()) ? true
                : false;

    }

}
