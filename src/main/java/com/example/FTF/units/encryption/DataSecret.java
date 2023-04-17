package com.example.FTF.units.encryption;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class DataSecret {


    public static byte[] encode(byte[] txt) throws UnsupportedEncodingException {

        return Base64.encodeBase64(txt);
    }

    public static byte[] decode(String txt){

        return Base64.decodeBase64(txt);
    }
}