package com.mokylin.cabal.modules.global.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/9
 * admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/global/levelDistribution")
public class LevelDistributionController {

    @RequestMapping("vipLevelDistribution")
    public String vipLevelDistribution(HttpServletRequest request, HttpServletResponse response, Model model) {

        return "";
    }
}
