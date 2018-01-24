package com.mokylin.cabal.modules.ws.web;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.utils.Md5Utils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.utils.WebUtil;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * 此接口做在Gate项目中...
 */
@Controller
@RequestMapping("/ws/phone")
public class PhoneController extends BaseController {

    private static final String API_KEY = "V3Y!z5*,J1~1aNUZ$G21*DnzN;RV9#";

    @Resource
    private ServerManager serverManager;

    @RequestMapping(value = {"", "index"})
    public void index(HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> parameter = WebUtil.getRequestParamMap(request);
        String sid = MapUtils.getString(parameter, "sid");              //服务器ID
        String username = MapUtils.getString(parameter, "username");    //平台用户名
        String actor = MapUtils.getString(parameter, "actor");          //角色ID
        String time = MapUtils.getString(parameter, "time");            //时间
        String sign = MapUtils.getString(parameter, "sign");             //签名
        String pid = MapUtils.getString(parameter, "pid");

        logger.info("当前接口-手机校验通知游戏接口");

        if (!checkSign(sid, username, actor, time, sign)) {
            WebUtil.writeJSON(response, new Result(true).error(3).data("加密签名错误"));
            return;
        }

        //调用游戏接口
        String serverId = serverManager.getGameServer(pid, sid);
        if (StringUtils.isEmpty(serverId)) {
            WebUtil.writeJSON(response, new Result(true).error(7).data("###校验失败!当前参数平台【" + pid + "】,sid【" + sid + "】获取不到服务器编号###"));
            return;
        }

        gameTemplate.roleOperation().notice(serverId, Long.parseLong(actor));
        WebUtil.writeJSON(response, new Result(true).error(7).data("校验成功"));
    }

    private boolean checkSign(String sid, String username, String actor, String time, String sign) {
        boolean flag = false;
        if (Md5Utils.md5To32(sid + username + actor + time + API_KEY).equals(sign)) {
            flag = true;
        }
        return flag;
    }
}
