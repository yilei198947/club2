package com.iscard.club.util;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * ����View�Ĺ�����
 * 
 * @author YiLei
 * 
 */
public class ViewUtils {
	
	/**
     * ��ȡTextView EditTextView �е��ı���Ϣ
     * 
     * �ı�����Ϊ��ʱ���ء���
     * 
     * @param view
     * @return String
     */
    public static <T extends TextView> String getTextString(T view) {
        return !TextUtils.isEmpty(view.getText().toString().trim()) ? view
                .getText().toString() : "";
    }

    /**
     * �ж�TextView EditTextView �������Ƿ�Ϊ��
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
