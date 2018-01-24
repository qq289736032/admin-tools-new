package com.mokylin.cabal.modules.tools.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Maps;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.core.DataContext;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.entity.GamePlatform;
import com.mokylin.cabal.modules.tools.utils.CmdExecutor;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import com.mokylin.cabal.modules.tools.vo.Followed;

@Controller
@RequestMapping(value = "${adminPath}/tools/gameArea")
public class GameAreaController extends BaseController {
	
	@RequiresPermissions("sys:gameServer:view")
	@RequestMapping(value = "gameAreas")
	public String gameAreas(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		parameter.setPage(new Page(request, response));
		Page<Map<String, Object>> page = toolDaoTemplate.paging("gameArea.findGameAreas", parameter);
		model.addAttribute("page", page);
		return "modules/tools/gameAreaList";
	}

	@RequestMapping(value = { "selectSingleGameServer" })
	public String selectSingleGameServer(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String pid = MapUtils.getString(parameter, "pid");
		parameter.put("pid", pid);
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(request, response);
		//
		if (pid == null) {
			List<GamePlatform> platformList = UserUtils.getGamePlatformList();
			if (platformList.size() > 0) {
				Random rnd = new Random();
				int index = rnd.nextInt(platformList.size()); 
				pid = platformList.get(index).getPid();
				parameter.put("pid", pid);
			}
		}
		// 没有平台参数，默认不查询
		if (pid != null && StringUtils.isNotBlank(pid)) {
			page = toolPaging(request, response, "gameArea.findGameAreaByPid");
		}
		model.addAttribute("page", page);
		return "modules/tools/singleGameAreaList";
	}
	
	/**
	 * 根据pid得到服务器列表
	 * @param pid
	 * @return
	 */
	@RequestMapping(value = "getServerListByPid")
	@ResponseBody
	public List<Map<String,Object>> getServerListByPid(String pid) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("pid", pid);
		return toolDaoTemplate.selectList("gameArea.findGameAreaByPid", map);
	}

	@RequestMapping(value = "changeGameServer")
	public @ResponseBody
	Result changeGameServer(HttpServletRequest request, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String gameServerId = request.getParameter("gameServerId");
		String pid = MapUtils.getString(parameter, "pid");	//当前选择的平台
		request.getSession().setAttribute(ConfigConstants.SELECTED_SERVER_KEY, gameServerId);
		LookupContext.setCurrentServerId(gameServerId);
		Server server = RedisUtils.getGameServer(gameServerId);
		DataContext.put(UserUtils.getUser().getId(), pid, gameServerId, server.getName());
//		request.getSession().setAttribute(ConfigConstants.DEFAULT_PLATFORM_KEY, pid);
//		LookupContext.setCurrentPlatformId(pid);

		model.addAttribute("message", "切换服务器成功");
		return new Result(true).data(request.getParameter("gameServerName"));
	}


	/**
	 * 单平台服务器多选
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "singlePlatformGameServer" )
	public String singlePlatformGameServer(HttpServletRequest request, HttpServletResponse response, Model model) {
//		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
//		String pid = MapUtils.getString(parameter, "pid");
//		parameter.put("pid", pid);
//
//		Collection<Server> list = null;
//
//		// 没有平台参数，默认不查询
//		if (pid != null && StringUtils.isNotBlank(pid)) {
//			list = RedisUtils.getGameServerListByPid(pid);
//		}
//		model.addAttribute("list", list);
		return "modules/tools/singlePlatformGameServer";
	}

	@RequestMapping(value = "singleGameServer" )
	public String singleGameServer(HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/tools/singleGameServer";
	}

	@RequestMapping(value = "selectSingleGameArea" )
	public String selectSingleGameArea(HttpServletRequest request, HttpServletResponse response, Model model) {
//		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
//		String pid = MapUtils.getString(parameter, "pid");
//		parameter.put("pid", pid);
//
//		Collection<Server> list = null;
//
//		// 没有平台参数，默认不查询
//		if (pid != null && StringUtils.isNotBlank(pid)) {
//			list = RedisUtils.getGameServerListByPid(pid);
//		}
//		model.addAttribute("list", list);
		return "modules/tools/singleGameArea";
	}


	@RequestMapping(value = "changePlatform")
	@ResponseBody
	public Result changePlatform(HttpServletRequest request, Model model) {
		String pid = request.getParameter("pid");
		request.getSession().setAttribute(ConfigConstants.DEFAULT_PLATFORM_KEY, pid);
		LookupContext.setCurrentPlatformId(pid);

		Server server = GameAreaUtils.getRandomGameServer(pid);
		request.getSession().setAttribute(ConfigConstants.SELECTED_SERVER_KEY, String.valueOf(server.getWorldId()));
		LookupContext.setCurrentServerId(String.valueOf(server.getWorldId()));

		DataContext.put(UserUtils.getUser().getId(), pid, String.valueOf(server.getWorldId()), server.getName());	//存入或者修改缓存
		return new Result(true).data(server.getName());
	}


	/**
	 * 多选服务器弹窗，去除了合过服的机器
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "openGameServerDialog")
	public String openServerDialog(HttpServletRequest request, Model model) {

		model.addAttribute("gamePlatform", UserUtils.getGamePlatformListContainServer());

		return "modules/tools/gameServerDialog";
	}

	@RequestMapping(value = "openGameServer")
	public String openGameServer(HttpServletRequest request, Model model) {

		model.addAttribute("gamePlatform", UserUtils.getGamePlatformListContainServer());

		return "modules/tools/gameServer";
	}
	//followerId
	@RequestMapping(value = "followerId")
	public String followerId(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Server> sourcelist = toolDaoTemplate.selectList("gameArea.findAllGameArea");
		List<Followed> target = new ArrayList<Followed>();
		List<Server> followeds = new ArrayList<Server>();
		
		for (Server server :  sourcelist) {
			if (server != null && server.getFollowerId() == 0) {
				target.add(new Followed(server));
			}else {
				followeds.add(server);
			}
		}
		
		
		for (Followed obj : target) {
			for(Server server : followeds) {
				if(server.getFollowerId() == obj.getTarget().getWorldId()&&obj.getTarget().getPlatformId().equals(server.getPlatformId()) ) {
					obj.addFollowed(server);
				}
			}
		}
		
		model.addAttribute("FollowedList", target);
		
		return "modules/tools/followerId";
	}
	@RequestMapping(value = "viewFollowerId")
	public String viewFollowerId(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		List<Server> sourcelist = toolDaoTemplate.selectList("gameArea.findAllGameArea",parameter);
		model.addAttribute("followedDetail", sourcelist);
		
		return "modules/tools/viewfollowerId";
	}
	
	@RequiresPermissions("sys:gameServer:edit")
	@RequestMapping(value = "updateOpenTime")
	@ResponseBody
	public String updateOpenTime(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model,String open_time,String woldId) throws Exception {
		Map<Object, Object> parameter = Maps.newHashMap();
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		parameter.put("open_time", open_time);
		parameter.put("woldId", woldId);
		int updateOpenTime=1;
			updateOpenTime = toolDaoTemplate.update("gameArea.updateOpen", parameter);
		if(updateOpenTime==1){
			Date date=simpleDateFormat.parse(open_time);
		    Long modifyOpenTime = date.getTime();
			gameTemplate.getToolOperation().openTime(modifyOpenTime, woldId);
			return "1";
		}
		return "2";
	}
	
	@RequiresPermissions("sys:gameServer:edit")
	@RequestMapping(value = "updateHefuTime")
	@ResponseBody
	public String updateHefuTime(HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttributes, Model model,String hefu_time,String woldId) throws Exception {
		Map<Object, Object> parameter = Maps.newHashMap();
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		parameter.put("hefu_time", hefu_time);
		parameter.put("woldId", woldId);
		int updatehefuTime=1;
		updatehefuTime = toolDaoTemplate.update("gameArea.updateHefu", parameter);
		if(updatehefuTime==1){
			Date date1=simpleDateFormat.parse(hefu_time);
		    Long configureName = date1.getTime();
			gameTemplate.getToolOperation().combineTime(configureName, woldId);
			return "1";
		}
		return "2";
	}
	
	@RequiresPermissions("sys:gameServer:edit")
	@RequestMapping(value = "addNew")
    public String addNew(Server gameServer, Model model) {
        model.addAttribute("gameServer", gameServer);
        return "modules/tools/gameAreaAdd";
    }

	@RequiresPermissions("sys:gameServer:edit")
    @RequestMapping(value = "saveNew")//@RequestParam(value="qqSid",required=true) int qqSid
    public String saveNew(Server gameServer, Model model, RedirectAttributes redirectAttributes) {

        if (!beanValidator(model, gameServer)){
            return addNew(gameServer, model);
        }
        Integer serverId = gameServer.getWorldId();
        String openTime = DateUtils.formatDate(gameServer.getOpenTime());
        if (serverId <= 0 || gameServer.getOpenTime() == null) {
        	addMessage(model, "添加游戏服失败，参数非法!");
        	return addNew(gameServer, model);
		}
        String qqId = gameServer.getId()+"";
//        if ( systemService.findGamePlatformByName(gameServer.getPlatformId()) == null){
//            addMessage(model, "添加游戏服失败，平台不存在：" + gameServer.getPlatformId());
//            return addNew(gameServer, model);
//        }
//
//       
//        Server s = serverManager.getGameServer(serverId);
//        if(s != null) {
//        	addMessage(model, "添加游戏服失败，游戏服id已存在：" + serverId);
//            return addNew(gameServer, model);
//        }

        String execRlt = CmdExecutor.execSshCmd("open", gameServer.getExternalIp(), serverId.toString(), openTime, qqId);
        //addMessage(model, "执行新增服务器命令结果:" + execRlt );
        addMessage(redirectAttributes, "执行新增服务器命令结果:" + execRlt);
        //return "redirect:"+Global.getAdminPath()+"/tools/gameAreaList/";
        return "redirect:"+Global.getAdminPath()+"/tools/gameAreaAdd/";
        //return "modules/tools/gameAreaAdd";
    }
	
}
