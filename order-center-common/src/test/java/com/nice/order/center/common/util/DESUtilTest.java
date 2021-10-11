package com.nice.order.center.common.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TODO Fill in desc
 *
 * @author haihuang95@zto.com
 * @date 2021/10/11 09:22
 */
@Slf4j
public class DESUtilTest {


    private static  final String KEY_STR = "12345678";

    /**
     * ebe3d1376885023dcca963e327a457f0
     */
    private static  final String MD5_ENCODE = MD5Util.lowercaseMD5("1643768714949" + "12345678" + "{\"deliveryTypeId\":\"HOP\"}");

    private static final String ENCODED_STR = "Hj+3yBzS4L5nU7z9XX+EBKTJvVRm6o5ZbyVba7LSQQeaDR8QE1wdIBeddECLsWObvf7NFApkpckKoO5xplZtgftjNG3JQXS1OW8ls9tiefA=";

    private static String DATA = "{\"sign\":\"" + MD5_ENCODE + "\"," + "\"data\":{\"deliveryTypeId\":\"HOP\"}}";

    @Test
    public void testEncryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String desEncode = DESUtil.encryptStr(KEY_STR, DATA);
        assertEquals(desEncode, ENCODED_STR);
    }

    @Test
    public void testDecryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String desEncode = DESUtil.decryptStr(KEY_STR, ENCODED_STR);
        assertEquals(desEncode, DATA);
    }


}
