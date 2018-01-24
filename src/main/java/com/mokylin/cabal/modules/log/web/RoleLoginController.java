package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.AuthCondition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/21 10:37
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/log/roleLogin")
public class RoleLoginController extends BaseController {


   @RequestMapping(value = {"list", ""})
    public String list( HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        AuthCondition.filterPlatform(parameter);	//根据平台显示

        parameter.setPage(new Page(request, response));
        Page<Map<String,Object>> page = logDaoTemplate.paging("roleLogout.paging", parameter);
        model.addAttribute("page", page);
        return "modules/logs/roleLoginList";
    }
    @RequestMapping("roleLoginIp")
    public String roleIpLogin( HttpServletRequest request, HttpServletResponse response, Model model) {
    	/*IP查询用户*/
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	AuthCondition.filterPlatform(parameter);	//根据平台显示
    	//默认查询时间范围为7天
    	setDefaultTimeRange(parameter);
    	setDefaultDateRange(parameter);
    	parameter.setPage(new Page(request, response));
    	Page<Map<String,Object>> page = logDaoTemplate.paging("roleLogin.roleLoginIp", parameter);
    	model.addAttribute("page", page);
    	return "modules/logs/roleLoginIpList";
    }


}
