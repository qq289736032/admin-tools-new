package com.mokylin.cabal.modules.tools.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.service.SystemService;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import com.mokylin.cabal.modules.welfare.service.WelfareTempService;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/27 18:15
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/gamePlatform")
public class GamePlatformController extends BaseController {

    @Autowired
    private SystemService systemService;
    
	@Autowired
	WelfareTempService welfareService;

    @ModelAttribute("gamePlatform")
    public GamePlatform get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return systemService.getGamePlatform(id);
        }else{
            return new GamePlatform();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GamePlatform gamePlatform, Model model) {
        List<GamePlatform> list = systemService.findAllGamePlatform();
        model.addAttribute("list", list);
        return "modules/tools/gamePlatformList";
    }
    
    /**
     * 获取所有平台列表数据
     * @return
     */
    @RequestMapping(value = "allPlat")
    @ResponseBody
    public List<Map<String,Object>> allPlat() {
    	List<GamePlatform> list = systemService.findAllGamePlatform();
    	List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
    	for (GamePlatform gamePlatform : list) {
    		Map<String,Object> map = new HashMap<String, Object>();
    		map.put("pid", gamePlatform.getPid());
    		map.put("name", gamePlatform.getName());
		
			result.add(map);
		}
        return result;
    }

    @RequestMapping(value = "form")
    public String form(GamePlatform gamePlatform, Model model) {

        model.addAttribute("gamePlatform", gamePlatform);
        return "modules/tools/gamePlatformForm";
    }

    @RequestMapping(value = "save")
    public String save(GamePlatform gamePlatform, Model model, String oldName, RedirectAttributes redirectAttributes) {

        if (!beanValidator(model, gamePlatform)){
            return form(gamePlatform, model);
        }
        if (!"true".equals(checkName(oldName, gamePlatform.getName()))){
            addMessage(model, "保存平台'" + gamePlatform.getName() + "'失败, 平台名已存在");
            return form(gamePlatform, model);
        }
       
        systemService.saveGamePlatform(gamePlatform);
        
        //新增平台自动生成通用福利配置
        welfareService.insertPlatConfig(gamePlatform);
        addMessage(redirectAttributes, "保存平台'" + gamePlatform.getName() + "'成功");
        return "redirect:"+Global.getAdminPath()+"/tools/gamePlatform/";
    }


    public String checkName(String oldName, String name) {
        if (name!=null && name.equals(oldName)) {
            return "true";
        } else if (name!=null && systemService.findGamePlatformByName(name) == null) {
            return "true";
        }
        return "false";
    }


    @RequestMapping(value = "delete")
    public String delete(String id, RedirectAttributes redirectAttributes){
        systemService.deleteGamePlatform(id);
        addMessage(redirectAttributes, "删除平台成功");
        return "redirect:"+Global.getAdminPath()+"/tools/gamePlatform/";
    }
}
