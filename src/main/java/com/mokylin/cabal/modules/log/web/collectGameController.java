package com.mokylin.cabal.modules.log.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.web.BaseController;

@Controller
@RequestMapping(value = "${adminPath}/log/collect")
public class collectGameController extends BaseController {
	
    /**
     * 点击收藏游戏
     */
    @RequestMapping("collectGameList")
    public String propProduce(HttpServletRequest request, HttpServletResponse response, Model model) {
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		setDefaultLogDay(parameter);
		model.addAttribute("active", logDaoTemplate.selectList("stateCombat.findActiveStatistics", parameter));
		try {
			model.addAttribute("collect", logDaoTemplate.selectList("collectGame.collect", parameter));
			model.addAttribute("phone", logDaoTemplate.selectList("collectGame.phone", parameter));
			model.addAttribute("collectPhone", logDaoTemplate.selectList("collectGame.collectPhone", parameter));
		} catch (Exception e) {
		}
        return "modules/logs/collectGameList";
    }
    
    

}
