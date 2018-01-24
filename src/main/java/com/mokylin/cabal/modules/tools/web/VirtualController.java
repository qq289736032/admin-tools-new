package com.mokylin.cabal.modules.tools.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.game.api.model.VirtualItem;
import com.mokylin.cabal.common.game.api.model.VirtualItemDTO;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import com.mokylin.cabal.modules.tools.vo.VirtualItemVo;

/**
 * 作者 : 稻草鸟人
 * 时间: 2015/7/1
 * admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/virtual")
public class VirtualController extends BaseController {


    @RequestMapping(value = {"","list"})
    public String virtualList(HttpServletRequest request, HttpServletResponse response, Model model) {


        Page<Map<String,Object>> page = toolPaging(request, response, "virtualPoint.paging");
        List<Map<String,Object>> list = toolDaoTemplate.selectList("virtualPoint.itemList");
        String itemId,name,goods;
        for(Map<String,Object> map : list){
            itemId = MapUtils.getString(map,"item_id");
            name = MapUtils.getString(map,"name");
            for(Map<String,Object> ret : page.getList()){
                goods = MapUtils.getString(ret,"goods");
                goods.replace(itemId,name);
            }
        }

        model.addAttribute("page", page);
        return "/modules/tools/virtualList";
    }


    @RequestMapping("form")
    public String form(HttpServletRequest request, HttpServletResponse response, Model model) {

        List<Map<String,Object>> list = toolDaoTemplate.selectList("virtualPoint.itemList");

        model.addAttribute("item", list);

        return "/modules/tools/virtualForm";
    }

    @RequestMapping("itemList")
    public String itemList(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("page", toolPaging(request,response,"virtualPoint.itemList"));
        return "/modules/tools/itemList";
    }


    @RequestMapping("itemForm")
    public String itemForm(HttpServletRequest request, HttpServletResponse response, Model model) {


        return "/modules/tools/itemForm";
    }

    /**
     * 保存申请的虚拟物品
     * @param virtualItemVo
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequestMapping("save")
    public String save(VirtualItemVo virtualItemVo, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        int isGlobal = MapUtils.getIntValue(parameter, "global");
        String roleIds = "";
        if(isGlobal == 0){  //不是全服发送
            String[] roleNames = StringUtils.split(MapUtils.getString(parameter, "receiverNames"),",");
            String pid = MapUtils.getString(parameter,"pid");
            for(String roleName : roleNames){
                String roleId = RedisUtils.getRoleIdByRoleName(pid, roleName, "");
                roleIds = roleIds == ""? roleId : ","+roleId;
            }
            parameter.put("roleIds",roleIds);
        }

        parameter.put("virtualItems", virtualItemVo.getVirtualItems());
        toolDaoTemplate.insert("virtualPoint.insert", parameter);
        addMessage(redirectAttributes, "申请虚拟物品成功");
        return "redirect:" + Global.getAdminPath() + "/tools/virtual/list";
    }

    @RequestMapping("saveItem")
    public String saveItem(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        toolDaoTemplate.insert("virtualPoint.insertItem", parameter);
        return "redirect:" + Global.getAdminPath() + "/tools/virtual/itemList";
    }


    @RequestMapping("deleteItem")
    public String deleteItem(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        toolDaoTemplate.insert("virtualPoint.deleteItem", parameter);
        addMessage(model,"删除成功!");
        return "redirect:" + Global.getAdminPath() + "/tools/virtual/itemList";
    }

    @RequestMapping("send")
    public String send(HttpServletResponse response, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        Map<String,Object> map = toolDaoTemplate.selectOne("virtualPoint.selectById",parameter);
        int global = MapUtils.getIntValue(map, "global");
        String goods = MapUtils.getString(map,"goods");

        VirtualItemDTO dto = new VirtualItemDTO();
        List<VirtualItem> virtualItems = Lists.newArrayList();
        for(String item : StringUtils.split(goods, ";")){
            VirtualItem virtualItem = new VirtualItem();
            String[] str = item.split(":");
            virtualItem.setItemId(str[0]);
            virtualItem.setItemNum(Integer.parseInt(str[1]));
            virtualItems.add(virtualItem);
        }
        dto.setVirtualItems(virtualItems);

        dto.setGlobal(global == 1);
        if(0 == global){
            String serverId = MapUtils.getString(map, "serverIds");
            dto.setRoleIds(Arrays.asList(MapUtils.getString(map,"roleIds").split(",")));
            gameTemplate.roleOperation().sendVirtualGoods(serverId,dto);
        }else{
            List<String> serverIds = Arrays.asList(MapUtils.getString(map,"serverIds").split(","));
            gameTemplate.roleOperation().sendVirtualGoods(serverIds, dto);
        }

        parameter.put("state", 1);  //已发送   偷懒偷懒
        toolDaoTemplate.update("virtualPoint.approve", parameter);

        addMessage(redirectAttributes, "发送虚拟物品成功");

        return "redirect:" + Global.getAdminPath() + "/tools/virtual/";
    }


    @RequestMapping("cancel")
    public String cancel(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("state", 2);
        toolDaoTemplate.update("virtualPoint.cancel", parameter);
        addMessage(redirectAttributes, "取消虚拟物品成功！");
        return "redirect:" + Global.getAdminPath() + "/tools/virtual/";
    }

    @RequestMapping("recover")
    public String recover(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("state", 0);  //未发送
        toolDaoTemplate.update("virtualPoint.cancel", parameter);
        addMessage(redirectAttributes, "取消虚拟物品成功！");
        return "redirect:" + Global.getAdminPath() + "/tools/virtual/";
    }
}
