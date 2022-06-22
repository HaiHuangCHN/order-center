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

@Slf4j
public class DESUtilTest {


    private static  final String KEY_STR = "7r*cQSA#";

    /**
     * ebe3d1376885023dcca963e327a457f0
     */
    private static  final String MD5_ENCODE = MD5Util.lowercaseMD5("1643768714949" + KEY_STR + "{\"deliveryTypeId\":\"HOP\"}");

    private static String DATA = "{\"sign\":\"" + MD5_ENCODE + "\"," + "\"data\":{\"deliveryTypeId\":\"HOP\"}}";

    private static final String ENCODED_STR = "l6Phseyz7b3dJ2tT9WxwxPXiLVE9fmL/pfKMorYnsprha0R8Dcbfef5BK3g41jLPcoDBIqEmYoYk0YmW28BaPvUyLiaF6bwy2DlrmQEs8Jc=";

    @Test
    public void testEncryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String desEncode = DESUtil.encryptStr(KEY_STR, DATA);
        assertEquals(desEncode, ENCODED_STR);
    }

    @Test
    public void testDecryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String desDecode = DESUtil.decryptStr(KEY_STR, ENCODED_STR);
        System.out.println(desDecode);
//        assertEquals(desDecode, DATA);
    }


}
