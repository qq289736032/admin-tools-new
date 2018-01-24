package com.mokylin.cabal.common.utils;

import com.alibaba.fastjson.JSONObject;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/24 13:23
 * 项目: cabal-tools
 */
public class JsonUtils {

    /**
     * 根据JSONObject字符串，以及key得到对应的value
     * @param obj
     * @param key
     * @return
     */
    public static String parseObject(String obj, String key){
        if(StringUtils.isEmpty(obj)){
            return "";
        }
        if(!obj.contains(key)){
            return "";
        }
        JSONObject object = JSONObject.parseObject(obj);
        return object.get(key).toString();
    }

    
    public static int parseObj2int(String obj, String key){
        if(StringUtils.isEmpty(obj)){
            return 0;
        }
        if(!obj.contains(key)){
            return 0;
        }
        JSONObject object = JSONObject.parseObject(obj);
        return Integer.parseInt(object.get(key).toString());
    }
}
