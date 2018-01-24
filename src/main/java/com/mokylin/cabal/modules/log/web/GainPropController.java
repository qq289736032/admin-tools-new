package com.mokylin.cabal.modules.log.web;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.utils.excel.ExportExcel;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.log.entity.GainPropDetail;
import com.mokylin.cabal.modules.sys.entity.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/23 14:41
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/log/gainProp")
public class GainPropController extends BaseController {


    @ModelAttribute
    public GainPropDetail get(@RequestParam(required=false) String id) {
        if (StringUtils.isNotBlank(id)){
            return logDaoTemplate.selectOne("gainProp.selectOneById", id);
        }else{
            return new GainPropDetail();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(GainPropDetail gainPropDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request,response));
        Page<GainPropDetail> page = logDaoTemplate.paging("gainProp.paging",parameter);
        model.addAttribute("page", page);
        return "modules/logs/gainPropList";
    }

    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(GainPropDetail gainPropDetail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        try {
            String fileName = "道具获取"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<GainPropDetail> gainPropDetailList = logDaoTemplate.selectList("gainProp.paging", parameter);
            new ExportExcel("道具获取", GainPropDetail.class).setDataList(gainPropDetailList).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出用户失败！失败信息："+e.getMessage());
        }
        return "redirect:"+ Global.getAdminPath()+"/sys/user/?repage";
    }
}
