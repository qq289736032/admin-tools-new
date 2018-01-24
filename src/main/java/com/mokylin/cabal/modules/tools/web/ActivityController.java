package com.mokylin.cabal.modules.tools.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.FileUtils;
import com.mokylin.cabal.common.utils.Md5Utils;
import com.mokylin.cabal.common.utils.ShellUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.Activity;
import com.mokylin.cabal.modules.tools.entity.ActivityConfig;

/**
 * 活动 日期: 2015/3/26 项目: cabal-tools
 */

@Controller
@RequestMapping(value = "${adminPath}/tools/activity")
public class ActivityController extends BaseController {

	// 当增删改的时候才去初始化，启动时不用
	private static final Map<String/* txt文件名 */, HashMap<String/* serverId */, Activity>> activitysMap = new HashMap<String, HashMap<String, Activity>>();

	private static final String GLOBAL = "global";

//	private static boolean initedMap = false;

	@ModelAttribute
	public Activity get(@RequestParam(required = false) String id) {
		if (StringUtils.isNotBlank(id)) {
			return toolDaoTemplate.selectOne("activity.selectActivityById", id);
		} else {
			return new Activity();
		}
	}
	
    @PostConstruct
    public void init(){
    	initActivitysMap();
    }
    
	// ---------------------------- --------------------------------------
	@RequestMapping(value = "activityConfigList")
	public String getActivityConfigList(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		parameter.setPage(new Page(request, response));
		Page<ActivityConfig> page = toolDaoTemplate.paging("activity.selectActivityConfigList", parameter);
		model.addAttribute("page", page);
		return "modules/tools/activityConfigList";
	}

	@RequestMapping(value = "activityConfigForm")
	public String activityConfigForm(ActivityConfig activityConfig, HttpServletRequest request, Model model) {
		// MybatisParameter parameter = (MybatisParameter)
		// request.getAttribute("paramMap");
		if (StringUtils.isNotBlank(activityConfig.getId())) {
			activityConfig = toolDaoTemplate.selectOne("activity.selectActivityConfigById", activityConfig.getId());
		} else {
			activityConfig = new ActivityConfig();
		}
		model.addAttribute("activityConfig", activityConfig);
		// model.addAttribute("activityConfigList", getActivityConfigList());
		return "modules/tools/activityConfigForm";
	}

	@RequestMapping(value = "saveActivityConfig")
	public String saveActivityConfig(ActivityConfig activityConfig, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		if (!StringUtils.isNotBlank(activityConfig.getId())) {
			List<Map<String, Object>> list = toolDaoTemplate.selectList("activity.selectActivityAlias",parameter);
			if(list.size() <=0){
			   toolDaoTemplate.insert("activity.insertActivityConfig", parameter);
			}else{
				addMessage(redirectAttributes, "alias已经存在，请重新选择");
				return "redirect:" + Global.getAdminPath() + "/tools/activity/activityConfigForm";
			}
		} else {
			toolDaoTemplate.update("activity.updateActivityConfig", parameter);
		}

		addMessage(redirectAttributes, "保存成功");
		return "redirect:" + Global.getAdminPath() + "/tools/activity/activityConfigList";
	}

	// ---------------------------- --------------------------------------

	@RequestMapping("syncActivity")
	@ResponseBody
	public Result syncActivity(HttpServletRequest request, Model model){
		String path = Global.getCommonMap().get("hot.load.shell").toString();
		if(FileUtils.exists(path)) {
			ShellUtils.exec(path);
		}
		logger.info("同步活动完成，脚本路径:[{}]",path);
		return new Result(true);
	}


	@RequestMapping(value = "activityList")
	public String getActivityList(HttpServletRequest request, HttpServletResponse response, Model model) {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
		parameter.setPage(new Page(request, response));
		Page<Activity> page = toolDaoTemplate.paging("activity.paging", parameter);
		model.addAttribute("page", page);
		model.addAttribute("activityConfigList", getActivityConfigList());
		return "modules/tools/activityList";
	}

	@RequestMapping(value = "form")
	public String form(Model model) {
		model.addAttribute("activityConfigList", getActivityConfigList());
		return "modules/tools/activityForm";
	}

	@RequestMapping(value = "save")
	public String save(Activity activity, MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model)
			throws IOException {

		String originalFileName = file.getOriginalFilename();

		String fileName = originalFileName;

		boolean notModifyFile = false;
		if (StringUtils.isNotBlank(activity.getId()) && StringUtils.isBlank(fileName)) {
			notModifyFile = true;
			Activity oldActivity = toolDaoTemplate.selectOne("activity.selectActivityById", activity.getId());
			fileName = oldActivity.getName();
			originalFileName = oldActivity.getOriginalFile();
		}  else {
			fileName = originalFileName.substring(0,originalFileName.indexOf(".")) + "_" + System.currentTimeMillis()
					+ originalFileName.substring(originalFileName.indexOf("."));
		}

		String alias = getAliasByActivityConfigName(activity.getActivityName());
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

		parameter.put("name", fileName);
		parameter.put("originalFile", originalFileName);

		// check
		if (activitysMap.get(alias) != null) {
			if (activity.isGlobal()) {
				// 检查是否globa已存在
				if (activitysMap.get(alias).get(GLOBAL) != null) {
					Activity gloableActivity = activitysMap.get(alias).get(GLOBAL);
					System.out.println(gloableActivity);
					if (!gloableActivity.getId().equals(activity.getId())) {
						if(activity.getIsGlobal()==1){
								//修改操作
								//删除数据库的数据（del_flag = 1）
							   toolDaoTemplate.update("activity.deleteActivityStatues", gloableActivity.getId());
						       HashMap<String,Activity>classes=activitysMap.get(alias); 
								//获取内存Map里面数据
						       Set<Map.Entry<String,Activity>>entryset=classes.entrySet(); 
						        System.out.println(entryset);
						        for(java.util.Iterator<Map.Entry<String,Activity>> it=entryset.iterator();it.hasNext();) 
						        { 
						            Map.Entry<String,Activity> me=it.next(); 
						            if(me.getKey()==GLOBAL){
						               it.remove();
						               classes.remove(me.getKey());
						            }
						            if(StringUtils.isNotBlank(activity.getId())&& StringUtils.isBlank(fileName) ){
						            	Activity oldActivity = toolDaoTemplate.selectOne("activity.selectActivityById", activity.getId());
							            if(me.getKey().equals(oldActivity.getServerIds())){
											System.out.println(classes.get(GLOBAL));
							            	classes.put(GLOBAL, me.getValue());
								               classes.remove(me.getKey());
								               System.out.println("we"+classes);
									            toolDaoTemplate.update("activity.updateGlobal", activity.getId());
										}
							            String contextPath = request.getSession().getServletContext().getRealPath("/") + "/";
										String filePath = MapUtils.getString(Global.getCommonMap(), "activity-file"); // 上传文件路径
										String fullUploadPath = contextPath + filePath;
										File txtFile = new File(fullUploadPath + alias);
								    	List<String> txtContents = getTxtContents(alias);
										writeToTxt(txtContents, txtFile);
										return "redirect:" + Global.getAdminPath() + "/tools/activity/activityList";
						            }
						        } 
						    /*  String contextPath = request.getSession().getServletContext().getRealPath("/") + "/";
								String filePath = MapUtils.getString(Global.getCommonMap(), "activity-file"); // 上传文件路径
								String fullUploadPath = contextPath + filePath;
								File txtFile = new File(fullUploadPath + alias);
						    	List<String> txtContents = getTxtContents(alias);
								writeToTxt(txtContents, txtFile);
								return "redirect:" + Global.getAdminPath() + "/tools/activity/activityList";*/
						}
					}
				}
			} else {
				String serverArr[] = activity.getServerIds().split(",");
				for (String serverId : serverArr) {
					if (activitysMap.get(alias).get(serverId) != null) {
						Activity oldActivity = activitysMap.get(alias).get(serverId);
						if (!oldActivity.getId().equals(activity.getId())) {
							addMessage(model, "服务器" + serverId + "已经存在" + activity.getActivityName() + "，不允许选择服务器" + serverId);
							return form(model);
						}
					}
				}
			}
		}

		// 编辑保存则删除旧文件和数据库记录

		String contextPath = request.getSession().getServletContext().getRealPath("/") + "/";
		String filePath = MapUtils.getString(Global.getCommonMap(), "activity-file"); // 上传文件路径
		String fullUploadPath = contextPath + filePath;
		// if (StringUtils.isNotBlank(activity.getId()) && !notModifyFile) {
		// new File(fullUploadPath + activity.getName()).delete();
		// // toolDaoTemplate.delete("activity.delete", activity.getId());
		// }

		if (!notModifyFile) {
			String path = fullUploadPath + activity.getName();
			//FileUtils.deleteFile(path);
			new File(path).delete();
			new File(fullUploadPath).mkdirs();
			File fileFormat = new File(fullUploadPath + fileName);
			file.transferTo(fileFormat);
			String md5 = Md5Utils.MD5EncodeFile(fileFormat);
			activity.setMd5(md5);
		}

		Activity oldActivity = toolDaoTemplate.selectOne("activity.selectActivityById", activity.getId());
		boolean isGloablToServer = false; // 是否全服转指定服
		if (oldActivity != null && oldActivity.isGlobal() && !activity.isGlobal()) {
			isGloablToServer = true;
		}
		
		activity.setName(fileName);
		activity.setOriginalFile(originalFileName);
		activity.setId(MapUtils.getString(parameter, "id"));
		// 更新map
		updateActivitysMap(activity, alias, oldActivity == null ? "" : oldActivity.getServerIds(), isGloablToServer); // 原来服是2,3,4，现在改为4,5,6，要把23删掉
		if (oldActivity != null) {
			toolDaoTemplate.delete("activity.delete", activity.getId());
		}

		// 生成或者更新文件内容
		File txtFile = new File(fullUploadPath + alias);
		List<String> txtContents = getTxtContents(alias);
		writeToTxt(txtContents, txtFile);

		parameter.put("md5", activity.getMd5());
		toolDaoTemplate.insert("activity.insertActivity", parameter);
		addMessage(redirectAttributes, "保存并刷新活动成功");

		return "redirect:" + Global.getAdminPath() + "/tools/activity/activityList";
	}

	@RequestMapping(value = "batchDelete")
	public String batchDelete(HttpServletRequest request, Model model) throws IOException {
		MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

		List<String> aliasList = new ArrayList<>();
		
//		initActivitysMap();

		String contextPath = request.getSession().getServletContext().getRealPath("/") + "/";
		String filePath = MapUtils.getString(Global.getCommonMap(), "activity-file"); // 上传文件路径
		String fullUploadPath = contextPath + filePath;

		List<String> recordIds = (List<String>) MapUtils.getObject(parameter, "recordIdList");
		for (String id : recordIds) {
			Activity activity = toolDaoTemplate.selectOne("activity.selectActivityById", id);
			String alias = getAliasByActivityConfigName(activity.getActivityName());

			if (!aliasList.contains(alias)) {
				aliasList.add(alias);
			}
			new File(fullUploadPath + activity.getName()).delete();

			deleteFromActivitysMap(alias, activity.getServerIds(), activity.isGlobal());
		}

		toolDaoTemplate.update("activity.batchDelete", parameter);

		// 根据被删除的alias 重新生成txt

		for (String alias : aliasList) {
			File txtFile = new File(fullUploadPath + alias);
			List<String> txtContents = getTxtContents(alias);

			new File(fullUploadPath).mkdirs();

			writeToTxt(txtContents, txtFile);
		}

		// toolDaoTemplate.update("activity.batchDelete", parameter);

		addMessage(model, "批量删除成功");
		return "redirect:" + Global.getAdminPath() + "/tools/activity/activityList";
	}

	private List<Map<String, Object>> getActivityConfigList() {
		List<Map<String, Object>> list = toolDaoTemplate.selectList("activity.selectActivityConfigList");
		return list;
	}

	private String getAliasByActivityConfigName(String name) {
		String alias = toolDaoTemplate.selectOne("activity.getAliasByActivityConfigName", name);
		return alias;
	}

	private List<String> getTxtContents(String alias) {
		if (activitysMap.get(alias) == null) {
			return new ArrayList<String>();
		}
		HashMap<String, Activity> map = activitysMap.get(alias);
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, Activity> entry : map.entrySet()) {
			String str = entry.getKey() + "=" + entry.getValue().getMd5() + "," + entry.getValue().getName() + ";";
			list.add(str);
		}
		return list;
	}

	// 输出TXT
	private void writeToTxt(List<String> list, File file) throws IOException {
		if (!file.exists()) {
			try {
				// 不存在则创建
				file.createNewFile();
			} catch (IOException e) {
				logger.error(null, e);
			}
		}

		String s1 = new String();
		for (String str : list) {
			s1 += str;
			// s1 += com.mokylin.cabal.common.utils.StringUtils.ENTER;
		}

		if (StringUtils.isBlank(s1)) {
			s1 = "";
		}

		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		try {
			output.write(s1);
		} catch (IOException e1) {
			logger.error(null, e1);
		} finally {
			try {
				if (output != null) {
					output.close();
				}
			} catch (IOException e) {
				logger.error(null, e);
			}
		}
	}

	/**
	 * 初始化map 从数据库里面取
	 */
	private void initActivitysMap() {
		List<Activity> allList = toolDaoTemplate.selectList("activity.paging");

		List<String> activityNameList = new ArrayList<String>();
		// 取去有多少个活动配置
		for (Activity activity : allList) {
			if (activityNameList.contains(activity.getActivityName())) {
				continue;
			}
			activityNameList.add(activity.getActivityName());
		}

		for (String activityName : activityNameList) {
			HashMap<String, Activity> serverMap = new HashMap<String, Activity>();
			for (Activity activity : allList) {
				if (activity.getActivityName().equals(activityName)) {
					if (activity.isGlobal()) {
						serverMap.put(GLOBAL, activity);
					} else {
						String serverArr[] = activity.getServerIds().split(",");
						for (String serverId : serverArr) {
							serverMap.put(serverId, activity);
						}
					}
				}
			}
			String alias = getAliasByActivityConfigName(activityName);
			activitysMap.put(alias, serverMap);
		}
	}

	/**
	 * 更新map
	 */
	private void updateActivitysMap(Activity activity, String alias, String oldServerIds, boolean isGloablToServer) {
		HashMap<String, Activity> map = activitysMap.get(alias);
		if (map == null) {
			map = new HashMap<String, Activity>();
			activitysMap.put(alias, map);
		} else {
			if (isGloablToServer) {
				map.remove(GLOBAL);
			}
			// 删除原来的serverid
			if (StringUtils.isNotBlank(oldServerIds)) {
				String oldServerArr[] = oldServerIds.split(",");
				for (String serverId : oldServerArr) {
					map.remove(serverId);
				}
			}

		}

		if (activity.isGlobal()) {
			map.put(GLOBAL, activity);
			return;
		}
		String serverArr[] = activity.getServerIds().split(",");
		for (String serverId : serverArr) {
			map.put(serverId, activity);
		}
	}

	/**
	 * 删除更新map
	 */
	private void deleteFromActivitysMap(String alias, String oldServerIds, boolean isGloabl) {
		HashMap<String, Activity> map = activitysMap.get(alias);
		if (map == null) {
			map = new HashMap<String, Activity>();
		} else {
			if (isGloabl) {
				map.remove(GLOBAL);
			} else {
				// 删除原来的serverid
				if (StringUtils.isNotBlank(oldServerIds)) {
					String oldServerArr[] = oldServerIds.split(",");
					for (String serverId : oldServerArr) {
						map.remove(serverId);
					}
				}
			}
		}
	}
}
