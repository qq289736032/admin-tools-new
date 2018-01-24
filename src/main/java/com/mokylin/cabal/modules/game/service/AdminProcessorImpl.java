package com.mokylin.cabal.modules.game.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.mokylin.cabal.common.config.TalkDTO;
import com.mokylin.cabal.common.core.ErrorCode;
import com.mokylin.cabal.common.game.api.GameTemplate;
import com.mokylin.cabal.common.io.MsgType;
import com.mokylin.cabal.common.io.Session;
import com.mokylin.cabal.common.io.SessionManager;
import com.mokylin.cabal.common.persistence.GameDaoTemplate;
import com.mokylin.cabal.common.persistence.ToolDaoTemplate;
import com.mokylin.cabal.common.redis.RedisManager;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.common.redis.ServerManager;
import com.mokylin.cabal.common.utils.DateUtils;
import com.mokylin.cabal.common.utils.IdGen;
import com.mokylin.cabal.common.utils.ObjectUtils;
import com.mokylin.cabal.common.utils.StringUtils;
import com.mokylin.cabal.modules.sys.constant.ChannelConstant;
import com.mokylin.cabal.modules.sys.utils.ConfigConstants;
import com.mokylin.cabal.modules.sys.utils.UserUtils;
import com.mokylin.cabal.modules.tools.utils.RedisUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
@DependsOn("chatMonitorManager")
public class AdminProcessorImpl extends AdminProcessor {
    private static final Logger logger = LogManager.getLogger(AdminProcessorImpl.class);

    @Autowired
    private ServerManager serverManager;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private ChatMonitorManager chatMonitorManager;

    @Resource
    private GameTemplate gameTemplate;

    @Autowired
    private ToolDaoTemplate toolDaoTemplate;

    @Autowired
    private GameDaoTemplate gameDaoTemplate;

    @Override
    public void keepAlive(Session session, Object[] msg) {
        session.sendMsg(new Object[]{MsgType.Ping_S2C_Msg, new Object[]{}});
    }

    @Override
    public void login(Session session, Object[] msg) {
        String pid = String.valueOf(msg[0]);
        String userId = String.valueOf(msg[1]);
        Session old = SessionManager.getInstance().getSession4User(userId);
        if (old != null) {
            old.sendMsg(new Object[]{MsgType.Disconnect_S2C_Msg, new Object[]{}});
            old.close();
        }
        SessionManager.getInstance().addSession4User(pid, userId, session);
        session.sendMsg(new Object[]{MsgType.Login_S2C_Msg, new Object[]{}});
    }

    @Override
    public void getServerList(Session session, Object[] msg) {
        List<Server> list = serverManager.getValidGameServerList(String.valueOf(session.getPid()));

        Collections.sort(list, new Comparator<Server>() {
            @Override
            public int compare(Server o1, Server o2) {
                return o1.getWorldId() - o2.getWorldId();
            }
        });

        Object[] areaList = new Object[list.size()];
        int i = 0;
        for (Server e : list) {
            areaList[i++] = e.toAreaVO();
        }
//        logger.debug("聊天监控，服务器列表:" + JSON.toJSONString(areaList));
        session.sendMsg(new Object[]{MsgType.GetServerList_S2C_Msg, areaList});
    }

    @Override
    public void selectArea(Session session, Object[] msg) {
        String pid = String.valueOf(msg[0]);
        int areaId = (int) msg[1];
        logger.info("pid={},areaId={},session={}", pid, areaId, session);
        chatMonitorManager.add2ObserverList(pid, areaId, session.getUserId());
        session.sendMsg(new Object[]{MsgType.SelectArea_S2C_Msg, new Object[]{}});
    }

    @Override
    public void deselectArea(Session session, Object[] msg) {
        String pid = String.valueOf(msg[0]);
        int areaId = (int) msg[1];
        logger.info("pid={},areaId={},session={}", pid, areaId,session);
        chatMonitorManager.remove4ObserverList(pid, areaId, session.getUserId());
        session.sendMsg(new Object[]{MsgType.DeselectArea_S2C_Msg, new Object[]{}});
    }

    @Override
    public void punish(Session session, Object[] msg) {
        String pid = String.valueOf(msg[0]);
        int areaId = (int) msg[1];
        int type = (int) msg[2];
        String name = (String) msg[3];
        Date beginTime = new Date(ObjectUtils.arg2long(msg[4]));
        Date endTime = new Date(ObjectUtils.arg2long(msg[5]));
        String reason = (String) msg[6];
//        String userId = String.valueOf(msg[7]);
//        String ip = String.valueOf(msg[8]);

        //punishManager.punish(pid, areaId, type, reason, beginTime, endTime, name);
        String roleId = RedisUtils.getRoleIdByRoleName(pid,name,"");
        
        if(StringUtils.isEmpty(roleId)){
        	session.sendMsg(new Object[]{MsgType.Ping_S2C_Msg, new Object[]{ErrorCode.EC_NOT_FIND_USER}});
        }else{
        /**
         * type
         * 1、自言自语
         * 2、禁言
         * 4、封号
         * 5、警告
         * 6、封账号
         * 7、封IP
         */
        if(type == MsgType.PUNISH_TYPE_JINYAN){
            gameTemplate.roleOperation().jinYan(String.valueOf(areaId),roleId, DateUtils.formatDateTime(endTime));
            logging(reason,DateUtils.formatDateTime(endTime), ConfigConstants.OPERATION_TYPE_SILENCE,roleId,String.valueOf(areaId));
        }else if(type == MsgType.PUNISH_TYPE_FENHAO) {
            gameTemplate.roleOperation().fenHao(String.valueOf(areaId), roleId, DateUtils.formatDateTime(endTime));
            logging(reason,DateUtils.formatDateTime(endTime), ConfigConstants.OPERATION_TYPE_FREEZE,roleId,String.valueOf(areaId));
        }
//        else if(type == MsgType.PUBLISH_TYPE_USER) {
//            redisManager.addUser2BlackList(new UserBlack(pid,userId,0,0,1));
//        }else if(type == MsgType.PUBLISH_TYPE_IP) {
//            redisManager.addIp2BlackList(new IpBlack(ip, 0, 1));
//        }
        logger.info("pid={},areaId={},type={},name={},beginTime={},endTime={},reason={},userId={},ip={}", pid, areaId, type, name, beginTime, endTime, reason);
        session.sendMsg(new Object[]{MsgType.Ping_S2C_Msg, new Object[]{ErrorCode.EC_OK}});
        }
    }

    private void logging(String msg, String expireTime, String operationType, String roleId, String serverId) {
        //角色信息,查询出来，插入禁言封号日志表
        //List<Map<String, Object>> roleList = gameDaoTemplate.selectList("role.findRoleByIdList", map);
        String createName = UserUtils.getUser().getLoginName();
        String createBy = UserUtils.getUser().getId();
        Map<String,Object> map = Maps.newHashMap();
        map.put("msg", msg);
        map.put("id", IdGen.uuid());                   //一条记录一个主键
        map.put("createName", createName);
        map.put("createBy", createBy);
        map.put("expireTime", expireTime);
        //String operationType = MapUtils.getString(map, "operationType");
        map.put("operationType",operationType);
        map.put("roleId",roleId);
        map.put("roleName", RedisUtils.getRoleNameByRoleId(roleId,roleId));
        map.put("serverId",serverId);

        toolDaoTemplate.insert("silenceFreezeLog.insert", map);
    }

    // http://xxxxx.swf?pid=1212&area_id=2&user_id=2132&user_name=whh&ip=192.168.1.21&port=8888

    @Override
    public void chat(Session session, Object[] msg) {
        String pid = String.valueOf(msg[0]);
//        int worldId = (int) msg[1];
//        int areaId = (int) msg[2];
        int areaId = (int) msg[1];
        int worldId = (int) msg[2];
        int channelType = (int) msg[3];
        String content = (String) msg[4];
        if (channelType == ChannelConstant.CHANNEL_TYPE_PRIVATE) {
            long roleId = ObjectUtils.arg2long(msg[5]);
            String roleName = (String) msg[6];
            chatMonitorManager.sendPlayer(pid, worldId, areaId, session.getUserId(), roleId, roleName, content);
        }

    }

    /**
     * 获取玩家问的问题列表
     */
    @Override
    public void getQuestionList(Session session, Object[] msg) {
        Collection<TalkDTO> list = chatMonitorManager.getQuestionlist();
        Object[] result = new Object[list.size()];
        int i = 0;
        for (TalkDTO e : list) {
            result[i++] = e.toVO();
        }
        session.sendMsg(new Object[]{MsgType.GET_QUESTION_LIST_S2C_Msg, result});

    }

    /**
     * 接客
     */
    @Override
    public void receptionPlayer(Session session, Object[] msg) {
        int id = (int) msg[0];
        TalkDTO dto = chatMonitorManager.remove(id);
        if (dto != null) {
            session.sendMsg(new Object[]{MsgType.Reception_Player_S2C_Msg, new Object[]{ErrorCode.EC_OK, dto.toVO()}});
        } else {
            session.sendMsg(new Object[]{MsgType.Reception_Player_S2C_Msg, new Object[]{ErrorCode.EC_FAILED}});
        }
    }

    @Override
    public void unPunish(Session session, Object[] msg) {
        String pid = String.valueOf(msg[0]);
        int areaId = (int) msg[1];
        int type = (int) msg[2];
        String name = (String) msg[3];
        String roleId = (String) msg[4];
        String userId = String.valueOf(msg[5]);
        String ip = String.valueOf(msg[6]);

        if(type == MsgType.UN_PUNISH_TYPE_FENHAO) {
            gameTemplate.roleOperation().cancelFenHao(roleId, String.valueOf(areaId));
            logging("一键解除封号",DateUtils.formatDateTime(new Date()), ConfigConstants.OPERATION_TYPE_CANCEL_FREEZE,roleId,String.valueOf(areaId));
        }else if(type == MsgType.UN_PUNISH_TYPE_JINYAN) {
            gameTemplate.roleOperation().cancelJinYan(roleId, String.valueOf(areaId));
            logging("一键解除禁言",DateUtils.formatDateTime(new Date()), ConfigConstants.OPERATION_TYPE_CANCEL_SILENCE,roleId,String.valueOf(areaId));
        }
        logger.info("pid={},areaId={},type={},name={},userId={},ip={}", pid, areaId, type, name, userId, ip);
        session.sendMsg(new Object[]{MsgType.Ping_S2C_Msg, new Object[]{ErrorCode.EC_OK}});
    }
}
