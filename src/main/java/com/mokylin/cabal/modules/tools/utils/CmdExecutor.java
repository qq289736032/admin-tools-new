package com.mokylin.cabal.modules.tools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CmdExecutor
{
	
	private static final Logger logger = LoggerFactory.getLogger(CmdExecutor.class);
	
	private static List<String> cmdInfo = new ArrayList<>();
	
	public static String getCmdInfo()
	{
		StringBuffer buffer = new StringBuffer();
		for (String s : cmdInfo) {
			buffer.append(s);
			buffer.append("\n");
		}
		return buffer.toString();
	}
	
	public static String execSshCmd(String cmd, String host, String...params) {
		StringBuffer sbuf = new StringBuffer(128);
		sbuf.append("ssh root@").append(host).append(" ").append(cmd); //拼接ip和命令 ssh root@192.168.1 zttest
		for (String param : params) {
			sbuf.append(" ").append(param);//拼接参数 ssh root@192.168.1 zttest 1 2 3
		}
		String rlt = "";
		//末尾引号 ssh root@192.168.1 zttest 1 2 3
		String[] shell = new String[] {"sh","-c", sbuf.toString() };
		logger.info("--开始执行命令: sh -c " + sbuf.toString());
		try {
			Process process = Runtime.getRuntime().exec(shell);

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				//String str = new String(line.getBytes(), "utf-8");
				logger.info("--exe cmd info log:" + line);
				rlt += line;
			}	
			
		    //读取标准错误流  
		    BufferedReader brError = new BufferedReader(new InputStreamReader(process.getErrorStream()));  
		    String errline = null;  
		    while ((errline = brError.readLine()) != null) {  
		    	logger.info("--exe cmd error log:" + errline);
		    	rlt += errline;
		    } 
			if (process.waitFor() != 0) {
				logger.info("--执行命令失败，rlt:"+process.waitFor()+",cmd: sh -c " + sbuf.toString());	
				rlt += "--执行命令失败，rlt:"+process.waitFor()+",cmd: sh -c " + sbuf.toString();
			}
			reader.close();
		} catch (Exception e) {
			logger.error("exe cmd error!!:" + e.getMessage(), e);
			
		}
		return rlt;
	}
	public static String execCmd(String cmds)
	{
		logger.info("开始执行脚本命令："+cmds);
		cmdInfo.clear();
		try
		{
			Process process = Runtime.getRuntime().exec(cmds);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				String str = new String(line.getBytes(), "utf-8");
				
				if ((!str.contains("WARNING")) && (!str.contains("INFO")) && (!str.contains("extracting")) && 
				(!str.contains("inflating")) && (!str.contains("adding")) && (!str.contains("creating")) && 
				(!str.contains("echo")) && (!str.contains("main"))) {
					cmdInfo.add(str);
				}
				logger.info("--exe cmd log:" + str);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("exe cmd error!!:" + e.getMessage(), e);
		}
		return cmdInfo.toString();
	}
}