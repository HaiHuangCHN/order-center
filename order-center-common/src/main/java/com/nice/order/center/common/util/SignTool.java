package com.nice.order.center.common.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SignTool {
    public SignTool() {
    }

    public static void main(String[] args) {
        String res = sha256HMAC("appId=OMS-SEA&pageNum=1&pageSize=1000&scope=B006", "sdh87re23j");
        System.out.println(res);
    }

    public static String sign(String securetKey, Map<String, String> paraMap) {
        if (null != paraMap && !paraMap.isEmpty()) {
            String lexicographicOrderFormParams = getLexicographicOrderFormParams(paraMap);
            return sha256HMAC(lexicographicOrderFormParams, securetKey);
        } else {
            return "";
        }
    }

    public static boolean verifySign(String sign, String secureKey, Map<String, String> paraMap) {
        String signStr = sign(secureKey, paraMap);
        return signStr.equals(sign);
    }

    private static String getLexicographicOrderFormParams(Map<String, String> params) {
        List<Map.Entry<String, String>> paramEntryLst = new ArrayList(params.entrySet());
        paramEntryLst.sort(Comparator.comparing(Map.Entry::getKey));
        StringBuilder formParams = new StringBuilder();
        Iterator var3 = paramEntryLst.iterator();

        while (var3.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var3.next();
            if (null != entry.getValue() && !((String)entry.getValue()).trim().isEmpty()) {
                formParams.append("&");
                formParams.append((String)entry.getKey());
                formParams.append("=");
                formParams.append((String)entry.getValue());
            }
        }

        if (formParams.length() > 0) {
            formParams.delete(0, 1);
        }

        return formParams.toString();
    }

    private static String sha256HMAC(String message, String secret) {
        String hashStr = "";

        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256HMAC.init(secret_key);
            byte[] bytes = sha256HMAC.doFinal(message.getBytes());
            hashStr = byteArrayToHexString(bytes);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return hashStr;
    }

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();

        for (int n = 0; b != null && n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 255);
            if (stmp.length() == 1) {
                hs.append('0');
            }

            hs.append(stmp);
        }

        return hs.toString().toLowerCase();
    }
}
