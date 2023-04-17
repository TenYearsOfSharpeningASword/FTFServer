package com.example.FTF.units.encryption;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSA {

    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";

    /**
     * 加密
     * @param data 对象数据
     * @param key 密钥
     * @throws UnsupportedEncodingException 异常
     */
    public static String encrypt(String data, String key) throws UnsupportedEncodingException {

        byte[] decode = DataSecret.decode(key);
        byte[] result = new byte[]{0};

        try {

            RSAPublicKey publicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(decode));
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        }
        catch (NoSuchAlgorithmException
               | InvalidKeySpecException
               | NoSuchPaddingException
               | BadPaddingException
               | IllegalBlockSizeException
               | InvalidKeyException e) {

            e.printStackTrace();
        }

        return new String(DataSecret.encode(result));
    }

    //解密
    public static String decrypt(String encrypted, String key){

        byte[] content = DataSecret.decode(encrypted);
        byte[] decoded = DataSecret.decode(key);

        byte[] result = new byte[]{0};

        try {

            RSAPrivateCrtKey priKey = (RSAPrivateCrtKey)KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(decoded));
            Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            result = cipher.doFinal(content);
        }
        catch (InvalidKeySpecException
               | NoSuchAlgorithmException
               | NoSuchPaddingException
               | InvalidKeyException
               | BadPaddingException
               | IllegalBlockSizeException e){
            e.printStackTrace();
        }

        return new String(result);
    }
}