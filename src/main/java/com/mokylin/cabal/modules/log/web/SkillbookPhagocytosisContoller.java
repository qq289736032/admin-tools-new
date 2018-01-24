package com.mokylin.cabal.modules.log.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;
/**
 * 技能书吞噬
 * @author Administrator
 *
 */


@Controller
@RequestMapping(value = "${adminPath}/log/phagocytosis")
public class SkillbookPhagocytosisContoller extends BaseController {

	@RequestMapping("skillbookPhagocytosisList")
	public String skillbookPhagocytosisList(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String roleName = MapUtils.getString(parameter, "roleName");
		//默认当前时间
		setDefaultLogDay(parameter);
		// 默认不选服务器的取当前用户有权限访问的服务器
		//setServerIdList(parameter);
		parameter.setPage(new Page(request, response));
		Page<Map<String, Object>> page = logDaoTemplate.paging("phagocytosis.paging", parameter);
		model.addAttribute("page", page); 
		model.addAttribute("roleName", roleName);
		return "modules/logs/skillbookPhagocytosisList";
		}
	
	 @RequestMapping(value = "export", method= RequestMethod.POST)
	    public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
	        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
	        String roleName = MapUtils.getString(parameter, "roleName");
	        setDefaultLogDay(parameter);
	        List<Map> skillList = logDaoTemplate.selectList("phagocytosis.paging", parameter);
	        return super.exportXls(skillList, "技能书吞噬" + System.currentTimeMillis(), "角色名", "吞噬ID", "道具ID", "被吞噬ID", "之前经验", "之后经验", "时间");
	    }


}
