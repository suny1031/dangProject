package com.dang.common.random;

import java.util.Random;

public class RandomString {
	
	
	
	public static String randomStr(int size) {
		Random random = new Random();
		
		String randomNum = "!";
		
		
		for(int i=0; i< 2; i++) {
			
			for(int j=0; j < size; j++) {
				String num = String.valueOf(random.nextInt(10)); //랜덤숫자 출력
				randomNum = randomNum+num;
			}
			
			for(int j = 0; j< size; j++) {
				String num = String.valueOf((char)((int)(random.nextInt(26))+ 97)); //랜덤 소문자
				randomNum = randomNum+num;
			}
			
			for(int j = 0; j< size; j++) {
				String num = String.valueOf((char)((int)(random.nextInt(26))+ 65)); //랜던 대문자
				randomNum = randomNum+num;
			}
			
		}
		return randomNum;
	}
	
}
