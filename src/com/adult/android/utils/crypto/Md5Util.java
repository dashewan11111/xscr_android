package com.adult.android.utils.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;

public class Md5Util {
	public static String getMD5(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] bytes = digest.digest(str.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				int n = b & 0xff;
				String hex = Integer.toHexString(n);
				if (hex.length() == 1) {
					sb.append("0");
				}
				sb.append(hex);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getMD5(File file) {
		InputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance("MD5");
			int numRead;
			while ((numRead = fis.read(buffer)) != -1) {
				complete.update(buffer, 0, numRead);
			}
			return toHexString(complete.digest());
		} catch (java.io.IOException e) {
			e.printStackTrace();
			return "";
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
	}

	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	protected static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(DIGITS_LOWER[(b[i] & 0xf0) >>> 4]);
			sb.append(DIGITS_LOWER[b[i] & 0x0f]);
		}
		return sb.toString();
	}
}