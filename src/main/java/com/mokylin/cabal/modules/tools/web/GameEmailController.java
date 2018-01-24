package com.mokylin.cabal.modules.tools.web;

import com.alibaba.fastjson.JSON;
import com.mokylin.cabal.async.AsyncService;
import com.mokylin.cabal.common.config.Global;
import com.mokylin.cabal.common.persistence.MybatisParameter;
import com.mokylin.cabal.common.persistence.Page;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.Encodes;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.common.utils.excel.ImportExcel;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.GameEmail;
import com.mokylin.cabal.modules.tools.entity.GameRole;
import com.mokylin.cabal.modules.tools.service.GoodsAnalyzeService;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import com.mokylin.cabal.modules.tools.vo.AttachmentGoods;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/6 15:53
 * 项目: cabal-tools
 */

@Controller
@RequestMapping(value = "${adminPath}/tools/gameEmail")
public class GameEmailController extends BaseController {

    @Autowired
    private GoodsAnalyzeService goodsAnalyzeService;

    @Resource
    private AsyncService asyncService;

    @ModelAttribute
    public GameEmail get(@RequestParam(required = false) String id) {
        if (StringUtils.isNotBlank(id)) {
            return toolDaoTemplate.selectOne("gameEmail.selectOneById", id);
        } else {
            return new GameEmail();
        }
    }

    @RequestMapping(value = {"list", ""})
    public String getGameServerList(GameEmail gameEmail, HttpServletRequest request, HttpServletResponse response, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.setPage(new Page(request, response));

        Page<GameEmail> page = toolDaoTemplate.paging("gameEmail.paging", parameter);

        model.addAttribute("page", page);
        return "modules/tools/gameEmailList";
    }

    @RequiresPermissions("game.email.edit")
    @RequestMapping(value = "form")
    public String form(GameEmail gameEmail, Model model) {
    	
        return "modules/tools/gameEmailForm";
    }

    @RequiresPermissions("game.email.batchadd")
    @RequestMapping(value = "batchAdd")
    public String batchAdd(GameEmail gameEmail, Model model) {
        
        return "modules/tools/gameEmailBatchAdd";
    }


    @RequiresPermissions("game.email.edit")
    @RequestMapping(value = "applyEmail")
    public String applyEmail(GameEmail gameEmail, Model model) {

        return "modules/tools/applyEmail";
    }

    @RequestMapping(value = "saveAndSend")
    public String saveAndSend(GameEmail gameEmail, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("emailStatus", 0);

        //附加物品列表，绑定为默认值，但是其并没有选择物品，这里需要移除掉
        List<AttachmentGoods> list = gameEmail.getGoodsList();
        for (AttachmentGoods goods : list) {
            if (StringUtils.isBlank(goods.getId()) || StringUtils.isEmpty(goods.getId())) {
                list.remove(goods);
            }
        }

        parameter.put("attachments", JSON.toJSONString(list));

        int isGlobal = MapUtils.getIntValue(parameter,"isGlobal");
        String roleIds = "";
        if(isGlobal == 0){  //不是全服发送
            String[] roleNames = StringUtils.split(MapUtils.getString(parameter,"receiverNames"),",");
            String pid = MapUtils.getString(parameter,"pid");
            for(String roleName : roleNames){
                String roleId = RedisUtils.getRoleIdByRoleName(pid,roleName,"");
                roleIds = roleIds == ""? roleId : ","+roleId;
            }
            parameter.put("receiverUserIds",roleIds);
        }

        toolDaoTemplate.insert("gameEmail.insert", parameter);
        addMessage(redirectAttributes, "申请邮件成功");


        GameEmail email = toolDaoTemplate.selectOne("gameEmail.selectOneById", MapUtils.getString(parameter, "id"));
        //如果不是全服发送，那么收件人的角色id必须存在
        if(email != null && !email.isGlobal() && email.getReceiverUserIds() == null){
            logger.info("当前发送的邮件存在异常~收件人的角色ID为空,【{}】",JSON.toJSONString(email));
            addMessage(redirectAttributes, "发布邮件失败，角色编号不存在！");
            return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
        }
        Result result = gameTemplate.gameEmailOperation().sendEmail(email);
        if (result.isSuccess()) {
            email.setEmailStatus("1");
            //MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
            parameter.put("emailStatus", "1");
            toolDaoTemplate.update("gameEmail.updateStatus", parameter);

            addMessage(redirectAttributes, "发布邮件成功");
        }


        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    @RequiresPermissions("game.email.edit")
    @RequestMapping(value = "save")
    public String save(GameEmail gameEmail, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("emailStatus", 0);

        //附加物品列表，绑定为默认值，但是其并没有选择物品，这里需要移除掉
        List<AttachmentGoods> list = gameEmail.getGoodsList();
        for (AttachmentGoods goods : list) {
            if (StringUtils.isBlank(goods.getId()) || StringUtils.isEmpty(goods.getId())) {
                list.remove(goods);
            }
        }

        parameter.put("attachments", JSON.toJSONString(list));

        int isGlobal = MapUtils.getIntValue(parameter,"isGlobal");
        String roleIds = "";
        if(isGlobal == 0){  //不是全服发送
            String[] roleNames = StringUtils.split(MapUtils.getString(parameter,"receiverNames"),",");
            String pid = MapUtils.getString(parameter,"pid");
            for(String roleName : roleNames){
                String roleId = RedisUtils.getRoleIdByRoleName(pid, roleName, "");
                roleIds = roleIds == ""? roleIds + roleId : roleIds + ","+roleId;

                logger.info("当前发送的邮件~收件人的角色roleid={},roleName={},pid={}",roleId,roleName,pid);

            }


            parameter.put("receiverUserIds",roleIds);
        }

        toolDaoTemplate.insert("gameEmail.insert", parameter);
        addMessage(redirectAttributes, "申请邮件成功");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    /**
     * 批量补偿申请，直接发送，按人来发，一个人一封邮件，未区分是否是一个服
     *
     * @param gameEmail
     * @param file
     * @param request
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("game.email.batchadd")
    @RequestMapping(value = "batchSave")
    public String batchSave(GameEmail gameEmail, MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("emailStatus", 1);

        //附加物品列表，绑定为默认值，但是其并没有选择物品，这里需要移除掉
        List<AttachmentGoods> list = gameEmail.getGoodsList();
        for (AttachmentGoods goods : list) {
            if (StringUtils.isBlank(goods.getId()) || StringUtils.isEmpty(goods.getId())) {
                list.remove(goods);
            }
        }

        String attachments = JSON.toJSONString(list);
        parameter.put("attachments", JSON.toJSONString(list));
        gameEmail.setAttachments(attachments);

        StringBuffer serverIds = new StringBuffer();
        StringBuffer receiverUserIds = new StringBuffer();
        try {
            List<String> recordList = IOUtils.readLines(file.getInputStream());
            Set<String> records = new HashSet<String>(recordList);  //去重复
            for (String record : records) {
                String[] array = StringUtils.split(record, ",");
                int len = serverIds.length();
                serverIds = serverIds.length() > 0 ? serverIds.append("," + array[0]) : serverIds.append(array[0]);
                receiverUserIds = receiverUserIds.length() > 0 ? receiverUserIds.append("," + array[1]) : receiverUserIds.append(array[1]);
                gameEmail.setId(IdGen.uuid());      //每个人每封邮件都有一个ID
                gameEmail.setReceiverUserIds(array[1]);
                gameTemplate.gameEmailOperation().sendEmail(gameEmail, array[0]);
            }
        } catch (IOException e) {
            logger.error("", e);
        }
        parameter.put("serverIds", serverIds.toString());
        parameter.put("receiverUserIds", receiverUserIds.toString());

        toolDaoTemplate.insert("gameEmail.insert", parameter);


        addMessage(redirectAttributes, "批量邮件成功");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    @RequestMapping(value = "goodsDialog")
    public String goodsDialog(String goodsName, Model model) {

        model.addAttribute("list", goodsAnalyzeService.query(goodsName));

        return "modules/tools/goodsDialog";
    }

    @RequiresPermissions("game.email.cancel")
    @RequestMapping(value = "cancel")
    public String cancel(String id, HttpServletRequest request, Model model) {

        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        toolDaoTemplate.update("gameEmail.cancel", parameter);
        addMessage(model, "取消成功咯~");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    /**
     * 批量取消
     *
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("game.email.batchcancel")
    @RequestMapping(value = "batchCancel")
    public String batchCancel(HttpServletRequest request, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        toolDaoTemplate.update("gameEmail.batchCancel", parameter);

        addMessage(model, "批量取消成功咯~");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    @RequiresPermissions("game.email.batchrecover")
    @RequestMapping(value = "batchRecover")
    public String batchRecover(HttpServletRequest request, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        toolDaoTemplate.update("gameEmail.batchRecover", parameter);

        addMessage(model, "批量恢复成功咯~");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    @RequiresPermissions("game.email.recover")
    @RequestMapping(value = "recover")
    public String recover(HttpServletRequest request, Model model) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");

        toolDaoTemplate.update("gameEmail.recover", parameter);

        addMessage(model, "恢复成功咯~");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }

    @RequiresPermissions("game.email.send")
    @RequestMapping(value = "send")
    public String publish(@ModelAttribute GameEmail email, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        if(email.getEmailStatus().equals("1")) {
            addMessage(redirectAttributes, "该邮件已经发送，请勿重新发送！");
            return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
        }

        //如果不是全服发送，那么收件人的角色id必须存在
        if(email != null && !email.isGlobal() && email.getReceiverUserIds() == null){
            logger.info("当前发送的邮件存在异常~收件人的角色ID为空,【{}】",JSON.toJSONString(email));
            addMessage(redirectAttributes, "发布邮件失败，角色编号不存在！");
            return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
        }
//        Result result = gameTemplate.gameEmailOperation().sendEmail(email);
        asyncService.sendEmail(email);
//        if (result.isSuccess()) {
        email.setEmailStatus("1");
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("emailStatus", "1");
        toolDaoTemplate.update("gameEmail.updateStatus", parameter);

        addMessage(redirectAttributes, "发布邮件成功");
//        }
        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }


    private MultiValueMap parse(MultipartFile file) {
        MultiValueMap result = new MultiValueMap();
        try {
            List<String> list = IOUtils.readLines(file.getInputStream());
            for (String record : list) {
                String[] array = StringUtils.split(record, ",");

            }
        } catch (IOException e) {
            logger.error("", e);
        }

        return result;
    }


    @ResponseBody
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(@RequestParam MultipartFile file, Model model, HttpServletRequest request) {
        String ret = "";
        try {
            ImportExcel importExcel = new ImportExcel(file, 1, 0);
            List<GameRole> dataList = importExcel.getDataList(GameRole.class);
            for (GameRole role : dataList) {
                ret = ret != "" ? (ret + "," + role.getName()) : ret + role.getName();
            }
        } catch (Exception e) {
            logger.error("", e);
        }

        return Encodes.urlEncode(ret);
    }
    
    @RequiresPermissions("game.email.name")
    @RequestMapping(value = "batchNameSave")
    public String batchNameSave(GameEmail gameEmail, MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        MybatisParameter parameter = (MybatisParameter) request.getAttribute("paramMap");
        parameter.put("emailStatus", 1);

        //附加物品列表，绑定为默认值，但是其并没有选择物品，这里需要移除掉
        List<AttachmentGoods> list = gameEmail.getGoodsList();
        for (AttachmentGoods goods : list) {
            if (StringUtils.isBlank(goods.getId()) || StringUtils.isEmpty(goods.getId())) {
                list.remove(goods);
            }
        }

        String attachments = JSON.toJSONString(list);
        parameter.put("attachments", JSON.toJSONString(list));
        gameEmail.setAttachments(attachments);

        StringBuffer serverIds = new StringBuffer();
        StringBuffer name = new StringBuffer();
        StringBuffer receiverUserIds = new StringBuffer();
        String pid = MapUtils.getString(parameter,"pid");
        try {
            List<String> recordList = IOUtils.readLines(file.getInputStream());
            Set<String> records = new HashSet<String>(recordList);  //去重复
            for (String record : records) {
                String[] array = StringUtils.split(record, ",");
                int len = serverIds.length();
                serverIds = serverIds.length() > 0 ? serverIds.append("," + array[0]) : serverIds.append(array[0]);
                name = name.length() > 0 ? name.append("," + array[1]) : name.append(array[1]);
                String   roleId=RedisUtils.getRoleIdByRoleName(pid,array[1], "");
                receiverUserIds = receiverUserIds.length() > 0 ? receiverUserIds.append("," + roleId) : receiverUserIds.append(roleId);
                gameEmail.setId(IdGen.uuid());      //每个人每封邮件都有一个ID
                gameEmail.setReceiverUserIds(roleId);
                gameTemplate.gameEmailOperation().sendEmail(gameEmail, array[0]);
            }
        } catch (IOException e) {
            logger.error("", e);
        }
        parameter.put("serverIds", serverIds.toString());
        parameter.put("receiverNames", name.toString());
        parameter.put("receiverUserIds", receiverUserIds.toString());

        toolDaoTemplate.insert("gameEmail.insert", parameter);


        addMessage(redirectAttributes, "批量邮件成功");

        return "redirect:" + Global.getAdminPath() + "/tools/gameEmail/";
    }
    
    @RequiresPermissions("game.email.name")
    @RequestMapping(value = "batchNameAdd")
    public String batchNameAdd(GameEmail gameEmail, Model model) {
        
        return "modules/tools/gameEmailBatchNameAdd";
    }
}
