package com.mokylin.cabal.common.game.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.mokylin.cabal.common.game.api.RoleOperation;
import com.mokylin.cabal.common.game.api.model.VirtualItemDTO;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.persistence.dynamicDataSource.LookupContext;
import com.mokylin.cabal.common.redis.Server;
import com.mokylin.cabal.modules.tools.utils.GameAreaUtils;

import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/17 13:53
 * 项目: cabal-tools
 */
public class RoleTemplate extends AbstractGameOperations implements RoleOperation {

    private static final String API_URL_ROLE_BATCH_JINYAN_SUFFIX = "/role/batchJinYan";

    private static final String API_URL_ROLE_BATCH_FENHAO_SUFFIX = "/role/batchFenHao";

    private static final String API_URL_ROLE_BATCH_CANCEL_JINYAN_SUFFIX = "/role/batchCancelJinYan";

    private static final String API_URL_ROLE_BATCH_CANCEL_FENHAO_SUFFIX = "/role/batchCancelFenHao";

    public static final String API_URL_ROLE_UPDATE_ROLE_TYPE = "/role/updateRoleType";

    public static final String API_URL_ROLE_DELETE = "/role/delete";

    public static final String API_URL_ROLE_ONLINE = "/role/roleOnline";

    public static final String API_URL_ROLE_PHONE_CHECK = "/role/notice";   //平台手机校验完之后通知游戏发送物品

    public static final String API_URL_ROLE_VIRTUAL_ITEM = "/role/virtualGoods";   //虚拟物品
    
    public static final String API_URL_ROLE_SET_WELFARE = "/role/setYYGm";   //设置YY福利号
    
    public static final String API_URL_ROLE_IS_WELFARE = "/role/isGmYY";   //是不是YY福利号
    
    public static final String API_URL_ROLE_EDIT_ROLE_CURRENCY = "/role/editRoleCur";   //编辑玩家货币
    
    public static final String API_URL_ROLE_EDIT_ROLE_LEVEL = "/role/editRoleLevel";   //编辑玩家等级
    
    public static final String API_URL_ROLE_DEL_ROLE_ITEM = "/item/deleteItem";   //删除玩家道具


    public RoleTemplate(AsyncRestTemplate restTemplate,  RestTemplate template) {
        super(restTemplate, template);
    }

    @Override
    public Result jinYan(String serverId, String roleId, String expireTime) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_JINYAN_SUFFIX);
        List<String> roleIds = Lists.newArrayList();
        roleIds.add(roleId);
        JSONObject obj = new JSONObject();
        obj.put("roleIds",roleIds);
        obj.put("expireTime",expireTime);
        return execute(obj,uri);
    }

    @Override
    public Result fenHao(String serverId, String roleId, String expireTime) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_FENHAO_SUFFIX);
        List<String> roleIds = Lists.newArrayList();
        roleIds.add(roleId);
        JSONObject obj = new JSONObject();
        obj.put("roleIds",roleIds);
        obj.put("expireTime",expireTime);
        return execute(obj,uri);
    }

    @Override
    public Result batchJinYan(List<String> roleIds) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(LookupContext.getCurrentServerId());
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_JINYAN_SUFFIX);
        return execute(roleIds,uri);
    }

    @Override
    public Result batchFenHao(List<String> roleIds) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(LookupContext.getCurrentServerId());
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_FENHAO_SUFFIX);
        return execute(roleIds,uri);
    }

    @Override
    public Result batchJinYan(List<String> roleIds, String expireTime) {
        JSONObject obj = new JSONObject();
        obj.put("roleIds",roleIds);
        obj.put("expireTime",expireTime);
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(LookupContext.getCurrentServerId());
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_JINYAN_SUFFIX);
        return execute(obj,uri);
    }

    @Override
    public Result batchFenHao(List<String> roleIds, String expireTime) {
        JSONObject obj = new JSONObject();
        obj.put("roleIds",roleIds);
        obj.put("expireTime",expireTime);
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(LookupContext.getCurrentServerId());
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_FENHAO_SUFFIX);
        return execute(obj,uri);
    }

    @Override
    public Result batchCancelJinYan(List<String> roleIds) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(LookupContext.getCurrentServerId());
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_CANCEL_JINYAN_SUFFIX);
        return execute(roleIds,uri);
    }

    @Override
    public Result batchCancelFenHao(List<String> roleIds) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(LookupContext.getCurrentServerId());
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_CANCEL_FENHAO_SUFFIX);
        return execute(roleIds,uri);
    }

    @Override
    public Result cancelJinYan(String roleId, String serverId) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_CANCEL_FENHAO_SUFFIX);
        return execute(new ArrayList<String>().add(serverId),uri);
    }

    @Override
    public Result cancelFenHao(String roleId, String serverId) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(remoteUrl+API_URL_ROLE_BATCH_CANCEL_FENHAO_SUFFIX);
        return execute(new ArrayList<String>().add(serverId),uri);
    }

    @Override
    public Result updateRoleType(String serverId, String roleId, String roleType) {
        String remoteUrl = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(remoteUrl+API_URL_ROLE_UPDATE_ROLE_TYPE);
        JSONObject object = new JSONObject();
        object.put("roleId",roleId);
        object.put("roleType",roleType);
        return execute(object,uri);
    }

    @Override
    public Result delete(String serverId, Long roleId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_DELETE);
        JSONObject object = new JSONObject();
        object.put("roleId",roleId);
        return execute(object,uri);
    }

    @Override
    public Result roleOnline(String serverId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_ONLINE);
        JSONObject object = new JSONObject();
        return syncExecute(object, uri);
    }

    @Override
    public Result notice(String serverId, Long roleId) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_PHONE_CHECK);
        JSONObject object = new JSONObject();
        object.put("roleId",roleId);
        return execute(object,uri);
    }

    @Override
    public Result sendVirtualGoods(String serverId, VirtualItemDTO virtualItem) {
        String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_VIRTUAL_ITEM);
        return execute(virtualItem,uri);
    }

    @Override
    public Result sendVirtualGoods(List<String> serverIds, VirtualItemDTO virtualItem) {
        List<Server> servers =  GameAreaUtils.getGameServerListWithoutRepetition(serverIds);
        for(Server server : servers){
            URI uri = buildUri(server.createUrl() + API_URL_ROLE_VIRTUAL_ITEM);
            execute(virtualItem,uri);
        }
        return new Result(true);
    }

	@Override
	public Result setYYWelfare(String serverId,Long roleId, int state) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", roleId);
		param.put("state", state);
		String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_SET_WELFARE);
        return execute(param,uri);
	}

	@Override
	public Result isYYWelfare(String serverId,Long roleId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", roleId);
		String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_IS_WELFARE);
        return execute(param,uri);
	}

	@Override
	public Result editRoleCurrency(String serverId, Long roleId, int currencyType, int value) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", roleId);
		param.put("curType", currencyType);
		param.put("value", value);
		String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_EDIT_ROLE_CURRENCY);
        return syncExecute(param,uri);
	}

	@Override
	public Result editRoleLevel(String serverId, Long roleId, int levelType, int value) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", roleId);
		param.put("levelType", levelType);
		param.put("value", value);
		String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
        URI uri = buildUri(url + API_URL_ROLE_EDIT_ROLE_LEVEL);
        return syncExecute(param,uri);
	}
	
	@Override
	public Result delRoleItem(String serverId, Long roleId, Long itemId, Integer itemNum) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("roleId", roleId);
		param.put("itemId", itemId);
		String url = GameAreaUtils.getGameRemoteUrlByServerId(serverId);
		URI uri = buildUri(url + API_URL_ROLE_DEL_ROLE_ITEM);
		return syncExecute(param,uri);
	}


//    private List<Server> getWaitingToSendGameServerList(VirtualItemDTO virtualItemDTO){
//        List<Server> gameServerList = null;
//        if(virtualItemDTO.isGlobal()){
//            gameServerList = GameAreaUtils.getGameServerListWithoutRepetition();
//        }else {
//            List<String> serverIdList = Arrays.asList(StringUtils.split(virtualItemDTO.getServerIds(), ","));
//            gameServerList = GameAreaUtils.getGameServerListWithoutRepetition(serverIdList);
//        }
//        return gameServerList;
//    }
}
