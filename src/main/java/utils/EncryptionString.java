package utils;

import java.util.Base64;

public class EncryptionString {

	public static String encode(String originalInput) {
		return Base64.getEncoder().encodeToString(originalInput.getBytes());
		
	}

	public static String deccode(String encodedString) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		return new String(decodedBytes);

	}

}
