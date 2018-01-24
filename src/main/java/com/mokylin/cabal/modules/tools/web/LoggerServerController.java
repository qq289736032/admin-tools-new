package com.mokylin.cabal.modules.tools.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/4/24 17:26
 * 项目: admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/logger")
public class LoggerServerController extends BaseController {

    @Resource
    private ServerManager serverManager;

    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, Model model){
        model.addAttribute("loggerServerList", serverManager.getLoggerServerList());
        return "modules/tools/loggerServerList";

    }
}
