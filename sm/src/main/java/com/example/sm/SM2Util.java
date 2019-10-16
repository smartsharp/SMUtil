package com.example.sm;

import android.util.Base64;

import java.io.IOException;
import java.math.BigInteger;

public class SM2Util {


    public static String getEncryption(String string,String privateKey,String publicKey) {
//        byte[] bytes = testSM2Creator(string,privateKey,publicKey);
        String encryption = testSM2Creator(string,privateKey,publicKey);
        return encryption;
    }



//    public static String getDecrypt(byte[] cipherBytes,String privateKey,String publicKey) {
//        String text = SM2Decrypt(cipherBytes,privateKey,publicKey);
//        return text;
//    }


    public static String getDecrypt(String  cipherBytes,String privateKey,String publicKey) {
        String text = SM2Decrypt(cipherBytes,privateKey,publicKey);
        return text;
    }


    public static String  testSM2Creator(String string,String privateKey,String publicKey) {
//        String privateKey = "cKwtbFCaURkAoCREdYOIKzngWwybP8er4gYz234gOqY=";
        byte[] privateBytes = Base64.decode(privateKey, Base64.NO_WRAP);

//        String publicKey = "BNa6GBPM3SPpYXze00+OcoltK08XbeWifpzF1sEbpGR00P3ae5rCw7fWXNCmW6FHVCgciDHoJybVKxPmCJLcqKg=";
        byte[] publicBytes = Base64.decode(publicKey, Base64.NO_WRAP);

        String source = string;
        String userId = "";
        String result = "操作失败";
        result = getEncString(privateBytes, publicBytes, userId, source);
        return result;
    }



    public static String SM2Decrypt(String cipherBytes,String privateKey,String publicKey) {
//        String privateKey = "cKwtbFCaURkAoCREdYOIKzngWwybP8er4gYz234gOqY=";
        byte[] privateBytes = Base64.decode(privateKey, Base64.NO_WRAP);

//        String publicKey = "BNa6GBPM3SPpYXze00+OcoltK08XbeWifpzF1sEbpGR00P3ae5rCw7fWXNCmW6FHVCgciDHoJybVKxPmCJLcqKg=";
        byte[] publicBytes = Base64.decode(publicKey, Base64.NO_WRAP);

        String userId = "";
        String result = "操作失败";
        result = getDecryptString(privateBytes, publicBytes, userId, cipherBytes);
        return result;
    }


//    /**
//     * 加密
//     */
//    private static   byte[] getEncString(byte[] privateBytes, byte[] publicBytes, String userId, String source) {
//        try {
//            byte[] cipherBytes = SM2Util.encrypt(publicBytes, source.getBytes());
//            return cipherBytes;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    /**
     * 加密
     */
    private static   String getEncString(byte[] privateBytes, byte[] publicBytes, String userId, String source) {
        try {
            byte[] cipherBytes = SM2Util.encrypt(publicBytes, source.getBytes());
            String encryption = Base64.encodeToString(cipherBytes, Base64.NO_WRAP);
            return encryption;
        } catch (Exception e) {
            e.printStackTrace();
            return "加密失败";
        }
    }






//    /**
//     * 解密
//     */
//    public static String getDecryptString(byte[] privateBytes, byte[] publicBytes, String userId, byte[] cipherBytes) {
//        try {
//            String q = "qwe";
//            byte[] privateByte =  Base64.decode(q, Base64.NO_WRAP);
//
//
//            byte[] plainBytes = SM2Util.decrypt(privateBytes, cipherBytes);
//            String plainText = null == plainBytes ? null : new String(plainBytes);
//            return plainText;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "解密失败";
//        }
//    }

    /**
     * 解密
     */
    public static String getDecryptString(byte[] privateBytes, byte[] publicBytes, String userId,String encryption) {
        try {

            byte[] cipherBytes =  Base64.decode(encryption, Base64.NO_WRAP);
            byte[] plainBytes = SM2Util.decrypt(privateBytes, cipherBytes);
            String plainText = null == plainBytes ? null : new String(plainBytes);
            return plainText;
        } catch (Exception e) {
            e.printStackTrace();
            return "解密失败";
        }
    }


    /**
     * 生成密钥对，公钥和私钥
     *
     * @return 密钥对
     */
    public static SM2Impl.SM2KeyPair createKeyPair() {
        return new SM2Impl().genKeyPair();
    }

    /**
     * 获取私钥（经过Base64转码过）
     *
     * @param keyPair 秘钥对
     * @return 被Base64转码加密过的私钥
     */
    public static String getPrivateKey(SM2Impl.SM2KeyPair keyPair) {
        if (null == keyPair) {
            return null;
        }

        BigInteger privateKeyInteger = keyPair.getPrivateKey();
        byte[] privateKeyBytes = SM2.bigInteger2Bytes(privateKeyInteger);
        return (null == privateKeyBytes ? null : Base64.encodeToString(privateKeyBytes, Base64.NO_WRAP));
    }

    /**
     * 获取公钥（经过Base64转码加密过）
     *
     * @param keyPair 密钥对
     * @return 被Base64转码加密过的公钥
     */
    public static String getPublicKey(SM2Impl.SM2KeyPair keyPair) {
        if (null == keyPair) {
            return null;
        }

        byte[] publicKeyBytes = keyPair.getPublicKey().getEncoded();
        return (null == publicKeyBytes ? null : Base64.encodeToString(publicKeyBytes, Base64.NO_WRAP));
    }

    public static byte[] encrypt(byte[] publicKey, byte[] data) throws IOException {
        return new SM2Impl().encrypt(data, publicKey);
    }

    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData) throws IOException {
        return new SM2Impl().decrypt(encryptedData, privateKey);
    }

    public static byte[] sign(byte[] userId, byte[] privateKey, byte[] sourceData) throws IOException {
        return new SM2Impl().sign(userId, privateKey, sourceData);
    }

    public static boolean verifySign(byte[] userId, byte[] publicKey, byte[] sourceData, byte[] signData) throws IOException {
        return new SM2Impl().verifySign(userId, publicKey, sourceData, signData);
    }
}
