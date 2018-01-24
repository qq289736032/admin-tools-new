package com.mokylin.cabal.modules.game.web;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.core.DataContext;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.UserUtils;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "${adminPath}/game/guildLog")
public class GuildController extends BaseController {
//	@Resource
//	protected MonitorConfigService monitorConfigService;
	@Resource
	RedisManager  redisManger;
    @RequestMapping(value = "getGuildList")
    public String getGuildList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String name = MapUtils.getString(parameter, "name");
        createQueryCondition(parameter);
        Page<Map<String, Object>> page = gamePaging(request, response, "guild.findGuildList");
        model.addAttribute("name", name);
        model.addAttribute("page", page);
        return "modules/game/guildList";
    }

    @RequiresPermissions("game.guild.delete")
    @RequestMapping(value = "deleteGuild")
    public String deleteGuild(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam String id) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String name = MapUtils.getString(parameter, "name");
        createQueryCondition(parameter);
        Page<Map<String, Object>> page = gamePaging(request, response, "guild.findGuildList");
        model.addAttribute("name", name);
        model.addAttribute("page", page);

        String serverId = LookupContext.getCurrentServerId();
        Result result = gameTemplate.guildOperation().delete(serverId, Long.parseLong(id));
        if (result.isSuccess()) {
            model.addAttribute("message", "删除公会成功");
        } else {
            model.addAttribute("message", "删除公会失败");
        }
        return "modules/game/guildList";
    }

    @RequiresPermissions("game.guild.export")
    @RequestMapping(value = "exportXls")
    public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        createQueryCondition(parameter);
        List<Map> guildList = gameDaoTemplate.selectList("guild.findGuildList", parameter);
        return super.exportXls(guildList, "公会" + System.currentTimeMillis(), "公会Id", "公会名", "会长", "创建时间", "公会等级", "公会战斗力", "公会成员");
    }

    @RequestMapping("membersList")
    public String membersList(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        Page<Map<String, Object>> page = gamePaging(request, response, "guild.MembersList");
        model.addAttribute("guildId", parameter.get("guildId"));
        model.addAttribute("page", page);
        return "modules/game/membersList";
    }


    /**
     * 创建查询条件，上面的两个操作要求的查询条件是一样的
     *
     * @param parameter
     */
    private void createQueryCondition(MybatisParameter parameter) {
//		setDefaultTimeRange(parameter);
        String name = MapUtils.getString(parameter, "name");
        if (null == name) {
            parameter.put("name", "%");
        } else {
            parameter.put("name", "%" + name + "%");
        }
    }
    @RequestMapping(value = "changeName")
    public String changeName(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String  serverId= MapUtils.getString(parameter, "serverId");
        Long  roleId= Long.parseLong(String.valueOf(parameter.get("roleId")));
        String  newName=MapUtils.getString(parameter, "newName");
        Result result =gameTemplate.guildOperation().change(serverId, roleId, newName);
        if (result.isSuccess()) {
        	    gameDaoTemplate.update("guild.updateName",parameter) ;
                addMessage(redirectAttributes, "公会改名成功"+newName );
        } else {
            addMessage(redirectAttributes, "公会改名失败！");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/guildLog/getGuildList";
    }
    
    @RequestMapping(value = "transferName")
    public String transferName(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String  serverId= MapUtils.getString(parameter, "serverId");
        Long  guildId= Long.parseLong(String.valueOf(parameter.get("guildId")));
        String  newName= MapUtils.getString(parameter, "newName");
        String yuanName=MapUtils.getString(parameter, "yuanName");
        Long  yunaRoleId=MapUtils.getLong(parameter, "roleId");
        String pid = DataContext.getInstance(UserUtils.getUser().getId()).getPid();
        String   roleId=redisManger.getRoleIdByRoleName(pid,newName, yuanName);
	    Result result =gameTemplate.guildOperation().transfer(serverId, yunaRoleId, roleId);
	    if (result.isSuccess()) {
	             addMessage(redirectAttributes, "公会转让成功"+roleId );
        }else{
        	 addMessage(redirectAttributes, "公会转让失败！");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/guildLog/getGuildList";
    }
}
