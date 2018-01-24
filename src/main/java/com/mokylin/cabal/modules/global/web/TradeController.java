package com.mokylin.cabal.modules.global.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.constant.OperateType;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.web.BaseController;

/**
 * 充值消耗
 *
 * @author ln
 */
@Controller
@RequestMapping(value = "${adminPath}/global/tradeController")
public class TradeController extends BaseController {

    /**
     * 资源线消费分布
     */
    @RequestMapping(value = "resLineDistribution")
    public String resLineDistribution(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //setServerIdList(parameter);
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        model.addAttribute("createDateStart", parameter.get("createDateStart"));
        model.addAttribute("createDateEnd", parameter.get("createDateEnd"));
        model.addAttribute("reslist", globalDaoTemplate.selectList("consumeDistribution.resLineYbDisList", parameter));
        List<Map<String, Object>> totalList = globalDaoTemplate.selectList("consumeDistribution.selectTotal", parameter);
        if (totalList.get(0) != null) {
            for (Map<String, Object> map : totalList) {

//
//                if( "0".equals(map.get("total_yb").toString())){  //消耗总元宝
//                    model.addAttribute("totalYb", 0.0001);
//                }else{
                model.addAttribute("totalYb", map.get("total_yb"));
//                }

//                if( "0".equals(map.get("yb_num").toString())){
//                    model.addAttribute("ybNum",  0.0001);
//                }else{
                model.addAttribute("ybNum", map.get("yb_num"));
//                }
                //消耗元宝总人数


//                model.addAttribute("totalBind", map.get("total_bind"));    //绑定总元宝
//                if( "0".equals(map.get("total_bind").toString())){
//                    model.addAttribute("totalBind", 0.0001);
//                }else{
                model.addAttribute("totalBind", map.get("total_bind"));
//                }


//                if( "0".equals(map.get("bind_num").toString())){//绑定元宝总人数
//                    model.addAttribute("bindNum",  0.0001);
//                }else{
                model.addAttribute("bindNum", map.get("bind_num"));
//                }

            }
        }
        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/resLineDistribution";
    }


    /**
     * 资源线统计元宝消耗分布服务器统计展示
     */
    @RequestMapping(value = "resLineServersDistribution")
    @ResponseBody
    public List<Map<String, Object>> resLineServersDistribution(HttpServletRequest request, HttpServletResponse response, Model model, String id,
                                                                String serverIds, String DateStart, String DateEnd) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String[] strs = serverIds.split(",");
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++) {
            list.add(strs[i]);
        }
        parameter.put("list", list);
        parameter.put("id", id);
        parameter.put("DateStart", DateStart);
        parameter.put("DateEnd", DateEnd);
        List<Map<String, Object>> resServersList = globalDaoTemplate.selectList("consumeDistribution.resLineServersList", parameter);
        return resServersList;
    }

    /**
     * 活动面板消费分布
     */
    @RequestMapping(value = "activePanelDistribution")
    public String activePanelDistribution(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        //setServerIdList(parameter);
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        model.addAttribute("createDateStart", parameter.get("createDateStart"));
        model.addAttribute("createDateEnd", parameter.get("createDateEnd"));
        model.addAttribute("activeList", globalDaoTemplate.selectList("consumeDistribution.activePanelList", parameter));
        List<Map<String, Object>> totalList = globalDaoTemplate.selectList("consumeDistribution.selectTotal", parameter);
        if (totalList.get(0) != null) {
            for (Map<String, Object> map : totalList) {
                model.addAttribute("totalYb", map.get("total_yb"));        //消耗总元宝
                model.addAttribute("ybNum", map.get("yb_num"));            //消耗元宝总人数
                model.addAttribute("totalBind", map.get("total_bind"));            //消耗元宝总人数
                model.addAttribute("bindNum", map.get("bind_num"));            //消耗元宝总人数


            }
        }

        // 把serverIdList转成string
        parameter.put("serverIdList", parameter.get("serverIds"));
        return "modules/global/activePanelDistribution";
    }

    /**
     * 道具销售 及新服道具销售
     */
    @RequestMapping(value = "propSale")
    public String propSale(String stype, HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        //setServerIdList(parameter);
        //默认查询时间范围为7天
        setDefaultTimeRange(parameter);
        model.addAttribute("createDateStart", parameter.get("createDateStart"));
        model.addAttribute("createDateEnd", parameter.get("createDateEnd"));
        parameter.put("stype", stype.equals("0") ? null : stype);
        Integer total = globalDaoTemplate.selectOne("goodsSales.selecTotal", parameter);
        model.addAttribute("total", total == null ? 0 : total);
        if (stype.equals("1")) {
            parameter.setPage(new Page(request, response));
            Page<Map<String, Object>> page = globalDaoTemplate.paging("goodsSales.goodsSalesList", parameter);
            for(Map<String, Object> obj : page.getList()){
            	OperateType operateType = OperateType.getOperateType(Integer.valueOf(obj.get("operateType").toString()));
            	obj.put("operateName", operateType.getDescription());
            }
            model.addAttribute("page", page);
            // 把serverIdList转成string
            parameter.put("serverIdList", parameter.get("serverIds"));
            return "modules/global/newServerPropSale";
        } else {
            parameter.setPage(new Page(request, response));
            Page<Map<String, Object>> page = globalDaoTemplate.paging("goodsSales.goodsSalesList", parameter);
            for(Map<String, Object> obj : page.getList()){
            	OperateType operateType = OperateType.getOperateType(Integer.valueOf(obj.get("operateType").toString()));
            	if(operateType != null){
            		obj.put("operateName", operateType.getDescription());
            	}
            }
            model.addAttribute("page", page);
            // 把serverIdList转成string
            parameter.put("serverIdList", parameter.get("serverIds"));
            
            return "modules/global/propSale";
        }
    }


}
