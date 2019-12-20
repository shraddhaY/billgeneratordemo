package com.example.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class StringUtils {

	private static final int BILL_CODE_LENGTH = 7;
	
	public static String getBillInvoiceNumber() {

		String randonNumberString = "0123456789" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "9876543210" + "ZYXWVUTSRQPONMLKJIHGFEDCBA";
		Random rnd = new SecureRandom();
		StringBuilder referralCode = new StringBuilder();
		for (int i = 0; i < BILL_CODE_LENGTH; i++) {
			referralCode.append(randonNumberString.charAt(rnd.nextInt(randonNumberString.length())));
		}
		return referralCode.toString();

	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
