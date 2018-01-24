package com.mokylin.cabal.modules.log.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mokylin.cabal.common.core.DataContext;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.GameEmail;

/**
 * 
 * 邮件日志
 * @author maojs
 * @date 2015年1月8日
 */
@Controller
@RequestMapping(value = "${adminPath}/log/mail")
public class MailController extends BaseController {


	@RequestMapping(value = "list")
	public String getMailList(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
//		setDefaultTimeRange(parameter);
		String roleName = MapUtils.getString(parameter, "roleName");
		String pid = DataContext.getInstance(UserUtils.getUser().getId()).getPid();
		if(StringUtils.isNoneEmpty(roleName)&&StringUtils.isNoneEmpty(pid)){
			String roleId = RedisUtils.getRoleIdByRoleName(pid,roleName,roleName);
			parameter.put("roleId", roleId);
		}
		// 有adminEmailId的邮件 表示从后台发过来的
		parameter.put("serverId", MapUtils.getString(parameter, "currentServerId"));
		Page<Map<String, Object>> page = gamePaging(request,response,"mail.findMailList");
		
		model.addAttribute("page", page);
		
		model.addAttribute("roleId", MapUtils.getString(parameter, "roleId"));
		model.addAttribute("roleName", MapUtils.getString(parameter, "roleName"));
		model.addAttribute("contentKey", MapUtils.getString(parameter, "contentKey"));
		
		return "modules/logs/mailList";
	}
	
	/**
	 * gm后台详细信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getGmMailInfo")
	public String getGmMailInfo(HttpServletRequest request, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		String adminEmailId = MapUtils.getString(parameter, "gmMailId");
		GameEmail gameEmail = toolDaoTemplate.selectOne("gameEmail.selectOneById", adminEmailId);
		model.addAttribute("gameEmail", gameEmail);

		return "modules/logs/gmMailDialog";
	}
	
	@RequestMapping(value = "gmMailInfo")
	public String gmMailInfo(HttpServletRequest request, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		String adminEmailId = MapUtils.getString(parameter, "gmMailId");
		GameEmail gameEmail = toolDaoTemplate.selectOne("gameEmail.selectOneById", adminEmailId);
		model.addAttribute("gameEmail", gameEmail);
		return "modules/logs/gmMailInfo";
	}
	
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest request, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
		String mailId = MapUtils.getString(parameter, "id");
		String roleId = MapUtils.getString(parameter, "roleId");
		String serverId = LookupContext.getCurrentServerId();
	    gameTemplate.gameEmailOperation().delete(serverId, roleId, mailId);
		return "redirect:" + Global.getAdminPath() + "/log/mail/list";
	}
	
	
}
