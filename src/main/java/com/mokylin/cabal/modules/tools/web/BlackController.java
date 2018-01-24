package com.mokylin.cabal.modules.tools.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.util.StringUtils;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.HttpUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.vo.IpBlack;
import com.mokylin.cabal.modules.tools.vo.UserBlack;

/**
 * 作者 : 稻草鸟人 时间: 2015/6/19 admin-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/black")
public class BlackController extends BaseController {

	private final String TAOBAO_IP_URL = "http://ip.taobao.com/service/getIpInfo.php";

	@Autowired
	private RedisManager redisManager;

	@RequestMapping("ipSilenceFreeze")
	public String ipSilenceFreeze(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<IpBlack> list = redisManager.getIpBlackList();
		String ip = request.getParameter("ip");
		String createDateStart = request.getParameter("createDateStart");
		String createDateEnd = request.getParameter("createDateEnd");
		List<IpBlack> result = new ArrayList<>();
		if (!StringUtils.isEmpty(ip) || !StringUtils.isEmpty(createDateEnd) || !StringUtils.isEmpty(createDateStart)) {
			boolean flag = false;
			try {
				for (IpBlack ipBlack : list) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					long date = df.parse(ipBlack.getCreateTime().substring(0, 10)).getTime();

					if (!StringUtils.isEmpty(ip)) {
						flag = ipBlack.getIp().equals(ip);
						if (flag == false)
							continue;
					}
					if (!StringUtils.isEmpty(createDateStart)) {
						flag = df.parse(createDateStart).getTime() <= date;
						if (flag == false)
							continue;
					}
					if (!StringUtils.isEmpty(createDateEnd)) {
						flag = df.parse(createDateEnd).getTime() >= date;
						if (flag == false)
							continue;
					}
					if (flag) {
						result.add(ipBlack);
					}
				}

			} catch (ParseException e) {
				logger.info("",e);
			}
			list = result;
		}
		Page page = new Page(request, response);
		Collections.sort(list, new Comparator<IpBlack>() {

			@Override
			public int compare(IpBlack o1, IpBlack o2) {
				return DateUtils.parseDate(o1.getCreateTime()).compareTo(DateUtils.parseDate(o2.getCreateTime()));
			}
		});
		Integer end = page.getPageNo() * page.getPageSize();
		if (end > list.size()) {
			end = list.size();
		}
		page.setList(list.subList((page.getPageNo() - 1) * page.getPageSize(), end));
		page.setCount(list.size());
		model.addAttribute("page", page);
		// model.addAttribute("list", list);
		return "modules/tools/ipSilenceFreezeList";
	}

	@RequestMapping("form")
	public String form(HttpServletRequest request, HttpServletResponse response, Model model) {


		return "modules/tools/ipSilenceFreezeForm";
	}



	 @RequestMapping("userBlackList")
    public String userBlackList(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<UserBlack> list = redisManager.getUserBlackList();
        String userId = request.getParameter("userId");
        String createDateStart = request.getParameter("createDateStart");
        String createDateEnd = request.getParameter("createDateEnd");
        String pids= request.getParameter("pids");
        List<UserBlack> result = new ArrayList<>();
        if (!StringUtils.isEmpty(userId) || !StringUtils.isEmpty(createDateEnd) || !StringUtils.isEmpty(createDateStart)||!StringUtils.isEmpty(pids)) {
            boolean flag = false;
            try {
                for(UserBlack userBlack:list){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    long date = df.parse(userBlack.getCreateTime().substring(0, 10)).getTime();

                    if (!StringUtils.isEmpty(userId)) {
                        flag = userBlack.getUserId().equals(userId);
                        if(flag==false) continue;
                    }
                    if (!StringUtils.isEmpty(createDateStart)) {
                        flag = df.parse(createDateStart).getTime() <= date;
                        if(flag==false) continue;
                    }
                    if (!StringUtils.isEmpty(createDateEnd)) {
                        flag = df.parse(createDateEnd).getTime() >= date;
                        if(flag==false) continue;
                    }
                    if (!StringUtils.isEmpty(pids)) {
                        flag = userBlack.getPid().equals(pids);
                        if(flag==false) continue;
                    }
                    if (flag) {
                        result.add(userBlack);
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            list = result;
        }
        Page page = new Page(request,response);
        Collections.sort(list, new Comparator<UserBlack>() {

            @Override
            public int compare(UserBlack o1, UserBlack o2) {
                return DateUtils.parseDate(o1.getCreateTime()).compareTo(DateUtils.parseDate(o2.getCreateTime()));
            }
        });
        Integer end = page.getPageNo() * page.getPageSize();
        if(end > list.size()){
            end = list.size();
        }
        page.setList(list.subList((page.getPageNo() - 1) * page.getPageSize() , end));
        page.setCount(list.size());
        model.addAttribute("page", page);
        return  "modules/tools/userBlackList";
    }

	@RequestMapping("userForm")
	public String userForm(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/tools/userForm";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("addUserBlack")
	public String addUserBlack(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String pid = MapUtils.getString(parameter, "pid");
		List<String> userIdList = Arrays.asList(MapUtils.getString(parameter, "userIds").split(","));
		int self = MapUtils.getString(parameter, "self", null) == null ? 0 : 1;
		int silence = MapUtils.getString(parameter, "silence", null) == null ? 0 : 1;
		int freeze = MapUtils.getString(parameter, "freeze", null) == null ? 0 : 1;
		String expireTime = MapUtils.getString(parameter, "expireTime");

		for (String userId : userIdList) {
			UserBlack userBlack = new UserBlack(pid, userId, self, silence, freeze, expireTime,
					DateUtils.getDateTime());
			redisManager.addUser2BlackList(userBlack);
		}

		addMessage(model, "添加userId到黑名单列表成功");
		return "redirect:" + Global.getAdminPath() + "/tools/black/userBlackList";
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String ipAddress = MapUtils.getString(parameter, "ipAddress");
		int silence = MapUtils.getString(parameter, "silence", null) == null ? 0 : 1;
		int freeze = MapUtils.getString(parameter, "freeze", null) == null ? 0 : 1;

		String expireTime = MapUtils.getString(parameter, "expireTime");

		IpBlack ipBlack = new IpBlack(ipAddress, silence, freeze, expireTime, DateUtils.getDateTime()); // 默认开启禁言
		redisManager.addIp2BlackList(ipBlack);

		addMessage(model, "添加IP到黑名单列表成功");

		return "redirect:" + Global.getAdminPath() + "/tools/black/ipSilenceFreeze";
	}

	@RequestMapping("updateStatus")
	@ResponseBody
	public Result updateStatus(HttpServletRequest request) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String ip = MapUtils.getString(parameter, "ip");
		String type = MapUtils.getString(parameter, "type");
		int flag = MapUtils.getIntValue(parameter, "flag");
		// 禁言
		if (type.equals("silence")) {
			IpBlack ipBlack = redisManager.getIpBlackByIp(ip);
			ipBlack.setSilence(flag);
			redisManager.addIp2BlackList(ipBlack);
		}
		// 封号
		if (type.equals("freeze")) {
			IpBlack ipBlack = redisManager.getIpBlackByIp(ip);
			ipBlack.setFreeze(flag);
			redisManager.addIp2BlackList(ipBlack);
		}

		return new Result(true);
	}

	@RequestMapping("updateUserBlackStatus")
	@ResponseBody
	public Result updateUserBlackStatus(HttpServletRequest request) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String userId = MapUtils.getString(parameter, "userId");
		String pid = MapUtils.getString(parameter, "pid");
		String type = MapUtils.getString(parameter, "type");
		int flag = MapUtils.getIntValue(parameter, "flag");
		// 禁言
		if (type.equals("silence")) {
			UserBlack userBlack = redisManager.getUserBlackByUserPid(pid, userId);
			userBlack.setSilence(flag);
			redisManager.addUser2BlackList(userBlack);
		}
		// 封号
		if (type.equals("freeze")) {
			UserBlack userBlack = redisManager.getUserBlackByUserPid(pid, userId);
			userBlack.setFreeze(flag);
			redisManager.addUser2BlackList(userBlack);
		}
		// 自言自语
		if (type.equals("self")) {
			UserBlack userBlack = redisManager.getUserBlackByUserPid(pid, userId);
			userBlack.setSelf(flag);
			redisManager.addUser2BlackList(userBlack);
		}

		return new Result(true);
	}

	@RequestMapping("delete")
	public String delete(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String ip = MapUtils.getString(parameter, "ip");

		redisManager.delIpBlackList(ip);

		addMessage(redirectAttributes, "操作成功!");

		return "redirect:" + Global.getAdminPath() + "/tools/black/ipSilenceFreeze";
	}

	@RequestMapping("searchip")
	public String searchip(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String ip = MapUtils.getString(parameter, "ip");
		String url = TAOBAO_IP_URL + "?ip=" + ip;
		String result = "";
		try {
			result = HttpUtils.getHttp(url);

		} catch (Exception e) {
			result = "";
			// result =
			// "{'code':1,'data':{'ip':'210.75.225.254','country':'\u4e2d\u56fd','area':'\u534e\u5317','region':'\u5317\u4eac\u5e02','city':'\u5317\u4eac\u5e02','county':'','isp':'\u7535\u4fe1','area_id':'100000','region_id':'110000','city_id':'110000','county_id':'-1','isp_id':'100017'}}";
			// // e.printStackTrace();
		}
		response.getWriter().print(result);
		return null;
	}

	@RequestMapping("deleteUserBlack")
	public String deleteUserBlack(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		String userId = MapUtils.getString(parameter, "userId");
		String pid = MapUtils.getString(parameter, "pid");

		redisManager.delUserBlackList(pid, userId);

		addMessage(redirectAttributes, "操作成功!");

		return "redirect:" + Global.getAdminPath() + "/tools/black/userBlackList";
	}

}
