package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/log/stallTrade")
public class StallTradeController extends BaseController {
	
	  @RequestMapping(value = "list")
	    public String list(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		  MybatisParameter parameter= (MybatisParameter) request.getAttribute("paramMap");
	        String roleName = MapUtils.getString(parameter,"roleName");
	        String sellerRoleName = MapUtils.getString(parameter,"sellerRoleName");
	        String sellerRoleId= MapUtils.getString(parameter,"sellerRoleId");
	        String roleId= MapUtils.getString(parameter,"roleId");
	        int gap = setDefaultDateRange(parameter);
	        if(gap > 15){
	            addMessage(redirectAttributes, "时间范围必须小于15天");
	            return "redirect:"+Global.getAdminPath()+"/log/stallTrade/list";
	        }
            parameter.setPage(new Page(request, response));
	        Page<Map<String, Object>> page = new Page(request,response);
	        if(StringUtils.isNotEmpty(roleName) || StringUtils.isNotEmpty(sellerRoleName)|| StringUtils.isNotEmpty(sellerRoleId)|| StringUtils.isNotEmpty(roleId)){
	        	       page = logDaoTemplate.paging("stallTrade.findStallTrade", parameter);
	        }
	        model.addAttribute("page", page);
	        return "modules/logs/stallTradeList";
	    }
	  
	  //导出excel
	  @RequestMapping(value = "export", method= RequestMethod.POST)
	    public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
	    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
			List<Map> roleRank = logDaoTemplate.selectList("stallTrade.export",parameter);
			for (Map<String, Object> map : roleRank) {
				map.put("start_time", DateUtils.formatDate(new Date(MapUtils.getLongValue(map, "start_time")), "yyyy-MM-dd HH:mm"));
			}
			return super.exportXls(roleRank, "拍卖行列表"+System.currentTimeMillis(),"服务器ID","购买ID","购买名","物品名","出售物品","数量","出售者ID","出售者名","花费元宝数量",	"税后元宝数量","上架时间");

		}


}
