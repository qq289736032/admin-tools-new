package com.mokylin.cabal.modules.tools.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.AuthCondition;
import com.mokylin.cabal.modules.tools.entity.Recharge;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/13 17:06
 * 项目: cabal-tools
 * 元宝充值
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/recharge")
public class RechargeController extends BaseController {

    @ModelAttribute
    public Recharge get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return toolDaoTemplate.selectOne("recharge.selectOneById", id);
        } else {
            return new Recharge();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String list(Recharge recharge, HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));

        AuthCondition.filterPlatform(parameter);

        Page<Map<String, Object>> page = toolDaoTemplate.paging("recharge.paging", parameter);

        model.addAttribute("page", page);
        return "modules/tools/rechargeList";
    }

    @RequiresPermissions("game.recharge.apply")
    @RequestMapping(value = "form")
    public String form(Recharge recharge, Model model) {

        return "modules/tools/rechargeForm";
    }

    @RequiresPermissions("game.recharge.add")
    @RequestMapping(value = "add")
    public String add(Recharge recharge, Model model) {

        return "modules/tools/rechargeForm2";
    }


    @RequestMapping(value = "saveAndSend")
    public String saveAndSend(Recharge recharge, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        String serverId = MapUtils.getString(parameter, "currentServerId");
        if (StringUtils.isEmpty(serverId)) {
            addMessage(model, "当前选择的服已经失效，请重新选择！");
            return form(recharge, model);
        }

        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        //List<Map> list = new ArrayList<Map>();

        String roleIds = "";
        String roleNames = "";
        String userIds = "";

        //recharge.setServerId(LookupContext.getCurrentServerId());
        //判断是按照角色名充值还是按照角色Id

        if (recharge.getRoleNames() != null) {
            roleNameList = Arrays.asList(StringUtils.split(recharge.getRoleNames(), "\r\n"));
            parameter.put("roleNameList", roleNameList);
            //list = gameDaoTemplate.selectListByServerId(LookupContext.getCurrentServerId(), "role.findRoleByRoleNameList", parameter);
            String pid = MapUtils.getString(parameter,"pid");
            for (String roleName : roleNameList) {
                roleNames = roleNames == "" ? roleNames + roleName : roleNames + "," + roleName;
                roleIds = roleIds == "" ? roleIds + RedisUtils.getRoleIdByRoleName(pid, roleName, "") : roleIds + "," + RedisUtils.getRoleIdByRoleName(pid, roleName, "");
            }
        } else {
            roleIdList = Arrays.asList(StringUtils.split(recharge.getRoleIds(), "\r\n"));
            parameter.put("roleIdList", roleIdList);
            //list = gameDaoTemplate.selectListByServerId(LookupContext.getCurrentServerId(), "role.findRoleByRoleIdList", parameter);
            for (String roleId : roleIdList) {
                roleNames = roleNames == "" ? roleNames + RedisUtils.getRoleNameByRoleId(roleId, "") : roleNames + "," + RedisUtils.getRoleNameByRoleId(roleId, "");
                roleIds = roleIds == "" ? roleIds + roleId : roleIds + "," + roleId;
            }
        }

        if (roleIds == "" || StringUtils.isBlank(roleIds)) {
            addMessage(model, "当前服查询按照角色查询不到对应的玩家信息");
            return form(recharge, model);
        }

        int size = Arrays.asList(StringUtils.split(roleIds, ",")).size();
        if (roleNameList.size() != 0) {
            if (roleNameList.size() != size) {
                addMessage(model, "输入的角色名数量和当前服查询到的玩家数量不一致");
                return form(recharge, model);
            }
        } else {
            if (roleIdList.size() != size) {
                addMessage(model, "输入的角色ID数量和当前服查询到的玩家数量不一致");
                return form(recharge, model);
            }
        }
        parameter.put("serverId", recharge.getServerId());
        parameter.put("userIds", userIds);
        parameter.put("roleIds", roleIds);
        parameter.put("roleNames", roleNames);
        toolDaoTemplate.insert("recharge.insert2", parameter);

        Recharge rechargeList = toolDaoTemplate.selectOne("recharge.findById", parameter);

        gameTemplate.treasureOperation().recharge(rechargeList);

//        parameter.put("rechargeStatus", 1);  //通过
//        toolDaoTemplate.update("recharge.batchUpdateStatus", parameter);

        //addMessage(redirectAttributes, "审核并充值成功！！");
        addMessage(redirectAttributes, "审核并充值成功，此次申请人数共:" + size + "人");
       /* returnResourceService.returnSource(rechargeList.getMoneyNum(), serverId, MapUtils.getString(parameter,"pid"), roleIds);
        rebateRechargeService.insertCharge(roleNames, rechargeList.getMoneyType(), rechargeList.getMoneyNum(), serverId,
        		MapUtils.getString(parameter,"pid"), rechargeList.getRechargeType(), rechargeList.getRechargeStatus());*/
        return "redirect:" + Global.getAdminPath() + "/tools/recharge/";
    }


    @RequiresPermissions("game.recharge.apply")
    @RequestMapping(value = "save")
    public String save(Recharge recharge, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        String serverId = MapUtils.getString(parameter, "currentServerId");
        if (StringUtils.isEmpty(serverId)) {
            addMessage(model, "当前选择的服已经失效，请重新选择！");
            return form(recharge, model);
        }

        List<String> roleNameList = new ArrayList<>();
        List<String> roleIdList = new ArrayList<>();
        List<Map> list = new ArrayList<Map>();

        String roleIds = "";
        String roleNames = "";
        String userIds = "";

        //recharge.setServerId(LookupContext.getCurrentServerId());
        //判断是按照角色名充值还是按照角色Id

        if (recharge.getRoleNames() != null) {
            roleNameList = Arrays.asList(StringUtils.split(recharge.getRoleNames(), "\r\n"));
            parameter.put("roleNameList", roleNameList);
            //list = gameDaoTemplate.selectListByServerId(serverId, "role.findRoleByRoleNameList", parameter);
//            for(String roleName : roleNameList){
//                roleNames = roleNames == "" ? roleNames + roleName : roleNames + "," + roleName;
//                roleIds = roleIds == "" ? roleIds + RedisUtils.getRoleIdByRoleName(roleName,"") : roleIds + "," + RedisUtils.getRoleIdByRoleName(roleName, "");
//            }

            //list = gameDaoTemplate.selectListByServerId(LookupContext.getCurrentServerId(), "role.findRoleByRoleNameList", parameter);
            String pid = MapUtils.getString(parameter,"pid");
            for (String roleName : roleNameList) {
                roleNames = roleNames == "" ? roleNames + roleName : roleNames + "," + roleName;
                roleIds = roleIds == "" ? roleIds + RedisUtils.getRoleIdByRoleName(pid, roleName, "") : roleIds + "," + RedisUtils.getRoleIdByRoleName(pid, roleName, "");
            }
        } else {
            roleIdList = Arrays.asList(StringUtils.split(recharge.getRoleIds(), "\r\n"));
            parameter.put("roleIdList", roleIdList);

            //list = gameDaoTemplate.selectListByServerId(serverId, "role.findRoleByRoleIdList", parameter);
//            for(String roleId : roleIdList){
//                roleNames = roleNames == "" ? roleNames + RedisUtils.getRoleNameByRoleId(roleId,"") : roleNames + "," + RedisUtils.getRoleNameByRoleId(roleId,"");
//                roleIds = roleIds == "" ? roleIds + roleId : roleIds + "," + roleId;
//            }

            //list = gameDaoTemplate.selectListByServerId(LookupContext.getCurrentServerId(), "role.findRoleByRoleIdList", parameter);
            for (String roleId : roleIdList) {
                roleNames = roleNames == "" ? roleNames + RedisUtils.getRoleNameByRoleId(roleId, "") : roleNames + "," + RedisUtils.getRoleNameByRoleId(roleId, "");
                roleIds = roleIds == "" ? roleIds + roleId : roleIds + "," + roleId;
            }

        }

        if (roleIds == "" || StringUtils.isBlank(roleIds)) {
            addMessage(model, "当前服查询按照角色查询不到对应的玩家信息");
            return form(recharge, model);
        }

        int size = Arrays.asList(StringUtils.split(roleIds, ",")).size();

        if (roleNameList.size() != 0) {
            if (roleNameList.size() != size) {
                addMessage(model, "输入的角色名数量和当前服查询到的玩家数量不一致");
                return form(recharge, model);
            }
        } else {
            if (roleIdList.size() != size) {
                addMessage(model, "输入的角色ID数量和当前服查询到的玩家数量不一致");
                return form(recharge, model);
            }
        }
        parameter.put("serverId", recharge.getServerId());
        parameter.put("userIds", userIds);
        parameter.put("roleIds", roleIds);
        parameter.put("roleNames", roleNames);
        toolDaoTemplate.insert("recharge.insert", parameter);
        addMessage(redirectAttributes, "申请成功，此次申请人数共:" + size + "人");

        return "redirect:" + Global.getAdminPath() + "/tools/recharge/";
    }

    /**
     * 批量审核，然后直接充值
     *
     * @return
     */
    @RequiresPermissions("game.recharge.batchadd")
    @RequestMapping(value = "batchAdd")
    public String batchAdd(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        List<Recharge> rechargeList = toolDaoTemplate.selectList("recharge.findRechargeByIdList", parameter);

        gameTemplate.treasureOperation().recharge(rechargeList);

        parameter.put("rechargeStatus", 1);  //通过
        toolDaoTemplate.update("recharge.batchUpdateStatus", parameter);

        addMessage(redirectAttributes, "审核并充值成功！！");
      /*  for (Recharge recharge : rechargeList) {
        	returnResourceService.returnSource(recharge.getMoneyNum(), recharge.getServerId(), MapUtils.getString(parameter,"pid"), recharge.getRoleIds());
        	rebateRechargeService.insertCharge(recharge.getRoleNames(), recharge.getMoneyType(), recharge.getMoneyNum(), recharge.getServerId(),
            		MapUtils.getString(parameter,"pid"), recharge.getRechargeType(), recharge.getRechargeStatus());
        }*/
        return "redirect:" + Global.getAdminPath() + "/tools/recharge/";
    }

    @RequiresPermissions("game.recharge.batchcancel")
    @ResponseBody
    @RequestMapping(value = "batchCancel")
    public Result batchCancel(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> recordId = (List<String>) MapUtils.getObject(parameter, "recordIdList");
        List<String> status = toolDaoTemplate.selectList("recharge.selectStatus", parameter);
        if (status.contains("1") || status.contains("2")) {
            String data = "已取消和已审核的申请将不能再取消，请重新选择！";
            return new Result(false).data(data);
        } else {
            parameter.put("rechargeStatus", 2);  //取消状态
            toolDaoTemplate.update("recharge.batchUpdateStatus", parameter);
            String data = "取消成功，共取消申请：" + recordId.size() + "条";
            return new Result(true).data(data);
        }

    }


    @RequiresPermissions("game.recharge.batchrecover")
    @ResponseBody
    @RequestMapping(value = "batchRecover")
    public Result batchRecover(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> recordIds = (List<String>) MapUtils.getObject(parameter, "recordIdList");
        List<String> status = toolDaoTemplate.selectList("recharge.selectStatus", parameter);
        if (status.contains("0") || status.contains("1")) {
            String data = "待审核和已审核的申请将不能再恢复，请重新选择！";
            return new Result(false).data(data);

        } else {
            parameter.put("rechargeStatus", 0);  //待审核状态
            toolDaoTemplate.update("recharge.batchUpdateStatus", parameter);
            String data = "恢复成功，共恢复申请：" + recordIds.size() + "条";
            return new Result(true).data(data);
        }

    }

    /**
     * 按角色id申请充值跳转
     */
    @RequiresPermissions("game.recharge.apply")
    @RequestMapping(value = "roleIdRechargeForm")
    public String RoleIdRechargeForm(Recharge recharge, Model model) {
        return "modules/tools/roleIdRechargeForm";
    }
    



}
