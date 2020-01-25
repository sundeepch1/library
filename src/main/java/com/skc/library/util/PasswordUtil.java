package com.skc.library.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {

	static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public static String getPasswordHash(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

}
