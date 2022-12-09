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
class DESUtilTest {

    private static final String KEY_STR = "x1Cgf$570#YoskN%xZE%SLO56CQsrZ#c";

    private static final String REQ_DATA = "{\"orderNo\":\"1D1670206806553\",\"billCode\":\"202212050234\"," +
            "\"userCode\":\"466595\",\"refuseType\":\"503\",\"refuseReason\":\"液体\",\"remark\":\"测试啊啊啊\"}";

    private static Long currentMillis = 1670206479191L /*System.currentTimeMillis()*/;

    private static  final String MD5_ENCODE = MD5Util.lowercaseMD5(currentMillis + KEY_STR + REQ_DATA);

    private static String SIGN_DATA = "{\"sign\":\"" + MD5_ENCODE + "\"," + "\"data\":" + JacksonUtils.objectToJsonCamel(REQ_DATA) + "}";

    private static final String ENCODED_STR = "ectVzxF9xF69gn1Th++nzewP2ox598ndyEA83+qm0W/D7sWwsN6Un2mxefGz6h6W";

    @Test
    void testEncryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException,
            IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        System.out.println(currentMillis);
        System.out.println(REQ_DATA);
        System.out.println(SIGN_DATA);
        String desEncode = DESUtil.encryptStr(KEY_STR, SIGN_DATA);
        System.out.println(desEncode);
        assertEquals(desEncode, ENCODED_STR);
    }

    @Test
    public void testDecryptStr() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String desDecode = DESUtil.decryptStr(KEY_STR, ENCODED_STR);
        System.out.println(desDecode);
//        assertEquals(desDecode, SIGN_DATA);

        Resp resp = JacksonUtils.jsonToObject(desDecode, Resp.class);
        System.out.println(resp.getData());

        String md5 = MD5Util.lowercaseMD5(currentMillis + KEY_STR + resp.getData());
        assertEquals(resp.getSign(), md5);
    }

    private static class Resp {
        private String sign;
        private String data;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

    }

    @Test
    void testResp() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        String str = "{\"aField\":\"123456789\"}";
        String desEncode = DESUtil.encryptStr(KEY_STR, str);
        System.out.println(desEncode);

        String desDecode = DESUtil.decryptStr(KEY_STR, desEncode);
        System.out.println(desDecode);
    }


}
