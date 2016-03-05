package com.iscard.club.util;

import java.util.regex.Pattern;

/**
 * 验证
 * */
public class VerificationUtils {

	/**
	 * 验证身份证，支持18位身份证号码验证
	 * 
	 * @param idCard
	 *             支持验证身份首位地址码{ "11", "12", "13", "15", "21", "22", "23", "31",
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
	 * 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * 
	 * @param mobile
	 *            移动、联通、电信运营商的号码段
	 *            <p>
	 *            移动的号段：134(0-8)、135、136、137、138、139、147
	 *            、150、151、152、157、158、159、187、188
	 *            </p>
	 *            <p>
	 *            联通的号段：130、131、132、155、156、185、186
	 *            </p>
	 *            <p>
	 *            电信的号段：133、153、180、189
	 *            </p>
	 * @return 验证成功返回true，验证失败返回false
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
