package com.mokylin.cabal.modules.tools.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.game.api.impl.ToolTemplate;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 协议管理
 *
 * @author
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/command")
public class GameCommandController extends BaseController {
	ToolTemplate toolTemplate;
	
	 @Autowired
	 private RedisManager redisManager;
	
    @RequestMapping("gameCommand")
    public String gameCommand(HttpServletRequest request, Model model,HttpServletResponse response) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	//parameter.setPage(new Page(request, response));
    	//model.addAttribute("page", toolDaoTemplate.paging("gameCommand.findCommand",parameter));
    	String serverId = MapUtils.getString(parameter, "currentServerId");
    	Result result = gameTemplate.getToolOperation().offCmdList(serverId);
    	List list = new ArrayList<>();
		  Map map = new HashMap<>();
    	if (result.isSuccess()) {
			  JSONArray array = JSON.parseArray(JSON.toJSONString(result.getData()));	
		      if(array!=null){
		    	  for (int i = 0; i < array.size(); i++) {
		    		  map.put("module",array.getJSONObject(i).get("module"));
		    		  map.put("cmd",array.getJSONObject(i).get("cmd"));
		    		  list.add(map);
		    		  map  = new HashMap();
				}
		      }
		  }
    	model.addAttribute("list", list);
		return "modules/tools/gameCommand";

    }
    
    @RequestMapping("gameCommandModules")
    @ResponseBody
    public String gameModules(){
    	return redisManager.getAllModules();
    }
    
    
    @RequestMapping("addGameCommand")
    public String addGameCommand(HttpServletRequest request, Model model,RedirectAttributes redirectAttributes) {
    	 MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
         List<String> serverIds = (List<String>) MapUtils.getObject(parameter,"serverIdList");
         String command = MapUtils.getString(parameter,"command");
         boolean flag;
         String status=MapUtils.getString(parameter,"status");
         if(status.equals("1")){
        	 flag=true; 
         }else{
        	 flag=false;
         }
         Result result = gameTemplate.getToolOperation().closeCmd(command, flag, serverIds);
        if (result.isSuccess()) {
             addMessage(redirectAttributes, "添加成功");
         } else {
             addMessage(redirectAttributes, "添加失败！");
         }
		return "redirect:"+Global.getAdminPath()+"/tools/command/gameCommand";
    }
}
