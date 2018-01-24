package com.mokylin.cabal.modules.tools.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.async.AsyncService;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.CodecUtil;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/tools/code")
public class DevToolController extends BaseController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
    private AsyncService asyncService;
	
	
	@RequestMapping("code")
	public String to(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        Page<Map<String, Object>> page =  toolPaging(request, response, "codeLog.paging");
        model.addAttribute("page", page);
		return "modules/tools/code";
	}
	
	
	
	@ResponseBody
	@RequestMapping("execute")
	public Result execute(HttpServletRequest request, HttpServletResponse response, Model model){
		
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String code = CodecUtil.urlEncode(request.getParameter("code"));
        if (StringUtils.isEmpty(code)) {
            return new Result(false).data("代码不能为空");
        	
        }
        List<String> serverIds = (List<String>) MapUtils.getObject(parameter, "serverIdList");
        asyncService.executeCode(code,serverIds);
        toolDaoTemplate.insert("codeLog.insertTool", parameter);
        return new Result(true).data("执行完成!");
		
	}
	
	
}
