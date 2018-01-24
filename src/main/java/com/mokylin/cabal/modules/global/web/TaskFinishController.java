package com.mokylin.cabal.modules.global.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 任务完成度统计
 * @author Administrator
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/global/taskFinish")
public class TaskFinishController extends BaseController{

	
	@RequestMapping(value = "list")
	public String taskFinishList (HttpServletRequest request, HttpServletResponse response, Model model){
		
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		//默认当前时间
		if(!parameter.containsKey("createDate")){
			parameter.put("createDate", DateUtils.getDate());
		}
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
		
		model.addAttribute("vipLevelMap", vipLevelMap());
		
		model.addAttribute("list", globalDaoTemplate.selectList("taskFinish.taskFinishList", parameter));
	    model.addAttribute("total",  globalDaoTemplate.selectList("taskFinish.taskFinishTotal", parameter));
		model.addAttribute("viplevel", parameter.get("viplevel"));
        // 把serverIdList转成string
    	parameter.put("serverIdList", parameter.get("serverIds"));
		return "modules/global/taskFinishList";
	}
	
	/**
	 * 任务完成度统计导出excel
	 */
	@RequestMapping(value ="exportXls" ,method = RequestMethod.POST)
	 public ResponseEntity<byte[]> exportXls(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
	    	
	    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	    	//默认当前时间
	    	if(!parameter.containsKey("createDate")){
				parameter.put("createDate", DateUtils.getDate());
			}
	    	
	    	List<Map> taskList = globalDaoTemplate.selectList("taskFinish.taskFinishList", parameter);
	    	List<Map> total = globalDaoTemplate.selectList("taskFinish.taskFinishTotal", parameter);
	    	taskList.add(total.get(0));
	   
	    	
	    	return super.exportXls(taskList, "任务完成度统计"+System.currentTimeMillis(), "任务名称","用户数","完成用户数","占比(%)");
	  }
	 
}
