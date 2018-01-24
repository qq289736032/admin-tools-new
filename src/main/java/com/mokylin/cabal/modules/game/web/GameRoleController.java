package com.mokylin.cabal.modules.game.web;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.Encodes;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.game.utils.TongyongLogin;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import com.mokylin.cabal.modules.sys.utils.DictUtils;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;

/**
 * 虞美人·春花秋月何时了 五代·李煜
 *
 * 春花秋月何时了？往事知多少。小楼昨夜又东风，故国不堪回首月明中。
 * 雕栏玉砌应犹在，只是朱颜改。问君能有几多愁？恰似一江春水向东流
 *
 * 作者: 稻草鸟人
 * 日期: 2014/11/11 13:33
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/game/role")
public class GameRoleController extends BaseController {
    
    @RequestMapping(value = "gameRoleDialog")
    public String openGameRoleDialog(String serverId, HttpServletRequest request,HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request,response));
        String roleId = MapUtils.getString(parameter, "roleId");
        String roleName = MapUtils.getString(parameter, "roleName");
        String startLevel = MapUtils.getString(parameter, "startLevel");
        String endLevel = MapUtils.getString(parameter, "endLevel");

        if (StringUtils.isNotBlank(serverId) && (StringUtils.isNotBlank(roleId) || StringUtils.isNotBlank(roleName)
                || StringUtils.isNotBlank(startLevel) || StringUtils.isNotBlank(endLevel))) {
            model.addAttribute("page", gameDaoTemplate.paging(serverId, "role.roleDialog", parameter));
        }

        return "modules/game/gameRoleDialog";
    }

    @RequestMapping(value = {"list", ""})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String, Object>> page = gameDaoTemplate.paging("role.paging", parameter);

        model.addAttribute("page", page);

        return "modules/game/gameRoleList";
    }

    @RequiresPermissions("game.role.logingame")
    @RequestMapping(value = "loginGame")
    public String loginGame(String userId,String serverId, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String url = null;
        Server server = GameAreaUtils.getGameServerByServerId(serverId);
        if (server == null) {
        	 addMessage(redirectAttributes, "找不到游戏服务器！");
        	 return "redirect:"+url;
        }
        logger.debug("参数serverId={},server={}", serverId, JSON.toJSONString(server));
        GamePlatform gamePlatform = GameAreaUtils.getGamePlatformByPId(String.valueOf(server.getPlatformId()));
        try {
            url = TongyongLogin.LoginGame(server.getPlatformId(), userId,String.valueOf(server.getWorldName()), server.createGateUrl(), gamePlatform.getSignKey());
        } catch (Exception e) {
            logger.error("", JSON.toJSONString(gamePlatform));
            logger.error("",ExceptionUtils.getStackTrace(e));
        }

        return "redirect:"+url;
    }


    @RequestMapping(value = "form")
    public String form(String id,HttpServletRequest request, Model model){
        model.addAttribute("role",gameDaoTemplate.selectOne("role.findRoleById",id));
        model.addAttribute("items",gameDaoTemplate.selectList("item.findItemByRoleId",id));
        model.addAttribute("xunbao",gameDaoTemplate.selectList("item.findXunbaoItemByRoleId",id));
//        model.addAttribute("horse",gameDaoTemplate.selectList("horse.findHorseByRoleId",id));    //主资源信息（坐骑）
//        model.addAttribute("cloak",gameDaoTemplate.selectList("cloak.findCloakByRoleId",id));    //主资源线信息（神翼）cloak
//        model.addAttribute("wuji",gameDaoTemplate.selectList("wuji.findWujiByRoleId",id));    //主资源线信息（舞姬）cloak
//        model.addAttribute("baoshis",gameDaoTemplate.selectList("baoshi.findBaoshiByRoleId",id));    //主资源线信息（宝石）baoshi
//
/*        model.addAttribute("nvwushen",gameDaoTemplate.selectList("nvwushen.findByRoleId",id));   //主资源线信息（女武神）role_nvwushen
        model.addAttribute("touxian",gameDaoTemplate.selectList("touxian.findByRoleId",id));     //主资源线信息（头衔）role_touxian
        model.addAttribute("tiansuo",gameDaoTemplate.selectList("tiansuo.findByRoleId",id));     //主资源线信息（天梭）role_tiansuo
        model.addAttribute("huashen",gameDaoTemplate.selectList("huashen.findByRoleId",id));     //主资源线信息（化神）role_huashen
        model.addAttribute("shengyi",gameDaoTemplate.selectList("shengyi.findByRoleId",id));     //主资源线信息（圣衣）role_shengyi
*/
        return "modules/game/gameRoleForm";
    }

    @RequestMapping(value = "bag")
    public String bag(String roleId, Model model, String serverId){
        model.addAttribute("items",gameDaoTemplate.selectList("item.findBagItemByRoleId",roleId));
        model.addAttribute("roleId",roleId);
        model.addAttribute("serverId",serverId);
        return "modules/game/gameRoleBag";
    }

    @RequiresPermissions("game.role.jinyan")
    @RequestMapping(value = "jinYan")
    public Result jinYan(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        Result result = gameTemplate.roleOperation().jinYan(MapUtils.getString(parameter,"serverId"), MapUtils.getString(parameter,"roleId"),MapUtils.getString(parameter,"expireTime"));
        if (result.isSuccess()) {
            //禁言之后的角色信息,查询出来，插入禁言封号日志表
            parameter.put("operationType", ConfigConstants.OPERATION_TYPE_SILENCE);
            parameter.put("msg",Encodes.urlEncode("监控禁言"));

            logging(parameter);

            return new Result(true).data("监控禁言成功");
        }

        return new Result(true).data("监控禁言失败");
    }

    @RequiresPermissions("game.role.jinyan")
    @RequestMapping(value = "batchJinYan")
    public String batchJinYan(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> roleIds = (List<String>) parameter.get("recordIdList");
        Result result = gameTemplate.roleOperation().batchJinYan(roleIds, MapUtils.getString(parameter,"expireTime"));
        if (result.isSuccess()) {
            //禁言之后的角色信息,查询出来，插入禁言封号日志表
            parameter.put("operationType", ConfigConstants.OPERATION_TYPE_SILENCE);

            logging(parameter);

            addMessage(redirectAttributes, "批量禁言成功，共禁言:" + roleIds.size() + "角色");
        } else {
            addMessage(redirectAttributes, "批量禁言失败！");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/role/";
    }

    @RequiresPermissions("game.role.fenghao")
    @RequestMapping(value = "batchFengHao")
    public String batchFengHao(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> roleIds = (List<String>) parameter.get("recordIdList");
        Result result = gameTemplate.roleOperation().batchFenHao(roleIds, MapUtils.getString(parameter,"expireTime"));
        if (result.isSuccess()) {
            parameter.put("operationType", ConfigConstants.OPERATION_TYPE_FREEZE);
            logging(parameter);
            addMessage(redirectAttributes, "批量封号成功，共封号:" + roleIds.size() + "角色");
        } else {
            addMessage(redirectAttributes, "批量封号失败！");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/role/";
    }

    @RequiresPermissions("game.role.jinyan")
    @RequestMapping(value = "batchCancelJinYan")
    public String batchCancelJinYan(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> roleIds = (List<String>) parameter.get("recordIdList");
        Result result = gameTemplate.roleOperation().batchCancelJinYan(roleIds);
        if (result.isSuccess()) {
            parameter.put("operationType", ConfigConstants.OPERATION_TYPE_CANCEL_SILENCE);
            logging(parameter);
            addMessage(redirectAttributes, "批量禁言成功，共禁言:" + roleIds.size() + "角色");
        } else {
            addMessage(redirectAttributes, "批量禁言失败！");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/role/";
    }

    @RequiresPermissions("game.role.fenghao")
    @RequestMapping(value = "batchCancelFengHao")
    public String batchCancelFengHao(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        List<String> roleIds = (List<String>) parameter.get("recordIdList");
        Result result = gameTemplate.roleOperation().batchCancelFenHao(roleIds);
        if (result.isSuccess()) {
            parameter.put("operationType", ConfigConstants.OPERATION_TYPE_CANCEL_FREEZE);
            logging(parameter);
            addMessage(redirectAttributes, "批量取消封号成功，共封号:" + roleIds.size() + "角色");
        } else {
            addMessage(redirectAttributes, "批量封号失败！");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/role/";
    }

    private void logging(Map<String, Object> map) {
        //角色信息,查询出来，插入禁言封号日志表
        List<Map<String, Object>> roleList = gameDaoTemplate.selectList("role.findRoleByIdList", map);
        String createName = UserUtils.getUser().getLoginName();
        String createBy = UserUtils.getUser().getId();
        for (Map<String, Object> role : roleList) {
            role.put("msg", Encodes.urlDecode(MapUtils.getString(map, "msg")));
            role.put("id", IdGen.uuid());                   //一条记录一个主键
            role.put("createName", createName);
            role.put("createBy", createBy);
            role.put("expireTime", MapUtils.getString(map,"expireTime"));
            String operationType = MapUtils.getString(map, "operationType");
            role.put("operationType",operationType);
        }

        toolDaoTemplate.batchInsert2("silenceFreezeLog.batchInsert", roleList);
    }

    @RequestMapping(value="silenceFreezeLog")
    public String log(HttpServletRequest request,HttpServletResponse response , Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String,Object>> page = toolDaoTemplate.paging("silenceFreezeLog.paging",parameter);
        model.addAttribute("page",page);
        return "modules/tools/silenceFreezeLog";
    }

    @RequestMapping(value = "gmList")
    public String gmList(HttpServletRequest request,HttpServletResponse response ,Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));
        Page<Map<String,Object>> page = toolDaoTemplate.paging("gmAccount.paging",parameter);
        model.addAttribute("page",page);

        return "modules/tools/gmList";
    }


    /**
     * 指导员和GM 表单
     * @param model
     * @return
     */
    @RequiresPermissions("game.gm.edit")
    @RequestMapping (value = "gmForm")
    public String addGm(Model model){

        return "modules/tools/gmForm";
    }

    @RequiresPermissions("game.gm.edit")
    @RequestMapping(value = "updateGmForm")
    public String updateGmForm(HttpServletRequest request,Model model){
        String id = request.getParameter("id");
        if(StringUtils.isNotBlank(id) && StringUtils.isNotEmpty(id)){
            Map<String,Object> map =  toolDaoTemplate.selectOne("gmAccount.findGmAccountById", id);
            model.addAttribute("map",map);
        }
        return "modules/tools/updateGmForm";
    }

    @RequiresPermissions("game.gm.edit")
    @RequestMapping(value = "saveGm")
    public String saveGm(HttpServletRequest request,Model model, RedirectAttributes redirectAttributes){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        String roleName = MapUtils.getString(parameter,"roleName");
        String serverId = MapUtils.getString(parameter,"serverIds");
        String roleId = gameDaoTemplate.selectOneByServerId(serverId,"role.findRoleIdByRoleName",parameter);
        if(StringUtils.isEmpty(roleId) || StringUtils.isBlank(roleId)){
            addMessage(model,"当前服不存在用户:"+roleName);
            return addGm(model);
        }

        parameter.put("roleId",roleId);
        //调用游戏接口，更新角色类型，O、普通玩家 2、指导员 1、GM
        gameTemplate.roleOperation().updateRoleType(serverId,roleId,MapUtils.getString(parameter,"roleType"));

        toolDaoTemplate.insert("gmAccount.insert",parameter);
        addMessage(redirectAttributes,"添加成功，用户名:"+roleName);


        return "redirect:"+ Global.getAdminPath()+"/game/role/gmList";
    }

    @RequiresPermissions("game.gm.edit")
    @RequestMapping(value = "updateGm")
    public String updateGm(HttpServletRequest request, RedirectAttributes redirectAttributes){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        //更新游戏角色类型
        Result result = gameTemplate.roleOperation().updateRoleType(MapUtils.getString(parameter,"serverId"),
                MapUtils.getString(parameter,"roleId"),MapUtils.getString(parameter,"roleType"));
        if(result.isSuccess()) {
            toolDaoTemplate.update("gmAccount.update", parameter);
            addMessage(redirectAttributes, "更新角色：" + MapUtils.getString(parameter, "roleName") + "成功");
        }else{
            addMessage(redirectAttributes, "更新角色：" + MapUtils.getString(parameter, "roleName") + "失败");
        }
        return "redirect:"+ Global.getAdminPath()+"/game/role/gmList";
    }

    @RequestMapping(value = "gmPublish")
    public String gmPublish(HttpServletRequest request,HttpServletResponse response, Model model){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request,response));
        Page<Map<String,Object>> page = logDaoTemplate.paging("gmPublish.paging",parameter);
        model.addAttribute("page",page);

        return "modules/logs/gmPublishLog";
    }


    /**
     * 角色等级排行
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "levelRanking")
    public String levelRanking(HttpServletRequest request, HttpServletResponse response, Model model){

        model.addAttribute("page",gamePaging(request, response, "role.levelRanking"));

        return "modules/game/levelRankingList";
    }

    /**
     * 角色战斗力排行
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "battleRanking")
    public String powerRanking(HttpServletRequest request, HttpServletResponse response, Model model){

        model.addAttribute("page",gamePaging(request, response, "role.battleRanking"));

        return "modules/game/battleRankingList";
    }
    
    @RequiresPermissions("game.role.delete")
    @RequestMapping(value = "delete")
    public String deleteRole(HttpServletRequest request,Model model, RedirectAttributes redirectAttributes){
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        long roleId = MapUtils.getLongValue(parameter,"id");
        String serverId = LookupContext.getCurrentServerId();
        Result result = gameTemplate.roleOperation().delete(serverId, roleId);

        if (result.isSuccess()) {
	        addMessage(redirectAttributes, "删除成功");
	    } else {
	        addMessage(redirectAttributes, "删除失败");
	    }
        return "redirect:"+ Global.getAdminPath()+"/game/role/";
    }
    
    
    /**
     * 角色等级排行榜导出excel
     */
    @RequestMapping(value = "export", method= RequestMethod.POST)
    public ResponseEntity<byte[]> export(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		List<Map> roleRank = gameDaoTemplate.selectList("role.levelRanking",parameter);
		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request); 
    	WebApplicationContext ac = RequestContextUtils.getWebApplicationContext(request);
    	String str1673= ac.getMessage("str1673", null,"角色等级排行榜", locale);
    	String str85= ac.getMessage("str85", null,"等级", locale);
    	String str6= ac.getMessage("str6", null,"角色账号", locale);
    	String str7= ac.getMessage("str7", null,"角色名", locale);
    	String str11= ac.getMessage("str11", null,"升级时间", locale);
		
		return super.exportXls(roleRank, str1673+System.currentTimeMillis(),str85, str6,str7,str11);
	}
    
    
    /**
     * 战斗力排行导出excel
     */
    @RequestMapping(value ="exportXls",method= RequestMethod.POST)
    public ResponseEntity<byte[]> exportXls(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
    	
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	List<Map> battleRank = gameDaoTemplate.selectList("role.battleRanking", parameter);
    	return super.exportXls(battleRank, "玩家战斗力排行"+System.currentTimeMillis(), "角色账号","角色名","战斗力","最大战斗力","最小战斗力","升级时间");
    }
    @RequestMapping(value ="roleExport",method= RequestMethod.POST)
    public ResponseEntity<byte[]> roleExport(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap){
    	MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
    	List<Map> role = gameDaoTemplate.selectList("role.paging", parameter);
		for (Map<String, Object> map : role) {
    		  map.put("job",DictUtils.getDictLabel(map.get("job").toString(),"job_type","类别错误") );
    		  if(map.get("isjinyan").toString()=="true"){
    			  map.put("isjinyan", "是");
    		  }else{
    			  map.put("isjinyan", "否");
    		  }
    		  if(map.get("isfenghao").toString()=="true"){
    			  map.put("isfenghao", "是");
    		  }else{
    			  map.put("isfenghao", "否");
    		  }
    		  
    	}
    	return super.exportXls(role, "玩家信息"+System.currentTimeMillis(), "角色名Id","用户ID","角色名","职业","性别","等级","当前经验","最后上线时间","最后离线时间","区服","角色创建时间","服务器","是否禁言","是否封号");
    }

    @RequestMapping(value = "currency")
    @ResponseBody
    public boolean currency(HttpServletRequest request, HttpServletResponse response, Model model, long id,
                                                                String serverId, int curType, int value) {
        try {
			Result result = gameTemplate.roleOperation().editRoleCurrency(serverId, id, curType, value);
			return result.isSuccess();
		} catch (Exception e) {
		}
        return false;
    }
    
    @RequestMapping(value = "level")
    @ResponseBody
    public boolean level(HttpServletRequest request, HttpServletResponse response, Model model, long id,
    		String serverId, int levelType, int value) {
    	try {
			Result result = gameTemplate.roleOperation().editRoleLevel(serverId, id, levelType, value);
			return result.isSuccess();
		} catch (Exception e) {
		}
    	return false;
    }
    
    @RequestMapping(value = "deleteItem")
    @ResponseBody
    public boolean deleteItem(HttpServletRequest request, HttpServletResponse response, Model model, long id,
    		String serverId, long itemId, int count) {
    	try {
			Result result = gameTemplate.roleOperation().delRoleItem(serverId, id, itemId, count);
			return result.isSuccess();
		} catch (Exception e) {
		}
    	return false;
    }
}
