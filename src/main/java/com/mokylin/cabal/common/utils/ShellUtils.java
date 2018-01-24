package com.mokylin.cabal.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/4/1 14:21
 * 项目: admin-tools
 */
public class ShellUtils {

    private static final Logger logger = LoggerFactory.getLogger(ShellUtils.class);


    public static void execute(String command){

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (Exception e) {
            logger.error("",e);
        }
    }


    public static void exec(String path){
        String[] cmd = {"/bin/sh","-c",path};
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            logger.error("",e);
        }
    }

    public static String executeWithReturn(String command){
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
