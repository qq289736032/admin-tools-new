package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.log.utils.IncidentManage;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/16
 * admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/log/moneyFlow")
public class MoneyFlowController extends BaseController {


    @RequestMapping("treasureProduce")
    public String treasureProduce(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/treasureProduce";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("moneyFlowLog.findTreasureProduce", parameter);

        model.addAttribute("list", list);
        return "modules/logs/treasureProduce";
    }

    @RequestMapping("treasureConsume")
    public String treasureConsume(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/treasureConsume";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("moneyFlowLog.findTreasureConsume", parameter);

        model.addAttribute("list", list);
        return "modules/logs/treasureConsume";
    }

    @RequestMapping("bandingProduce")
    public String bandingProduce(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/bandingProduce";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("moneyFlowLog.findBandingProduce", parameter);

        model.addAttribute("list", list);
        return "modules/logs/bandingProduce";
    }

    @RequestMapping("bandingConsume")
    public String bandingConsume(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/bandingConsume";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("moneyFlowLog.findBandingConsume", parameter);

        model.addAttribute("list", list);
        return "modules/logs/bandingConsume";
    }
    
    //金币统计
    @RequestMapping("coinProduce")
    public String coinProduce(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/coinProduce";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("moneyFlowLog.findCoinProduce", parameter);

        model.addAttribute("list", list);
        return "modules/logs/coinProduce";
    }
    
    //金币消耗
    @RequestMapping("coinProduceConsume")
    public String coinProduceConsume(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/coinProduceConsume";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("moneyFlowLog.coinProduceConsume", parameter);

        model.addAttribute("list", list);
        return "modules/logs/coinProduceConsume";
    }
}
