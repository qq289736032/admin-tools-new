package com.mokylin.cabal.common.utils;

import java.io.File;

public class WebPathUtil {
	
	private static String resourcePath = null;
	private static String webContentPath = null;
	/**
	 * classes文件目录
	 * @return
	 */
	public static String getWebResourcePath(){
		if( resourcePath == null ){
			resourcePath = WebPathUtil.class.getClassLoader().getResource("").getPath();
			if( !resourcePath.endsWith("/") && !resourcePath.endsWith("\\") ){
				resourcePath = resourcePath + "/";
			}
		}
		return resourcePath;
	}
	/**
	 * webContent目录
	 * @return
	 */
	public static String getWebContentPath(){
		if( webContentPath == null ){
			String parentPath = getWebResourcePath();
			webContentPath = new File(parentPath).getParentFile().getParent(); 
			if( !webContentPath.endsWith("/") && !webContentPath.endsWith("\\") ){
				webContentPath = webContentPath + File.separator;
			}
		}
		return webContentPath;
	}
	
}
