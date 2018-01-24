package com.mokylin.cabal.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/13 19:19
 * 项目: admin-tools
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);
    /**
     * 通过反射创建实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtil.loadClass(className);
            instance = (T) commandClass.newInstance();
        } catch (Exception e) {
            logger.error("创建实例出错！", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * message处使用的从arg转换成long类型
     *
     * @param arg
     * @return
     */
    public static long arg2long(Object arg) {
        if (arg instanceof Long) {
            return (Long) arg;
        } else if (arg instanceof Double) {
            return ((Double) arg).longValue();
        } else if (arg instanceof BigDecimal) {
            return ((BigDecimal) arg).longValue();
        } else if (arg instanceof Integer) {
            return ((Integer) arg).longValue();
        }else if(arg instanceof String){
            return Long.parseLong((String)arg);
        }
        return 0L;
    }

}
