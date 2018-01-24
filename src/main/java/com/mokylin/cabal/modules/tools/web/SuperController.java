package com.mokylin.cabal.modules.tools.web;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.game.api.model.VipQq;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;

import edu.emory.mathcs.backport.java.util.Arrays;

//超级会员
@Controller
@RequestMapping(value = "${adminPath}/tools/super")
public class SuperController extends BaseController {

    @RequestMapping(value = "list")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //parameter.put("serverId", MapUtils.getString(parameter, "currentServerId"));
        Page<Map<String, Object>> page = toolPaging(request, response, "vip.findSuper");
        model.addAttribute("page", page);
        model.addAttribute("qq", MapUtils.getString(parameter, "qq"));
        return "modules/tools/superMember";
    }

    @RequestMapping("form")
    public String form(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "modules/tools/superForm";
    }

    @RequestMapping("status")
    public String status(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        String id = MapUtils.getString(parameter, "id");
        String type = MapUtils.getString(parameter, "status");
        int flag = MapUtils.getIntValue(parameter, "flag");
        String qq = MapUtils.getString(parameter, "qq");
        String recharge = MapUtils.getString(parameter, "recharge");
        String mmrecharge= MapUtils.getString(parameter, "mmrecharge");
        String pic = MapUtils.getString(parameter, "pic");
        String serverIds = MapUtils.getString(parameter, "serverIds");
        if (type.equals("status")) {
            parameter.put("status", flag);

            VipQq vipQq = new VipQq();
            vipQq.setStatus(flag == 1);
            vipQq.setPic(pic);
            vipQq.setMinRecharge(recharge);
            vipQq.setMmRecharge(mmrecharge);
            vipQq.setQq(qq);
            gameTemplate.vipQqOperation().superMember(vipQq, Arrays.asList(serverIds.split(",")));
            toolDaoTemplate.update("vip.status", parameter);
        }
        return "redirect:" + Global.getAdminPath() + "/tools/super/list";
    }

    @RequestMapping("save")
    public String save(VipQq vip, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        toolDaoTemplate.insert("vip.insert", parameter);
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request); 
    	WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    	String str1672= ac.getMessage("str1672", null,"添加超级会员成功", locale);
        addMessage(redirectAttributes, str1672);
        return "redirect:" + Global.getAdminPath() + "/tools/super/list";
    }


}
