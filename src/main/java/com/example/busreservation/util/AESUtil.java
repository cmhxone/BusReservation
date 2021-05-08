package com.example.busreservation.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 암복호화 클래스
 *
 * @author mhcho
 */
@Component
public class AESUtil {
    /**
     *
     * @param plainText 평문
     * @param secret SECRET_KEY
     * @return  암호화 된 문자
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @author mhcho
     */
    public static String Encrypt(String plainText, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String result = null;
        String IV = secret.substring(0, 16);

        SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));

        result = new String(Base64.encodeBase64(cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8))));

        return result;
    }


    /**
     *
     * @param encryptedText 암호화 된 문자
     * @param secret SECRET_KEY
     * @return 평문
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @author mhcho
     */
    public static String Decrypt(String encryptedText, String secret) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String result = null;
        String IV = secret.substring(0, 16);

        SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));

        result = new String(cipher.doFinal(Base64.decodeBase64(encryptedText.getBytes(StandardCharsets.UTF_8))));

        return result;
    }

}
