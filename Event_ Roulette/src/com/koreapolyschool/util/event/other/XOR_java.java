package com.koreapolyschool.util.event.other;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class XOR_java {
	
	public static String convert(String str, String encoding) throws IOException {
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		requestOutputStream.write(str.getBytes(encoding));
		return requestOutputStream.toString(encoding);
	}

	public static String xor_str(String s, String k) throws IOException {

		String to_enc = s; // 암호화 키
		// Character str = new Character((char) 65);
		// System.out.println(str);
		String xor_key = k; // 복호화 키
		String the_res = "";
		// convert(the_res, "UTF-8");

		for (int i = 0; i < to_enc.length(); ++i) {
			if ((xor_key.charAt(i % xor_key.length()) ^ to_enc.charAt(i)) == (1 ^ 1)) {
				the_res += '\0';

			} else if (to_enc.charAt(i) == '\0') {

				the_res += xor_key.charAt(i % xor_key.length());
			} else {

				the_res += (char) (xor_key.charAt(i % xor_key.length()) ^ to_enc
						.charAt(i)); // 숫자
			}
		}
		System.out.println("결과 :: " + the_res);
		return the_res;
	} // xor_str

}
