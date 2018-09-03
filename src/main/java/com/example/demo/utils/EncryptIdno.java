package com.example.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Create by qs on 2018/9/3
 * email:qinsen@chinaredsun.com
 */
public class EncryptIdno {
    private static Logger logger = LoggerFactory.getLogger(EncryptIdno.class);

    static final String TESTSTR="1212154548121478";
    static final String KEYPATH="75EC8Feg3G22ABe0";
//    static final String KEYPATH="E:/temp/key.key";

    /**
     * 加解密测试
     * */
    public static void main(String[] args) throws Exception {

        String idno_send = null;
        String idno_read = null;
        System.out.println("原文`;"+TESTSTR);
        idno_send = encrypt4Idno(TESTSTR,KEYPATH);
        System.out.println("密文:"+idno_send);
        idno_read = decrypt4Idno(idno_send,KEYPATH);
        System.out.println("解码后:"+idno_read);

    }

    public static String decrypt4Idno(String idno, String keyPath) throws NoSuchAlgorithmException {
        byte[] decrypt = decrypt(parseHexStr2Byte(idno), keyPath);
        String strRead = new String(decrypt);
        return strRead;
    }


    public static String encrypt4Idno(String idno, String keyPath) throws NoSuchAlgorithmException {
        byte[] encrypt = encrypt(idno, keyPath);
        String str = parseByte2HexStr(encrypt);
        return str;
    }


    /**
     *  初始化密钥
     *
     *  @param keyPath  密码
     *
     *  @return  key
     * */
    public static SecretKey getKey(String keyPath) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance( "AES" );
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
            secureRandom.setSeed(keyPath.getBytes());
            _generator.init(128,secureRandom);
            return _generator.generateKey();
        }  catch (Exception e) {
            throw new RuntimeException( "初始化密钥出现异常 " );
        }
    }

    /**
     * 加密
     *
     * @param content  明文
     * @param keyPath  加密密钥
     * @return
     */
    public static byte[] encrypt(String content, String keyPath) throws NoSuchAlgorithmException {

        SecretKeySpec key = null;
        try {
           /* SecretKey secretKey =getKey(keyPath);
            byte[] enCodeFormat = secretKey.getEncoded();*/
            key = new SecretKeySpec(keyPath.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("GBK");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (NoSuchAlgorithmException e) {
            logger.error("加密失败："+e.getMessage());

        } catch (NoSuchPaddingException e) {
            logger.error("加密失败："+e.getMessage());

        } catch (InvalidKeyException e) {
            logger.error("加密失败："+e.getMessage());

        } catch (UnsupportedEncodingException e) {
            logger.error("加密失败："+e.getMessage());

        } catch (IllegalBlockSizeException e) {
            logger.error("加密失败："+e.getMessage());

        } catch (BadPaddingException e) {
            logger.error("加密失败："+e.getMessage());

        }
        return null;
    }

    /**解密
     * @param content 密文
     * @param keyPath 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String keyPath) throws NoSuchAlgorithmException {

        SecretKeySpec key = null;
        try {
         /*   SecretKey secretKey =getKey(keyPath);
            byte[] enCodeFormat = secretKey.getEncoded();*/
            key = new SecretKeySpec(keyPath.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            logger.error("解密失败："+e.getMessage());

        } catch (NoSuchPaddingException e) {
            logger.error("解密失败："+e.getMessage());

        } catch (InvalidKeyException e) {
            logger.error("解密失败："+e.getMessage());

        } catch (IllegalBlockSizeException e) {
            logger.error("解密失败："+e.getMessage());

        } catch (BadPaddingException e) {
            logger.error("解密失败："+e.getMessage());

        }
        return null;
    }

    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
