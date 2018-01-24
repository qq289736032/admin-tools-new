package com.mokylin.cabal.common.support;


import com.mokylin.cabal.common.config.Global;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * 根据条件获取相关类
 */
public class ClassHelper {

    /**
     * 获取 ClassScanner
     */
    private static final ClassScanner classScanner = InstanceFactory.getClassScanner();

    /**
     * 获取基础包名中的所有类
     */
    public static List<Class<?>> getClassList() {
        return classScanner.getClassList(Global.BASE_PACKAGE);
    }

    /**
     * 获取基础包名中指定父类或接口的相关类
     */
    public static List<Class<?>> getClassListBySuper(Class<?> superClass) {
        return classScanner.getClassListBySuper(Global.BASE_PACKAGE, superClass);
    }

    /**
     * 获取基础包名中指定注解的相关类
     */
    public static List<Class<?>> getClassListByAnnotation(Class<? extends Annotation> annotationClass) {
        return classScanner.getClassListByAnnotation(Global.BASE_PACKAGE, annotationClass);
    }
}
