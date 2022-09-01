package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Solution {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb"));
//        System.out.println(compareMD5(null, "5a47d12a2e3f9fecf2d9ba1fd98152eb"));
//        oos.writeObject("");
//        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb"));
//        System.out.println(compareMD5(bos, null));

    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        if (byteArrayOutputStream == null || md5 == null) return false;
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(byteArrayOutputStream.toByteArray());

        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder hex = new StringBuilder();
        for(byte b : digest) {
            String s = Integer.toHexString(0xff & b);
            s = (s.length() == 1) ? "0" + s : s;
            hex.append(s);
        }

        System.out.println(hex);
        return hex.toString().equals(md5);
    }
}
