package com.mokylin.cabal.modules.tools.web;//package com.mokylin.cabal.modules.tools.web;
//
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.mokylin.cabal.common.config.Global;
//import com.mokylin.cabal.common.persistence.Page;
//import com.mokylin.cabal.common.persistence.Result;
//import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
//import com.mokylin.cabal.common.redis.Server;
//import com.mokylin.cabal.common.utils.StringUtils;
//import com.mokylin.cabal.common.web.BaseController;
//import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
//import com.mokylin.cabal.modules.sys.utils.UserUtils;
//import com.mokylin.cabal.modules.tools.entity.GamePlatform;
//import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;
//
///**
// * 作者: 稻草鸟人
// * 日期: 2014/10/21 14:20
// * 项目: cabal-tools
// */
//@Controller
//@RequestMapping(value = "${adminPath}/tools/gameServer")
//public class GameServerController extends BaseController {
//
//    @Resource
//    private GameServerService gameServerService;
//
//
////    @ModelAttribute
////    public GameServer get(@RequestParam(required = false) String id) {
////        if (StringUtils.isNotBlank(id)) {
////            return gameServerService.get(id);
////        } else {
////            return new GameServer();
////        }
////    }
//
////    @RequestMapping(value = {"list", ""})
////    public String getGameServerList(GameServer gameServer, HttpServletRequest request, HttpServletResponse response, Model model) {
//////        gameServer.setGamePlatform(new GamePlatform(request.getParameter("gamePlatform.id")));
////        Page<GameServer> page = gameServerService.findGameServer(new Page<GameServer>(request, response), gameServer);
////
////        model.addAttribute("page", page);
////        return "modules/tools/gameServerList";
////    }
//
////    @RequestMapping(value = {"selectSingleGameServer"})
////    public String selectSingleGameServer(GameServer gameServer, HttpServletRequest request, HttpServletResponse response, Model model) {
////
////        String gamePlatformId = request.getParameter("gamePlatform.id");
//////        gameServer.setGamePlatform(new GamePlatform(gamePlatformId));
////        Page<GameServer> page = null;
////        //没有平台参数，默认不查询
////        if(gamePlatformId != null && StringUtils.isNotBlank(gamePlatformId)) {
////            page = gameServerService.findGameServer(new Page<GameServer>(request, response), gameServer);
////        }else{
////            page = new Page<GameServer>(request,response);
////        }
////        model.addAttribute("page", page);
////        return "modules/tools/singleGameServerList";
////    }
//
////    @RequestMapping(value = "changeGameServer")
////    public
////    @ResponseBody
////    Result changeGameServer(HttpServletRequest request, Model model) {
////        String gameServerId = request.getParameter("gameServerId");
////        request.getSession().setAttribute(ConfigConstants.SELECTED_SERVER_KEY, gameServerId);
////        LookupContext.setCurrentServerId(gameServerId);
////        model.addAttribute("message", "切换服务器成功");
////        return new Result(true).data(request.getParameter("gameServerName"));
////    }
//
//
////    @RequestMapping(value = "form")
////    public String form(GameServer gameServer, Model model) {
////
////
////        model.addAttribute("gameServer", gameServer);
////        return "modules/tools/gameServerForm";
////    }
//
//
////    @RequestMapping(value = "save")
////    public String save(GameServer gameServer, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
////        if (!beanValidator(model, gameServer)) {
////            return form(gameServer, model);
////        }
////        gameServer.setGamePlatform(new GamePlatform(request.getParameter("gamePlatform.id")));
////
////        gameServerService.saveGameServer(gameServer);
////        addMessage(redirectAttributes, "保存游戏服'" + gameServer.getServerId() + "'成功");
////        return "redirect:" + Global.getAdminPath() + "/tools/gameServer/";
////    }
////
////    @RequestMapping(value = "delete")
////    public String delete(String id, RedirectAttributes redirectAttributes) {
////
////        gameServerService.deleteGameServer(id);
////        addMessage(redirectAttributes, "删除用户成功");
////
////        return "redirect:" + Global.getAdminPath() + "/sys/user/";
////    }
//
//
////    /**
////     * 多选服务器弹窗，去除了合过服的机器
////     * @param request
////     * @param model
////     * @return
////     */
////    @RequestMapping(value = "openGameServerDialog")  
////    public String openServerDialog(HttpServletRequest request, Model model){  
////
////        model.addAttribute("gamePlatform", UserUtils.getGamePlatformList());
////
////
////        return "modules/tools/gameServerDialog";
////    }
// 
////    @ResponseBody
////    @RequestMapping(value = "treeData")  
////    public List<Map<String, Object>> treeData(@RequestParam(required=false) Long extId, HttpServletResponse response) {
////        response.setContentType("application/json; charset=UTF-8");
////        List<Map<String, Object>> mapList = Lists.newArrayList();
////        List<Server> list = GameAreaUtils.getGameServerListWithoutRepetition();
////        for (int i=0; i<list.size(); i++){
////        	Server e = list.get(i);
////            if (extId == null || (extId!=null && !extId.equals(e.getId()))){
////                Map<String, Object> map = Maps.newHashMap();
////                map.put("id", e.getId());
////                map.put("pId",e.getPid());
////                map.put("name", e.getName());
////                mapList.add(map);
////            }
////        }
////        return mapList;
////    }
//}
