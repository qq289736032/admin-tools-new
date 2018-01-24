package com.mokylin.cabal.modules.game.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;

@Controller
@RequestMapping(value = "${adminPath}/log/auctionHouse")
public class AuctionController extends BaseController {

    @RequestMapping(value = "view")
	public String view(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	

        String roleName = MapUtils.getString(parameter,"roleName");
        String itemName = MapUtils.getString(parameter,"itemName");
        int gap = setDefaultDateRange(parameter);

        if(gap > 15){
            addMessage(redirectAttributes, "时间范围必须小于15天");
            return "redirect:"+Global.getAdminPath()+"/log//auctionHouse/view/";
        }

        List operaTypeList = new ArrayList();
        if (!parameter.containsKey("operaTypeList") && parameter.containsKey("operaType")) {
            operaTypeList.add(parameter.get("operaType").toString());
            parameter.put("operaTypeList", operaTypeList);
        } else if (parameter.containsKey("operaTypeList")) {
            operaTypeList = (List) parameter.get("operaTypeList");
            if (operaTypeList.size() == OperationTypeService.getOperaTypeMap().size()) {
                parameter.put("operaTypeList", null);
            }
        }

        if(StringUtils.isNoneBlank(roleName) || StringUtils.isNoneBlank(itemName)){
        	try {
        		parameter.setPage(new Page(request, response));
        		Page<Map<String, Object>> sellPage = logDaoTemplate.paging("auctionHouse.auctionStat", parameter);
        		model.addAttribute("sellPage", sellPage);
        	} catch (Exception e) {
        		logger.error("",e);
        	}
        	model.addAttribute("selectedOperas", parameter.get("operaType"));
        	
        	operaTypeList.add(4101);
        	operaTypeList.add(4100);
        	operaTypeList.add(4105);
        	operaTypeList.add(4102);
        	operaTypeList.add(4103);
        	parameter.put("operaTypeList", operaTypeList);
        	try {
        		Page page = new Page(request,response);
        		page = logDaoTemplate.selectPage("goodsFlowLog.findAllGoodsConsume", parameter, page, "log_time");
        		model.addAttribute("page", page);
        	} catch (Exception e) {
        		logger.error("",e);
        	}
        }

        return "modules/logs/auctionStatList";
	}

    public int setDefaultDateRange(HashMap parameter){
        String createDateStart = MapUtils.getString(parameter, "createDateStart");
        String createDateEnd = MapUtils.getString(parameter, "createDateEnd");
        if (StringUtils.isEmpty(createDateStart) && StringUtils.isEmpty(createDateEnd)) {
            parameter.put("startDate", DateUtils.formatDate(DateUtils.addDays(new Date(), -15),"yyyyMMdd"));
            parameter.put("endDate", DateUtils.formatDate(DateUtils.addDays(new Date(), 0),"yyyyMMdd"));
            parameter.put("createDateStart", DateUtils.formatDate(DateUtils.addDays(new Date(), -15)));
            parameter.put("createDateEnd", DateUtils.formatDate(DateUtils.addDays(new Date(), 0)));
        }else{
            parameter.put("startDate", DateUtils.formatDate(DateUtils.parseDate(createDateStart),"yyyyMMdd"));
            parameter.put("endDate", DateUtils.formatDate(DateUtils.parseDate(createDateEnd),"yyyyMMdd"));
        }
        Date date1 = DateUtils.parseDate(MapUtils.getString(parameter, "createDateEnd"));
        Date date2 = DateUtils.parseDate(MapUtils.getString(parameter, "createDateStart"));
        return DateUtils.getDays(date1, date2);

    }
}
