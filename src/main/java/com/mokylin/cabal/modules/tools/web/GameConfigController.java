package com.mokylin.cabal.modules.tools.web;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.ConfigFile;
import com.mokylin.cabal.modules.tools.service.ActivePanelService;
import com.mokylin.cabal.modules.tools.service.GoodsAnalyzeService;
import com.mokylin.cabal.modules.tools.service.MapLineService;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;
import com.mokylin.cabal.modules.tools.service.ResourceTypeService;
import com.mokylin.cabal.modules.tools.service.TaskTypeservice;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/10 16:09
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/gameConfig")
public class GameConfigController extends BaseController {

    @Autowired
    private GoodsAnalyzeService goodsAnalyzeService;

    @Autowired
    private ResourceTypeService resourceTypeService;
   
    @Autowired
    private OperationTypeService operationTypeService;
    
    @Autowired
    private TaskTypeservice  taskTypeService;

    @Autowired
    private MapLineService mapLineService;
    
    @Autowired
    private ActivePanelService activePanelService;
    
    @ModelAttribute
    public ConfigFile get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return toolDaoTemplate.selectOne("configFile.selectOneById", id);
        } else {
            return new ConfigFile();
        }
    }

    @ModelAttribute("fileMap")
    public Map gameFileMap() {
        return Global.getConfigFileMap();
    }

    @RequestMapping(value = {"list", ""})
    public String getGameServerList(ConfigFile configFile, HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));

        Page<ConfigFile> page = toolDaoTemplate.paging("configFile.paging", parameter);

        model.addAttribute("page", page);
        return "modules/tools/configFileList";
    }


    @RequestMapping(value = "form")
    public String form(ConfigFile configFile, Model model) {
        return "modules/tools/configFileForm";
    }


    @RequestMapping(value = "save")
    public String save(ConfigFile configFile, MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        String contextPath = request.getSession().getServletContext().getRealPath("/") + "/";
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String fileName = file.getOriginalFilename();
        String newName = configFile.getFileName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());    //判断文件类型
        String filePath = MapUtils.getString(Global.getCommonMap(), "game-config");             //上传文件路径
        //编辑保存则删除旧文件和数据库记录
        if (configFile.getId() != null) {
            new File(contextPath + configFile.getFileName()).delete();
            toolDaoTemplate.delete("configFile.delete", newName);
        }
        parameter.put("fileName", fileName);
        parameter.put("newName", newName);
        parameter.put("filePath", filePath);
        parameter.put("fileType",newName);
        String fullUploadPath = contextPath + filePath;
        new File(fullUploadPath).mkdirs();
        file.transferTo(new File(fullUploadPath + fileName));
        toolDaoTemplate.insert("configFile.insert", parameter);
        if (fileType.equals("jat")) {
            goodsAnalyzeService.refresh();
        } else {
        	switch (newName) {
			case "resourceType.txt":            //资源线类型
				resourceTypeService.refresh(); 
				break;
			case "operation.txt": 				//操作类型
				 operationTypeService.refresh();
				 break;
			case "taskType.txt":				//任务类型
				taskTypeService.refresh();
				 break;
			case "mapLine.txt":					//地图场景 
				mapLineService.refresh();
				break;
			case "activePanel.txt":    			//活动面板
				activePanelService.refresh();
        		break;	
			}
        	
        }


        addMessage(redirectAttributes, "配置保存并刷新成功，文件名:" + fileName);

        return "redirect:" + Global.getAdminPath() + "/tools/gameConfig/";
    }


    @RequestMapping(value = "delete")
    public String delete(ConfigFile configFile, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String contextPath = request.getSession().getServletContext().getRealPath("/") + "/";
        new File(contextPath + configFile.getFileName()).delete();
        toolDaoTemplate.delete("configFile.delete", configFile.getNewName());
        addMessage(redirectAttributes, "删除文件成功，文件名:" + configFile.getFileName());
        return "redirect:" + Global.getAdminPath() + "/tools/gameConfig/";
    }
}
