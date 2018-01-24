package com.mokylin.cabal.modules.tools.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.GameEmail;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/20 14:30
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/feedback")
public class FeedbackController extends BaseController {


    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = toolDaoTemplate.paging("feedback.paging", parameter);

        model.addAttribute("page", page);

        return "modules/tools/feedbackList";
    }

    @RequiresPermissions("game.feedback.reply")
    @RequestMapping(value = "feedbackForm")
    public String feedbackForm(HttpServletRequest request, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        model.addAttribute("map",parameter);
        return "modules/tools/feedbackForm";
    }

    /**
     * 发送完邮件其实可以把邮件内容都记录下来的
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("game.feedback.reply")
    @RequestMapping(value = "send")
    public String send(HttpServletRequest request, RedirectAttributes redirectAttributes){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        GameEmail email = new GameEmail();
        email.setReceiverUserIds(parameter.get("roleId").toString());
        email.setId(parameter.get("id").toString());
        email.setTitle(parameter.get("title").toString());
        email.setContent(parameter.get("content").toString());
        Result result = gameTemplate.gameEmailOperation().sendEmail(email, MapUtils.getString(parameter,"serverId"));
        if(result.isSuccess()) {
            //更新回复状态
            parameter.put("isReplied",1);   //1表示已回复，0表示未回复
            parameter.put("feedbackContent", JSON.toJSONString(email));
            toolDaoTemplate.update("feedback.updateRepliedStatus",parameter);
            addMessage(redirectAttributes, "回复玩家：" + parameter.get("roleName") + "成功");
        }

        return "redirect:"+ Global.getAdminPath()+"/tools/feedback/";
    }




}
