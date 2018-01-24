package com.mokylin.cabal.modules.ws.web;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/8/25
 * admin-tools
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller("stat")
@RequestMapping("/ws/stat")
public class StatController extends BaseController {

    public static final String AUTH_CODE = "a39793d88e269abf8041a0c93fad7abb";

    public static final int RET_CODE_SUCCESS = 1;  //查询成功
    public static final int RET_CODE_ERROR = -1;  //校验错误
    public static final int RET_CODE_SERVER_ERROR = -2;  //内部错误

    @Resource
    private ServerManager serverManager;

    /**
     * 获取游戏在线用户数量
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"roleOnlineAvg", ""})
    public Result roleAvg(HttpServletRequest request, HttpServletResponse response, Model model) {

        String serverId = request.getParameter("serverId");

        String pid = request.getParameter("pid");
        String startTime = new Long(Long.valueOf(request.getParameter("startTime")) + 5 * 1000).toString();
        String endTime = new Long(Long.valueOf(request.getParameter("endTime")) + 5 * 1000).toString();

        Map<Object, Object> parameter = Maps.newHashMap();
        parameter.put("serverId", serverId);
        parameter.put("pid", pid);
        parameter.put("startTime", startTime);
        parameter.put("endTime", endTime);

        logger.info("YY roleAvg查询接口-参数:[{}]", JSON.toJSONString(parameter));

        Map<String, Object> map = logDaoTemplate.selectOne("statRealTimeService.findBylogTimeAvg", parameter);
        float online = MapUtils.getFloat(map, "online");
        int top = MapUtils.getIntValue(map, "top");
        int low = MapUtils.getIntValue(map, "low");

        logger.info("YY roleAvg 查询接口-返回值:[{}]", JSON.toJSONString(map));

        Result result = new Result(RET_CODE_SUCCESS, online + ":" + top + ":" + low);
        return result;
    }

    /**
     * 获取游戏注册用户数量
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"roleCreateCount", ""})
    public Result roleCreateCount(HttpServletRequest request, HttpServletResponse response, Model model) {
        String serverId = request.getParameter("serverId");
        String pid = request.getParameter("pid");

        String startTime = new Long(Long.valueOf(Long.valueOf(request.getParameter("startTime"))) + 5 * 1000).toString();
        String endTime = new Long(Long.valueOf(Long.valueOf(request.getParameter("endTime"))) + 5 * 1000).toString();

        Map<Object, Object> parameter = Maps.newHashMap();
        parameter.put("serverId", serverId);
        parameter.put("pid", pid);
        parameter.put("startTime", startTime);
        parameter.put("endTime", endTime);
        logger.info("YY roleCreateCount 查询接口-参数:[{}]", JSON.toJSONString(parameter));
        Map<String, Object> map = logDaoTemplate.selectOne("statRealTimeService.findByCreateRoleCount", parameter);
        int register = MapUtils.getIntValue(map, "register");
        logger.info("YY roleCreateCount 查询接口-返回值:[{}]", JSON.toJSONString(map));
        Result result = new Result(RET_CODE_SUCCESS, register + "");
        return result;
    }

    /**
     * 角色相关
     *
     * @param request
     * @param response
     * @param model
     */
    @ResponseBody
    @RequestMapping(value = {"role", ""})
    public Result role(HttpServletRequest request, HttpServletResponse response, Model model) {
        String serverId = request.getParameter("serverId");
        String date = request.getParameter("date");
        Map<Object, Object> parameter = Maps.newHashMap();
        parameter.put("serverId", serverId);
        parameter.put("game", "yy");
        parameter.put("date", date);
        logger.info("YY查询接口-参数:[{}]", JSON.toJSONString(parameter));
        Map<String, Object> map = logDaoTemplate.selectOne("statRealTimeService.findByDay", parameter);
        int roleTotal = MapUtils.getIntValue(map, "dru");
        int loginTotal = MapUtils.getIntValue(map, "dau");
        int maxOnline = MapUtils.getIntValue(map, "pcu");
        logger.info("YY查询接口-返回值:[{}]", JSON.toJSONString(map));
        Result result = new Result(RET_CODE_SUCCESS, roleTotal + ":" + loginTotal + ":" + maxOnline);
        return result;
    }

    class Result {
        private int retcode;
        private String data;

        public Result(int retcode) {
            this.retcode = retcode;
        }

        public Result() {
        }

        public Result(int retcode, String data) {
            this.retcode = retcode;
            this.data = data;
        }

        public int getRetcode() {
            return retcode;
        }

        public void setRetcode(int retcode) {
            this.retcode = retcode;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


}
