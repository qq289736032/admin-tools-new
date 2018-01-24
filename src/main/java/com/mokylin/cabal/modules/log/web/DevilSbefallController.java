package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/log/devil")
public class DevilSbefallController extends BaseController {
	
    /**
     * 魔王降临
     */
    @RequestMapping("devilSbefallList")
    public String devilSbefallList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String startDate = MapUtils.getString(parameter, "createDateStart");    
        if (StringUtils.isEmpty(startDate)) {
           return "modules/logs/devilSbefallList";
        }
        /*1日*/
        parameter.put("startDate",StringUtils.replace(startDate,"-",""));
		model.addAttribute("activeOne", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		model.addAttribute("participationOne", logDaoTemplate.selectList("devilSbefall.participation", parameter));
		model.addAttribute("deathOne", logDaoTemplate.selectList("devilSbefall.death", parameter));
		model.addAttribute("boosKillOne", logDaoTemplate.selectList("devilSbefall.boosKill", parameter));
		/*2日*/
		String twoDay =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(startDate), +1));
    	parameter.put("startDate",StringUtils.replace(twoDay,"-",""));
    	model.addAttribute("activeTwo", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
    	model.addAttribute("participationTwo", logDaoTemplate.selectList("devilSbefall.participation", parameter));
    	model.addAttribute("deathTwo", logDaoTemplate.selectList("devilSbefall.death", parameter));
    	model.addAttribute("boosKillTwo", logDaoTemplate.selectList("devilSbefall.boosKill", parameter));
    	/*3日*/
    	String threeDay =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(startDate), +2));
    	parameter.put("startDate",StringUtils.replace(threeDay,"-",""));
    	model.addAttribute("activeThree", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
    	model.addAttribute("participationThree", logDaoTemplate.selectList("devilSbefall.participation", parameter));
    	model.addAttribute("deathThree", logDaoTemplate.selectList("devilSbefall.death", parameter));
    	model.addAttribute("boosKillThree", logDaoTemplate.selectList("devilSbefall.boosKill", parameter));
    	/*4日*/
    	String fourDay =DateUtils.formatDate(DateUtils.addDays(DateUtils.parseDate(startDate), +3));
    	parameter.put("startDate",StringUtils.replace(fourDay,"-",""));
    	model.addAttribute("activeFour", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
    	model.addAttribute("participationFour", logDaoTemplate.selectList("devilSbefall.participation", parameter));
    	model.addAttribute("deathFour", logDaoTemplate.selectList("devilSbefall.death", parameter));
    	model.addAttribute("boosKillFour", logDaoTemplate.selectList("devilSbefall.boosKill", parameter));
    	
    	parameter.put("startDate", StringUtils.replace(startDate,"-",""));
    	model.addAttribute("boosKillMsg", logDaoTemplate.selectList("devilSbefall.boosKillMsg", parameter));
    	
    	parameter.put("startDate", startDate);
        return "modules/logs/devilSbefallList";
    }
    
    

}
