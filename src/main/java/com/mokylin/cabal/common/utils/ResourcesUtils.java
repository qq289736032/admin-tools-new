package com.mokylin.cabal.common.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/25
 * admin-tools
 */
public class ResourcesUtils {

    /**获取classpath中可能存在的资源，以流的形式返回。*/
    public static InputStream getResourceAsStream(String resourcePath) throws IOException {
        resourcePath = formatResource(resourcePath);
        InputStream inStream = getCurrentLoader().getResourceAsStream(resourcePath);
        if (inStream == null)
            inStream = ClassLoader.getSystemClassLoader().getResourceAsStream(resourcePath);
        return inStream;
    }

    private static ClassLoader getCurrentLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static String formatResource(String resourcePath) {
        if (resourcePath != null && resourcePath.length() > 1) {
            if (resourcePath.charAt(0) == '/') {
                resourcePath = resourcePath.substring(1);
            }
        }
        return resourcePath;
    }
}
