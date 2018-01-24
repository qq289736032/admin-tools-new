package com.mokylin.cabal.modules.tools.web;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.SpringContextHolder;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.service.QueryService;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/24
 * admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/query")
public class QueryController extends BaseController {

    @Autowired
    private QueryService queryService;


    private JdbcTemplate jdbcTemplate = SpringContextHolder.getBean("logJdbcTemplate");

    /**
     * 白名单
     */
    private String[] whiteList = {"select", "show", "desc", "describe"};



    @RequestMapping(value = {"","list"})
    public String list(HttpServletRequest request, Model model) {
        model.addAttribute("list", toolDaoTemplate.selectList("sqlHistory.select"));
        return "modules/tools/queryList";
    }


    @RequestMapping("query")
    @ResponseBody
    public String query(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String sql = MapUtils.getString(parameter,"sql");
        sql = StringUtils.trim(sql);
        List<Map<String,Object>> list = Collections.emptyList();
        boolean flag = isAllowed(sql);
        if(!flag){
            return "不允许执行此SQL！";
        }

      /*  if(sql.toLowerCase().startsWith("select") && !sql.toLowerCase().contains("limit")){
            sql = sql + " limit 100";
        }*/

        list = jdbcTemplate.queryForList(sql);
        Page page = new Page(request,response);
        page.setCount(list.size());
        Integer end = page.getPageNo() * page.getPageSize();
		if(end > list.size()){
			end = list.size();
		}
    	page.setList(list.subList((page.getPageNo() - 1) * page.getPageSize() , end));

        StringBuffer sb = new StringBuffer();
        String[] header = queryService.getHeaders(page.getList());
        sb.append("<table  class='table table-striped table-hover' style='overflow: scroll'>");
        sb.append("<thead><tr>");
        for(int i = 0; i < header.length; i ++){
            sb.append("<th>");
            sb.append(header[i]);
            sb.append("</th>");
        }
        sb.append("</tr></thead>");

        sb.append("<tbody>");
        list=page.getList();
        for(Map<String,Object> map : list){
            sb.append("<tr>");
            for(Map.Entry<String,Object> entry : map.entrySet()){
                Object value = entry.getValue();
                sb.append("<td>");
                sb.append(value);
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</tbody>");
        sb.append("</table>"); 
        sb.append("<div class='pagination'>"); 
        sb.append(page.toString());
        sb.append("</div>");
        toolDaoTemplate.insert("sqlHistory.insert", parameter);

        return sb.toString();
    }

    /**
     * 黑名单
     */
    private String[] blackList = {"delete", "update", "alter", "drop", "create", "insert", "replace", "truncate", "call", "grant"};

    private boolean isAllowed(String sql) {
        boolean ret = false;
        for (String e : whiteList) {
            if (StringUtils.startsWithIgnoreCase(sql, e)) {
                ret = true;
                return ret;
            }
        }
        return ret;
    }


    private boolean isForbidden(String sql) {
        boolean ret = false;
        for (String e : blackList) {
            if (StringUtils.startsWithIgnoreCase(sql, e)) {
                ret = true;
                return ret;
            }
        }
        return ret;
    }
}
