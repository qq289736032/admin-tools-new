package com.mokylin.cabal.modules.global.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 实时地图用户分布
 * @author Administrator
 */
@Controller
@RequestMapping(value = "${adminPath}/global/mapRoleOnline")
public class MapRoleOnlineController extends BaseController{
	
	@RequestMapping(value = "list")
	public String mapRoleOnlineList(HttpServletRequest request,HttpServletResponse response,Model model){
		 
		  MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		  Result result = gameTemplate.roleOperation().roleOnline(parameter.get("currentServerId").toString());
		  List list = new ArrayList<>();
		  Map map = new HashMap<>();
		  int total=0;
		  if (result.isSuccess()) {
			  JSONArray array = JSON.parseArray(JSON.toJSONString(result.getData()));	
		      if(array!=null){
		    	  for (int i = 0; i < array.size(); i++) {
		    		  map.put("mapId",array.getJSONObject(i).get("mapId"));
		    		  map.put("mapName",array.getJSONObject(i).get("mapName"));
		    		  map.put("num",array.getJSONObject(i).get("num"));
		    		  total+= Integer.valueOf(map.get("num").toString());    //在线人数总和
		    		  list.add(map);
		    		  map  = new HashMap();
				}
		      }
		  }
		  model.addAttribute("total", total);
		  model.addAttribute("list", list);
		return "modules/global/mapRoleOnlineList";
	}
	
}
