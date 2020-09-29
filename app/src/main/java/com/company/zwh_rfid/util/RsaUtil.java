package com.company.zwh_rfid.util;

import com.zwh.binary.Base64;

import org.apache.commons.collections.map.HashedMap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * RSA2工具类
 * Created by zhr on 18/3/5.
 */
public class RsaUtil {
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    /**
     * 公钥Key
     */
    public static final String PUBKEY = "pubkey";
    /**
     * 私钥Key
     */
    public static final String PRIKEY = "prikey";

    /**
     * 生成RSAKey
     *
     * @return
     */
    public static Map<String, String> generateKeyPair() {
        Map<String, String> rkmap = new HashedMap();
        try {
            // 1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            rkmap.put(PUBKEY, com.zwh.binary.Base64.encodeBase64String(rsaPublicKey.getEncoded()));
            rkmap.put(PRIKEY, Base64.encodeBase64String(rsaPrivateKey.getEncoded()));
            return rkmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rkmap;
    }

    /**
     * RSA签名
     *
     * @param content       待签名数据
     * @param privateKey    商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String input_charset) {
        try {
            byte[] decode = Base64.decodeBase64(privateKey);
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decode);
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(input_charset));

            byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA验签名检查
     *
     * @param content        待签名数据
     * @param sign           签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset  编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(ali_public_key);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(input_charset));

            boolean bverify = signature.verify(Base64.decodeBase64(sign));
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解密
     *
     * @param content       密文
     * @param private_key   商户私钥
     * @param input_charset 编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decodeBase64(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;
        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;
            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }
            writer.write(cipher.doFinal(block));
        }
        return new String(writer.toByteArray(), input_charset);
    }

    /**
     * 加密
     *
     * @param content    密文
     * @param public_key 商户私钥
     * @return 解密后的字符串
     */
    public static String encode(String content, String public_key) throws Exception {
        PublicKey pubkey = getPublicKey(public_key);

        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pubkey);
        byte[] result = cipher.doFinal(content.getBytes());

        return Base64.encodeBase64String(result);
    }

    /**
     * 得到私钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.decodeBase64(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 得到公钥
     *
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = android.util.Base64.decode(key, android.util.Base64.DEFAULT);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        return publicKey;
    }

    public static void main(String[] args) {
        String pubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAISgAQSP7m6hWI8Zz1O3y2M2+B1a9jhd1Gn/zr+DwSfMD00FsNvNBfpTDdKkIzrnlk6P5a9KwPYV9vjGc9U4ms0CAwEAAQ==";
        String priKey = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAhKABBI/ubqFYjxnPU7fLYzb4HVr2OF3Uaf/Ov4PBJ8wPTQWw280F+lMN0qQjOueWTo/lr0rA9hX2+MZz1TiazQIDAQABAkAK/hgXgLaKhOUKN2Fv1vQMrSmzhS6TTXOltYY1aX5BBhJS6hrPcmwVq44Y9dlj7Haz+iCUNJ27UB+mafY7NRwBAiEAuURYEdenjR2QdABN+V4IrUJt9prFVZeqoITDIyGIMmECIQC3QogGn/z2yMt4zsHhxHcayz7AB9WiavZR/2+t9eNX7QIgY2ZRwjDsSoAkMri/HR3gleTgTsE3Qvb+quTQNZcWFcECIC49Q0UoqRM7pttcj97/S24fSj35vBW3F1+pK+yg1jQhAiB/IWXvm9uzGZHJmJu4c1KTVEtXc8GbgidzWXlX6xS/3g==";
        try {
            String aa = "1106102175026778112^";
            String end_aa = encode(aa, pubKey);
            String dec_aa = decrypt(end_aa, priKey, "UTF-8");
            System.out.println("aa:" + aa);
            System.out.println("end_aa:" + end_aa);
            System.out.println("dec_aa:" + dec_aa);
            String front_aa = decrypt("Z5mO3mWQDJ3mWYw8XJO1Tucc5zL2N3+JNDPvZkMWchcXX2/BF8rWO3vwGelI3EpFRRd+3kfdfA9DNxYiPkSoqg==", priKey, "UTF-8");
            System.out.println("front_aa:" + front_aa);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
