package com.mokylin.cabal.modules.tools.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.redis.CoopBase;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 运营数据配置
 *
 * @author
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/coop")
public class CoopBaseController extends BaseController {
	@Autowired
	private RedisManager redisManager;

    @RequestMapping("coopBaseList")
    public String coopBaseList(HttpServletRequest request, Model model,String flag) {
    	String coopname = request.getParameter("coopname");
		List<CoopBase> list = new ArrayList<>();
		if (coopname != null && coopname.trim().length() > 0) {
			CoopBase coopBase = redisManager.getCoopBase(coopname);
			if(coopBase!=null){				
				list.add(coopBase );
			}
				model.addAttribute("list", list);
		} else {
			 List<CoopBase> coopList=redisManager.getAllCoopBase();
			 for(CoopBase coopBase:coopList){
				 list.add(coopBase);
			 }
			model.addAttribute("list", list);
		}
		if(flag!=null){
			model.addAttribute("flag", flag);
			return "modules/tools/coopBaseForm";
		}else{
			return "modules/tools/coopBaseList";
		}
    }
    
    @RequestMapping("coopBaseForm")
    public String coopBaseForm(HttpServletRequest request, Model model) {
        return "modules/tools/coopBaseForm";
    }
    
    @RequestMapping("coopBaseAdd")
    public String coopBaseAdd(HttpServletRequest request, Model model) {
    	String coopname = request.getParameter("coopname");
		String key=request.getParameter("key");
		String rechargeKey=request.getParameter("rechargeKey");
		String baseUrl = request.getParameter("baseUrl");
		String errorUrl = request.getParameter("errorUrl");
		String payUrl = request.getParameter("payUrl");
		String bbsUrl = request.getParameter("bbsUrl");
		String suggestUrl=request.getParameter("suggestUrl");
		String cmUrl = request.getParameter("cmUrl");
		String microClientUrl = request.getParameter("microClientUrl");
		
		JSONObject jsonObject = new JSONObject();   
		jsonObject.put("coopname",coopname);
		jsonObject.put("key", key);
		jsonObject.put("rechargeKey", rechargeKey);
		jsonObject.put("baseUrl", baseUrl);
		jsonObject.put("errorUrl", errorUrl);
		jsonObject.put("payUrl", payUrl);
		jsonObject.put("bbsUrl", bbsUrl);
		jsonObject.put("suggestUrl", suggestUrl);
		jsonObject.put("cmUrl", cmUrl);
		jsonObject.put("microClientUrl", microClientUrl);
		
		
		boolean hasNull = checkNull(key,rechargeKey, baseUrl, errorUrl, payUrl, bbsUrl, cmUrl, microClientUrl);
		if (hasNull) {
			redisManager.setCoopBase(coopname, jsonObject.toJSONString());
			model.addAttribute("message", "添加成功！");
			return"redirect:" + Global.getAdminPath() + "/tools/coop/coopBaseList";
		} else {
			model.addAttribute("message", "添加失败！");
			return "modules/tools/coopBaseForm";
		}
    	
    }
	
	/** 判断是否有参数为空 */
	protected final boolean checkNull(String... params) {
		
		for( String param : params ){
			if(StringUtils.isEmpty(param) ){
				return false;
			}
		}
		return true;
	}

}
