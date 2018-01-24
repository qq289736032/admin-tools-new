package com.mokylin.cabal.modules.tools.web;

import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.cache.EhcacheCacheManager;
import com.mokylin.cabal.common.cache.ICache;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.Gift;
import com.mokylin.cabal.modules.tools.service.GoodsAnalyzeService;
import com.mokylin.cabal.modules.tools.vo.AttachmentGoods;

/**
 * 作者: 礼包码
 * 日期: 2014/11/6 15:53
 * 项目: cabal-tools
 */

@Controller
@RequestMapping(value = "${adminPath}/tools/gift")
public class GiftCodeController extends BaseController{

    @Autowired
    private GoodsAnalyzeService goodsAnalyzeService;
    
    private ExecutorService exec = Executors.newCachedThreadPool();

    
    private static final int CODE_PREFIX_MAX_LENGTH = 6; // 前缀不超过6
    private static final int CODE_LESS_LENGTH = 9; // 中间加后缀  最少长度9位， 因为后面4位作为查询表的id，后面固定4位本来是数字，现转为18进制  
    private static final int CODE_COUNT_MAX = 100000; //生成数量最大个数 可以做成配置
    private static final int CODE_TABLE_INDEX_LENGTH = 4; // 礼包码后缀固定长度4
	
    @RequestMapping(value = {"giftBaseList"})
    public String getGiftBaseList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Gift> page = toolDaoTemplate.paging("gift.paging", parameter);
        model.addAttribute("page", page);
        model.addAttribute("content", parameter.get("content"));
        return "modules/tools/giftBaseList";
    }

    @RequestMapping(value = "form")
    public String form(Model model) {
    	model.addAttribute("gift", new Gift());
        return "modules/tools/giftBaseForm";
    }

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "save")
    public String save(Gift gift, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //附加物品列表，绑定为默认值，但是其并没有选择物品，这里需要移除掉
        List<AttachmentGoods> list = gift.getGoodsList();
        for (AttachmentGoods goods : list) {
            if (StringUtils.isBlank(goods.getId()) || StringUtils.isEmpty(goods.getId())) {
                list.remove(goods);
            }
        }
        parameter.put("attachments", JSON.toJSONString(list));
        toolDaoTemplate.insert("gift.insertGiftBase", parameter);
        addMessage(redirectAttributes, "申请礼包成功");

		gift.setAttachments(JSON.toJSONString(list));

        //把礼品设置给它
        gift.setAttachments(JSON.toJSONString(list));
		ICache<String, Object> cache = ehcacheCacheManager.getCache(EhcacheCacheManager.CACHE_KEY_GIFT_BASE);
		cache.put(MapUtils.getString(parameter, "id"), gift);
		
        return "redirect:" + Global.getAdminPath() + "/tools/gift/giftBaseList";
    }
    
    // ------------------------------------- 礼包码生成 -------------------------------------
    
    @RequestMapping(value = {"createCodeList"})
    public String getGiftCreateCodList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        Page<Map<String, Object>> page =  toolPaging(request, response, "gift.createCodeList");
        model.addAttribute("page", page);
        model.addAttribute("name", parameter.get("name"));
        return "modules/tools/createCodeList";
    }

    @RequestMapping(value = "createCodeForm")
    public String createCodeForm(HttpServletRequest request, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	//礼包列表
    	List<Gift> giftList = toolDaoTemplate.selectList("gift.paging", parameter);
    	model.addAttribute("giftList", giftList);
        return "modules/tools/createCodeForm";
    }

    @RequestMapping(value = "createCodeSave")
    public String createCodeSave(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        
        // 码长度要9位以上 ，不然会出现重复
        int codeLength = MapUtils.getIntValue(parameter, "codeLength");
        if (codeLength < CODE_LESS_LENGTH) { 
        	addMessage(redirectAttributes, "码长度要"+ CODE_LESS_LENGTH + "位以上");
        	return "redirect:" + Global.getAdminPath() + "/tools/gift/createCodeList";
        }
        
        int count = MapUtils.getIntValue(parameter, "count");
	    if (count > CODE_COUNT_MAX) { 
	      addMessage(redirectAttributes, "数量太多！不能超过" + CODE_COUNT_MAX);
	      return "redirect:" + Global.getAdminPath() + "/tools/gift/createCodeList";
	    }
	    
	    String prefix = MapUtils.getString(parameter, "prefix");
	    if (prefix.length() > CODE_PREFIX_MAX_LENGTH) { 
	      addMessage(redirectAttributes, "前缀太长！不能超过" + CODE_PREFIX_MAX_LENGTH);
	      return "redirect:" + Global.getAdminPath() + "/tools/gift/createCodeList";
	    }
        
        String giftId = MapUtils.getString(parameter, "giftId");
        Gift gift = toolDaoTemplate.selectOne("gift.selectGiftById", giftId);
        parameter.put("giftName", gift.getName());

        parameter.put("uuid", MapUtils.getString(parameter, "id"));
        toolDaoTemplate.insert("gift.insertGiftCreateCode", parameter);
        
        Map<String, Object> giftCreateCode = toolDaoTemplate.selectOne("gift.selectGiftCreateCodeByUuid", MapUtils.getString(parameter, "uuid"));
        int giftCreateCodeId = MapUtils.getIntValue(giftCreateCode, "id");
        // giftCreateCodeId除以100作为分表的后缀
        int tableIndex = giftCreateCodeId / 100;
        synchronized (this){
	        if(!checkTableExist(tableIndex)) {
	        	createGiftCodeTable(tableIndex);
	        }
	        // 批量插入表
	        batchInsertGiftCodeTable(parameter, tableIndex, giftCreateCodeId);
        }
        
        addMessage(redirectAttributes, "新增礼包码成功");
        return "redirect:" + Global.getAdminPath() + "/tools/gift/createCodeList";
    }
    
    /**
     * 废弃生成礼包码
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "deleteCreateCode")
    public String deleteCreateCode(@RequestParam String id, RedirectAttributes redirectAttributes, Model model){
    	int result = toolDaoTemplate.update("gift.deleteGiftCreateCode", id);
	    if (result == 1) {
	    	addMessage(redirectAttributes, "废弃礼包成功");
	    } else {
	    	addMessage(redirectAttributes, "废弃礼包失败");
	    }
	    return "redirect:"+Global.getAdminPath()+"/tools/gift/createCodeList";
    }

    /**
     * 导出礼包码
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "exportCreateCode")
    public void exportCreateCode(@RequestParam String id, HttpServletResponse response){
    	List<String> codeList = new ArrayList<String>();
    	Map<String, Object> parmMap = new HashMap<>();
    	int tableIndex = Integer.parseInt(id) / 100;;
    	parmMap.put("tableIndex", tableIndex);
    	parmMap.put("giftCreateId", id);
    	codeList = toolDaoTemplate.selectList("gift.selectCodeListByGiftCreateId", parmMap);
    	writeToTxt(response, codeList);
    }
    
    /**
     * 激活码查询  现在只通过激活码查询
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = {"viewCodeList"})
    public String viewCodeList(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String code = MapUtils.getString(parameter, "code");
        
        model.addAttribute("code", parameter.get("code"));
        
        boolean flag = true;
        if (StringUtils.isBlank(code)) { // 现在只通过激活码查询
        	flag = false;
        }
        
        if (flag && code.length() < CODE_LESS_LENGTH) { 
  	      model.addAttribute("message", "礼包码长度太短！");
  	      flag = false;
  	    }
        
        // 最后4位 16进制 转成10进制
        if (flag) {
	        try {
	        	Integer.parseInt(code.substring (code.length()-4), 16);
	        } catch (Exception e) {
	        	model.addAttribute("message", "礼包码格式不对！");
	        	flag = false;
	        }
        }
//        if (flag && !StringUtils.isNum(code.substring(18))) { 
//    	    model.addAttribute("message", "礼包码格式不对！");
//    	    flag = false;
//    	}
        
        if (flag) {
        	 // 通过code获得tableIndex
            int tableIndex = Integer.parseInt(code.substring (code.length()-4), 16) / 100;
            parameter.put("tableIndex", tableIndex);
            
            try {
            	Page<Map<String, Object>> page = toolPaging(request, response, "gift.viewCodeList");
            	model.addAttribute("page", page);
            } catch (Exception e) {
            	model.addAttribute("message", "该礼包码不存在！");
            }
        }
       
//        model.addAttribute("gift_name", parameter.get("gift_name"));
//        model.addAttribute("pid", parameter.get("pid"));
//        model.addAttribute("status", parameter.get("status"));
        return "modules/tools/viewCodeList";
    }

    @RequestMapping(value = "goodsDialog")
    public String goodsDialog(String goodsName, Model model) {
        model.addAttribute("list", goodsAnalyzeService.query(goodsName));
        return "modules/tools/goodsDialog";
    }
    
	/**
	 * 生成礼包码详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "viewCreateCodeDetail")
	public String viewCreateCodeDetail(HttpServletRequest request, Model model, @RequestParam String giftId, @RequestParam String id) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		Gift gift = toolDaoTemplate.selectOne("gift.selectGiftById", giftId);
		model.addAttribute("name", gift.getName());
		model.addAttribute("attachments", gift.getAttachments());
		model.addAttribute("content", gift.getContent());
		Map<String, Object> giftCreateCode = toolDaoTemplate.selectOne("gift.selectGiftCreateCodeById", id);
		model.addAttribute("id", MapUtils.getIntValue(giftCreateCode, "id"));
		model.addAttribute("prefix", MapUtils.getString(giftCreateCode, "prefix"));
		model.addAttribute("type", MapUtils.getString(giftCreateCode, "type"));
		model.addAttribute("count", MapUtils.getIntValue(giftCreateCode, "count"));
		model.addAttribute("effective_end_time", giftCreateCode.get("effective_end_time")); // 有效期到期时间
		//parameter.put("prefix", MapUtils.getString(giftCreateCode, "prefix"));// 码长度 不包括前缀和
		return "modules/tools/createCodeDetailDialog";
	}
    
    // 检查表是否存在
    private boolean checkTableExist(int tableIndex) {
        try {
        	Map<String, Integer> parm = new HashMap<String, Integer>();
        	parm.put("tableIndex", tableIndex);
        	toolDaoTemplate.selectOne("gift.checkGiftCodeTableExist", parm);
        } catch (Exception e) {
        	logger.warn("table gift_code_{} doesn't exist ", tableIndex);
        	return false;
        }
        return true;
    }
    
    // 新建表
    private void createGiftCodeTable(int tableIndex) {
    	try {
        	Map<String, Integer> parm = new HashMap<String, Integer>();
        	parm.put("tableIndex", tableIndex);
        	toolDaoTemplate.selectOne("gift.createGiftCodeTable", parm);
        } catch (Exception e) {
        	logger.error("", e);
        }
    }
    
    // 批量生产激活码
    private void batchInsertGiftCodeTable(MybatisParameter parameter, int tableIndex, int giftCreateCodeId) {
    	String prefix = MapUtils.getString(parameter, "prefix");
    	int count = MapUtils.getIntValue(parameter, "count");
    	int middleCodeLength = MapUtils.getIntValue(parameter, "codeLength") - CODE_TABLE_INDEX_LENGTH;
    	List<String> codeList = generateCodeList(count, middleCodeLength);
    	List<Map<String, Object>> parameterList = new ArrayList<>();
		String codeTail = Integer.toHexString(giftCreateCodeId);
		if (codeTail.length() > 4 || codeTail.length() < 1) {
			logger.error("codeTail.length err! length=", codeTail.length());
			return;
		}
		if (codeTail.length() == 1) {
			codeTail = "000" + codeTail;
		} else if (codeTail.length() == 2){
			codeTail = "00" + codeTail;
		} else if (codeTail.length() == 3){
			codeTail = "0" + codeTail;
		}
		for (int  i = 0; i < count; i ++)  {
			Map<String, Object> map = new HashMap<>();
			map.put("id", IdGen.uuid());
    		map.put("code", prefix + codeList.get(i) + codeTail);
    		map.put("gift_id", MapUtils.getString(parameter, "giftId"));
    		map.put("gift_name", MapUtils.getString(parameter, "giftName"));
    		map.put("gift_create_id", giftCreateCodeId);
    		map.put("platform_id", MapUtils.getString(parameter, "pids"));
    		parameterList.add(map);
    	}
    	parameter.put("tableIndex", tableIndex);
    	parameter.put("parmList", parameterList);
    	
    	toolDaoTemplate.batchInsert2("gift.batchInsertGiftCode", parameter);
    }
    
	private List<String> generateCodeList(int count, int codeLength) {
    	List<String> list = new ArrayList<String>();
    	Set<String> codeSet = new HashSet<>();
    	while (codeSet.size() < count) { // 应该不会死循环吧
    		codeSet.add(StringUtils.getStringRandom(codeLength));
    	}
    	list.addAll(codeSet);
    	return list;
    }
    
	// 输出TXT
	public static void writeToTxt(HttpServletResponse response, List<String> list) {
		response.setContentType("text/plain");// 一下两行关键的设置
		response.addHeader("Content-Disposition",
					"attachment;filename=codeList.txt");// filename指定默认的名字
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		ServletOutputStream outSTr = null;
		try {
			outSTr = response.getOutputStream();// 建立
			buff = new BufferedOutputStream(outSTr);
			for (int i = 0; i < list.size(); i++) {
				write.append(list.get(i));
				write.append(StringUtils.ENTER);		
			}
			buff.write(write.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "viewCreateName")
    public String viewCreateName(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
        String id = MapUtils.getString(parameter, "giftId");
        setDefaultTimeRange(parameter);
    	List<Map<String, Object>> listCode=new ArrayList<>();
    	List<Map<String, Object>> listCodeOne=new ArrayList<>();

    	//礼包列表
    	List<Gift> giftList = toolDaoTemplate.selectList("gift.name", parameter);
    	model.addAttribute("giftList", giftList);
        if (StringUtils.isNotBlank(id)) {
        	try {
				listCode=selectListByMultiTable("gift.countGiftId", parameter);

			} catch (Exception e) {
	        	logger.error("", e);
			}
        	model.addAttribute("giftId", id);
       }
        model.addAttribute("listCode", listCode); 

        return "modules/tools/viewCodeName";
		
	}
	  public List<Map<String, Object>> selectListByMultiTable( String statement,  Map parameter)  {
      	List<Map<String ,Object>> list = new ArrayList<Map<String ,Object>>();
      	List<Map<String, Object>> listgift=toolDaoTemplate.selectList("gift.createCodeList", parameter);
      	List<Map<String, Object>>   codeList=Lists.newArrayList();
          if(listgift.size() >0){
	        	for(Map<String, Object>map:  listgift){
	        		 int giftCreateCodeId = MapUtils.getIntValue(map, "id");
	         	     // giftCreateCodeId除以100作为分表的后缀
	         	     int tableIndex = giftCreateCodeId / 100;
	    	          String giftId =map.get("gift_id").toString();
	    	          String name =map.get("gift_name").toString();
	    	          int createCodeId = giftCreateCodeId;
	    	          int count = MapUtils.getIntValue(map, "count");
	    	          String pid = MapUtils.getString(map, "pid");
	    	         Map newParameter = Maps.newHashMap();
                   newParameter.put("tableIndex",tableIndex);
                   newParameter.put("giftCreateId", createCodeId);
                   newParameter.put("count", count);
                   newParameter.put("pid", pid);
                   list.add(newParameter);
                   parameter.put("name", name);
             }
	        	parameter.put("list", list);
	        	List<Map<String, Object>> newList=toolDaoTemplate.selectList(statement, parameter);
                for(Map newmap:newList){
                 	newmap.put("name", parameter.get("name").toString());
                 	newmap.put("gift_create_id",newmap.get("gift_create_id"));
                 	for(Map m :list){
                 		if(m.get("giftCreateId").toString().equals(newmap.get("gift_create_id").toString())){
                 		 	newmap.put("count", MapUtils.getIntValue(m, "count"));
                 		    newmap.put("pid", MapUtils.getString(m, "pid"));
                 		}
                 	}
                 	newmap.put("num", newmap.get("num"));
                 	codeList.add(newmap);
                 }
     }
          return codeList;
      }


	
	
	
}
