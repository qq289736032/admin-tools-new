package com.mokylin.cabal.modules.tools.service;

import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.utils.WebPathUtil;
import com.mokylin.cabal.modules.sys.entity.OperationType;
import com.mokylin.cabal.modules.tools.entity.ConfigFile;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作类型初始化
 *
 * @author Administrator
 */
@Component
public class OperationTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(OperationTypeService.class.getName());

    @Resource
    protected ToolDaoTemplate toolDaoTemplate;

    private static final String FILE_TYPE = "operation.txt"; //操作类型
    private static Map<String, OperationType> operationMap = new HashMap<String, OperationType>();

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
            if (!file.exists()) {
            	file = FileUtils.getFile(contextPath + "WEB-INF" + File.separator + filePath + fileName);
            }
        	if (file.isFile() && file.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String data = null;
                while ((data = br.readLine()) != null) {
                    String[] event = data.split("\t");
                      if (event.length == 3) {
                        OperationType operationType = new OperationType(event[1], event[2]);
                        operationMap.put(event[0], operationType);
                      } else if(event.length == 2) {
                        OperationType operationType = new OperationType(event[1], event[1]);
                        operationMap.put(event[0], operationType);
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
        operationMap.clear();
        init();
    }


    public static Map<String, OperationType> getOperaTypeMap() {
        return operationMap;
    }

    public static String getOperationType(String key) {
       if(!operationMap.isEmpty()){
    		String operaType = operationMap.get(key) == null? key: operationMap.get(key).getChDes();
    		return operaType;
    	}else{
    		return key;
    	}
        
    }

}
