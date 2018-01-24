package com.mokylin.cabal.common.utils;

import flex.messaging.io.SerializationContext;
import flex.messaging.io.amf.Amf3Input;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/10 18:13
 * 项目: cabal-tools
 */
public class AsFileParseUtils {

    private static final Logger log = LoggerFactory.getLogger(AsFileParseUtils.class);

    public static List parse(File file){
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            Amf3Input amf3In = new Amf3Input(new SerializationContext());
            amf3In.setInputStream(is);
            Object[] array = (Object[])amf3In.readObject();
            return Arrays.asList(array);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
        }
        finally{
            IOUtils.closeQuietly(is);
        }
        return Collections.EMPTY_LIST;
    }

    public static List parse(String fileName){
    	File file = new File(fileName);
        if(file.isFile()&& file.exists()){
        return AsFileParseUtils.parse(file);
        }else{
        	return null;
        }
    }
}
