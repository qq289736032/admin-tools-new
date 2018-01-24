package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.utils.excel.ExcelExcelLocal;
import com.mokylin.cabal.common.utils.excel.ExportExcel;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.log.entity.PropsDetail;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;
import com.mokylin.cabal.modules.tools.service.ResourceTypeService;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 关于道具的日志
 *
 * @author ln
 */
@Controller
@RequestMapping(value = "${adminPath}/log/propController")
public class PropsController extends BaseController {

    /**
     * 道具消耗日志
     */
    @RequestMapping(value = "propConsumeList")
    public String propConsumeList(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        HashMap parameter = (MybatisParameter) request.getAttribute("paramMap");

        String roleName = MapUtils.getString(parameter,"roleName");
        String itemName = MapUtils.getString(parameter,"itemName");
        int gap = setDefaultDateRange(parameter);

        if(gap > 15){
            addMessage(redirectAttributes, "时间范围必须小于15天");
            return "redirect:"+Global.getAdminPath()+"/log/propController/propConsumeList/";
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

        Page page = new Page(request,response);
        if(StringUtils.isNoneBlank(roleName) || StringUtils.isNoneBlank(itemName)){
            try {
                page = logDaoTemplate.selectPage("goodsFlowLog.findGoodsConsume", parameter, page, "log_time");
            } catch (Exception e) {
                logger.error("",e);
            }

        }
        model.addAttribute("selectedOperas", parameter.get("operaType"));
        model.addAttribute("page", page);

        return "modules/logs/propConsumeList";
    }

    /**
     * 道具消耗导出excel
     *
     * @param request
     * @param response
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("log.propConsume.export")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefaultDateRange(parameter);

        List<String> operaTypeList = new ArrayList<String>();
        if (!parameter.containsKey("operaTypeList") && parameter.containsKey("operaType")) {
            operaTypeList.add(parameter.get("operaType").toString());
            parameter.put("operaTypeList", operaTypeList);
        } else if (parameter.containsKey("operaTypeList")) {
            operaTypeList = (List) parameter.get("operaTypeList");
            if (operaTypeList.size() == OperationTypeService.getOperaTypeMap().size()) {
//					 operaTypeList= new ArrayList();
//					 parameter.put("operaTypeList", operaTypeList);
                parameter.put("operaTypeList", null);
            }
        }
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request); 
    	WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    	String prop= ac.getMessage("consume.prop", null,"道具消耗", locale);
    	String str1674= ac.getMessage("str1674", null,"导出道具消耗！失败信息：", locale);
        try {
            String fileName = prop + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            //List<PropsDetail> PropConsumeList = logDaoTemplate.selectList("goodsFlowLog.paging", parameter);
            List<Map<String, Object>> PropConsumeList = logDaoTemplate.selectListByMultiTable("goodsFlowLog.paging", parameter);
            /*for (int i = 0; i < PropConsumeList.size(); i++) {
                PropConsumeList.get(i).setOpereateType(OperationTypeService.getOperationType(PropConsumeList.get(i).getOpereateType()));
                PropConsumeList.get(i).setResource(ResourceTypeService.getResourceType(PropConsumeList.get(i).getResource()));
                PropConsumeList.get(i).setFlowType(PropConsumeList.get(i).getFlowType() == "1" ? "获得" : "消耗");
            }*/
            new ExcelExcelLocal(prop, PropsDetail.class,request).setDataList(getProDetail(PropConsumeList,request)).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, str1674 + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/log/propController/propConsumeList";
    }


    /**
     * 道具获取日志
     */
    @RequestMapping(value = "propGainList")
    public String propGainList(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        String roleName = MapUtils.getString(parameter,"roleName");
        String itemName = MapUtils.getString(parameter,"itemName");
        int gap = setDefaultDateRange(parameter);

        if(gap > 15){
            addMessage(redirectAttributes, "时间范围必须小于15天");
            return "redirect:"+Global.getAdminPath()+"/log/propController/propConsumeList/";
        }

        List operaTypeList = new ArrayList();

        //setDefaultTableSuffix(parameter);

        if (!parameter.containsKey("operaTypeList") && parameter.containsKey("operaType")) {
            operaTypeList.add(parameter.get("operaType").toString());
            parameter.put("operaTypeList", operaTypeList);
        } else if (parameter.containsKey("operaTypeList")) {
            operaTypeList = (List) parameter.get("operaTypeList");
            if (operaTypeList.size() == OperationTypeService.getOperaTypeMap().size()) {
                parameter.put("operaTypeList", null);
            }
        }

        Page page = new Page(request,response);
        if(StringUtils.isNoneBlank(roleName) || StringUtils.isNoneBlank(itemName)){
            try {
                page = logDaoTemplate.selectPage("goodsFlowLog.findGoodsGain", parameter, page, "log_time");
            } catch (Exception e) {
                logger.error("",e);
            }

        }

        model.addAttribute("selectedOperas", parameter.get("operaType"));
        model.addAttribute("page", page);
        return "modules/logs/propGainList";
    }

    /**
     * 道具获取导出excel
     */
    @RequiresPermissions("log.propGain.export")
    @RequestMapping(value = "gainExport", method = RequestMethod.POST)
    public String gainExport(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        setDefaultDateRange(parameter);

        List<String> operaTypeList = new ArrayList<String>();
        if (!parameter.containsKey("operaTypeList") && parameter.containsKey("operaType")) {
            operaTypeList.add(parameter.get("operaType").toString());
            parameter.put("operaTypeList", operaTypeList);
        } else if (parameter.containsKey("operaTypeList")) {
            operaTypeList = (List) parameter.get("operaTypeList");
            if (operaTypeList.size() == OperationTypeService.getOperaTypeMap().size()) {
                parameter.put("operaTypeList", null);
            }
        }
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request); 
    	WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    	String gain= ac.getMessage("gain.prop", null,"道具获取", locale);
    	String str1675= ac.getMessage("str1675", null,"导出道具获取！失败信息：", locale);
        try {

            String fileName = gain + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            //List<PropsDetail> PropConsumeList = logDaoTemplate.selectList("goodsFlowLog.propGainPaging", parameter);
            List<Map<String, Object>> PropConsumeList = logDaoTemplate.selectListByMultiTable("goodsFlowLog.propGainPaging", parameter);

           /* for (int i = 0; i < PropConsumeList.size(); i++) {
                PropConsumeList.get(i).setOpereateType(OperationTypeService.getOperationType(PropConsumeList.get(i).getOpereateType()));
                PropConsumeList.get(i).setResource(ResourceTypeService.getResourceType(PropConsumeList.get(i).getResource()));
                PropConsumeList.get(i).setFlowType(PropConsumeList.get(i).getFlowType() == "1" ? "获得" : "消耗");

            }*/
           new ExcelExcelLocal(gain, PropsDetail.class,request).setDataList(getProDetail(PropConsumeList,request)).write(response, fileName).dispose();
           return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, str1675 + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/log/propController/propGainList?repage";
    }
    
    
    	public    List<PropsDetail>  getProDetail(List<Map<String,Object>>propConsumeList,HttpServletRequest request){
       	    List<PropsDetail> propConsumeListEnd=new ArrayList<>();
       	   Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request); 
       	   WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
           String str921= ac.getMessage("str921", null,"获得", locale);
           String str851= ac.getMessage("str851", null,"消耗", locale);
            for(Map<String, Object> map:propConsumeList){
            	if(map.size()!=0){
            		PropsDetail  propsDetail=new PropsDetail();
            	  propsDetail.setRoleName(map.get("roleName").toString());
            	  propsDetail.setActive(map.get("active").toString());
            	  propsDetail.setItemName(map.get("itemName").toString());
            	  propsDetail.setValue(map.get("value").toString());
            	  propsDetail.setBeforeValue(map.get("beforeValue").toString());
            	  propsDetail.setAfterValue(map.get("afterValue").toString());
            	  propsDetail.setLogTime(DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "log_time")), "yyyy-MM-dd HH:mm"));
            	  propsDetail.setOpereateType(OperationTypeService.getOperationType(map.get("opereateType").toString()));
            	  propsDetail.setResource(map.get("resource").toString());
            	  propsDetail.setFlowType(map.get("flowType").toString()== "1" ? str921 : str851);
            	  propConsumeListEnd.add(propsDetail);
            	}
            	  
            }
            return   propConsumeListEnd;
    	
    }
    
    


}
