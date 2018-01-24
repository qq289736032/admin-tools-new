package com.mokylin.cabal.modules.tools.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.utils.AsFileParseUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.utils.WebPathUtil;
import com.mokylin.cabal.modules.tools.entity.ConfigFile;
import com.mokylin.cabal.modules.tools.vo.AttachmentGoods;
import com.mokylin.cabal.modules.tools.vo.Goods;
import com.mokylin.cabal.modules.tools.vo.Item;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/10 18:17
 * 项目: cabal-tools
 */
@Component
public class GoodsAnalyzeService{

    private static final Logger LOG = LoggerFactory.getLogger(GoodsAnalyzeService.class.getName());

    @Resource
    protected ToolDaoTemplate toolDaoTemplate;

    private static final String FILE_TYPE = "goods.jat";
    
    private static final String ZHUANGBEI_FILE_TYPE = "zhuangbei.jat";
    
    private static final String ZHUANGBEIBIAO_FILE_TYPE = "ZhuangBeiBiao.jat";
    
    private static final String MEIREN_FILE_TYPE   = "meiren.jat";
    
    private static final String ZHUXIANRENWU_FILE_TYPE   = "zhuxianrenwu.jat";
    
    private static final String DUOXUANLIBAO_FILE_TYPE   = "duoxuanlibao.jat";

    private static final Map<String, Goods> goodsMap = new HashMap<String, Goods>();

    private List<Goods> xuniGoodsList = new ArrayList<Goods>();
    
    private static final Map<String, String> meirenMap = new HashMap<String, String>();
    
    private static final Map<Integer, String> baoshiPartMap = new HashMap<>();
    
    private static final Map<String, String> jobMap = new HashMap<>();
    
    private static final Map<String, String> taskMap = new HashMap<>();
    
    private static final Map<String, String> duoxuanlibaoMap = new HashMap<String, String>();

/*                           翅膀=73 
    		武器=70 
    		头盔=55 
    		发型=50 
    		护肩=45 (暂时不用)
    		上衣=40 
    		下装=25 (暂时不用)
    		鞋子=20 (暂时不用)
    		颈部=80
    		腰部=81
    		手套=82
    		戒指=85
    		装饰=90(暂时不用)
    		101=鞍具（坐骑）
    		102=缰绳（坐骑）
    		103=蹬具（坐骑）
    		104=蹄铁（坐骑）*/
    
    static{
    	baoshiPartMap.put(69, "武器");
    	baoshiPartMap.put(41, "衣服");
    	baoshiPartMap.put(29, "护腕");
    	baoshiPartMap.put(43, "护腿");
    	baoshiPartMap.put(25, "鞋子");
    	baoshiPartMap.put(55, "头盔");
    	baoshiPartMap.put(51, "头盔");
    	baoshiPartMap.put(55, "项链");
    	baoshiPartMap.put(21, "戒指");
    	baoshiPartMap.put(73, "耳环");
    	baoshiPartMap.put(42, "手镯");
    	baoshiPartMap.put(45, "护肩");
    	baoshiPartMap.put(30, "手套");
    	
    	meirenMap.put("xiaolongnv", "露易丝");
    	meirenMap.put("jiufeng", "爱丽丝");
    	meirenMap.put("qingxia", "希利亚");
    	meirenMap.put("nvwa", "米璐卡");
    	meirenMap.put("change", "卡特琳娜");
    	meirenMap.put("pipajing", "艾希");
    	meirenMap.put("baijingjing", "妮可");
    	
    	jobMap.put("A", "轩辕");
    	jobMap.put("B", "女娲");
    	jobMap.put("C", "蚩尤");
    	jobMap.put("D", "神农");
    	
    }
    
    
    @PostConstruct
    public void init(){

        try{
            String contextPath = WebPathUtil.getWebContentPath();
           
            putItem(contextPath, FILE_TYPE);
            putItem(contextPath, ZHUANGBEI_FILE_TYPE);
            putItem(contextPath, ZHUANGBEIBIAO_FILE_TYPE);
            putMeiren(contextPath, MEIREN_FILE_TYPE);
            putRenzhu(contextPath, ZHUXIANRENWU_FILE_TYPE);
            putDuoxuanLibao(contextPath, DUOXUANLIBAO_FILE_TYPE);
            
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("fileType", FILE_TYPE);
			ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
			// ConfigFile configFile = null;
			if (configFile == null) {
				LOG.error("找不到配置文件{}", FILE_TYPE);
				return;
			}
			String filePath = configFile.getFilePath();
			String fileName = configFile.getFileName();
			String fullFileName = contextPath + filePath + fileName;
			List goodsFileDataList = AsFileParseUtils.parse(fullFileName);
			if (CollectionUtils.isEmpty(goodsFileDataList)) {
				goodsFileDataList = AsFileParseUtils.parse(contextPath + "WEB-INF" + File.separator + filePath + fileName);
			}
			if (CollectionUtils.isEmpty(goodsFileDataList)) {
				LOG.error("物品表{}文件没找到或者文件格式错误", fullFileName);
				return;
			}
			// Object[] array = (Object[])goodsFileDataList.get(0);
			for (Object object : goodsFileDataList) {
				Map map = (Map) object;
				String id = MapUtils.getString(map, "id");
				String name = MapUtils.getString(map, "name");
				String maxstack = MapUtils.getString(map, "maxstack");
				boolean xuni = MapUtils.getBooleanValue(map, "xuni");
				if (StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
					continue;
				}
				Goods goods = new Goods(id, name, maxstack);
				goods.setXuniGoods(xuni);
				goods.setGoodsDesc(MapUtils.getString(map, "describe"));

				// 是虚拟物品，则加入虚拟物品列表
				if (goods.isXuniGoods()) {
					xuniGoodsList.add(goods);
				}

				goodsMap.put(goods.getId(), goods);
			}
        }catch (Exception e) {
            LOG.error("解析物品配置文件出错",  e);
        }
    }



	private void putDuoxuanLibao(String contextPath, String duoxuanlibaoFileType) {

		Map<String,Object> parameter = new HashMap<String, Object>();
    	parameter.put("fileType",duoxuanlibaoFileType);
        ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
        //ConfigFile configFile = null;
        if(configFile == null){
            LOG.error("找不到配置文件{}",duoxuanlibaoFileType);
            return;
        }
        String filePath = configFile.getFilePath();
        String fileName = configFile.getFileName();
        String fullFileName = contextPath + filePath + fileName;
        List goodsFileDataList = AsFileParseUtils.parse(fullFileName);
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
			goodsFileDataList = AsFileParseUtils.parse(contextPath + "WEB-INF" + File.separator + filePath + fileName);
		}
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
            LOG.error("物品表{}文件没找到或者文件格式错误", fullFileName);
            return ;
        }
        for (Object object : goodsFileDataList) {   	
            Map map = (Map)object;
            String id = MapUtils.getString(map, "id");
            String name = MapUtils.getString(map, "name");
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(name)) {
            	duoxuanlibaoMap.put(id, name);
			}
        }
		
		
	
	}



	private void putRenzhu(String contextPath, String zhuxianrenwuFileType) {
		Map<String,Object> parameter = new HashMap<String, Object>();
    	parameter.put("fileType",zhuxianrenwuFileType);
        ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
        //ConfigFile configFile = null;
        if(configFile == null){
            LOG.error("找不到配置文件{}",zhuxianrenwuFileType);
            return;
        }
        String filePath = configFile.getFilePath();
        String fileName = configFile.getFileName();
        String fullFileName = contextPath + filePath + fileName;
        List goodsFileDataList = AsFileParseUtils.parse(fullFileName);
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
			goodsFileDataList = AsFileParseUtils.parse(contextPath + "WEB-INF" + File.separator + filePath + fileName);
		}
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
            LOG.error("物品表{}文件没找到或者文件格式错误", fullFileName);
            return ;
        }
        for (Object object : goodsFileDataList) {   	
            Map map = (Map)object;
            String id = MapUtils.getString(map, "id2");
            String name = MapUtils.getString(map, "name2");
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(name)) {
            	taskMap.put(id, name);
			}
        }
		
		
	}



	public void refresh(){
        goodsMap.clear();
        xuniGoodsList.clear();
        System.err.println("refresh");
        init();
    }
   

    /** 获取所有物品列表 **/
    public Map<String, Goods> getGoods(){
        return goodsMap;
    }

    /** 获取虚拟物品列表 */
    public List<Goods> getXuniGoodsList(){
        return xuniGoodsList;
    }


    /**
     * 按物品名查找
     */
    public List<Goods> query(String goodsName){
        List<Goods> result = new ArrayList();
        for (Goods goods : this.getGoods().values()) {
            if (StringUtils.contains(goods.getName(), goodsName)){
                result.add(goods);
            }
        }
        return result;
    }
    
    /**
     * 根据物品id 查找物品名称
     */
    public static String getGoodName (String key){
    	   if(!goodsMap.isEmpty()){
       		String goodsName = goodsMap.get(key) == null? key: goodsMap.get(key).getName();
       		return goodsName;
       	}else{
       		return key;
       	}
    } 
    
    /**
     * 根据物品id 查找物品名称
     */
    public static String getMeirenName(String key){
    	   if(!meirenMap.isEmpty()){
       		String goodsName = meirenMap.get(key) == null? key: meirenMap.get(key);
       		return goodsName;
       	}else{
       		return key;
       	}
    }
    
    /**
     * 根据礼盒id 查询礼包名称
     */
    public static String getDuoxuanLibaoName(String key){
    	   if(!duoxuanlibaoMap.isEmpty()){
       		String goodsName = duoxuanlibaoMap.get(key) == null? key: duoxuanlibaoMap.get(key);
       		return goodsName;
       	}else{
       		return key;
       		
       	}
    }
    
    /**
     * 查询多选礼盒map
     */
    public static Map<String, String> getDuoxuanLiheMap(){
    	return duoxuanlibaoMap;
       	}
    
    /**
     * 找出对应中文职业
     */
    public static String getJobName (String job){
    	if (jobMap.containsKey(job)) {
			return jobMap.get(job);
		}
    	return "轩辕";
    } 
    
    /**
     * 按物品名查找
     */
    public static String getBaishiName(String part){
    	int baoshipart = Integer.parseInt(part);
        if (baoshiPartMap.containsKey(baoshipart)) {
			return baoshiPartMap.get(baoshipart);
		}
        return "衣服";
    }
    /**
     * 根据文件读取物品
     */
    public void putItem(String contextPath, String fileType){
    	//contextPath = "E://admin-tools-new//src//main//webapp//";
    	Map<String,Object> parameter = new HashMap<String, Object>();
    	parameter.put("fileType",fileType);
        ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
        //ConfigFile configFile = null;
        if(configFile == null){
            LOG.error("找不到配置文件{}",fileType);
            return;
        }
        String filePath = configFile.getFilePath();
        String fileName = configFile.getFileName();
        String fullFileName = contextPath + filePath + fileName;
        List goodsFileDataList = AsFileParseUtils.parse(fullFileName);
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
			goodsFileDataList = AsFileParseUtils.parse(contextPath + "WEB-INF" + File.separator + filePath + fileName);
		}
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
            LOG.error("物品表{}文件没找到或者文件格式错误", fullFileName);
            return ;
        }
        for (Object object : goodsFileDataList) {
            Map map = (Map)object;
            String id = MapUtils.getString(map, "id");
            String name = MapUtils.getString(map, "name");
            String maxstack = MapUtils.getString(map, "maxstack");
            boolean xuni = MapUtils.getBooleanValue(map, "xuni");
            if (StringUtils.isBlank(id) || StringUtils.isBlank(name)) {
                continue;
            }
            Goods goods = new Goods(id, name, maxstack);
            goods.setXuniGoods(xuni);
            goods.setGoodsDesc( MapUtils.getString(map, "describe") );

            //是虚拟物品，则加入虚拟物品列表
            if( goods.isXuniGoods() ){
                xuniGoodsList.add(goods);
            }

            goodsMap.put(goods.getId() , goods);
        }
    }
    
    private void putMeiren(String contextPath, String meirenFileType) {
    	Map<String,Object> parameter = new HashMap<String, Object>();
    	parameter.put("fileType",meirenFileType);
        ConfigFile configFile = toolDaoTemplate.selectOne("configFile.selectOneByName", parameter);
        //ConfigFile configFile = null;
        if(configFile == null){
            LOG.error("找不到配置文件{}",meirenFileType);
            return;
        }
        String filePath = configFile.getFilePath();
        String fileName = configFile.getFileName();
        String fullFileName = contextPath + filePath + fileName;
        List goodsFileDataList = AsFileParseUtils.parse(fullFileName);
        if (CollectionUtils.isEmpty(goodsFileDataList)) {
            LOG.error("物品表{}文件没找到或者文件格式错误", fullFileName);
            return ;
        }
        for (Object object : goodsFileDataList) {
            Map map = (Map)object;
            String id = MapUtils.getString(map, "meinvtype");
            String name = MapUtils.getString(map, "name");
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(name)) {
				meirenMap.put(id, name);
			}
        }
		
	}
    
    
    
    /**
     * 根据任务id查询任务名字
     */
    public static String getTaskName (String key){
    	   if(!taskMap.isEmpty()){
       		String taskName = taskMap.get(key) == null? key: taskMap.get(key);
       		return taskName;
       	}else{
       		return key;
       	}
    } 
     
    /**
     * 统一处理附件物品json，根据id获取物品名称
     */
    public static String transformationGoodNames(String str){
    	List<AttachmentGoods> list = new ArrayList<>();
    	try {
    		List<Item> itemlist  = JSONArray.parseArray(str, Item.class);
        	for(Item item : itemlist){
        		AttachmentGoods object = new AttachmentGoods();
        		//object.setId(item.getTemplateId());
        		object.setCount(item.getCount()+"");
        		object.setBinding(item.getBind()+"");
        		object.setExpireTime(item.getEndTime()+"");
        		object.setId(getGoodName(item.getTemplateId()));
        		list.add(object);
        	}
		} catch (Exception e) {
			try {
				 list = JSONArray.parseArray(str, AttachmentGoods.class);
					for (AttachmentGoods object : list) {
			    		object.setId(getGoodName(object.getId()));
			    	}
			} catch (Exception e2) {
				
			}
			 
		}
    	
   
    	return JSON.toJSONString(list);
   } 
}
