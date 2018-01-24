package com.mokylin.cabal.modules.tools.web;

import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.sys.utils.DictUtils;
import com.mokylin.cabal.modules.tools.entity.GameNotice;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;




/**
 * 作者: 稻草鸟人
 * 日期: 2014/10/30 11:27
 * 项目: cabal-tools
 */
@Controller
@RequestMapping(value = "${adminPath}/tools/gameNotice")
public class GameNoticeController  extends BaseController {

    @ModelAttribute
    public GameNotice get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return toolDaoTemplate.selectOne("gameNotice.selectOneById",id);
        } else {
            return new GameNotice();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String getGameServerList(GameNotice gameNotice, HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request,response));

        Page<GameNotice> page = toolDaoTemplate.paging("gameNotice.paging",parameter);

        model.addAttribute("page", page);
        return "modules/tools/gameNoticeList";
    }

    @RequiresPermissions("game.notice.edit")
    @RequestMapping(value = "form")
    public String form(GameNotice gameNotice, Model model) {

//        JSONArray nodes = new JSONArray();
//        for(GamePlatform gamePlatform : gamePlatforms){
//            JSONObject object = new JSONObject();
//            object.put("name",gamePlatform.getName());
//            JSONArray array = new JSONArray();
//            Collection<Server> serverList = GameAreaUtils.getGameServerList(gamePlatform.getPid());
//            for(Server gameServer : serverList){
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("id",gameServer.getWorldId());
//                jsonObject.put("name",gameServer.getWorldName()+"【"+gameServer.getWorldId()+"】");
//                array.add(jsonObject);
//            }
//            object.put("children",array);
//            nodes.add(object);
//        }
//        model.addAttribute("nodes",nodes);
//        model.addAttribute("gamePlatform", UserUtils.getGamePlatformList());
//        model.addAttribute("gameServerList", GameServerUtils.getGameServerWithoutHeFu());

        return "modules/tools/gameNoticeForm";
    }

    @RequiresPermissions("game.notice.edit")
    @RequestMapping(value = "save")
    public String save(GameNotice gameNotice, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("id",IdGen.uuid());
        parameter.put("noticeStatus",0);    //尚未发布
        //前端AS语言不支持span标签<span style="color:#ff0000;">aaaaaaa</span> 修改为 <font color="#ff0000;">aaaaaaa</font>
        parameter.put("content", MapUtils.getString(parameter,"content").replace("span","font").replace("style","color")
                .replace("color:","").replace("<p>","").replace("</p>","").replace("\r","").replace("\n","").replace("\t","").replace("&nbsp;",""));

        toolDaoTemplate.insert("gameNotice.insert",parameter);
        addMessage(redirectAttributes, "新增公告成功");

        return "redirect:"+Global.getAdminPath()+"/tools/gameNotice/";
    }


    /**
     * GM 后台标记为已删除状态，请求游戏删除公告，达到取消的目的
     * @param redirectAttributes
     * @return
    */
    @RequiresPermissions("game.notice.delete")
    @RequestMapping(value = "delete")
    public String delete(GameNotice gameNotice,RedirectAttributes redirectAttributes){

        if(gameNotice.getNoticeStatus().equals("0")){       //如果公告尚未发布则不请求游戏服务器直接取消公告
            gameNotice.setNoticeStatus("4");                      //1 表示已发布，这里应该可以再改进，不直接写1
            toolDaoTemplate.update("gameNotice.updateStatus",gameNotice);
            addMessage(redirectAttributes, "删除公告成功");
            return "redirect:"+ Global.getAdminPath()+"/tools/gameNotice/";
        }

        //请求Game接口要求删除服务器公告
        Result result = gameTemplate.announceOperation().deleteAnnounce(gameNotice);
        if(result.isSuccess()) {
            //toolDaoTemplate.delete("gameNotice.delete", gameNotice.getId());
            gameNotice.setNoticeStatus("4");                      //1 表示已发布，这里应该可以再改进，不直接写1
            toolDaoTemplate.update("gameNotice.updateStatus",gameNotice);
        }
        addMessage(redirectAttributes, "删除公告成功");
        return "redirect:"+ Global.getAdminPath()+"/tools/gameNotice/";
    }

    @RequiresPermissions("game.notice.publish")
    @RequestMapping(value = "publish")
    public String publish(@ModelAttribute GameNotice gameNotice,Model model,RedirectAttributes redirectAttributes,HttpSession session){

        Result result = gameTemplate.announceOperation().addAnnounce(gameNotice);
        if(result.isSuccess()){
            gameNotice.setNoticeStatus("1");                      //1 表示已发布，这里应该可以再改进，不直接写1
            toolDaoTemplate.update("gameNotice.updateStatus",gameNotice);
        }
        addMessage(redirectAttributes, "发布公告成功");
        return "redirect:"+ Global.getAdminPath()+"/tools/gameNotice/";
    }
    /**
     * 玩家信息导出excel
     */
    @RequiresPermissions("game.role.fenghao")
    @RequestMapping(value ="roleExport",method= RequestMethod.POST)
    public ResponseEntity<byte[]> roleExport(HttpServletRequest request,HttpServletResponse response){
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
}
