package com.mokylin.cabal.modules.tools.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.utils.WebPathUtil;
import com.mokylin.cabal.modules.tools.entity.ConfigFile;


/**
 * 任务类型配置类
 * @author Administrator
 *
 */
@Component
public class TaskTypeservice {
   
	
	private static final Logger LOG = LoggerFactory.getLogger(TaskTypeservice.class);
	
	    @Resource
	    protected ToolDaoTemplate toolDaoTemplate;

	    private static final String FILE_TYPE = "taskType.txt"; //任务类型
	    private static Map<String, String> taskTypeMap = new HashMap<String, String>();
	    
	    @PostConstruct
	    public void init() {
	        try {
	            String contextPath = WebPathUtil.getWebContentPath();
	            Map<String, Object> parameter = new HashMap<String, Object>();

	            parameter.put("fileType", FILE_TYPE);
	            ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
	            if (configFile == null) {
	                return;
	            }
	            String filePath = configFile.getFilePath();
	            String fileName = configFile.getFileName();
	            String fullFileName = contextPath + filePath + fileName;
	            fullFileName = URLDecoder.decode(fullFileName, "utf-8");//关键啊 ！
	            File file = FileUtils.getFile(fullFileName);
	            if (file.isFile() && file.exists()) {
	                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
	                String data = null;
	                while ((data = br.readLine()) != null) {
	                    String[] event = data.split("\t");
	                    if(event.length==2){
	                    	taskTypeMap.put(event[0], event[1]);
	                    }else{
	                    	taskTypeMap.put(event[0], event[0]);
	                    }
	                }
	            } else {
	                LOG.info("找不到" + fileName + "配置文件");
	            }
	        } catch (Exception e) {
	            LOG.error("解析事件类型配置文件出错", e);
	        }

	    }

	    public void refresh() {
	    	taskTypeMap.clear();
	        init();
	    }

	   public static String getTaskType(String key){
		   if(!taskTypeMap.isEmpty()){
			   String taskType = taskTypeMap.get(key) == null? key: taskTypeMap.get(key).toString();
	    	   return taskType;
		   }else{
			   return key;
		   }
	   }
	
}
