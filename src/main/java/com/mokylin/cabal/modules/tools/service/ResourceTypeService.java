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

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.utils.WebPathUtil;
import com.mokylin.cabal.modules.tools.entity.ConfigFile;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/10 18:17
 * 项目: cabal-tools
 * 资源线类型初始化
 */
@Component
public class ResourceTypeService {

    private static final Logger LOG = LoggerFactory.getLogger(ResourceTypeService.class.getName());

    @Resource
    protected ToolDaoTemplate toolDaoTemplate;

    private static final String FILE_TYPE = "resourceType.txt";  //资源线
    private static Map<String, String> resourceMap = new HashMap<String, String>();   //资源线

    @PostConstruct
    public void init() {

        try {
            String contextPath = WebPathUtil.getWebContentPath();
            Map<String, Object> parameter = new HashMap<String, Object>();

            parameter.put("fileType", FILE_TYPE);
            ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
            if(configFile==null){
                return ;
            }
            String filePath = configFile.getFilePath();
            String fileName = configFile.getFileName();
            String fullFileName = contextPath + filePath + fileName;
            fullFileName=URLDecoder.decode(fullFileName,"utf-8");//关键啊 ！
            File file = FileUtils.getFile(fullFileName);
            if (!file.exists()) {
            	file = FileUtils.getFile(contextPath + "WEB-INF" + File.separator + filePath + fileName);
            }
            if (file.isFile() && file.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String data = null;
                while ((data = br.readLine()) != null) {
                    String[] event = data.split("\t");
                    resourceMap.put(event[0], event[1]);
                }
            } else {
                LOG.info("找不到" + fileName + "配置文件");
            }

        } catch (Exception e) {
            LOG.error("解析事件类型配置文件出错", e); 
        }
    }

    public void refresh() {
    	resourceMap.clear();
        init();
    }

    /**
     * 整个事件map 用于excel 导出时 替换
     */
    public static Map resourceMap() {
        return resourceMap;
    }

    //根据key找到资源西线名称
    public static String getResourceType(String key){
    	if(!resourceMap.isEmpty()){
      		String resourceType = resourceMap.get(key) == null? key: resourceMap.get(key).toString();
      		return resourceType;
      	}else{
      		return key;
      	}
    }


}
