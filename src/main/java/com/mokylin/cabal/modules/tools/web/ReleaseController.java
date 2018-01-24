package com.mokylin.cabal.modules.tools.web;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.game.api.model.ReleaseNote;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/6/4
 * admin-tools
 * 系统更新日志 公告
 */
@RequestMapping(value = "${adminPath}/tools/release")
@Controller
public class ReleaseController extends BaseController {

    @RequestMapping(value = {"list",""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model){
        Page<Map<String,Object>> page = toolPaging(request, response, "releaseNote.paging");
        model.addAttribute("page",page);
        return "modules/tools/releaseList";
    }

    @RequestMapping("from")
    public String form(HttpServletRequest request, Model model){
        return "modules/tools/releaseForm";
    }

    @RequestMapping("add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("status", ConfigConstants.ReleaseNoteStatus.UNPUBLISHED.getValue());
        int ret = toolDaoTemplate.insert("releaseNote.insert", parameter);
        if(ret > 0){
            addMessage(redirectAttributes,"新增成功");
        }else{
            addMessage(redirectAttributes,"新增失败");
        }
        return "redirect:" + Global.getAdminPath() + "/tools/release/";
    }

    @RequestMapping("publish")
    public String publish(@RequestParam("id") String id,HttpServletRequest request, Model model) {
        Map<String,Object> map = toolDaoTemplate.selectOne("releaseNote.selectById",id);
        ReleaseNote releaseNote = new ReleaseNote();
        releaseNote.setServerIdList(Arrays.asList(StringUtils.split(MapUtils.getString(map,"server_ids"), ",")));
        releaseNote.setVersionId(MapUtils.getString(map,"version_id"));
        releaseNote.setReleaseTime(MapUtils.getString(map,"version_time"));
        releaseNote.setReleaseNote(MapUtils.getString(map,"release_note"));
        releaseNote.setReleaseContent(MapUtils.getString(map,"release_content"));

        gameTemplate.announceOperation().releaseNote(releaseNote);

        map.put("status", ConfigConstants.ReleaseNoteStatus.PUBLISHED.getValue());
        toolDaoTemplate.update("releaseNote.update",map);


        return "redirect:" + Global.getAdminPath() + "/tools/release/";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("id") String id,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        int ret = toolDaoTemplate.delete("releaseNote.delete",id);
        if(ret > 0){
            addMessage(redirectAttributes,"删除成功");
        }else{
            addMessage(redirectAttributes,"删除失败");
        }
        return "redirect:" + Global.getAdminPath() + "/tools/release/";
    }


}
