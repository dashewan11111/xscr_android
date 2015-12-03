package com.adult.android.utils.crypto;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

public class AESUtil {
	private static byte[] KEY = { 97, 53, 48, 99, 49, 115, 50, 49, 48, 50, 110,
			51, 54, 56, 57, 113 };

	private static final String IV = "1567895450321478";

	// 加密
	public static String encrypt(String sSrc) {

		try {
			SecretKeySpec skeySpec = new SecretKeySpec(KEY, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted;
			try {
				encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
				return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			return "";
		} catch (BadPaddingException e) {
			e.printStackTrace();
			return "";
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			return "";
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			return "";
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
			return "";
		}
	}

	// 解密
	public static String decrypt(String sSrc) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(KEY, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);
				String originalString = new String(original);
				return originalString;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}