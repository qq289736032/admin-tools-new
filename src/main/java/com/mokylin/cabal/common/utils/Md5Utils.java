package com.mokylin.cabal.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author DaoZheng Yuan
 * @description MD5工具类
 * @created 2010-12-17下午12:52:28
 */
public class Md5Utils {

    private static final Logger log = LoggerFactory.getLogger(Md5Utils.class);


    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


    /**
     * MD5加密返回32位
     *
     * @param passText
     * @return
     */
    public static String md5To32(String passText) {
        StringBuffer buff = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passText.getBytes());
            byte[] bt = md.digest();

            int i;
            for (int offset = 0; offset < bt.length; offset++) {
                i = bt[offset];
                if (i < 0) i += 256;
                if (i < 16) buff.append("0");
                buff.append(Integer.toHexString(i));
            }

            return buff.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5加密返回16
     *
     * @param passText
     * @return
     */
    public static String md5To16(String passText) {
        StringBuffer buff = new StringBuffer("");
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passText.getBytes());
            byte[] bt = md.digest();

            int i;
            for (int offset = 0; offset < bt.length; offset++) {
                i = bt[offset];
                if (i < 0) i += 256;
                if (i < 16) buff.append("0");
                buff.append(Integer.toHexString(i));
            }
            return buff.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String MD5EncodeFile(File file){
        String result = null;
        try{
            FileInputStream fis = new FileInputStream(file);

            result = MD5EncodeFile(fis);
        }catch (Exception e) {
            log.error("", e);
        }
        return result;
    }
    public static String MD5EncodeFile(InputStream inputStream){
        String result = null;
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] b = new byte[2048];
            int num = inputStream.read(b);
            while( num != -1 ){
                md.update(b, 0, num);
                num = inputStream.read(b);
            }

            result = byteArrayToHexString(md.digest());

            inputStream.close();
        }catch (Exception e) {
            log.error("", e);
        }

        return result;
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }



    public static void main(String[] args) {
        String Str16 = md5To16("admin");
        System.out.println(Str16);
        String Str32 = md5To32("coopname=baidu&serverid=s1&userid=u111&key=3de397e1013&timestamp=1375409569425");
        System.out.println(Str32);
    }

}
