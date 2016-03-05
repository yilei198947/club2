package com.iscard.club.util;

import java.util.regex.Pattern;

/**
 * ��֤
 * */
public class VerificationUtils {

	/**
	 * ��֤���֤��֧��18λ���֤������֤
	 * 
	 * @param idCard
	 *             ֧����֤�����λ��ַ��{ "11", "12", "13", "15", "21", "22", "23", "31",
	 *             "32", "33", "34", "35", "36", "37", "41", "42", "43", "44",
	 *             "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",
	 *             "64", "65" }
	 * */
	public static boolean checkIdCard(String idCard) {
		return false;
	}
	
	public static boolean checkNum(String idCard) {
		return Pattern.matches("", idCard);
	}
	
	/**
	 * ��֤�ֻ����루֧�ֹ��ʸ�ʽ��+86135xxxx...���й��ڵأ���+00852137xxxx...���й���ۣ���
	 * 
	 * @param mobile
	 *            �ƶ�����ͨ��������Ӫ�̵ĺ����
	 *            <p>
	 *            �ƶ��ĺŶΣ�134(0-8)��135��136��137��138��139��147
	 *            ��150��151��152��157��158��159��187��188
	 *            </p>
	 *            <p>
	 *            ��ͨ�ĺŶΣ�130��131��132��155��156��185��186
	 *            </p>
	 *            <p>
	 *            ���ŵĺŶΣ�133��153��180��189
	 *            </p>
	 * @return ��֤�ɹ�����true����֤ʧ�ܷ���false
	 */
	public static boolean checkMobile(String mobile) {
		String regex = "(\\+\\d+)?1[3458]\\d{9}$";
		return Pattern.matches(regex, mobile);
	}
	
	public static boolean checkUserName(String userName) {
		String regex = "[a-zA-Z0-9@_.]{6,16}";
		return Pattern.matches(regex, userName);
	}
	
	public static boolean checkPWD(String pwd) {
		String regex = "[a-zA-Z0-9@_]{6,16}";
		return Pattern.matches(regex, pwd);
	}
	
}
