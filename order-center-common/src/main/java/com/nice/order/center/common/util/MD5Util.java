package com.nice.order.center.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * TODO Hard code here
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MD5Util {

    private static final String MD5 = "MD5";

    private static final char[] HEX_DIGITS_LOWER_CASE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'};

    private static final char[] HEX_DIGITS_UPPER_CASE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F'};

    /**
     * Encode String(lowercase + digit)
     * <p>
     * Alternative: {@link org.springframework.util.DigestUtils#md5Digest(byte[])}
     *
     * @param str string need encoded
     * @return string has been encoded
     */
    public static String lowercaseMD5(String str) throws NoSuchAlgorithmException {
        try {
            // Get instance from MessageDigest
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            // Use the specified charset to update digest
            byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
            log.debug(Arrays.toString(strBytes));
            messageDigest.update(strBytes);
            byte[] digest = messageDigest.digest();
            // Equal way: MessageDigest.getInstance("md5").digest(strBytes);
            log.debug("Digest: " + Arrays.toString(digest));
            // digest() return MD5 hash value(Octal byte), but actually MD5 hash value should
            // be hexadecimal byte
            // So use BigInteger change it into hexadecimal
            BigInteger bigInteger = new BigInteger(1, digest);
            log.debug("Decimal: " + bigInteger);// Stub
            log.debug("Hexadecimal: " + bigInteger.toString(16));
            return bigInteger.toString(16);
        } catch (Exception e) {
            log.error("MD5 encode error", e);
            throw e;
        }
    }

    /**
     * Encode String(uppercase + digit)
     *
     * @param str string need encoded
     * @return string has been encoded
     */
    public static String uppercaseMD5(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
            messageDigest.update(strBytes);
            byte[] digest = messageDigest.digest();
            int j = digest.length;
            char[] str2 = new char[j * 2];
            int k = 0;
            for (byte byte0 : digest) {
                str2[k++] = HEX_DIGITS_UPPER_CASE[byte0 >>> 4 & 0xf];
                str2[k++] = HEX_DIGITS_UPPER_CASE[byte0 & 0xf];
            }
            return new String(str2);
        } catch (Exception e) {
            log.error("MD5 encode error", e);
            throw e;
        }
    }

    /**
     * Encode String(uppercase + digit)
     *
     * @param data
     * @param salt
     * @param charset
     * @param isBase64 true to encode into Base64, false to encode into hexadecimal
     * @return
     */
    public static String md5(String data, String salt, String charset, boolean isBase64) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update((data + salt).getBytes(charset));
            byte[] md5Bytes = md.digest();
            if (isBase64) {
                digest = Base64.encodeBase64String(md5Bytes);
            } else {
                int k = 0;
                char[] str = new char[md5Bytes.length * 2];
                for (byte c : md5Bytes) {
                    str[k++] = HEX_DIGITS_LOWER_CASE[c >> 4 & 0xf];
                    str[k++] = HEX_DIGITS_LOWER_CASE[c & 0xf];
                }
                digest = new String(str);
            }
        } catch (Exception e) {
            log.error("MD5 encode error", e);
            throw e;
        }
        assert digest != null;
        return digest.replace("\r\n", "");
    }

}
