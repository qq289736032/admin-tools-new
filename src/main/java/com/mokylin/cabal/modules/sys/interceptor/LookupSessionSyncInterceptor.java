package com.mokylin.cabal.modules.sys.interceptor;

import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/23 17:07
 * 项目: cabal-tools
 */
public class LookupSessionSyncInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String currentServerId = (String)request.getSession().getAttribute(ConfigConstants.SELECTED_SERVER_KEY);

        LookupContext.setCurrentServerId(currentServerId);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
