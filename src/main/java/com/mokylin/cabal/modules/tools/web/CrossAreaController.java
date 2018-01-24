package com.mokylin.cabal.modules.tools.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.BaseCrossArea;
import com.mokylin.cabal.common.redis.BattleArea;
import com.mokylin.cabal.common.redis.BattleCrossArea;
import com.mokylin.cabal.common.redis.Country;
import com.mokylin.cabal.common.redis.CountryCrossArea;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.redis.SystemConstant;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.vo.BattleAreaVO;

@Controller
@RequestMapping(value = "${adminPath}/tools/crossArea")
public class CrossAreaController extends BaseController {
	@Resource
	private ServerManager serverManager;

	@Autowired
	private RedisManager redisManager;

	/**
	 * 获取跨服服务器
	 */
	@RequestMapping(value = "crossAreas")
	public String crossAreas(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Server> crossServerList = serverManager.getCrossServerList();
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		List<Server> result = new ArrayList<>();
		String serverId = (String) parameter.get("serverId");
		if (StringUtils.isNotEmpty(serverId)) {
			for (Server s : crossServerList) {
				if (s.getWorldId() == Integer.parseInt(serverId)) {
					result.add(s);
				}
			}
			model.addAttribute("crossServerList", result);
		} else {
			model.addAttribute("crossServerList", crossServerList);
		}
		return "modules/tools/crossAreaList";
	}

	@RequestMapping("form")
	public String form(String crossId, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("crossId", crossId);
		return "modules/tools/crossAreaForm";
	}

	@RequestMapping("saveGameCrossRelation")
	public String saveGameCrossRelation(HttpServletRequest request, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String crossId = MapUtils.getString(parameter,"crossId");
		String serverIds = request.getParameter("serverIds");
		List<String> serverIdList = Arrays.asList(StringUtils.split(serverIds,","));
		//删除旧的关系，新增就的关系   or   直接页面上不显示旧的关系呢？
		redisManager.setGameCrossRelation(serverIdList, crossId);
		return "redirect:"+Global.getAdminPath()+"/tools/crossArea/crossAreas";
	}


	@RequestMapping("battleArea")
	public String battleArea(HttpServletRequest request, HttpServletResponse response, Model model){
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String crossAreaName = (String) parameter.get("crossAreaName");
		String innerIp = (String) parameter.get("innerIp");
		List<BattleAreaVO> ret = new ArrayList<BattleAreaVO>();
		Collection<BattleArea> battleArea = serverManager.getBattleAreaList();
		for (BattleArea area : battleArea) {
			   Server cross = serverManager.getCrossServer(area.getCrossId());
				if (cross == null) {
					logger.info("不存在当前战区-@-" + area.getCrossId());
					continue;
				}
				BattleAreaVO battleAreaVO = new BattleAreaVO(area, cross);
				for (Integer countryId : area.getCountries()) {
					Country country = serverManager.getCountry(String.valueOf(countryId));
					battleAreaVO.addCountry(country);
				}
				if(StringUtils.isNoneEmpty(crossAreaName)){
					if(StringUtils.isNoneEmpty(innerIp)){
						if(battleAreaVO.getWorldName().equals(crossAreaName)&&battleAreaVO.getIp().equals(innerIp)){
							 ret.add(battleAreaVO);
						}
					}else{
						if(battleAreaVO.getWorldName().equals(crossAreaName)){
							 ret.add(battleAreaVO);
						}
					}
				}
				else{
					if(StringUtils.isNotEmpty(innerIp)){
						if(battleAreaVO.getIp().equals(innerIp)){
							 ret.add(battleAreaVO);
						}
					}else{
							 ret.add(battleAreaVO);
					}
					
				}
				
	       }


		Page page = new Page(request,response);
		Collections.sort(ret, new Comparator<BattleAreaVO>() {

			@Override
			public int compare(BattleAreaVO o1, BattleAreaVO o2) {
				return o1.getCrossId() - o2.getCrossId();
			}
		});
		Integer end = page.getPageNo() * page.getPageSize();
		if(end > ret.size()){
			end = ret.size();
		}
		page.setList(ret.subList((page.getPageNo() - 1) * page.getPageSize() , end));
        page.setCount(ret.size());


		model.addAttribute("battleArea",page);
		return "modules/tools/battleArea";
	}


	@RequestMapping("countryList")
	public String countryList(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		List<Country> ret = new ArrayList<Country>();
		String name = (String) parameter.get("crossAreaName");
		String crossId = (String) parameter.get("crossId");
		Collection<Country> battleArea = serverManager.getCountryList();
		for (Country area : battleArea) {
			if(StringUtils.isNoneEmpty(name)){
				if(StringUtils.isNoneEmpty(crossId)){
					if(area.getName().equals(name)&&String.valueOf(area.getCrossId()).equals(crossId)){
						ret.add(area);
					}
				}else{
					if(area.getName().equals(name)){
						ret.add(area);
					}
				}
			}
			else{
				if(StringUtils.isNotEmpty(crossId)){
					if(String.valueOf(area.getCrossId()).equals(crossId)){
						ret.add(area);
					}
				}else{
					ret.add(area);
				}

			}
		}
		Page page = new Page(request, response);
		Collections.sort(ret, new Comparator<Country>() {
			@Override
			public int compare(Country o1, Country o2) {
				return o1.getId() - o2.getId();
			}
		});
		Integer end = page.getPageNo() * page.getPageSize();
		if (end > ret.size()) {
			end = ret.size();
		}
		page.setList(ret.subList((page.getPageNo() - 1) * page.getPageSize(), end));
        page.setCount(ret.size());
		model.addAttribute("countries", page);
		model.addAttribute("crossAreaName", name);
		model.addAttribute("crossId", crossId);
		return "modules/tools/countryList";
	}

	@RequestMapping("showCountry")
	// @ResponseBody
	public String showCountry(String crossId, HttpServletRequest request, Model model) {
		List<Country> countryList = serverManager.getCountryListByCrossId(crossId);
			for (Country c : countryList) {
				if (c.getAddTime() != null) {
					c.setAddTime(DateUtils.parseDate(c.getAddTime()));
				}
			}
			model.addAttribute("crossId", crossId);
			model.addAttribute("countryList", countryList);
		// return countryList;
		return "modules/tools/toCountry";

	}

	@RequestMapping("removeCountry")
	@ResponseBody
	public Result removeCountry(String countryId, HttpServletRequest request, Model model) {

		serverManager.removeBattleAreaCountry(countryId);

		return new Result(true).data("success");
	}

	@RequestMapping("addCountry")
	@ResponseBody
	public List<Country> addCountry(HttpServletRequest request, Model model){
		Collection<BattleArea> battleArea = serverManager.getBattleAreaList();
		Map<String, Country> countryMap = serverManager.getCountryMap();
		List<Integer> countryIds = null;
		for(BattleArea area : battleArea){
			countryIds = area.getCountries();
			for(Integer countryId : countryIds){
				countryMap.remove(countryId);
			}
		}


		return new ArrayList<Country>(countryMap.values());
	}


	@RequestMapping(value = "crossAreaManage")
	public String crossAreaManage(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String crossAreaType = (String) parameter.get("crossAreaType");
		String crossAreaName = (String) parameter.get("crossAreaName");
		String innerIp = (String) parameter.get("innerIp");
		model.addAttribute("crossAreaList", queryResultCrossArea(model, crossAreaType, crossAreaName, innerIp));
		model.addAttribute("crossAreaType", crossAreaType);
		model.addAttribute("crossAreaName", crossAreaName);
		model.addAttribute("innerIp", innerIp);
		return "modules/tools/battleArea";
	}
	@RequestMapping(value = "exportXls")
	public ResponseEntity<byte[]> exportXls(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String crossAreaType = (String) parameter.get("crossAreaType");
		String crossAreaName = (String) parameter.get("crossAreaName");
		String innerIp = (String) parameter.get("innerIp");
		List<BaseCrossArea> crossAreaList = queryResultCrossArea(model, crossAreaType, crossAreaName, innerIp);
		model.addAttribute("crossAreaList", crossAreaList);
		model.addAttribute("crossAreaType", crossAreaType);
		model.addAttribute("crossAreaName", crossAreaName);
		model.addAttribute("innerIp", innerIp);

		List<Map> exportXls = new ArrayList<>();
		for (BaseCrossArea bca : crossAreaList) {
			Map map=new LinkedHashMap<>();
			map.put("crossAreaType", bca.getCrossType()==SystemConstant.BATTLE_CROSS?"竞技服务器":"国家");
			map.put("worldId",bca.getCrossServer().getWorldId());
			map.put("worldName", bca.getCrossServer().getWorldName());
			map.put("crossAreaName", bca.getCrossAreaName());
			map.put("innerIp", bca.getCrossServer().getInnerIp());
			map.put("innerPort", bca.getCrossServer().getInnerPort());
			map.put("serverName", bca.getServerName());
			exportXls.add(map);
		}
		return super.exportXls(exportXls, System.currentTimeMillis()+"","类型","跨服ID","跨服名字","赛区名称","IP","port","竞技服务器/国家");

	}
	private List<BaseCrossArea> queryResultCrossArea(Model model, String crossAreaType, String crossAreaName, String innerIp) {
		List<BaseCrossArea> crossAreaList = new ArrayList<>();
//		for (BaseCrossArea bca : serverManager.getAllCrossArea()) {
//			if (StringUtils.isEmpty(crossAreaType) || (StringUtils.isNotEmpty(crossAreaType) && Integer.parseInt(crossAreaType) == bca.getCrossType())) {
//				if (StringUtils.isEmpty(crossAreaName) || (StringUtils.isNotEmpty(crossAreaName) && bca.getCrossAreaName().indexOf(crossAreaName) != -1)) {
//					if (StringUtils.isEmpty(innerIp) || (StringUtils.isNotEmpty(innerIp) && bca.getCrossServer().getInnerIp().indexOf(innerIp) != -1)) {
//						crossAreaList.add(bca);// innerIp为空 || innerIp不为空但是满足查询条件的
//					}
//				}
//			}
//		}
		return crossAreaList;
	}

	@RequestMapping(value = { "crossAreaAdd" })
	public String crossAreaAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
		String crossAreaType=request.getParameter("crossAreaType");
		if(StringUtils.isNotEmpty(crossAreaType)){
			createModel(Integer.parseInt(crossAreaType),model);
			model.addAttribute("crossAreaType", crossAreaType);
		}
		return "modules/tools/crossAreaAdd";
	}
	
	@RequestMapping(value = { "doCrossAreaAdd" })
	public String doCrossAreaAdd(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String crossAreaType=(String) parameter.get("crossAreaType");
		String crossServerId=(String) parameter.get("crossServerId");
		String crossAreaName=(String) parameter.get("crossAreaName");
		String countryId=(String) parameter.get("countryId");
		String gameServerId=(String)parameter.get("gameServerId");
		if(StringUtils.isEmpty(crossAreaType)||StringUtils.isEmpty(crossServerId)||StringUtils.isEmpty(crossAreaName)||StringUtils.isEmpty(gameServerId)){
			model.addAttribute("message", "添加失败,信息不全!");
			return "redirect:"+ Global.getAdminPath()+"/tools/crossArea/crossAreaAdd?crossAreaType="+crossAreaType;
		}else{
			if(SystemConstant.BATTLE_CROSS==Integer.parseInt(crossAreaType)){
				Server server=serverManager.getCrossServer(Integer.parseInt(crossServerId));
				BattleCrossArea bca=new BattleCrossArea(crossAreaName, server);
				String[] gsIds=gameServerId.split(",");
				for (String gsId : gsIds) {
					bca.addServer(serverManager.getGameServer(Integer.parseInt(gsId)));
				}
				//serverManager.addCrossArea(bca);
			}else if(SystemConstant.COUNTRY_CROSS==Integer.parseInt(crossAreaType)){
				if(StringUtils.isEmpty(countryId)){
					model.addAttribute("message", "国战玩法,国家没有选!");
					return "redirect:"+ Global.getAdminPath()+"/tools/crossArea/crossAreaAdd?crossAreaType="+crossAreaType;
				}else{
					Server server=serverManager.getCrossServer(Integer.parseInt(crossServerId));
					CountryCrossArea cca=new CountryCrossArea(crossAreaName, server);
					CountryCrossArea.Country country=cca.getCountrys().get(new Integer(countryId));
					String[] gsIds=gameServerId.split(",");
					for (String gsId : gsIds) {
						country.addServer(serverManager.getGameServer(Integer.parseInt(gsId)));
					}
					//serverManager.addCrossArea(cca);
				}
			}
			return "redirect:"+ Global.getAdminPath()+"/tools/crossArea/crossAreaManage";
		}
		}
	
	@RequestMapping(value = { "getBattleCrossInfo" })
	public String getBattleCrossInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		createModel(SystemConstant.BATTLE_CROSS,model);
		model.addAttribute("crossAreaType", SystemConstant.BATTLE_CROSS);
		return "modules/tools/crossAreaAdd";
	}
	@RequestMapping(value = { "getCountryCrossInfo" })
	public String getCountryCrossInfo(HttpServletRequest request, HttpServletResponse response, Model model) {
		createModel(SystemConstant.COUNTRY_CROSS,model);
		model.addAttribute("crossAreaType", SystemConstant.COUNTRY_CROSS);
		return "modules/tools/crossAreaAdd";
	}
	@RequestMapping(value = { "deleteAllCrossArea" })
	public String deleteAllCrossArea(HttpServletRequest request, HttpServletResponse response, Model model) {

		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String  recordIdsq=request.getParameter("recordIds");
		String[] recordIds1=request.getParameterValues("recordIds");
		List<String> recordIds = (List<String>) MapUtils.getObject(parameter, "recordIdList");
		for (String crossId : recordIds) {
			serverManager.removeBattle(crossId);
		}
		return "redirect:" + Global.getAdminPath() + "/tools/crossArea/battleArea";
	}
	@RequestMapping(value = { "deleteCrossAreaById" })
	public String deleteCrossAreaById(HttpServletRequest request, HttpServletResponse response, Model model) {
		String crossType=request.getParameter("crossType");
		String corssAreaId=request.getParameter("crossAreaId");
		//serverManager.removeCrossAreaById(crossType,corssAreaId);
		return "redirect:"+ Global.getAdminPath()+"/tools/crossArea/crossAreaManage";
	}
	@RequestMapping(value = { "updateCrossArea" })
	public String updateCrossArea(HttpServletRequest request, HttpServletResponse response, Model model) {
		String crossType=request.getParameter("crossType");
		String corssAreaId=request.getParameter("crossAreaId");
		//BaseCrossArea crossArea = serverManager.getCrossArea(crossType,corssAreaId);
//		model.addAttribute("crossArea",crossArea);
//		model.addAttribute("crossAreaType", crossArea.getCrossType());
//		model.addAttribute("crossAreaName", crossArea.getCrossAreaName());
//		model.addAttribute("wroldId", crossArea.getCrossServer().getWorldId());
//		String gameServersId ="";
//		for (Server s : crossArea.getAllServers()) {
//			gameServersId+=s.getWorldId()+",";
//		}
		//model.addAttribute("gameServersId", gameServersId);
		createModel(Integer.parseInt(crossType),model);
		return "modules/tools/crossAreaAdd";
	}

	/**
	 * 更具战区类型,获取对应的可用跨服服务器和可用的 游戏服务器
	 * @param crossType
	 * @param model
	 * @return 返回给客户端的对象,只在json中用
	 */
	private void createModel(int crossType,Model model) {
		//跨服服务器
		List<Server> crossServerList = serverManager.getCrossServerList();
		//游戏服务器
		List<Server> gameServerList = serverManager.getGameServerList();
		// 当前存在的指定类型的战区
		List<BaseCrossArea> corssAreaMap = null;
		if(crossType==SystemConstant.BATTLE_CROSS){
			//corssAreaMap=serverManager.getBattleCrossArea();
		}else if(crossType==SystemConstant.COUNTRY_CROSS){
			//corssAreaMap=serverManager.getCountryCrossArea();
		}
		/** 可用的跨服服务器 */
		List<Server> crossServerToPage = new ArrayList<>();
		loop: for (Server s : crossServerList) {
			for (BaseCrossArea bca : corssAreaMap) {
				if (s.getWorldId()== bca.getCrossServer().getWorldId()) {
					continue loop;
				}
			}
			crossServerToPage.add(s);
		}
		model.addAttribute("crossServerToPage", crossServerToPage);
		/**可用的游戏服务器  */
		List<Server> gameServerToPage = new ArrayList<>();
		loop:for (Server s : gameServerList) {
				for (BaseCrossArea b : corssAreaMap) {
					if (b.isExistServer(s)) {
						continue loop;
					}
				}
			gameServerToPage.add(s);
		}
		model.addAttribute("gameServerToPage", gameServerToPage);
	}


	// 添加国家
	@RequestMapping("addDetailCountry")
	@ResponseBody
	public Result addDetailCountry(String countryId, String crossId, HttpServletRequest request, Model model) throws ServletException, IOException {
		String[] cou = countryId.split(",");
		Country country = null;
		BattleArea battleArea = null;
		List<Country> ret = new ArrayList<Country>();
		for (String str : cou) {
			country = serverManager.getCountry(str);
			ret.add(country);
			// 添加国家
			//country.setAddTime(new Date());
			redisManager.updateCountry(country);
			battleArea = serverManager.getBattleArea(String.valueOf(country.getCrossId()));
			if (crossId.equals(String.valueOf(battleArea.getCrossId()))) {
				battleArea.addCountry(Integer.parseInt(str));
				redisManager.updateBattleArea(battleArea);
			}
		}
		return new Result(true).data(ret);
	}


	@RequestMapping(value = "openCountry")
	public String openCountry(String crossId, HttpServletRequest request, Model model) {
		model.addAttribute("crossId", crossId);
		model.addAttribute("countryList", UserUtils.getCountry(crossId));
		return "modules/tools/country";
	}
	
}
