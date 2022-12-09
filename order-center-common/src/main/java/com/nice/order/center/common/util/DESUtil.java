package com.nice.order.center.common.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * DES utility
 * <p>
 * Refer from: https://blog.csdn.net/gs12software/article/details/83899389
 *
 * TODO 偏移变量/设置向量，这个是啥？
 *
 * @author haihuang95@zto.com
 * @date 2021/10/10 23:56
 */
@Deprecated
@Slf4j
public class DESUtil {


    /**
     * 偏移变量，固定占8位字节
     */
    private static final String IV_PARAMETER = "12345678";

    /**
     * 偏移变量，固定占8位字节
     * <p>
     * 设置向量，略去
     * <p>
     * 注：ZTO内部使用
     */
    private static final byte[] DES_IV = {0x12, 0x34, 0x56, 0x78, (byte)0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF};

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "DES";

    /**
     * 加密/解密算法-工作模式-填充模式
     */
    private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    /**
     * 生成key
     *
     * @param keyStr
     * @return
     * @throws Exception
     */
    private static Key generateKey(String keyStr) throws InvalidKeyException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        // 设置密钥参数
        DESKeySpec dks = new DESKeySpec(keyStr.getBytes(StandardCharsets.UTF_8));
        // 获得密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        // 得到密钥对象
        return keyFactory.generateSecret(dks);
    }

    /**
     * DES加密字符串
     *
     * @param password 加密密码，长度不能够小于8位
     * @param data     待加密字符串
     * @return 加密后内容
     */
    public static String encryptStr(String password, String data) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null) {
            return null;
        }

        Key secretKey = generateKey(password);
        // 得到加密对象Cipher
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 设置向量
        IvParameterSpec iv = new IvParameterSpec(DES_IV);
        // 设置工作模式为加密模式，给出密钥和向量
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] bytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        //JDK1.8及以上可直接使用Base64，JDK1.7及以下可以使用BASE64Encoder
        //Android平台可以使用android.util.Base64
        return new String(Base64.getEncoder().encode(bytes));
    }

    /**
     * DES解密字符串
     *
     * @param password 解密密码，长度不能够小于8位
     * @param data     待解密字符串
     * @return 解密后内容
     */
    public static String decryptStr(String password, String data) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, NoSuchPaddingException, InvalidAlgorithmParameterException,
            IllegalBlockSizeException, BadPaddingException {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        if (data == null)
            return null;
        Key secretKey = generateKey(password);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        IvParameterSpec iv = new IvParameterSpec(DES_IV);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8))),
                StandardCharsets.UTF_8);
    }

    /**
     * DES加密文件
     *
     * @param srcFile  待加密的文件
     * @param destFile 加密后存放的文件路径
     * @return 加密后的文件路径
     */
    public static String encryptFile(String password, String srcFile, String destFile) {

        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            // Note: the IV used here is different from the one used in decryptStr/encryptStr
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(password), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = cis.read(buffer)) > 0) {
                out.write(buffer, 0, r);
            }
            cis.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception e) {
            log.error("DES加密文件出错", e);
        }
        return null;
    }

    /**
     * DES解密文件
     *
     * @param srcFile  已加密的文件
     * @param destFile 解密后存放的文件路径
     * @return 解密后的文件路径
     */
    public static String decryptFile(String password, String srcFile, String destFile) {
        if (password == null || password.length() < 8) {
            throw new RuntimeException("加密失败，key不能小于8位");
        }
        try {
            File file = new File(destFile);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            // Note: the IV used here is different from the one used in decryptStr/encryptStr
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(StandardCharsets.UTF_8));
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password), iv);
            InputStream is = new FileInputStream(srcFile);
            OutputStream out = new FileOutputStream(destFile);
            CipherOutputStream cos = new CipherOutputStream(out, cipher);
            byte[] buffer = new byte[1024];
            int r;
            while ((r = is.read(buffer)) >= 0) {
                cos.write(buffer, 0, r);
            }
            cos.close();
            is.close();
            out.close();
            return destFile;
        } catch (Exception e) {
            log.error("DES解密文件出错", e);
        }
        return null;
    }


}
