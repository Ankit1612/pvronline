package org.pvronlineCommon;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypted {
	
	public static String getEncryptedText(String decrypted) throws NoSuchAlgorithmException {
		String encryptedCode = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] encodedHash = messageDigest.digest(decrypted.getBytes(StandardCharsets.UTF_8));
			encryptedCode = byteToHash(encodedHash);
		} catch(NoSuchAlgorithmException e) {
			throw e;
		}
		return encryptedCode;
	}

	private static String byteToHash(byte[] encodedHash) {
		StringBuffer hexString = new StringBuffer();
		for(int i = 0; i < encodedHash.length; i++) {
			String hex = Integer.toHexString(0xff & encodedHash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
}