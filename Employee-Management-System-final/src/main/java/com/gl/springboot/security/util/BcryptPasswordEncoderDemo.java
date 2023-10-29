package com.gl.springboot.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderDemo {
	
	public static void main(String args[]) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String strPassword = "welcome";
		
		String encodedPassword1 = passwordEncoder .encode(strPassword);
		String encodedPassword2 = passwordEncoder .encode(strPassword);
		String encodedPassword3 = passwordEncoder .encode(strPassword);
		String encodedPassword4 = passwordEncoder .encode(strPassword);
		
		System.out.println(encodedPassword1);
		System.out.println(encodedPassword2);
		System.out.println(encodedPassword3);
		System.out.println(encodedPassword4);
		
	}

}
