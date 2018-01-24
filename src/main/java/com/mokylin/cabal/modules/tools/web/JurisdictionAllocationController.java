package com.mokylin.cabal.modules.tools.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.entity.Menu;
import com.mokylin.cabal.modules.sys.entity.Role;
import com.mokylin.cabal.modules.sys.service.SystemService;
import com.mokylin.cabal.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/tools/jurisdiction")
public class JurisdictionAllocationController extends BaseController {

	@Autowired
	private SystemService systemService;

	/*@Autowired
	private OfficeService officeService;*/

	@ModelAttribute("role")
	public Role get(@RequestParam(required = false) String id) {

		if (StringUtils.isNotBlank(id)) {
			return systemService.getRole(id);
		} else {
			return new Role();
		}
	}

	@RequestMapping(value = "jurisdictionAllocationList")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model, Role role) {
		List<Role> list = systemService.findAllRole();
		model.addAttribute("list", list);
		model.addAttribute("menuList", systemService.findAllMenu());
		/*if (role.getOffice() == null) {
			role.setOffice(UserUtils.getUser().getOffice());
		}
		model.addAttribute("role", role);
		model.addAttribute("menuList", systemService.findAllMenu());
		model.addAttribute("officeList", officeService.findAll());
		model.addAttribute("allGamePlatforms", systemService.findAllGamePlatform());*/
		return "modules/tools/jurisdictionAllocationList";
	}

	@RequestMapping(value = "selectUser")
	@ResponseBody
	public List<Map<String, Object>> selectUser(HttpServletRequest request, HttpServletResponse response, Model model,
			String id) {
		List<Map<String, Object>> users = toolDaoTemplate.selectList("user.findUser", id);
		return users;
	}

	@RequestMapping(value = "selectMenu")
	@ResponseBody
	public List<Menu> selectMenu(HttpServletRequest request, HttpServletResponse response, Model model, String id) {
		List<Menu> menu = toolDaoTemplate.selectList("menu.findMenu", id);
		return menu;
	}

	@RequestMapping(value = "save")
	public String save(Role role, HttpServletRequest request, Model model, String menuId, String id) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		toolDaoTemplate.delete("menu.deleteMenu", id);
		if (menuId != null) {
			String[] strs = menuId.split(",");
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < strs.length; i++) {
				list.add(strs[i]);
			}
			parameter.put("id", id);
			parameter.put("list", list);
			toolDaoTemplate.insert("menu.insertMenu", parameter);
			UserUtils.removeCache(UserUtils.CACHE_ROLE_LIST);
		}
		return "modules/tools/jurisdictionAllocationList";
	}

}
