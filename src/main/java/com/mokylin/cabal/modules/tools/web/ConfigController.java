package com.mokylin.cabal.modules.tools.web;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.ConfigClient;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.mothRevenue;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;

/**
 * 运营数据配置
 *
 * @author
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/config")
public class ConfigController extends BaseController {

    @Autowired
    private RedisManager redisManager;

    /**
     * 月营收入指标列表
     */
    @RequestMapping(value = "list")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("list", toolDaoTemplate.selectList("monthRevenue.index"));
        return "modules/tools/mothRevenueList";
    }

    /**
     * 月营收指标模型（添加）
     */
    @RequestMapping(value = "save")
    public String monthRevenueModel(mothRevenue monthRevenue, HttpServletRequest request, HttpServletResponse response,
                                    Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        if (monthRevenue != null) {
            parameter.put("payment", Double.valueOf(parameter.get("payNum").toString())
                    / Double.valueOf(parameter.get("active").toString()));
            parameter.put("arpu", Integer.valueOf(parameter.get("income").toString())
                    / Integer.valueOf(parameter.get("payNum").toString()));
            if (parameter.containsKey("type") && parameter.get("type").equals("新增")) {
                toolDaoTemplate.insert("monthRevenue.insert", parameter);
                addMessage(redirectAttributes, "保存成功");
                return "redirect:" + Global.getAdminPath() + "/tools/config/list";
            } else {
                toolDaoTemplate.update("monthRevenue.updateRevenue", parameter);
                addMessage(redirectAttributes, "修改成功");
                return "redirect:" + Global.getAdminPath() + "/tools/config/list";
            }

        } else {
            addMessage(redirectAttributes, "输入有误！");
            return "redirect:" + Global.getAdminPath() + "/tools/config/form";
        }
    }

    /**
     * 月营收指标模型（修改跳转页面）
     */
    @RequestMapping(value = "form")
    public String form(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        mothRevenue mothRevenue = toolDaoTemplate.selectOne("monthRevenue.selectOneById", id);
        model.addAttribute("id", id);
        model.addAttribute("mothRevenue", mothRevenue);
        return "modules/tools/mothRevenueModel";
    }

    @RequestMapping("limitList")
    public String limitConfig(HttpServletRequest request, Model model) {
        model.addAttribute("jinbi", redisManager.getJinbiLimit());
        model.addAttribute("goods", redisManager.getGoodsLimit() == null ? "" : redisManager.getGoodsLimit());
        model.addAttribute("notice", redisManager.getGameUpdateLog());
        return "modules/tools/limitList";
    }

    @RequestMapping("saveLimit")
    @ResponseBody
    public Result saveLimit(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String jinbi = MapUtils.getString(parameter, "jinbi");
        if (!StringUtils.isEmpty(jinbi)) {
            redisManager.addJinbiLogLimit(jinbi);
            return new Result(true).data("success");
        }
        String goods = MapUtils.getString(parameter, "goods");
        if (!StringUtils.isEmpty(goods)) {
            redisManager.addGoodsLogLimit(goods);
            return new Result(true).data("success");
        }
        String notice = MapUtils.getString(parameter, "notice");
        if (!StringUtils.isEmpty(notice)) {
            redisManager.addGameUpdateLog(notice);
            return new Result(true).data("success");
        }
        return new Result(false).data("保存失败");
    }

    @RequestMapping("gameUpdate")
    public String gameUpdateForm(HttpServletRequest request, Model model) {
        JSONObject object = JSON.parseObject(redisManager.getGameUpdateLog());
        model.addAttribute("notice_log", object == null ? "" : object.get("log"));
        model.addAttribute("notice_content", object == null ? "" : object.get("content"));
        return "modules/tools/gameUpdateForm";
    }

    @RequestMapping("saveGameUpdateLog")
    @ResponseBody
    public Result gameUpdate(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String notice_log = MapUtils.getString(parameter, "notice_log");
        String notice_content = MapUtils.getString(parameter, "notice_content");
        JSONObject object = new JSONObject();
        object.put("log", notice_log);
        object.put("content", notice_content);
        redisManager.addGameUpdateLog(JSON.toJSONString(object));
        return new Result(true).data("success");
    }

    @RequestMapping("chenmi")
    public String chenmi(HttpServletRequest request, Model model) {
        model.addAttribute("map", redisManager.getAllChenMi());
        return "modules/tools/chenmiList";
    }

    @RequestMapping("chenmiForm")
    public String chenmiForm(HttpServletRequest request, Model model) {
        // model.addAttribute("map", redisManager.getAllChenMi());

        return "modules/tools/chenmiForm";
    }


    @RequestMapping("addChenMi")
    public String addChenMi(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String pid = MapUtils.getString(parameter, "pid");
        String isOpen = MapUtils.getString(parameter, "isOpen", null) == null ? "0" : "1";

		redisManager.addChenMi(pid, isOpen);

        return "redirect:" + Global.getAdminPath() + "/tools/config/chenmi";
    }

    @RequestMapping("updateChenMi")
    @ResponseBody
    public Result updateChenMi(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String pid = MapUtils.getString(parameter, "pid");
        int flag = MapUtils.getIntValue(parameter, "flag");

        redisManager.addChenMi(pid, String.valueOf(flag));

        return new Result(true);
    }

    @RequestMapping("deleteChenMi")
    public String deleteChenMi(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String pid = MapUtils.getString(parameter, "pid");
        redisManager.delChenMi(pid);
        return "redirect:" + Global.getAdminPath() + "/tools/config/chenmi";
    }

    @RequestMapping("client")
    public String client(HttpServletRequest request, Model model) {
        Map<String, String> map = redisManager.getAllClientUrl();
        List<ConfigClient> list = Lists.newArrayList();
        Server s = null;
        ConfigClient configClient;
        for (String key : map.keySet()) {
            if (!StringUtils.isNum(key)) {
                configClient = new ConfigClient();
                configClient.setServerId(key);
                configClient.setUrl(map.get(key).toString());
                list.add(configClient);

            } else {
                s = RedisUtils.getGameServer(key);
                if (s != null) {
                    configClient = new ConfigClient();
                    configClient.setPlatformId(RedisUtils.getGameServer(key).getPlatformId());
                    configClient.setName(RedisUtils.getGameServer(key).getName());
                    configClient.setServerId(key);
                    configClient.setUrl(map.get(key).toString());
                    list.add(configClient);
                } else {
                    configClient = new ConfigClient();
                    configClient.setServerId(key);
                    configClient.setUrl(map.get(key).toString());
                    list.add(configClient);
                }
            }
        }
        if (list != null) {
            Collections.sort(list, new Comparator<ConfigClient>() {
                @Override
                public int compare(ConfigClient o1, ConfigClient o2) {
                    int i = 0, j = 0;
                    if (StringUtils.isNotEmpty(o1.getPlatformId())) {
                        String p1 = o1.getPlatformId().substring(0, 1);
                        if (StringUtils.isNum(p1)) {
                            i = Integer.parseInt(p1);
                        } else {
                            i = p1.hashCode();
                        }
                    }
                    if (StringUtils.isNotEmpty(o2.getPlatformId())) {
                        String p2 = o2.getPlatformId().substring(0, 1);
                        if (StringUtils.isNum(p2)) {
                            j = Integer.parseInt(p2);
                        } else {
                            j = p2.hashCode();
                        }
                    }
                    int c = i - j;
                    if (c == 0) {
                        if (StringUtils.isNum(o1.getServerId()) && StringUtils.isNum(o2.getServerId())) {
                            return Integer.parseInt(o1.getServerId()) - Integer.parseInt(o2.getServerId());
                        }
                    }
                    return c;
                }
            });
        }

        model.addAttribute("map", new TreeMap<String, String>(map));

        model.addAttribute("list", list);

        return "modules/tools/clientList";
    }
    
//    /** 获取测试服的sid */
//    @RequestMapping("testsid")
//    public String testSid(HttpServletRequest request, Model model) {
//    	
//    	String tsid = redisManager.getTestSid();
//    	model.addAttribute("tsid", tsid);
//    	
//    	return "modules/tools/testsid";
//    }
//    
//    /** 测试服sid设置 */
//    @RequestMapping("updatetestsid")
//    public String updateTestSid(HttpServletRequest request) {
//    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
//    	String serverId = MapUtils.getString(parameter,"serverId");
//    	redisManager.updateTestSid(serverId);
//    	return "redirect:" + Global.getAdminPath() + "/tools/config/testsid";
//    }

    @RequestMapping("clientForm")
    public String clientForm(HttpServletRequest request, Model model) {
        // model.addAttribute("map", redisManager.getAllChenMi());

        return "modules/tools/clientForm";
    }

    @RequestMapping("addClient")
    public String addClient(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> serverIds = (List<String>) MapUtils.getObject(parameter, "serverIdList");
        String url = MapUtils.getString(parameter, "url");
        Integer isGlobal = MapUtils.getInteger(parameter, "isGlobal");

        if (isGlobal == 1) {
            redisManager.addClient("_default", url);
        } else {
            for (String server : serverIds) {
                redisManager.addClient(server, url);
            }
        }

        return "redirect:" + Global.getAdminPath() + "/tools/config/client";
    }

    @RequestMapping("deleteClient")
    public String deleteClient(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String serverId = MapUtils.getString(parameter, "serverId");
        redisManager.delClient(serverId);

        return "redirect:" + Global.getAdminPath() + "/tools/config/client";
    }

    @RequestMapping("delCheckClient")
    public String delCheckClient(HttpServletRequest request) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String[] recordIds = request.getParameterValues("recordIds");
        // List<String> recordIds = (List<String>) MapUtils.getObject(parameter,
        // "recordIdList");
        for (String s : recordIds) {
            redisManager.delClient(s);
        }

        return "redirect:" + Global.getAdminPath() + "/tools/config/client";
    }

}
