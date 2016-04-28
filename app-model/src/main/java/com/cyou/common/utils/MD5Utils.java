
package com.cyou.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class MD5Utils {
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    public static Map<String, String> getDirMD5(File file, boolean listChild) {
        if (!file.isDirectory()) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        String md5;
        File files[] = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            if (f.isDirectory() && listChild) {
                map.putAll(getDirMD5(f, listChild));
            } else {
                md5 = getFileMD5(f);
                if (md5 != null) {
                    map.put(f.getPath(), md5);
                }
            }
        }
        return map;
    }

    public static String getMD5(byte[] bytes) {
        String md5Str = "";
        try {
            MessageDigest md5;

            md5 = MessageDigest.getInstance("MD5");

            byte[] md5Bytes = md5.digest(bytes);
            StringBuffer hexValue = new StringBuffer();

            for (int m = 0; m < md5Bytes.length; m++) {
                int val = (md5Bytes[m]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            md5Str = hexValue.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5Str;
    }

    public static String digest(String text) {
        try {
            byte[] b = MessageDigest.getInstance("md5").digest(text.getBytes());
            String hs = "";
            String stmp = "";
            for (int n = 0; n < b.length; n++) {
                stmp = (Integer.toHexString(b[n] & 0XFF));
                if (stmp.length() == 1) {
                    hs = hs + "0" + stmp;
                } else {
                    hs = hs + stmp;
                }
            }
            return hs.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringMD5_16(String plainText) {
        String md5 = getStringMD5_32(plainText);
        if (md5.length() >= 24) {
            md5 = md5.substring(8, 24);
        }
        return md5;
    }

    public static String getStringMD5_32(String plainText) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
        } catch (Exception e) {

            return null;
        }
        return encodeHex(md.digest());
    }

//    public static String stringToMd5(String s) {
//        byte[] value = s.getBytes();
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            md.update(value);
//            byte[] temp = md.digest();
//            StringBuilder sb = new StringBuilder();
//            for (byte b : temp) {
//                sb.append(Integer.toHexString(b & 0xff));
//            }
//            String md5Version = sb.toString();
//            
//
//            return md5Version;
//        } catch (NoSuchAlgorithmException e) {
//
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String encodeHex(byte[] data) {

        if (data == null) {

            return null;
        }

        final String HEXES = "0123456789abcdef";
        int len = data.length;
        StringBuilder hex = new StringBuilder(len * 2);

        for (int i = 0; i < len; ++i) {

            hex.append(HEXES.charAt((data[i] & 0xF0) >>> 4));
            hex.append(HEXES.charAt((data[i] & 0x0F)));
        }

        return hex.toString();
    }
}
