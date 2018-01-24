package com.mokylin.cabal.modules.ws.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mokylin.cabal.common.cache.EhcacheCacheManager;
import com.mokylin.cabal.common.cache.ICache;
import com.mokylin.cabal.common.persistence.Result;
import com.mokylin.cabal.common.utils.WebUtil;
import com.mokylin.cabal.common.web.BaseController;
import com.mokylin.cabal.modules.tools.entity.Gift;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: 稻草鸟人
 * 日期: 2014/11/21 19:25
 * 项目: cabal-tools
 * <p/>
 * 提供给外部的接口，此接口接受游戏中礼包码兑换
 */
@Controller(value = "giftCode")
@RequestMapping(value = "/ws/giftCode")
public class GiftCodeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger("time_logger");

    // 0不可重复使用 1 可重复使用
    private static final int TYPE_UN_REUSE = 0;
    private static final int TYPE_REUSE = 1;

    // 使用状态 0 未使用 1 已使用
    private static final int STATUS_UN_USE = 0;
    private static final int STATUS_USED = 1;

//	private static final int CODE_LENGTH = 18; // 加前缀 不超过18  

    /**
     * userId,userName,codeId,platformId,serverId
     *
     * @param request
     * @return
     */


    @RequestMapping(value = "checkCode")
    @ResponseBody
    public Result check(HttpServletRequest request) {
        Map<Object, Object> parameter = WebUtil.getRequestParamMap(request);
        String[] params = new String[]{"userId", "userName", "code", "platformId", "serverId"};
        for (String str : params) {
            Object obj = MapUtils.getObject(parameter, str);
            if (obj == null) {
                return new Result(false).error(1).data("缺少参数:" + str);
            }
        }

        Result result = check(parameter);

        return result;
    }

    @RequestMapping(value = "use")
    @ResponseBody
    public Result use(HttpServletRequest request) {
        Map<Object, Object> parameter = WebUtil.getRequestParamMap(request);
        String[] params = new String[]{"userId", "userName", "code", "platformId", "serverId", "giftId"};
        for (String str : params) {
            Object obj = MapUtils.getObject(parameter, str);
            if (obj == null) {
                return new Result(false).error(1).data("缺少参数:" + str);
            }
        }
        Result result = use(parameter);

        return result;
    }


    @RequestMapping(value = "check")
    @ResponseBody
    public Result useGiftCode(HttpServletRequest request) {
        Map<Object, Object> parameter = WebUtil.getRequestParamMap(request);
        String[] params = new String[]{"userId", "userName", "code", "platformId", "serverId"};
        for (String str : params) {
            Object obj = MapUtils.getObject(parameter, str);
            if (obj == null) {
                return new Result(false).error(1).data("缺少参数:" + str);
            }
        }

        Map<Object, Object> map = new HashMap<>();
        String code = MapUtils.getString(parameter, "code");

        // 最后4位 16进制 转成10进制
        try {
            Integer.parseInt(code.substring(code.length() - 4), 16);
        } catch (Exception e) {
            return new Result(false).error(2).data("激活码格式不对");
        }

        map.put("code", code);
        int tableIndex = Integer.parseInt(code.substring(code.length() - 4), 16) / 100;
//        int tableIndex = Integer.valueOf(code.substring(CODE_LENGTH))/100;
        map.put("tableIndex", tableIndex);
        Map<String, Object> giftCode = null;
        try {
            giftCode = toolDaoTemplate.selectOne("gift.viewCodeList", map);
        } catch (Exception e) {
            logger.error("", e);
            return new Result(false).error(3).data("激活码无效");
        }
        if (giftCode == null) {
            return new Result(false).error(3).data("激活码无效");
        }
        
        logger.info("giftCode数据:{}",giftCode);
        logger.info("status:{}",MapUtils.getBoolean(giftCode, "status", true));
        logger.info("status:{}",MapUtils.getIntValue(giftCode, "status"));
        if(MapUtils.getBoolean(giftCode, "status", true)){
        	return new Result(false).error(11).data("激活码已被使用");
        }
        
        Result result = useCode(giftCode, parameter);
        logger.info("当前接口-使用礼包码,参数：{}", parameter);
        return result;
    }

    private Result useCode(Map<String, Object> giftCode, Map<Object, Object> paramMap) {
        Result result = new Result();

        int status = MapUtils.getIntValue(giftCode, "status");
        if (status == STATUS_USED) {
            return new Result(false).error(4).data("该激活码已被使用");
        }

        int giftCreateId = MapUtils.getIntValue(giftCode, "gift_create_id");

        // 先看主表里有没有被废弃或者过期
        Map<String, Object> giftCreateCode = toolDaoTemplate.selectOne("gift.selectGiftCreateCodeById", String.valueOf(giftCreateId));
        if (giftCreateCode == null) {
            return new Result(false).error(3).data("激活码无效");
        }
        Date now = new Date();
        Date endTime = (Date) MapUtils.getObject(giftCreateCode, "effective_end_time");
        Date startTime = (Date) MapUtils.getObject(giftCreateCode, "effective_time");
        if (endTime != null && endTime.before(now)) {
            return new Result(false).error(5).data("激活码已过期");
        }
        if (startTime.after(now)) {
            return new Result(false).error(6).data("激活码没在有效期内");
        }

        if(!MapUtils.getObject(giftCreateCode, "pid").equals("all")){
        	String platformId = MapUtils.getString(paramMap, "platformId");
	        if (!platformId.equals(MapUtils.getObject(giftCreateCode, "pid"))) {
	            return new Result(false).error(7).data("平台参数错误");
	        }
        }

        String userId = MapUtils.getString(paramMap, "userId");
        int type = MapUtils.getIntValue(giftCreateCode, "type");
        
        if (type == TYPE_UN_REUSE) { // 不能重复使用的 同平台里面只能用一次
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("giftCreateId", giftCreateId);
            map.put("tableIndex", giftCreateId / 100);
            Map<String, Object> usedCode = toolDaoTemplate.selectOne("gift.selectUsedCodeByUserIdAndGiftCreateId", map);
            if (usedCode != null && MapUtils.getIntValue(usedCode, "status") == STATUS_USED) {
                return new Result(false).error(8).data("该类型礼包不能使用多次");
            }
        }else{
        	//能重复使用的
        	Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            map.put("giftCreateId", giftCreateId);
            map.put("status", STATUS_USED);
            map.put("tableIndex", giftCreateId / 100);
            int count = toolDaoTemplate.selectOne("gift.selectCountByUserIdAndGiftCreateId", map);
            if(count>=MapUtils.getIntValue(giftCreateCode, "repeatNum",0)){
            	return new Result(false).error(10).data("该类型礼包不能超过可重复领取次数");
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("userName", MapUtils.getString(paramMap, "userName"));
        map.put("tableIndex", giftCreateId / 100);
        map.put("code", MapUtils.getString(paramMap, "code"));
        int updateResult = toolDaoTemplate.update("gift.updateGiftCodeStatus", map);
        if (updateResult == 1) {
            // 返回物品
//    		Gift gift = toolDaoTemplate.selectOne("gift.selectGiftById", MapUtils.getString(giftCreateCode, "gift_id"));
            Gift gift = null;
            ICache<String, Object> cache = ehcacheCacheManager.getCache(EhcacheCacheManager.CACHE_KEY_GIFT_BASE);
            gift = (Gift) cache.get(MapUtils.getString(giftCreateCode, "gift_id"));
            if (gift == null) {
                gift = toolDaoTemplate.selectOne("gift.selectGiftById", MapUtils.getString(giftCreateCode, "gift_id"));

        		cache.put(MapUtils.getString(giftCreateCode, "gift_id"), gift);
//				gift.setGiftId(MapUtils.getString(giftCreateCode, "gift_id"));
//				gift.setGiftType(MapUtils.getString(giftCreateCode, "type"));

    		}
    		
    		result.setSuccess(true);
    		if (gift == null) {
    			return new Result(false).error(9).data("获取礼包物品错误");
    		}

            result.setData(gift.getAttachments());
        }

        logger.info("参数:{},结果:{}", JSON.toJSONString(paramMap), result);

        return result;
    }

    /**
     * 返回礼包类型，礼包有效期，礼包平台等参数信息
     *
     * @param parameter
     * @return
     */
    public Result check(Map<Object, Object> parameter) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String code = MapUtils.getString(parameter, "code");
        Map<Object, Object> map = new HashMap<>();
        // 最后4位 16进制 转成10进制
        try {
            Integer.parseInt(code.substring(code.length() - 4), 16);
        } catch (Exception e) {
            return new Result(false).error(2).data("激活码格式不对");
        }

        map.put("code", code);
        int tableIndex = Integer.parseInt(code.substring(code.length() - 4), 16) / 100;
        map.put("tableIndex", tableIndex);
        Map<String, Object> giftCode = null;
        try {
            giftCode = toolDaoTemplate.selectOne("gift.viewCodeList", map);
        } catch (Exception e) {
            logger.error("", e);
            return new Result(false).error(3).data("激活码无效");
        }
        if (giftCode == null) {
            return new Result(false).error(3).data("激活码无效");
        }

        Result result = new Result();

        int status = MapUtils.getIntValue(giftCode, "status");
        if (status == STATUS_USED) {
            return new Result(false).error(4).data("该激活码已被使用");
        }

        int giftCreateId = MapUtils.getIntValue(giftCode, "gift_create_id");

        // 先看主表里有没有被废弃或者过期
        Map<String, Object> giftCreateCode = toolDaoTemplate.selectOne("gift.selectGiftCreateCodeById", String.valueOf(giftCreateId));

        LOGGER.info("check-校验code是否废弃或过期,耗时:{}, code:{}", stopWatch.getTime(), code);
        stopWatch.reset();
        stopWatch.start();


        if (giftCreateCode == null) {
            return new Result(false).error(3).data("激活码无效");
        }
        Date now = new Date();
        Date endTime = (Date) MapUtils.getObject(giftCreateCode, "effective_end_time");
        Date startTime = (Date) MapUtils.getObject(giftCreateCode, "effective_time");
        if (endTime != null && endTime.before(now)) {
            return new Result(false).error(5).data("激活码已过期");
        }
        if (startTime.after(now)) {
            return new Result(false).error(6).data("激活码没在有效期内");
        }
        if(!MapUtils.getObject(giftCreateCode, "pid").equals("all")){
        	String platformId = MapUtils.getString(parameter, "platformId");
	        if (!platformId.equals(MapUtils.getObject(giftCreateCode, "pid"))) {
	            return new Result(false).error(7).data("平台参数错误");
	        }
        }

        String userId = MapUtils.getString(parameter, "userId");
        int type = MapUtils.getIntValue(giftCreateCode, "type");
        //无需校验 - 游戏端会校验
//        if (type == TYPE_UN_REUSE) { // 不能重复使用的 同平台里面只能用一次
//            Map<String, Object> m = new HashMap<String, Object>();
//            m.put("userId", userId);
//            m.put("giftCreateId", giftCreateId);
//            m.put("tableIndex", giftCreateId / 100);
//            Map<String, Object> usedCode = toolDaoTemplate.selectOne("gift.selectUsedCodeByUserIdAndGiftCreateId", m);
//
//            LOGGER.info("check-校验code是否被使用,耗时:{}, code:{}", stopWatch.getTime(), code);
//            stopWatch.reset();
//            stopWatch.start();
//
//            if (usedCode != null && MapUtils.getIntValue(usedCode, "status") == STATUS_USED) {
//                return new Result(false).error(8).data("该类型礼包不能使用多次");
//            }
//        }

        JSONObject object = new JSONObject();
        object.put("batchId", MapUtils.getString(giftCreateCode, "id"));
        object.put("giftId", MapUtils.getString(giftCreateCode, "gift_id"));
        object.put("type", MapUtils.getString(giftCreateCode, "type"));

        stopWatch.stop();
        LOGGER.info("check-检验最后剩余耗时:{}", stopWatch.getTime());

        return new Result(true).data(object);
    }


    public Result use(Map<Object, Object> parameter) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("userId", MapUtils.getString(parameter,"userId"));
//        map.put("userName", MapUtils.getString(parameter, "userName"));
//        map.put("code", MapUtils.getString(parameter, "code"));
//
//        map.put("tableIndex", giftCreateId / 100);

        Result result = new Result();

        String code = MapUtils.getString(parameter, "code");

        // 最后4位 16进制 转成10进制
        try {
            Integer.parseInt(code.substring(code.length() - 4), 16);
        } catch (Exception e) {
            return new Result(false).error(2).data("激活码格式不对");
        }

        int tableIndex = Integer.parseInt(code.substring(code.length() - 4), 16) / 100;
//        int tableIndex = Integer.valueOf(code.substring(CODE_LENGTH))/100;
        parameter.put("tableIndex", tableIndex);

//        Map<String, Object> giftCode = null;
//        try {
//            giftCode = toolDaoTemplate.selectOne("gift.viewCodeList", parameter);
//        } catch (Exception e) {
//            logger.error("",e);
//            return new Result(false).error(3).data("激活码无效");
//        }
//        if (giftCode == null) {
//            return new Result(false).error(3).data("激活码无效");
//        }
//
//        int giftCreateId = MapUtils.getIntValue(parameter, "giftCreateId");
//
//        Map<String, Object> giftCreateCode = toolDaoTemplate.selectOne("gift.selectGiftCreateCodeById", String.valueOf(giftCreateId));
//        if (giftCreateCode == null) {
//            return new Result(false).error(3).data("激活码无效");
//        }


        String giftId = MapUtils.getString(parameter, "giftId");
        int updateResult = toolDaoTemplate.update("gift.updateGiftCodeStatus", parameter);

        LOGGER.info("use-更新当前code为已使用,耗时:{}, code:{}", stopWatch.getTime(), code);
        stopWatch.reset();
        stopWatch.start();

        if (updateResult == 1) {
            // 返回物品
//    		Gift gift = toolDaoTemplate.selectOne("gift.selectGiftById", MapUtils.getString(giftCreateCode, "gift_id"));
            Gift gift = null;
            //EhcacheCacheManager ehcacheCacheManager =  SpringContextHolder.getBean("toolDaoTemplate");
            ICache<String, Object> cache = ehcacheCacheManager.getCache(EhcacheCacheManager.CACHE_KEY_GIFT_BASE);
            gift = (Gift) cache.get(giftId);
            if (gift == null) {
                gift = toolDaoTemplate.selectOne("gift.selectGiftById", giftId);
                gift.setGiftId(giftId);
                gift.setGiftType(giftId);
                cache.put(giftId, gift);
            }

            result.setSuccess(true);
            if (gift == null) {
                return new Result(false).error(9).data("获取礼包物品错误");
            }
            result.setData(gift.getAttachments());
        }
        stopWatch.stop();
        LOGGER.info("use-使用方法结束，获取礼包物品结束 耗时:{}", stopWatch.getTime());
        logger.info("参数:{},结果:{}", JSON.toJSONString(parameter), result);

        return result;
    }
}
