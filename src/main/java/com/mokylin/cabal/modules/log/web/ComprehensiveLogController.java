package com.mokylin.cabal.modules.log.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.excel.ExportExcel;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.log.entity.comprehensiveDetail;
import com.mokylin.cabal.modules.tools.service.OperationTypeService;

/**
 * 综合日志查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/log/comprehensiveLogController")
public class ComprehensiveLogController extends BaseController{
	
	@RequestMapping(value = "comprehensiveList")
	public String ComprehensiveList(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");	
//		setDefaultTimeRange(parameter);
		model.addAttribute("eventList",OperationTypeService.getOperaTypeMap());
		List<String> operaTypeList = new ArrayList<String>();
//		if(!parameter.containsKey("createDateStart")&& !parameter.containsKey("createDateEnd")){
//			setDefaultTimeRange(parameter);
//		}
		
		    if(!parameter.containsKey("operaTypeList")&&parameter.containsKey("operaType")){
		    	operaTypeList.add(parameter.get("operaType").toString());
					parameter.put("operaTypeList", operaTypeList);
				}else if(parameter.containsKey("operaTypeList")){
					operaTypeList = (List) parameter.get("operaTypeList");
					 if(operaTypeList.size()==OperationTypeService.getOperaTypeMap().size()){
//						 operaTypeList= new ArrayList();
//						 parameter.put("operaTypeList", operaTypeList);
						 parameter.put("operaTypeList", null);
					 }
				}
			
		parameter.setPage(new Page(request, response));
        Page<Map<String,Object>> page = logDaoTemplate.paging("comprehensiveLog.paging", parameter);
        model.addAttribute("page", page);
        model.addAttribute("selectedOperas", parameter.get("operaType"));
		
		return "modules/logs/comprehensiveList";
	}
	
	
	/**
	 *综合日志导出excel
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("log.comprehensive.export")
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	List<String> operaTypeList = new ArrayList<String>();
    	if(!parameter.containsKey("createDateStart")&& !parameter.containsKey("createDateEnd")){
//			setDefaultTimeRange(parameter);
		}

    	  if(!parameter.containsKey("operaTypeList")&&parameter.containsKey("operaType")){
		    	operaTypeList.add(parameter.get("operaType").toString());
					parameter.put("operaTypeList", operaTypeList);
				}else if(parameter.containsKey("operaTypeList")){
					operaTypeList = (List) parameter.get("operaTypeList");
					 if(operaTypeList.size()==OperationTypeService.getOperaTypeMap().size()){
//						 operaTypeList= new ArrayList();
//						 parameter.put("operaTypeList", operaTypeList);
						 parameter.put("operaTypeList", null);
					 }
				}
        try {
            String fileName = "综合日志"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<comprehensiveDetail> comprehensiveList = logDaoTemplate.selectList("comprehensiveLog.paging", parameter);
            for (int i = 0; i < comprehensiveList.size(); i++) {
            	comprehensiveList.get(i).setOperationType(OperationTypeService.getOperationType(comprehensiveList.get(i).getOperationType()));
			}
            new ExportExcel("综合日志", comprehensiveDetail.class).setDataList(comprehensiveList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出综合日志！失败信息："+e.getMessage());
        }
        return "redirect:"+ Global.getAdminPath()+"/log/comprehensiveLogController/comprehensiveList?repage";
    }

}
