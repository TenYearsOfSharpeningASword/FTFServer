package com.example.FTF.units.encryption;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class SecretKey
{
    //公钥
    public static final String PUB_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtLksKOUExXa77" +
                    "MB6cHPoIZwKRTXiF6mpkCrBHhXvaGaw/90C6RSYzfieX4lAlFLiM" +
                    "KfxsunBkWEw9wmXh+N8RRUEy+ICMMSBj/O6BL+dm5ogpnmFXjoa0i" +
                    "fhHd0g7msIlsdLnkziftacH6vsbxF8x1AJ9yBQHCM3IQnaw6Naul" +
                    "/m7DZi01XyQYbJVhpjcR8ugVZYZrBjSuh1e7R2XlDYeZolgjZJQM" +
                    "qywjzKP472MyTZ3cIea3nerh2YP9eHxTGZi+Rx8zVVsvR/z4GU/Jl" +
                    "ReGXdntrrRoNpl+H0jrsXpMrigcAgi9nHfX3rByKE5A86cTthhtxP" +
                    "043oB7Z2ynKxtQIDAQAB";
    //撕咬
    public static final String PRI_KEY =
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC0uSwo5QTFd" +
                    "rvswHpwc+ghnApFNeIXqamQKsEeFe9oZrD/3QLpFJjN+J5fiUCUUu" +
                    "Iwp/Gy6cGRYTD3CZeH43xFFQTL4gIwxIGP87oEv52bmiCmeYVeOhrS" +
                    "J+Ed3SDuawiWx0ueTOJ+1pwfq+xvEXzHUAn3IFAcIzchCdrDo1q6X+" +
                    "bsNmLTVfJBhslWGmNxHy6BVlhmsGNK6HV7tHZeUNh5miWCNklAyrLCP" +
                    "Mo/jvYzJNndwh5red6uHZg/14fFMZmL5HHzNVWy9H/PgZT8mVF4Zd2e" +
                    "2utGg2mX4fSOuxekyuKBwCCL2cd9fesHIoTkDzpxO2GG3E/TjegHtnb" +
                    "KcrG1AgMBAAECggEBALGYowfQ/15ZbxWalPf7PywAHqui5vsf9IzDJy" +
                    "I8phcvi1L1Ss3oOr12S5RQ2dConrT35XJPerQ2EZtCptGYQN/WZu9Au6" +
                    "xW/4eKGMxfzS9DSECwfNp7OS46mGPzkVAaiMc7NFls7st8dNI+7kq7oa" +
                    "ZwRkq/S1pLVPq3p27IEfnBZ8OJ2gmYWCOLDaGP4d53Y6q+Fc6fzKEvhm" +
                    "5vEGDtjcz/03rLc6WZpLeJZ2h4IRKBS6l2ixgiabpBqC+fJWySY4BcV1" +
                    "ELuCfNDWGX2Ahv3w0U2Q21eH5kU4qwSsiEe9ynm8wyGH7bunVirs9VQrH" +
                    "5J/844VoN/KugbqFU7/n+q6UCgYEA960ZVaw3iwApuSPpFFVx5L2GT9u" +
                    "+pFGw8mbpa1weP14N18GCLIdt+m3xyfaVYciAx1CqQQhKfi1OFdOspkZ7" +
                    "4Hz5c5upvlmkGEMokPWQHQZojS8fGQz2+Nc1V5tYBcMoKLnUO7oRa6Yc7" +
                    "MxEjcnL56jX2oXitgmDWELfSpRGLmMCgYEAuswKJOXCwu86W2uAbtzeDG8" +
                    "/AiTqlggH69Z2sN9IIrLxuSJUQASDBotGNyj3YSMN4KeAihjiBkvn+0nQg" +
                    "nyBJk6uWkgQCE200sxeIQgfGuHmtkTdkmUwxRr8evQdBHce16RGO/R4qMr" +
                    "VU+gJTeIZ6Yk2ChxKJo9zJWE4tpiz7wcCgYBRCm6zvboe3VrJnZpycNLnia" +
                    "wdDUX/9krzxgCyhF3RVjEpW8QAVgACZV8sOmb+q+CXVymeRdw6FRDry2s+" +
                    "rOlztvDYhyKvih1LYw4vAGq7VJF5QOXoCHn3SlPF6qqOmCDX0pZDRMrWcn" +
                    "Ace2ojCtiRYIngktZvwgUcKCVv/bxJCQKBgQC1hLNS2aaiESCPV+7NTHnGc" +
                    "ZA0ADWpp5Iy/gwGomMRvPwiYJaoQvokMNEBpyCFVzAUIBFLoRR68+YxDbC+" +
                    "LEjANfM9rgtOVlyh/5B03tSDJgQxGMiLiyOSVkAFJxTPkwX4nRzXBCEAPWK" +
                    "CD9iCw3VUuE44EveHcxx8tXYMEmW/nwKBgHJWW2xcJqbg960vf03/HgopFc" +
                    "FsCP8zcMoRsUYU0Ux16Pb8xdEncC7/MCynqd5+WuF4zJKAdcgHfURF31vesW" +
                    "SAQQHNsqXVo7m0Vk6d8/IyoWIdlyAeQIINGxxQcMJIWBG2eKHYbC7XFO611G" +
                    "qt7s2lGecYvfzy/QHe/t8/Oize";


    public static void main(String[] args)
            throws UnsupportedEncodingException,
            NoSuchAlgorithmException {

//        String content = "123456";
//        String encrypted = RSA.encrypt(content, PUB_KEY);
//        System.out.println(encrypted);
//        System.out.println(RSA.decrypt(encrypted, PRI_KEY));
        genKeyPair();
    }

    //生成随机密钥
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");

        // 初始化密钥对生成器，密钥大小为96-2048位
        keyPairGen.initialize(1024, new SecureRandom());

        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   	// 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  		// 得到公钥

        // 得到公钥字符串
        String publicKeyStr = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyStr = new String(Base64.encodeBase64((privateKey.getEncoded())));

        System.out.println("随机生成的公钥为:" + publicKeyStr);
        System.out.println("随机生成的私钥为:" + privateKeyStr);
    }
}
