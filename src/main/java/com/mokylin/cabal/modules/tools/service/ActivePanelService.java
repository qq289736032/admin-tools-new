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
import com.mokylin.cabal.modules.sys.entity.OperationType;
import com.mokylin.cabal.modules.tools.entity.ConfigFile;

/**
 * 
 * @author Administrator
 * 活动面板资源配置
 */
@Component
public class ActivePanelService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ActivePanelService.class);
	
	 @Resource
	    protected ToolDaoTemplate toolDaoTemplate;

	    private static final String FILE_TYPE = "activePanel.txt";  //资源线
	    private static Map<String, OperationType> activePanelMap = new HashMap<String, OperationType>();   //资源线
	    
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
	                      if (event.length == 3) {
	                        OperationType operationType = new OperationType(event[1], event[2]);
	                        activePanelMap.put(event[0], operationType);
	                      } else if(event.length == 2) {
	                        OperationType operationType = new OperationType(event[1], event[1]);
	                        activePanelMap.put(event[0], operationType);
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
	    	activePanelMap.clear();
	        init();
	    }

	    public static String getActivePanel(String key) {
	        if(!activePanelMap.isEmpty()){
	     		String operaType = activePanelMap.get(key) == null? key: activePanelMap.get(key).getChDes();
	     		return operaType;
	     	}else{
	     		return key;
	     	}
	         
	     }

}
