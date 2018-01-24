package com.mokylin.cabal.modules.ws.web;

import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.common.utils.WebUtil;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/21 19:25
 * 项目: cabal-tools
 *
 * 提供给外部的接口，此接口接受游戏中玩家反馈信息
 *
 */
@Controller(value = "feedback")
@RequestMapping(value = "/ws/feedback")
public class FeedbackController extends BaseController {


    /**
     * roleId,title,content,type,platformId,roleName,contactInfo
     * @param request
     * @return
     */
    @RequestMapping(value = {"","index"})
    @ResponseBody
    public Result index(HttpServletRequest request){
        Map<Object, Object> parameter = WebUtil.getRequestParamMap(request);
        parameter.put("id", IdGen.uuid());
        String[] params = new String[]{"roleId","title","content","type","platformId","serverId","roleName"};
        for(String str : params){
            Object obj = MapUtils.getObject(parameter,str);
            if(obj == null){
                return new Result(false).data("缺少参数:"+str);
            }
        }

        String str = MapUtils.getString(parameter,"qq","");
        if(str.length() > 250){
            return new Result(false).data("参数:'联系信息'过长");
        }

        int result = toolDaoTemplate.insert("feedback.insert",parameter);
        logger.info("当前接口-用户反馈接口,参数：{}",parameter);

        return new Result(true);
    }
}
