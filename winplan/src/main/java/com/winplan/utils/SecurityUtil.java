/**
 * SecurityUtil.java.java
 * @author FengMy
 * @since 2015年7月1日
 */
package com.winplan.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.util.StringUtils;

/**  
 * 功能描述：安全工具类
 * 
 * @author FengMy
 * @since 2015年7月1日
 */
public class SecurityUtil {
	private static final String KEY_SHA = "SHA";
	private static final int RADIX = 29;
	public static String encryptSHA(String value){
		if(!StringUtils.isEmpty(value)){
			try {
				byte[] data = value.getBytes();
				MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
				sha.update(data);
				data = sha.digest();
				return new BigInteger(data).toString(RADIX);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptSHA("简单加密"));
	}
}
