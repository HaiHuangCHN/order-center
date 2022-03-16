package com.nice.order.center.common.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * TODO Hard code here
 */
@Slf4j
public class MD5Util {

    /**
     * Encode String(lowercase + digit)
     * <p>
     * Alternative: DigestUtils.md5Hex()
     *
     * @param str string need encoded
     * @return string has been encoded
     */
    public static String lowercaseMD5(String str) {
        try {
            // Get instance from MessageDigest
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // Compute MD5 function
            byte[] btInput = str.getBytes(StandardCharsets.UTF_8);
            log.debug(Arrays.toString(btInput));// Stub
            /**
             * TODO Why update???
             */
            mdInst.update(btInput);
            // digest() return MD5 hash value(Octal byte), but actually MD5 hash value should
            // be hexadecimal byte
            // So use BigInteger change it into hexadecimal
            byte[] md = mdInst.digest();
            // Equal way: MessageDigest.getInstance("md5").digest(btInput);
            log.debug("md: " + md);// Stub
            BigInteger bigInteger = new BigInteger(1, md);
            log.debug("decimal: " + bigInteger.toString());// Stub
            log.debug("Hexadecimal: " + bigInteger.toString(16));// Stub
            return bigInteger.toString(16);
        } catch (Exception e) {
            log.error("encode error", e);
            return null;
        }
    }

    /**
     * Encode String(uppercase + digit)
     *
     * @param str string need encoded
     * @return string has been encoded
     */
    public static String uppercaseMD5(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            // Get instance from MessageDigest
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // Use the specified charset to update digest
            byte[] btInput = str.getBytes(StandardCharsets.UTF_8);
            mdInst.update(btInput);
            // Get the secret
            byte[] md = mdInst.digest();
            // Change the secret into hexadecimal byte
            int j = md.length;
            char str2[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str2[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str2[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
