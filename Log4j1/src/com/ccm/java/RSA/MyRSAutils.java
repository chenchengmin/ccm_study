package com.ccm.java.RSA;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by Administrator on 2015/12/25.
 */
public class MyRSAutils {
	// 私钥
	private static final String GOUQI_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIodOSS4fXlvWYrgJdOCeLhBCsei"
			+ "CBaYqolfdViVCIETXJWQovUf1qRUtFyUaO4uh/2I0zx125MWA/Nr8Lz3iRSm2MfA62aON019Qsl8"
			+ "iAztT3a3Qe9duavhrNqyILX+QMyOIuHHHObD0ChbgsuBINrJ2OttDH+LP7Wss3kO4eMdAgMBAAEC"
			+ "gYAUmIkF9dEYQ8eZkxVw9hrkdyaRYJNOM1PzCl4oaIkNHoMG9rumTCoo9Uy0JZmEM0IqR7YgZP7q"
			+ "bzUFnsoWk99IESL98WnEZg+ze7xQdv0fwPA1c43MhzD+i1a0LWPS10HlO+M7PfQyWWguHa3os+aQ"
			+ "DSyYrHg6M+e6tkh4X7yf7QJBAODPtGe0F7XtXKVwvO+lnEzYw7mKhEsB6Rtz/bV/AVRsmT4ahnnb"
			+ "dCFvyyInEZ56faJyxjVhdB85TZ29ESHl9VcCQQCdRmvZePTlpT2vxjQswtRtOlzekIRgm19vgu2X"
			+ "foUyVgISiqvl0+uPHAju9wYkkyNm001l9OVU5uo+0LiMHc6rAkAL28j3Y2+QwCmP7Id62LCK+TAx"
			+ "3FWoIzypnw+2ADIQwv2+YeXQDbxtexkq/waV5n40EHvWGwLmFr66YFvD/v5XAkA2CneOf2Bu9Etw"
			+ "9YMCCmQhsBcvJqtF4r11PKDVGFU+SY1mv513WE0tnG15ZwoGUUSt1VdX/EE822fT87Yl9ywHAkBG"
			+ "7hb76FMfc9dXXxMSxeP+HVk0M6BW0GCNZWp2xE6yYxLvesy1IeLUOrsHjhd6JhvUc0+Ym91F7kNn"
			+ "aXqPCDRd";

	// 公钥
	private static final String GOUQI_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCKHTkkuH15b1mK4CXTgni4QQrHoggWmKqJX3VY"
			+ "lQiBE1yVkKL1H9akVLRclGjuLof9iNM8dduTFgPza/C894kUptjHwOtmjjdNfULJfIgM7U92t0Hv"
			+ "Xbmr4azasiC1/kDMjiLhxxzmw9AoW4LLgSDaydjrbQx/iz+1rLN5DuHjHQIDAQAB";

	/**
	 * 生成签名
	 * 
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, String privateKey) {
		return sign(content, str2PrivateKey(privateKey));
	}

	/**
	 * 验证签名
	 * 
	 * @param content
	 * @param publicKey
	 * @return
	 */
	public static boolean verify(String content, String sign, String publicKey) {
		return verify(content, sign, str2PublicKey(publicKey));
	}

	/**
	 * @param content
	 * @param privateKey
	 * @return
	 */
	public static String sign(String content, RSAPrivateKey privateKey) {
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initSign(privateKey);
			signature.update(content.getBytes("utf-8"));
			return new BASE64Encoder().encode(signature.sign());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @param content
	 * @param publicKey
	 * @return
	 */
	public static boolean verify(String content, String sign,
			RSAPublicKey publicKey) {
		try {
			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(publicKey);
			signature.update(content.getBytes("utf-8"));
			return signature.verify((new BASE64Decoder()).decodeBuffer(sign));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成公钥
	 * @param publicKeyPerm
	 * @return
	 */
	public static RSAPublicKey str2PublicKey(String publicKeyPerm) {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
					(new BASE64Decoder()).decodeBuffer(publicKeyPerm));
			// 获得RSA工厂
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// 返回公钥
			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 生成私钥
	 * @param privateKeyPerm
	 * @return
	 */
	public static RSAPrivateKey str2PrivateKey(String privateKeyPerm) {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec((new BASE64Decoder()).decodeBuffer(privateKeyPerm));
			// 获得RSA工厂
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// 返回私钥
			return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将所有POST 参数（sign除外）进行字典排序，组成字符串, 然后使用私钥产生签名
	 * 
	 * @param params
	 * @return sign
	 */
	public static String encodeParams(SortedMap<?, ?> params, String privateKey) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<?, ?> entry : params.entrySet()) {
			sb.append(entry.getKey()).append("=").append(entry.getValue())
					.append("&");
		}

		sb.deleteCharAt(sb.length() - 1);
		return sign(sb.toString(), privateKey);
	}

	public static void main1(String[] args) throws Exception {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(1024);
		KeyPair keyPair = keyPairGenerator.genKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();

		BASE64Encoder base64Encoder = new BASE64Encoder();
		
		System.out.println("-------------------public key----------------------");
		System.out.println(base64Encoder.encode(publicKey.getEncoded()));
		System.out.println("-------------------private key---------------------");
		System.out.println(base64Encoder.encode(privateKey.getEncoded()));

	}

	public static void main(String[] args) {
		String publicKey = GOUQI_PUBLIC_KEY;
		String privateKey = GOUQI_PRIVATE_KEY;

		String appPrivateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAN0yqP"
				+ "kLXlnhM+2H/57aHsYHaHXazr9pFQun907TMvmbR04wHChVsKVgGUF1hC0FN"
				+ "9hfeYT5v2SXg1WJSg2tSgk7F29SpsF0I36oSLCIszxdu7ClO7c22mxEVuCj"
				+ "mYpJdqb6XweAZzv4Is661jXP4PdrCTHRdVTU5zR9xUByiLSVAgMBAAECgYEAh"
				+ "znORRonHylm9oKaygEsqQGkYdBXbnsOS6busLi6xA+iovEUdbAVIrTCG9t854"
				+ "z2HAgaISoRUKyztJoOtJfI1wJaQU+XL+U3JIh4jmNx/k5UzJijfvfpT7Cv3ueM"
				+ "tqyAGBJrkLvXjiS7O5ylaCGuB0Qz711bWGkRrVoosPM3N6ECQQD8hVQUgnHEVH"
				+ "ZYtvFqfcoq2g/onPbSqyjdrRu35a7PvgDAZx69Mr/XggGNTgT3jJn7+2XmiGkH"
				+ "M1fd1Ob/3uAdAkEA4D7aE3ZgXG/PQqlm3VbE/+4MvNl8xhjqOkByBOY2ZFfWKh"
				+ "lRziLEPSSAh16xEJ79WgY9iti+guLRAMravGrs2QJBAOmKWYeaWKNNxiIoF7/4"
				+ "VDgrcpkcSf3uRB44UjFSn8kLnWBUPo6WV+x1FQBdjqRviZ4NFGIP+KqrJnFHzN"
				+ "gJhVUCQFzCAukMDV4PLfeQJSmna8PFz2UKva8fvTutTryyEYu+PauaX5laDjyQ"
				+ "bc4RIEMU0Q29CRX3BA8WDYg7YPGRdTkCQQCG+pjU2FB17ZLuKRlKEdtXNV6zQ"
				+ "FTmFc1TKhlsDTtCkWs/xwkoCfZKstuV3Uc5J4BNJDkQOGm38pDRPcUDUh2/";

		String sign = sign("jackiezhi", appPrivateKey);
		System.out.println(sign);
		System.out.println(verify("jackiezhi", sign, publicKey));

	}

}