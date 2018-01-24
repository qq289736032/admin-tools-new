package com.mokylin.cabal.modules.log.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.log.utils.IncidentManage;

@Controller
@RequestMapping(value = "${adminPath}/log/goodsFlow")
public class GoodsFlowController extends BaseController {
	
    /**
     * 道具产出统计
     */
    @RequestMapping("propProduce")
    public String propProduce(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/propProduce";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("goodsFlowLog.findPropProduce", parameter);
        model.addAttribute("list", list);
        return "modules/logs/propProduce";
    }
    
    /**
     * 道具消耗统计
     */
    @RequestMapping("propProduceConsume")
    public String propProduceConsume(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String logDay = MapUtils.getString(parameter, "logDay");
        if (StringUtils.isEmpty(logDay)) {
            return "modules/logs/propProduceConsume";
        }
        setTableSuffix(parameter);
        IncidentManage.filterOperationType(parameter);
        List<Map<String, Object>> list = logDaoTemplate.selectList("goodsFlowLog.propProduceConsume", parameter);
        model.addAttribute("list", list);
        return "modules/logs/propProduceConsume";
    }

}
