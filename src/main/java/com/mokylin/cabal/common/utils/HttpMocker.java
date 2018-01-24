package com.mokylin.cabal.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

public class HttpMocker {

	public static String requestMockGet(String requestUrl,String[]... params){
		
		String destUrl = requestUrl;
		String paramStr = null;
		if(null!=params){
			int i =0;
			StringBuilder builder = new StringBuilder(); 
	        for(String[] param : params){
	        	if(i==0){
	        		++i;
	        		builder.append(param[0]).append("=").append(param[1]);
	        	}else{
	        		builder.append("&").append(param[0]).append("=").append(param[1]);
	        	}
	        }
	        paramStr = builder.toString();
		}

//		System.out.println("request url:"+requestUrl+"?"+paramStr);
		URL url;
		try {
			if( paramStr.length() > 0 ){
				url = new URL(destUrl+"?"+paramStr);
			}else{
				url = new URL(destUrl);
			}
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDefaultUseCaches(false);
			connection.connect();
			
			BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String s = null;
			StringBuilder rspBuilder = new StringBuilder();
			while(null != (s = reader.readLine())){
				rspBuilder.append(s);
			}
			in.close();
			
			return rspBuilder.toString();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static String requestMockPost(String requestUrl,String[]... params) throws Exception{
		
		String destUrl = requestUrl;
//		System.out.println("request url:"+requestUrl);
		URL url;
		try {
			
			url = new URL(destUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);  
			
			JSONObject jsonObj = new JSONObject();
			
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());     
			if( null != params && params.length > 0 ){
		        for(String[] param : params){
		        	jsonObj.put(param[0], param[1]);
		        }
		        out.write(jsonObj.toString());
			}
			
			out.flush();     
			out.close();
			
			BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			String s = null;
//			System.out.print("response:");
			StringBuilder rspBuilder = new StringBuilder();
			while(null != (s = reader.readLine())){
				rspBuilder.append(s);
//				System.out.println(s);
			}
			in.close();
			
			return rspBuilder.toString();
		}catch (Exception e) {
			throw new Exception(e);
		}

	}
	
}
