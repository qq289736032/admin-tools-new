package com.mokylin.cabal.modules.chuangqu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.chuangqu.entity.ChuangquRequest;
import com.mokylin.cabal.modules.chuangqu.entity.ChuangquResponse;
import com.mokylin.cabal.modules.chuangqu.entity.CreateVo;
import com.mokylin.cabal.modules.chuangqu.entity.ItemLogVo;
import com.mokylin.cabal.modules.chuangqu.entity.LevelLogVo;
import com.mokylin.cabal.modules.chuangqu.entity.LoadingLogVo;
import com.mokylin.cabal.modules.chuangqu.entity.LoginVo;
import com.mokylin.cabal.modules.chuangqu.entity.MoneyCosumeLogVo;
import com.mokylin.cabal.modules.chuangqu.entity.OnlineUserLogVo;
import com.mokylin.cabal.modules.chuangqu.entity.OnlineVo;
import com.mokylin.cabal.modules.chuangqu.entity.RegisterVo;
import com.mokylin.cabal.modules.chuangqu.entity.TaskLogVo;
import com.mokylin.cabal.modules.log.web.MoneyConsumeLogController;

/**
 * 创趣数据中心接口
 * 
 * @author jassi
 * @version 2017-06-09
 */
@Controller
@RequestMapping(value = "/datareport")
public class ChuangquServlet extends BaseController {

	//md5 key : d218c3024e1a05192fe15e2b8ffec0f7
	@RequestMapping(value = "/chuangqu")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChuangquRequest chuangqu = parseParameters(request);
		logger.info("datareport/chuangqu--ChuangquRequest:" + JSON.toJSONString(chuangqu));
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		if ("login".equals(chuangqu.getType())) {
			login(chuangqu, response);
		}
		else if ("register".equals(chuangqu.getType())) {
			register(chuangqu, response);
		}
		else if ("create_role".equals(chuangqu.getType())) {
			create(chuangqu, response);
		}
		else if ("online".equals(chuangqu.getType())) {
			online(chuangqu, response);
		}
		else if ("recharge".equals(chuangqu.getType())) {
			recharge(chuangqu, response);
		} 
		else if ("level".equals(chuangqu.getType())) { // 等级日志
			levelupLog(chuangqu, response);
		}
		else if ("task".equals(chuangqu.getType())) { // 任务日志
			taskLog(chuangqu, response);
		}
		else if ("item".equals(chuangqu.getType())) { // 道具
			itemLog(chuangqu, response);
		}
		else if ("user_online".equals(chuangqu.getType())) { // 在线时长
			userOnlineLog(chuangqu, response);
		}
//		else if ("item_comsume".equals(chuangqu.getType())) { // 道具消耗日志
//			ite(chuangqu, response);
//		}
		else if ("comsume".equals(chuangqu.getType())) { // 货币消耗
			moneyConsumeLog(chuangqu, response);
		}
		else if ("login_log".equals(chuangqu.getType())) { // 进入游戏前加载日志
			loadingLog(chuangqu, response);
		}
		else {
			logger.error("chuangqu type is error:" + chuangqu.getType());
			ChuangquResponse result = new ChuangquResponse();
			response.getWriter().print(JSON.toJSONString(result));
		}

	}

	/**
	 * 解析参数
	 * 
	 */
	private ChuangquRequest parseParameters(HttpServletRequest request) {
		ChuangquRequest result = new ChuangquRequest();
		String reportType = getStrVal(request, "type");
		if (reportType == null || reportType.isEmpty()) {
			return null;
		}
		result.setType(reportType);		
		String startTime = getStrVal(request, "start_time");
		result.setStart_time(DateUtils.strToDate(startTime).getTime());
		result.setEnd_time(DateUtils.strToDate(getStrVal(request, "end_time")).getTime());
		result.setCursor(Integer.parseInt(getStrVal(request, "cursor")));
		result.setLimit(Integer.parseInt(getStrVal(request, "limit")));
		result.setGid(Integer.parseInt(getStrVal(request, "gid")));
		result.setTimestamp(Integer.parseInt(getStrVal(request, "timestamp")));
		result.setSign(getStrVal(request, "sign"));
		result.setSid(Integer.parseInt(getStrVal(request, "sid")));
		String logDate = startTime.substring(0,10).replace("-", "");
		result.setLogDate(logDate);
		return result;
	}

	private final String getStrVal(HttpServletRequest request, String key) {
		String val = request.getParameter(key);
		if (val == null)
			return "";
		return val.trim();
	}
	

	

	
	private void login(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<LoginVo> data = logDaoTemplate.selectList("chuangqu.findlogin", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void register(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<RegisterVo> data = logDaoTemplate.selectList("chuangqu.findregister", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void create(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<CreateVo> data = logDaoTemplate.selectList("chuangqu.findcreate", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void online(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<OnlineVo> data = logDaoTemplate.selectList("chuangqu.findonline", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void recharge(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<RegisterVo> data = logDaoTemplate.selectList("chuangqu.findrecharge", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void levelupLog(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<LevelLogVo> data = logDaoTemplate.selectList("chuangqu.selectPlayerLevel", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void taskLog(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<TaskLogVo> data = logDaoTemplate.selectList("chuangqu.selectTask", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}

	
	private void itemLog(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<ItemLogVo> data = logDaoTemplate.selectList("chuangqu.selectItemLog", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	private void userOnlineLog(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<OnlineUserLogVo> data = gameDaoTemplate.selectList("chuangqu.selectOnlineUserTime", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	
	private void moneyConsumeLog(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<MoneyCosumeLogVo> data = logDaoTemplate.selectList("chuangqu.selectMoneyConsume", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	
	
	private void loadingLog(ChuangquRequest chuangqu, HttpServletResponse response) throws IOException {
		List<LoadingLogVo> data = logDaoTemplate.selectList("chuangqu.selectLoading", chuangqu);
		ChuangquResponse result;
		if (data == null) {
			result = new ChuangquResponse();
		} else {
			result = new ChuangquResponse(data);
		}
		response.getWriter().print(JSON.toJSONString(result));
	}
	

}
