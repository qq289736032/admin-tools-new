package com.mokylin.cabal.modules.ws.web;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.Md5Utils;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.common.utils.WebUtil;
import com.mokylin.cabal.modules.sys.entity.User;
import com.mokylin.cabal.modules.sys.security.UsernamePasswordToken;
import com.mokylin.cabal.modules.sys.service.SystemService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: 稻草鸟人
 * 日期: 2015/2/15 11:08
 * 项目: admin-tools
 * <p/>
 * -1	参数不全
 * -2	加密签名错误
 * -3	请求超时（链接已过期）
 * -4	非法帐号
 * -5	登陆失败
 */

@Controller("authorize")
@RequestMapping("/ws/authorize")
public class AuthorizeController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);

    public static final String AUTH_CODE = "U2FsdGVkX1+8b36mfSfOq9fu2K5F7BohCecFdlUFQTFRr+Oqb/3n8zwWV7RjVUyh";

    private SystemService systemService = SpringContextHolder.getBean(SystemService.class);

    @RequestMapping(value = {"authorize", ""})
    public void authorize(HttpServletRequest request, HttpServletResponse response, Model model) {
        int error = -1;
        String name = request.getParameter("uname");
        String time = request.getParameter("time");
        String sign = request.getParameter("sign");

        //验证签名
        if (!checkSign(name, time, sign)) {
            WebUtil.writeJSON(response, new Result(true).error(-2).data("加密签名错误"));
            return;
        }
        //账号登陆
        User user = systemService.getUserByLoginName(name);
        if (user != null) {
            UsernamePasswordToken token = new UsernamePasswordToken(name, "37wanPassword");
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);
                WebUtil.redirectRequest("/a/login",request,response);
            } catch (AuthenticationException e) {
                WebUtil.writeJSON(response, new Result(true).error(-4).data("非法帐号"));
                logger.error("错误：登录失败！", e);
            }
        } else {
            WebUtil.writeJSON(response, new Result(true).error(-4).data("非法帐号"));
            return;
        }
    }

    private boolean checkSign(String name, String time, String sign) {
        boolean flag = false;
        if (Md5Utils.md5To32(name + time + AUTH_CODE).equals(sign)) {
            flag = true;
        }
        return flag;
    }

}
